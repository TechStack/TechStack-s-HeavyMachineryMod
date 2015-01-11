package com.projectreddog.machinemod.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.container.ContainerDumpTruck;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.reference.Reference;

public class GuiDumpTruck extends GuiContainer {

    public GuiDumpTruck (InventoryPlayer inventoryPlayer,
                    EntityDumpTruck dumpTruck) {
            //the container is instanciated and passed to the superclass for handling
    	
            super(new ContainerDumpTruck(inventoryPlayer, dumpTruck));
    }

    @Override
	public void initGui()
    {
    	this.xSize =176;
    	this.ySize =222;
    	super.initGui();
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
            //draw text and stuff here
            //the parameters for drawString are: string, x, y, color
//            fontRenderer.drawString("Tiny", 8, 6, 4210752);
//            //draws "Inventory" or your regional equivalent
//            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                    int par3) {
            //draw your Gui here, only thing you need to change is the path
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID , "textures/gui/dumptruck.png"));
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
