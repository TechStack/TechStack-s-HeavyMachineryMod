package com.projectreddog.machinemod.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerScreen;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityScreen;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiScreen extends GuiContainer {

	private TileEntityScreen sreen;

	public GuiScreen(InventoryPlayer inventoryPlayer, TileEntityScreen screen) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerScreen(inventoryPlayer, screen));
		this.sreen = screen;
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
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		// fontRenderer.drawString("Tiny", 8, 6, 4210752);
		// //draws "Inventory" or your regional equivalent
		// fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"),
		// 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/screengui.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
