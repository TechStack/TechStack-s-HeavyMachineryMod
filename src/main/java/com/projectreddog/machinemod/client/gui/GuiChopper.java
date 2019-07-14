package com.projectreddog.machinemod.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerChopper;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.gui.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;

public class GuiChopper extends ContainerScreen {

	public GuiChopper(PlayerInventory inventoryPlayer, EntityChopper chopper) {
		// the container is instanciated and passed to the superclass for
		// handling

		super(new ContainerChopper(inventoryPlayer, chopper));
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
	}

	@Override
	protected void drawContainerScreenBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/loader.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
