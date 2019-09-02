package com.projectreddog.machinemod.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerLaserLevel;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageBlockBlueprintSaveToServer;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.DeprecatedWrapper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLaserLevel extends GuiContainer {

	GuiTextField fileName;

	public GuiLaserLevel(InventoryPlayer inventoryPlayer) {
		super(new ContainerLaserLevel(inventoryPlayer));

	}

	@Override
	public void initGui() {
		this.xSize = 256;
		this.ySize = 222;

		int targetX = this.width / 2 + 52;
		int targetY = this.height / 2 - 94;

		this.buttonList.add(new GuiButton(Reference.GUI_TOWER_CRANE_BUTTON_SETTINGS, targetX - 20, targetY + 177, 30, 20, DeprecatedWrapper.translateToLocal("gui.laserlevel.savebutton")));

		this.fileName = new GuiTextField(0, this.fontRenderer, targetX - 172, targetY + 12, 250, this.fontRenderer.FONT_HEIGHT);
		// this
		this.fileName.setMaxStringLength(100);
		this.fileName.width = 185;
		this.fileName.setEnableBackgroundDrawing(true);
		this.fileName.setVisible(true);
		this.fileName.setTextColor(16777215);
		this.fileName.setCanLoseFocus(true);
		this.fileName.setFocused(true);
		this.fileName.setText("MyBlueprint");

		super.initGui();

	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException

	{

		if (isvalidTypedkey(typedChar)) {
			fileName.textboxKeyTyped(typedChar, keyCode);
		}
		if (keyCode != 18) {
			super.keyTyped(typedChar, keyCode);
		}
	}

	public boolean isvalidTypedkey(char typedChar) {
		switch (typedChar) {
		case '!':
			return false;
		case '@':
			return false;
		case '#':
			return false;
		case '$':
			return false;
		case '%':
			return false;
		case '^':
			return false;
		case '&':
			return false;
		case '*':
			return false;
		case '(':
			return false;
		case ')':
			return false;
		case '=':
			return false;
		case '+':
			return false;
		case '/':
			return false;
		case '\\':
			return false;

		case '`':
			return false;
		case '\'':
			return false;
		case '"':
			return false;
		case '{':
			return false;
		case '}':
			return false;
		case '[':
			return false;
		case ']':
			return false;
		case ',':
			return false;
		case '.':
			return false;
		case '<':
			return false;
		case '?':
			return false;
		case ';':
			return false;
		case ':':
			return false;
		case '|':
			return false;
		case '>':
			return false;
		case '~':
			return false;
		default:
			return true;
		}
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == Reference.GUI_LASER_LEVEL_BUTTON_SAVE) {
			// Button has been clicked.
			this.mc.displayGuiScreen((GuiScreen) null);
			// Send packet with filename & parms to server from client telling it to "Save" The blueprint at the cords.
			ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageBlockBlueprintSaveToServer(this.fileName.getText(), 0, 0, 0, 1, 1, 1));

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

		fontRenderer.drawString(DeprecatedWrapper.translateToLocal("gui.laserlevel.savetext"), 8, 5, 4210752);
		fontRenderer.drawString("Name:", 8, 19, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/blueprintsave.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		this.fileName.drawTextBox();

	}

}
