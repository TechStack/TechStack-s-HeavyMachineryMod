package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.ModelSub;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSub extends EntityRenderer {

	protected EntityModel modelSub;

	public RenderSub(EntityRendererManager renderManager) {

		super(renderManager);
		shadowSize = 1F;
		this.modelSub = new ModelSub();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelSub.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("machinemod", Reference.MODEL_SUB_TEXTURE_LOCATION);
	}

}
