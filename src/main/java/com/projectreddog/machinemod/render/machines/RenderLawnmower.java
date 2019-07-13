package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.ModelLawnmower;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLawnmower extends EntityRenderer {

	protected EntityModel modelLawnmower;

	public RenderLawnmower(EntityRendererManager renderManager) {

		super(renderManager);
		shadowSize = 1F;
		this.modelLawnmower = new ModelLawnmower();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		boolean flag = false;

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		// GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
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

		float f4 = 0.5F;
		// GL11.glScalef(f4, f4, f4);

		this.bindEntityTexture(entity);

		// Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		// this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);

		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelLawnmower.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");

		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		// return ((ModelLawnmower) modelLawnmower).myOBJModel.getMatLib().getMaterial("Material").getTexture().getTextureLocation();
		return new ResourceLocation("machinemod", Reference.MODEL_LAWNMOWER_TEXTURE_LOCATION);
	}

}
