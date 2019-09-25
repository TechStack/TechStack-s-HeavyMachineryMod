package com.projectreddog.machinemod.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.container.ContainerTowerCraneSettings;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageBlockBlueprintSelectionToServer;
import com.projectreddog.machinemod.network.MachineModMessageRequestFileListToServer;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;
import com.projectreddog.machinemod.utility.DeprecatedWrapper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiTowerCraneSettings extends GuiContainer {

	private TileEntityTowerCrane towerCrane;
	private InventoryPlayer iplayer;

	private int ScrollOffset;

	public GuiTowerCraneSettings(InventoryPlayer inventoryPlayer, TileEntityTowerCrane towerCrane) {

		super(new ContainerTowerCraneSettings(inventoryPlayer, towerCrane));
		this.towerCrane = towerCrane;
		this.iplayer = inventoryPlayer;

		ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageRequestFileListToServer());

	}

	@Override
	public void initGui() {
		this.xSize = 256;
		this.ySize = 222;
		super.initGui();

		int buttonX = this.width / 2 + 30;
		int buttonY = this.height / 2 - 107;
		int xoffset = -152;
		int btnWidht = 148;
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP1, buttonX + xoffset, buttonY + 22, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP2, buttonX + xoffset, buttonY + 44, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP3, buttonX + xoffset, buttonY + 66, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP4, buttonX + xoffset, buttonY + 88, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP5, buttonX + xoffset, buttonY + 110, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP6, buttonX + xoffset, buttonY + 132, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP7, buttonX + xoffset, buttonY + 154, btnWidht, 20, ""));
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_BP8, buttonX + xoffset, buttonY + 176, btnWidht, 20, ""));

		for (int i = 0; i < 8; i++) {
			this.buttonList.get(i).enabled = false;
		}
		buttonY = this.height / 2 - 106;

		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_INVENTORY, buttonX - 124, buttonY, 100, 20, DeprecatedWrapper.translateToLocal("gui.towercrane.inventory")));

		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_SCROLL_UP, buttonX - 2, buttonY, 17, 20, DeprecatedWrapper.translateToLocal("/\\")));

		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_SCROLL_DOWN, buttonX - 2, buttonY + 190, 17, 20, DeprecatedWrapper.translateToLocal("\\/")));

		this.buttonList.get(9).enabled = false;
		this.buttonList.get(10).enabled = false;
		super.initGui();

	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == Reference.GUI_TOWER_CRANE_BUTTON_INVENTORY) {
			BlockPos bp = towerCrane.getPos();
			int x = bp.getX();
			int y = bp.getY();
			int z = bp.getZ();
			iplayer.player.openGui(MachineMod.instance, Reference.GUI_TOWER_CRANE, towerCrane.getWorld(), towerCrane.getPos().getX(), towerCrane.getPos().getY(), towerCrane.getPos().getZ());

			// ModNetwork.simpleNetworkWrapper.sendToServer((new MachineModMessageTEGuiButtonClickToServer(x, y, z, button.id)));
		} else if (button.id == Reference.GUI_TOWER_CRANE_SCROLL_DOWN) {
			ScrollOffset = ScrollOffset + 8;
		} else if (button.id == Reference.GUI_TOWER_CRANE_SCROLL_UP) {
			ScrollOffset = ScrollOffset - 8;
		} else if (button.id < Reference.GUI_TOWER_CRANE_BUTTON_INVENTORY) {
			int selected = button.id + ScrollOffset - 1;
			String fileName = BlockBlueprintHelper.clientCacheBlueprintsFileName[selected];
			ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageBlockBlueprintSelectionToServer(fileName, towerCrane.getPos().getX(), towerCrane.getPos().getY(), towerCrane.getPos().getZ()));

		}

	}

	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);

		for (int i = 0; i < 8; i++) {

			if (this.buttonList.get(i).isMouseOver() && this.buttonList.get(i).enabled) {
				List<String> toolTiptext = new ArrayList<String>();
				toolTiptext.add("\u00A7fBlueprint: \u00A79" + BlockBlueprintHelper.clientCacheBlueprintsDisplayName[i + ScrollOffset]);
				toolTiptext.add("\u00A7fOwner: \u00A7d" + BlockBlueprintHelper.clientCacheBlueprintsOwner[i + ScrollOffset]);
				toolTiptext.add("\u00A7fFileName: \u00A78" + BlockBlueprintHelper.clientCacheBlueprintsFileName[i + ScrollOffset]);
				this.drawHoveringText(toolTiptext, mouseX, mouseY, (fontRenderer));
			}
		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {

		if (BlockBlueprintHelper.clientCacheBlueprintsDisplayName != null) {

			if (BlockBlueprintHelper.clientCacheBlueprintsDisplayName.length > 8) {

				if (ScrollOffset + 8 < BlockBlueprintHelper.clientCacheBlueprintsDisplayName.length) {

					// enable down
					this.buttonList.get(10).enabled = true;
				} else {

					// disable down as we are at the bottom
					this.buttonList.get(10).enabled = false;
				}

				if (ScrollOffset > 0) {
					// enable up
					this.buttonList.get(9).enabled = true;
				} else {
					// at top
					this.buttonList.get(9).enabled = false;
				}
			} else {
				// disable up & down
				this.buttonList.get(9).enabled = false;
				this.buttonList.get(10).enabled = false;
			}

			for (int i = 0; i < BlockBlueprintHelper.clientCacheBlueprintsDisplayName.length && i < 8; i++) {
				// LogHelper.info(BlockBlueprintHelper.clientCacheBlueprints[i]);
				// this.fontRenderer.FONT_HEIGHT = 1;
				// this.fontRenderer.drawString(BlockBlueprintHelper.clientCacheBlueprints[i], 5, (7 * i) + 5, 4210752);

				// TODO add check to see if "I"is > the 8 buttons will need to use some type of scrolling index for that
				if (i + ScrollOffset >= BlockBlueprintHelper.clientCacheBlueprintsDisplayName.length) {
					this.buttonList.get(i).displayString = "";
					this.buttonList.get(i).enabled = false;

				} else {
					this.buttonList.get(i).displayString = BlockBlueprintHelper.clientCacheBlueprintsDisplayName[i + ScrollOffset];
					this.buttonList.get(i).enabled = true;
				}

			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/towercranesettings.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
