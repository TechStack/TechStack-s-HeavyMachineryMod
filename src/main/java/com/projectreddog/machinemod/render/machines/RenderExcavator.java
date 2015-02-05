package com.projectreddog.machinemod.render.machines;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.model.ModelExcavator;
import com.projectreddog.machinemod.reference.Reference;

public class RenderExcavator extends Render {

	protected ModelBase modelExcavator;

	private RenderItem itemRenderer;

	public RenderExcavator(RenderManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelExcavator = new ModelExcavator();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);

		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelExcavator.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.translate(-1.4f, -0.25F, -.85F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(.5f, .5f, .5f);
		EntityExcavator eL = ((EntityExcavator) entity);

		boolean even = true;
		int count = 0;

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_EXCAVATOR_TEXTURE_LOCATION);
	}

}
