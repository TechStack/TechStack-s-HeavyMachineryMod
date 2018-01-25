package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.ModelBeam;
import com.projectreddog.machinemod.model.ModelLaserMiner;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserMiner extends Render {

	protected ModelBase modelLaserMiner;

	protected ModelBase modelBeam;

	public RenderLaserMiner(RenderManager renderManager) {

		super(renderManager);
		shadowSize = 1F;
		this.modelLaserMiner = new ModelLaserMiner();

		this.modelBeam = new ModelBeam();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelLaserMiner.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		this.modelBeam.render(entity, 0, 0, 0, yaw, pitch, .0625f);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("machinemod", Reference.MODEL_LASER_MINER_TEXTURE_LOCATION);
	}

}
