package com.projectreddog.machinemod.handler.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;

public class RenderOverlayHandler extends Gui {
	private final FontRenderer fontRenderer;
	private Minecraft mc;

	public RenderOverlayHandler() {
		this.fontRenderer = Minecraft.getMinecraft().fontRendererObj;
		this.mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {

		// if (event.isCancelable() || event.type != ElementType.HEALTH) {
		// return;
		// }
		// LogHelper.info(event.type);
		if (Minecraft.getMinecraft().thePlayer.isRiding()) {
			if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityMachineModRideable) {
				EntityMachineModRideable emr = (EntityMachineModRideable) Minecraft.getMinecraft().thePlayer.ridingEntity;

				int xPos = 2;
				int yPos = 2;

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				// GL11.glEnable(GL11.GL_ALPHA);
				this.mc.renderEngine.bindTexture(getTextureLocationGage());
				this.drawTexturedModalRect(xPos, yPos, 0, 0, 16, 64);

				this.mc.renderEngine.bindTexture(getTextureLocationMarker());
				int yOffest = (int) (4 + (54 - (((float) emr.currentFuelLevel / emr.maxFuelLevel) * 54)));

				this.drawTexturedModalRect(xPos + 10, yPos + yOffest, 0, 0, 6, 3);
				// this.fontRenderer.drawString("Fuel:" + emr.currentFuelLevel, 0, 0, 14737632);

				if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityDrillingRig) {

					EntityDrillingRig edr = (EntityDrillingRig) Minecraft.getMinecraft().thePlayer.ridingEntity;
					int depth = (int) (((edr.Attribute1) - 90) / 5);
					if (depth < 0) {
						depth = 0;
					}

					this.fontRenderer.drawString("Depth:" + (depth), 0, 20, 14737632);
				}

			}
		}
	}

	private ResourceLocation guageRL;

	protected ResourceLocation getTextureLocationGage() {
		if (guageRL == null) {
			guageRL = new ResourceLocation("machinemod", Reference.GUI_FUEL_GUAGE_TEXTURE_LOCATION);
		}
		return guageRL;
	}

	private ResourceLocation markerRL;

	protected ResourceLocation getTextureLocationMarker() {
		if (markerRL == null) {
			markerRL = new ResourceLocation("machinemod", Reference.GUI_FUEL_LEVEL_TEXTURE_LOCATION);
		}
		return markerRL;
	}
}
