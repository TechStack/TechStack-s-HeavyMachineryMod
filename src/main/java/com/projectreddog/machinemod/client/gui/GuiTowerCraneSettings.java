package com.projectreddog.machinemod.client.gui;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerTowerCraneSettings;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiTowerCraneSettings extends GuiContainer {

	private TileEntityTowerCrane towerCrane;

	public GuiTowerCraneSettings(InventoryPlayer inventoryPlayer, TileEntityTowerCrane towerCrane) {

		super(new ContainerTowerCraneSettings(inventoryPlayer, towerCrane));
		this.towerCrane = towerCrane;
	}

	@Override
	public void initGui() {
		this.xSize = 256;
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
