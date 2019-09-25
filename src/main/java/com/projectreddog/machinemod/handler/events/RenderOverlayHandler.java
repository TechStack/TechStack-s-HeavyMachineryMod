package com.projectreddog.machinemod.handler.events;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.item.armor.ItemMachineModElytraJetLegs;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderOverlayHandler extends Gui {
	private final FontRenderer fontRenderer;
	private Minecraft mc;

	public RenderOverlayHandler() {
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {

		// if (event.isCancelable() || event.type != ElementType.HEALTH) {
		// return;
		// }
		// LogHelper.info(event.type);
		if (Minecraft.getMinecraft().player.isRiding()) {
			if (Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityMachineModRideable) {
				EntityMachineModRideable emr = (EntityMachineModRideable) Minecraft.getMinecraft().player.getRidingEntity();

				int xPos = 2;
				int yPos = 2;

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);

				this.mc.renderEngine.bindTexture(getTextureLocationGage());
				this.drawTexturedModalRect(xPos, yPos, 0, 0, 16, 64);

				this.mc.renderEngine.bindTexture(getTextureLocationMarker());
				int yOffest = (int) (4 + (54 - (((float) emr.currentFuelLevel / emr.maxFuelLevel) * 54)));

				this.drawTexturedModalRect(xPos + 10, yPos + yOffest, 0, 0, 6, 3);
				// this.fontRenderer.drawString("Fuel:" + emr.currentFuelLevel, 0, 0, 14737632);

				if (Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityDrillingRig) {

					EntityDrillingRig edr = (EntityDrillingRig) Minecraft.getMinecraft().player.getRidingEntity();
					int depth = (int) (((edr.Attribute1) - 90) / 5);
					if (depth < 0) {
						depth = 0;
					}

					this.fontRenderer.drawString("Depth: " + (depth), 25, 4, 14737632);
				}

			}
		} else if (Minecraft.getMinecraft().player.isElytraFlying()) {
			if (!Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty()) {
				if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ModItems.elytrajetleg) {
					// has elytra jet legs on ! render fulel gage!
					int xPos = 2;
					int yPos = 2;

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_LIGHTING);

					this.mc.renderEngine.bindTexture(getTextureLocationGage());
					this.drawTexturedModalRect(xPos, yPos, 0, 0, 16, 64);

					this.mc.renderEngine.bindTexture(getTextureLocationMarker());
					int currentFuelLevel = ItemMachineModElytraJetLegs.MaxFuel - Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItemDamage();
					int maxFuelLevel = ItemMachineModElytraJetLegs.MaxFuel;

					int yOffest = (int) (4 + (54 - (((float) currentFuelLevel / maxFuelLevel) * 54)));

					this.drawTexturedModalRect(xPos + 10, yPos + yOffest, 0, 0, 6, 3);
					// this.fontRenderer.drawString("Fuel:" + emr.currentFuelLevel, 0, 0, 14737632);

				}
			}
		}

		// TODO make this work so it will render the frame of where the tower crane can place blocks .. yeah
//		else if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Item.getItemFromBlock(ModBlocks.machinetowercrane))
//
//		{
//			int x = (int) Minecraft.getMinecraft().player.posX;
//			int y = (int) Minecraft.getMinecraft().player.posY;
//			int z = (int) Minecraft.getMinecraft().player.posZ;
//
//			x = 0;
//			y = 60;
//			z = 0;
//			// HOLDING THE towercrane try to render something usefull
//			GL11.glPushMatrix();
//			Tessellator tessellator = Tessellator.getInstance();
//			BufferBuilder bufferBuilder = tessellator.getBuffer();
//			GlStateManager.enableBlend();
//
//			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//			GlStateManager.glLineWidth(8.0F);
//			GlStateManager.color(1f, 0, 0, 0.4F);
//			GlStateManager.depthMask(false);
//
//			// DO rendering here
//			bufferBuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
//
//			bufferBuilder.pos(0d, 60d, 0d).endVertex();
//			bufferBuilder.pos(0d, 61d, 0d).endVertex();
//			bufferBuilder.pos(0d, 60d, 0d).endVertex();
//			bufferBuilder.pos(200d, 60d, 0d).endVertex();
//
//			tessellator.draw();
//			GlStateManager.depthMask(true);
//
//			GlStateManager.disableBlend();
//			GlStateManager.enableTexture2D();
//			GL11.glPopMatrix();
//		}
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
