package com.projectreddog.machinemod.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.ModelBulldozer;
import com.projectreddog.machinemod.reference.Reference;

public class RenderBulldozer extends Render {


	protected ModelBase  modelBulldozer ;


	public RenderBulldozer(RenderManager renderManager)
	{
		
		super(renderManager);
		shadowSize = 1F;
        this.modelBulldozer = new ModelBulldozer();

	}



	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
	{


		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		float f2 =  pitch;
		float f3 = pitch;

		if (f3 < 0.0F)
		{
			f3 = 0.0F;
		}

		if (f2 > 0.0F)
		{
//			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)((EntityBulldozer) entity).getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelBulldozer.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		
		
		return   new ResourceLocation(   "machinemod",Reference.MODEL_BULLDOZER_TEXTURE_LOCATION); 
	}

}

