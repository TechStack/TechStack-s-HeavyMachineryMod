package com.projectreddog.machinemod.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerTurboFurnace;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTurboFurnace;

import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTurboFurnace extends ContainerScreen {

	private TileEntityTurboFurnace turboFurnace;

	public GuiTurboFurnace(PlayerInventory inventoryPlayer, TileEntityTurboFurnace turboFurnace) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerTurboFurnace(inventoryPlayer, turboFurnace));
		this.turboFurnace = turboFurnace;
	}

	@Override
	public void initGui() {
		this.xSize = 176;
		this.ySize = 222;
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawContainerScreenForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		// fontRenderer.drawString("Tiny", 8, 6, 4210752);
		// //draws "Inventory" or your regional equivalent
		// fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"),
		// 8, ySize - 96 + 2, 4210752);
		// this.fontRenderer.drawString("Fermented Mash: " + turboFurnace.getField(0), 5, 5, 4210752);

		// this.lastFuleBurnTimeRemaining field 0
		// this.lastProcessingTimeRemaining field 1

		String s = this.turboFurnace.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		// this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

	}

	@Override
	protected void drawContainerScreenBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/turbofurnace.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		int xPos = x + 66;
		int yPos = y + 53;

		float scalefactor = ((float) this.turboFurnace.getField(1) / TileEntityTurboFurnace.resetProcessingTime);
		this.mc.renderEngine.bindTexture(getTextureLocationturboFurnaceArrowFill());
		// TODO set the fill amount for the 1st 41 value
		this.drawModalRectWithCustomSizedTexture(xPos, yPos, 0, 0, (int) (41 - (41 * scalefactor)), 16, 41, 16);

		this.mc.renderEngine.bindTexture(getTextureLocationturboFurnaceArrowCutout());
		this.drawModalRectWithCustomSizedTexture(xPos, yPos, 0, 0, 41, 16, 41, 16);

		xPos = x + 47;
		yPos = y + 69;
		scalefactor = ((float) this.turboFurnace.getField(0) / TileEntityTurboFurnace.BLACE_POWDER_FUEL_AMT);

		this.mc.renderEngine.bindTexture(getTextureLocationturboFurnaceFill());
		// TODO set the fill amount for (edit the -8 to a var )
		this.drawModalRectWithCustomSizedTexture(xPos, (int) (yPos - (16 * scalefactor)), 0, 0, 16, (int) (16 * scalefactor), 16, 16);

		this.mc.renderEngine.bindTexture(getTextureLocationturboFurnaceflameCutoutt());
		this.drawModalRectWithCustomSizedTexture(xPos, yPos - 16, 0, 0, 16, 16, 16, 16);

	}

	private ResourceLocation turboFurnaceArrowCutoutRL;

	protected ResourceLocation getTextureLocationturboFurnaceArrowCutout() {
		if (turboFurnaceArrowCutoutRL == null) {
			turboFurnaceArrowCutoutRL = new ResourceLocation("machinemod", Reference.GUI_TURBO_FURNACE_ARROW_CUTOUT);
		}
		return turboFurnaceArrowCutoutRL;
	}

	private ResourceLocation turboFurnacArrowFillRL;

	protected ResourceLocation getTextureLocationturboFurnaceArrowFill() {
		if (turboFurnacArrowFillRL == null) {
			turboFurnacArrowFillRL = new ResourceLocation("machinemod", Reference.GUI_TURBO_FURNACE_ARROW_FILL);
		}
		return turboFurnacArrowFillRL;
	}

	private ResourceLocation turboFurnaceArrowFlameRL;

	protected ResourceLocation getTextureLocationturboFurnaceFill() {
		if (turboFurnaceArrowFlameRL == null) {
			turboFurnaceArrowFlameRL = new ResourceLocation("machinemod", Reference.GUI_TURBO_FURNACE_FLAME);
		}
		return turboFurnaceArrowFlameRL;
	}

	private ResourceLocation turboFurnaceFlameCutoutRL;

	protected ResourceLocation getTextureLocationturboFurnaceflameCutoutt() {
		if (turboFurnaceFlameCutoutRL == null) {
			turboFurnaceFlameCutoutRL = new ResourceLocation("machinemod", Reference.GUI_TURBO_FURNACE_FLAME_CUTOUT);
		}
		return turboFurnaceFlameCutoutRL;
	}

}
