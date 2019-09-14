package com.projectreddog.machinemod.client.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.container.ContainerTowerCrane;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;
import com.projectreddog.machinemod.utility.DeprecatedWrapper;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class GuiTowerCrane extends GuiContainer {

	private TileEntityTowerCrane towerCrane;
	private InventoryPlayer iplayer;
	float scrollPosY = 0;

	int slotvertcount = 6;

	boolean wasMouseDownLastFrame = false;

	public GuiTowerCrane(InventoryPlayer inventoryPlayer, TileEntityTowerCrane towerCrane) {
		super(new ContainerTowerCrane(inventoryPlayer, towerCrane));
		this.towerCrane = towerCrane;
		this.iplayer = inventoryPlayer;

	}

	@Override
	public void initGui() {
		this.xSize = 256;
		this.ySize = 222;

		int buttonX = this.width / 2 + 42;
		int buttonY = this.height / 2 + 84;
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_SETTINGS, buttonX, buttonY, 52, 20, DeprecatedWrapper.translateToLocal("gui.towercrane.settings")));
		// this

		super.initGui();

	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == Reference.GUI_TOWER_CRANE_BUTTON_SETTINGS) {
			BlockPos bp = towerCrane.getPos();
			int x = bp.getX();
			int y = bp.getY();
			int z = bp.getZ();
			iplayer.player.openGui(MachineMod.instance, Reference.GUI_TOWER_CRANE_SETTINGS, towerCrane.getWorld(), towerCrane.getPos().getX(), towerCrane.getPos().getY(), towerCrane.getPos().getZ());

			// ModNetwork.simpleNetworkWrapper.sendToServer((new MachineModMessageTEGuiButtonClickToServer(x, y, z, button.id)));
		}

	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();

		if (i != 0) {

			int j = (((towerCrane.getSizeInventory() + 9 - 1) / 9 - slotvertcount));

			if (i > 0) {
				i = 1;
			}

			if (i < 0) {
				i = -1;
			}

			this.scrollPosY = (float) ((double) this.scrollPosY - (double) i / (double) j);
			this.scrollPosY = MathHelper.clamp(this.scrollPosY, 0.0F, 1.0F);
		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);

		boolean isMouseDown = Mouse.isButtonDown(0);

		if (isMouseDown && !wasMouseDownLastFrame) {
			// just started a new click.
			wasMouseDownLastFrame = true;
			// Define scroll bar area here and check if Wwe are in it.
			int left = this.guiLeft + 171;
			int top = this.guiTop + 18;
			int bottom = this.guiTop + 18 + 100;
			int right = this.guiLeft + 171 + 7;

			if (mouseX > left && mouseX < right && mouseY > top && mouseY < bottom) {
				// We are in the scroll region
				int j = (((towerCrane.getSizeInventory() + 9 - 1) / 9 - slotvertcount));

				this.scrollPosY = j * (((float) mouseY - top) / ((float) bottom - top));
				LogHelper.info(scrollPosY);
			}
		} else {
			wasMouseDownLastFrame = false;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/towercrane2.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/towercrane3.png"));
		x = ((width - xSize) / 2) + 253;
		y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, 64, ySize);
		// this.mc.renderEngine.bindTexture(getTextureLocationScrollBar());

		// this.drawTexturedModalRect(x + 171, y + 18 + scrollPosY, 0, 0, 100, 180);

		List<ItemStack> neededItems = BlockBlueprintHelper.getMissingBlocks(towerCrane.BlockBluePrintArray, towerCrane);

		x = ((width - xSize) / 2) + 172;
		y = ((height - ySize) / 2) + 40;
		this.fontRenderer.FONT_HEIGHT = 8;

		for (Iterator iterator = neededItems.iterator(); iterator.hasNext();) {
			ItemStack itemStack = (ItemStack) iterator.next();

			this.fontRenderer.drawString(itemStack.getDisplayName() + " X " + itemStack.getCount(), x, y, 4210752);
			y = y + 10;

		}

	}

	private ResourceLocation scrollbar;

	protected ResourceLocation getTextureLocationScrollBar() {
		if (scrollbar == null) {
			scrollbar = new ResourceLocation("machinemod", Reference.GUI_SCROLL_BAR_MARKER_LOCATION);
		}
		return scrollbar;
	}

}
