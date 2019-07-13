package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.model.ModelBulldozer;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBulldozer extends EntityRenderer {

	public static final Factory FACTORY = new Factory();

	protected EntityModel modelBulldozer;

	public RenderBulldozer(EntityRendererManager renderManager) {

		super(renderManager);
		shadowSize = 1F;
		this.modelBulldozer = new ModelBulldozer();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
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
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);

		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.modelBulldozer.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("machinemod", Reference.MODEL_BULLDOZER_TEXTURE_LOCATION);
	}

	public static class Factory implements IRenderFactory<EntityBulldozer> {

		@Override
		public EntityRenderer<? super EntityBulldozer> createRenderFor(EntityRendererManager manager) {
			return new RenderBulldozer(manager);
		}

	}
}
