package com.projectreddog.machinemod.handler.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

public class RenderOverlayHandler extends Gui {
	private final FontRenderer fontRenderer;

	public RenderOverlayHandler() {
		this.fontRenderer = Minecraft.getMinecraft().fontRendererObj;

	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderGameOverlay(RenderGameOverlayEvent event) {
		if (Minecraft.getMinecraft().thePlayer.isRiding()) {
			if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityMachineModRideable) {
				EntityMachineModRideable emr = (EntityMachineModRideable) Minecraft.getMinecraft().thePlayer.ridingEntity;
				this.fontRenderer.drawString("Fuel:" + emr.currentFuelLevel, 0, 0, 14737632);
				// emr.currentFuelLevel;
			}
		}
	}
}
