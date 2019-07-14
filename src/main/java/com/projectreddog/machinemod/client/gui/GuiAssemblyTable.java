package com.projectreddog.machinemod.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerAssemblyTable;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprint;
import com.projectreddog.machinemod.network.MachineModMessageTEGuiButtonClickToServer;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;
import com.projectreddog.machinemod.utility.DeprecatedWrapper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiAssemblyTable extends ContainerScreen {
	private TileEntityAssemblyTable asssemblyTable;
	private int currentPage = 0;
	private int ingredentListSize = 0;
	private final int ASSEMBLE_BUTTON_LIST_INDEX = 0;
	private final int NEXT_BUTTON_LIST_INDEX = 1;
	private final int PREVIOUS_BUTTON_LIST_INDEX = 2;

	public GuiAssemblyTable(PlayerInventory inventoryPlayer, TileEntityAssemblyTable asssemblyTable) {
		// the container is instanciated and passed to the superclass for
		// handling
		super(new ContainerAssemblyTable(inventoryPlayer, asssemblyTable));
		this.asssemblyTable = asssemblyTable;

	}

	@Override
	public void initGui() {
		this.xSize = 176;
		this.ySize = 222;
		super.initGui();

		int buttonX = this.width / 2 - 60;
		int buttonY = this.height / 2 - 96;
		this.buttonList.add(new GuiButton(Reference.GUI_ASSEMBLY_TABLE_BUTTON_ASSEMBLE, buttonX, buttonY, 60, 20, DeprecatedWrapper.translateToLocal("gui.assemblytable.assemble")));
		this.buttonList.get(ASSEMBLE_BUTTON_LIST_INDEX).enabled = !asssemblyTable.hasBuildProject;

		buttonX = this.width / 2 + 35;
		buttonY = this.height / 2 - 50;
		this.buttonList.add(new GuiButton(Reference.GUI_ASSEMBLY_TABLE_BUTTON_NEXT_PAGE, buttonX, buttonY, 30, 20, DeprecatedWrapper.translateToLocal(">>")));
		this.buttonList.get(NEXT_BUTTON_LIST_INDEX).enabled = true;

		buttonX = this.width / 2 - 80;
		buttonY = this.height / 2 - 50;
		this.buttonList.add(new GuiButton(Reference.GUI_ASSEMBLY_TABLE_BUTTON_PREV_PAGE, buttonX, buttonY, 30, 20, DeprecatedWrapper.translateToLocal("<<")));
		this.buttonList.get(PREVIOUS_BUTTON_LIST_INDEX).enabled = currentPage > 0 ? true : false;
		// "â†“â†‘â†’â†�"
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.buttonList.get(ASSEMBLE_BUTTON_LIST_INDEX).enabled = !asssemblyTable.hasBuildProject;

	}

	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);

		if (mouseX > this.width / 2 + 67 && mouseX < this.width / 2 + 80 && mouseY > this.height / 2 - 96 && mouseY < this.height / 2 - 30) {
			List<String> toolTiptext = new ArrayList<String>();
			toolTiptext.add("Work");

			toolTiptext.add("\u00A7b" + asssemblyTable.workConsumedForThisTask + " of " + asssemblyTable.totalWorkNeededForThisTask);

			toolTiptext.add("\u00A7a" + String.format("%.2f", (float) Math.round(((float) asssemblyTable.workConsumedForThisTask / asssemblyTable.totalWorkNeededForThisTask) * 10000) / 100) + "% Complete");

			this.drawHoveringText(toolTiptext, mouseX, mouseY, (fontRenderer));
		}
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == Reference.GUI_ASSEMBLY_TABLE_BUTTON_ASSEMBLE) {
			BlockPos bp = asssemblyTable.getPos();
			int x = bp.getX();
			int y = bp.getY();
			int z = bp.getZ();
			ModNetwork.simpleNetworkWrapper.sendToServer((new MachineModMessageTEGuiButtonClickToServer(x, y, z, button.id)));
		} else if (button.id == Reference.GUI_ASSEMBLY_TABLE_BUTTON_NEXT_PAGE) {
			// hit next!
			currentPage++;
			setPageButtonStates();

		} else if (button.id == Reference.GUI_ASSEMBLY_TABLE_BUTTON_PREV_PAGE) {
			// hit next!
			currentPage--;

			setPageButtonStates();

		}

	}

	private void setPageButtonStates() {
		if (currentPage == 0) {
			this.buttonList.get(PREVIOUS_BUTTON_LIST_INDEX).enabled = false;
		} else {
			this.buttonList.get(PREVIOUS_BUTTON_LIST_INDEX).enabled = true;
		}
		if (((float) ingredentListSize / 5) > currentPage + 1) {
			this.buttonList.get(NEXT_BUTTON_LIST_INDEX).enabled = true;
		} else {
			this.buttonList.get(NEXT_BUTTON_LIST_INDEX).enabled = false;
		}

		// currentPage;
	}

	@Override
	protected void drawContainerScreenForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		// fontRenderer.drawString("Tiny", 8, 6, 4210752);
		// //draws "Inventory" or your regional equivalent
		// fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"),
		// 8, ySize - 96 + 2, 4210752);

		int xPos = 147;
		int yPos = 79;

		this.mc.renderEngine.bindTexture(getTextureLocationWorkProgress());
		int yOffest = (int) (((float) asssemblyTable.workConsumedForThisTask / asssemblyTable.totalWorkNeededForThisTask) * 64f);

		this.drawTexturedModalRect(xPos + 10, yPos - yOffest, 0, 0, 11, yOffest);
		// this.fontRenderer.drawString("Stored Work: " + asssemblyTable.workConsumedForThisTask + " of " + asssemblyTable.totalWorkNeededForThisTask, 5, 5, 4210752);

		this.fontRenderer.drawString(DeprecatedWrapper.translateToLocal("gui.assemblytable.name"), 9, 5, 4210752);

		ItemStack is = this.asssemblyTable.getStackInSlot(0);
		if (!is.isEmpty()) {
			if (is.getItem() instanceof ItemBlueprint) {
				ItemBlueprint ibp = (ItemBlueprint) is.getItem();
				this.fontRenderer.drawString("" + asssemblyTable.getFriendlyOutputname(), 9, 40, 4210752);
				int y = 85;

				List<String> ingredentStringList = asssemblyTable.getFriendlyIngredentList();
				ingredentListSize = ingredentStringList.size();
				int i = 0;
				for (String s : ingredentStringList) {

					if (i >= currentPage * 5 && i < ((currentPage + 1) * 5)) {
						this.fontRenderer.drawString(s, 9, y, 4210752);
						y = y + 10;
					}

					i++;

				}

			}
		}
		setPageButtonStates();
	}

	@Override
	protected void drawContainerScreenBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/assemblytable.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	private ResourceLocation workProgress;

	protected ResourceLocation getTextureLocationWorkProgress() {
		if (workProgress == null) {
			workProgress = new ResourceLocation("machinemod", Reference.GUI_WORK_PROGRESS_TEXTURE_LOCATION);
		}
		return workProgress;
	}

}
