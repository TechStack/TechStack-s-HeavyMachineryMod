package com.projectreddog.machinemod.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerExpCollector;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageTEGuiButtonClickToServer;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityExpCollector;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiExpCollector extends GuiContainer {

	private TileEntityExpCollector expCollector;
	private InventoryPlayer iplayer;
	private int ScrollOffset;

	public GuiExpCollector(InventoryPlayer inventoryPlayer, TileEntityExpCollector expCollector) {
		super(new ContainerExpCollector(inventoryPlayer, expCollector));
		this.expCollector = expCollector;
		this.iplayer = inventoryPlayer;
	}

	@Override
	public void initGui() {
		this.xSize = 176;
		this.ySize = 222;
		super.initGui();
		int buttonX = this.width / 2 + 0;
		int buttonY = this.height / 2 - 107;
		int btnWidht = 50;
		int xoffset = -btnWidht / 2;

		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_1, buttonX + xoffset, buttonY + 22, btnWidht, 20, "1"));
		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_10, buttonX + xoffset, buttonY + 44, btnWidht, 20, "10"));
		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_100, buttonX + xoffset, buttonY + 66, btnWidht, 20, "100"));
		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_1000, buttonX + xoffset, buttonY + 88, btnWidht, 20, "1k"));
		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_10000, buttonX + xoffset, buttonY + 110, btnWidht, 20, "10k"));
		this.buttonList.add(new GuiButton(Reference.GUI_EXP_COLLECTOR_BUTTON_NEXT_LEVEL, buttonX + xoffset - (10), buttonY + 160, btnWidht + 20, 20, "Next Level"));

		for (int i = 0; i < 5; i++) {

			this.buttonList.get(i).enabled = false;
		}

		this.buttonList.get(5).enabled = true;

		super.initGui();

	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		ModNetwork.simpleNetworkWrapper.sendToServer((new MachineModMessageTEGuiButtonClickToServer(expCollector.getPos().getX(), expCollector.getPos().getY(), expCollector.getPos().getZ(), button.id)));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		boolean state = false;
		for (int i = 0; i < this.buttonList.size(); i++) {
			state = false;
			if (i == 0 && expCollector.getField(0) >= 1) {
				state = true;
			} else if (i == 1 && expCollector.getField(0) >= 10) {
				state = true;
			} else if (i == 2 && expCollector.getField(0) >= 100) {
				state = true;
			} else if (i == 3 && expCollector.getField(0) >= 1000) {
				state = true;
			} else if (i == 4 && expCollector.getField(0) >= 10000) {
				state = true;
			}

			this.buttonList.get(i).enabled = state;
		}
		if (expCollector.getField(0) > 0) {
			this.buttonList.get(5).enabled = true;
		}

		this.fontRenderer.drawString("Exp Stored: " + expCollector.getField(0), 5, 5, 4210752);

		this.fontRenderer.drawString(" Your Current Level : " + iplayer.player.experienceLevel, 5, 150, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/expcollector.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
