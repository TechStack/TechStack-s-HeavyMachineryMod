package com.projectreddog.machinemod.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.container.ContainerTowerCraneSettings;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;
import com.projectreddog.machinemod.utility.DeprecatedWrapper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiTowerCraneSettings extends GuiContainer {

	private TileEntityTowerCrane towerCrane;
	private InventoryPlayer iplayer;

	public GuiTowerCraneSettings(InventoryPlayer inventoryPlayer, TileEntityTowerCrane towerCrane) {

		super(new ContainerTowerCraneSettings(inventoryPlayer, towerCrane));
		this.towerCrane = towerCrane;
		this.iplayer = inventoryPlayer;

	}

	@Override
	public void initGui() {
		this.xSize = 256;
		this.ySize = 222;
		super.initGui();

		int buttonX = this.width / 2 + 30;
		int buttonY = this.height / 2 - 106;
		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_INVENTORY, buttonX, buttonY, 15, 20, DeprecatedWrapper.translateToLocal("gui.towercrane.inventory")));

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
