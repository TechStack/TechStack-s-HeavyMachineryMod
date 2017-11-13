package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.ModelOilRig;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;

public class RenderOilRig extends Render {

	float wheelRadius = 10f;
	protected ModelBase modelOilRig;

	public RenderOilRig(RenderManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelOilRig = new ModelOilRig();

	}

	@Override
	public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ) {
		return true; // super.shouldRender(entity, camera, camX, camY, camZ) || (entity.riddenByEntity != null);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		float f2 = pitch;
		float f3 = pitch;

		if (f3 < 0.0F) {
			f3 = 0.0F;
		}

		if (f2 > 0.0F) {
			// GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F *
			// (float)((EntityBulldozer) entity).getForwardDirection(), 1.0F,
			// 0.0F, 0.0F);
		}

		float f4 = 0.75F;
		// GL11.glScalef(f4, f4, f4);
		// GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);
		// GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelOilRig.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// GlStateManager.translate(-1.4f, -0.25F, -.85F);
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		// GL11.glScalef(.5f, .5f, .5f);
		// EntityOilRig eL = ((EntityOilRig) entity);
		//
		GL11.glPopMatrix();

	}

	private void RenderHelper_B(BufferBuilder p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
		p_175033_1_.addVertexData(p_175033_2_.getVertexData());
		p_175033_1_.putColor4(p_175033_3_);
		this.RenderHelper_C(p_175033_1_, p_175033_2_);
	}

	private void RenderHelper_C(BufferBuilder p_175038_1_, BakedQuad p_175038_2_) {
		Vec3i vec3i = p_175038_2_.getFace().getDirectionVec();
		p_175038_1_.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_OIL_RIG_TEXTURE_LOCATION);
	}

}
