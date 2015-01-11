package com.projectreddog.machinemod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.model.ModelLoader;
import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

public class RenderLoader extends Render {


	protected ModelBase  modelLoader ;

private RenderItem itemRenderer;

	public RenderLoader(RenderManager renderManager)
	{

		super(renderManager);

		//LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelLoader = new ModelLoader();
		 itemRenderer = Minecraft.getMinecraft().getRenderItem();
		 
	}



	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
	{


		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
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
		this.modelLoader.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		((ModelLoader) this.modelLoader).renderGroupObject("LoaderBody_Cube");
		  GL11.glTranslatef(0f, -1.5f, -0.5f);
		  GL11.glRotatef(((EntityLoader) entity).Attribute1, 1,0, 0);
		((ModelLoader) this.modelLoader).renderGroupObject("Arm2_Cube.002");
		  GL11.glTranslatef(0f, 1.2f, -1.2f);
			if (((EntityLoader) entity).Attribute1 < -30){
				  GL11.glRotatef((((EntityLoader) entity).Attribute1 +30) *-2f, 1,0, 0);
				}
		((ModelLoader) this.modelLoader).renderGroupObject("Bucket_Cube.003");
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return   new ResourceLocation(   "machinemod",Reference.MODEL_LOADER_TEXTURE_LOCATION); 
	}

}
