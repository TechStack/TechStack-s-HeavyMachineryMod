package com.projectreddog.machinemod.render.mob;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.monster.EntityExpStalker;
import com.projectreddog.machinemod.model.mob.ModelExpStalker;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.ResourceLocation;

public class RenderExpStalker extends LivingRenderer<EntityExpStalker> {

	protected static Model myModel;

	private ItemRenderer itemRenderer;

	public RenderExpStalker(RenderManager renderManager) {

		super(renderManager, myModel, 1f);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.myModel = new ModelExpStalker();
		itemRenderer = Minecraft.getInstance().getRenderItem();

	}

	@Override
	public boolean shouldRender(EntityExpStalker entity, ICamera camera, double camX, double camY, double camZ) {
		return true; // super.shouldRender(entity, camera, camX, camY, camZ) || (entity.riddenByEntity != null);
	}

	@Override
	public void doRender(EntityExpStalker entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);

		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.myModel.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityExpStalker entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_BAGGER_TEXTURE_LOCATION);

	}

}
