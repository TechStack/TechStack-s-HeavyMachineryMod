package com.projectreddog.machinemod.model;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableList;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModRenderHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelLoader extends ModelTransportable {
	// fields
	public OBJModel myOBJModel;
	// private IFlexibleBakedModel fullModel;
	private IFlexibleBakedModel modelBody;
	private IFlexibleBakedModel modelArm2;
	private IFlexibleBakedModel modelBucket;

	public ModelLoader() {

		try {
			myOBJModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/loader.obj"));
			// fullModel = myOBJModel.bake(myOBJModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetter);
			modelBody = myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of("LoaderBody_Cube"), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV);
			modelArm2 = myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of("Arm2_Cube.002"), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV);
			modelBucket = myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of("Bucket_Cube.003"), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();

		this.renderGroupObject("LoaderBody_Cube");
		// GL11.glTranslatef(0f, -1.5f, -0.5f);
		GL11.glTranslatef(0f, -2.25f, -1.05f);
		if (entity != null) {

			GL11.glRotatef(((EntityLoader) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject("Arm2_Cube.002");
		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		GL11.glTranslatef(0f, 1.8f, -1.9f);
		if (entity != null) {
			if (((EntityLoader) entity).Attribute1 < -30) {
				GL11.glRotatef((((EntityLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
			}
		}
		this.renderGroupObject("Bucket_Cube.003");

	}

	public void renderGroupObject(String groupName) {
		// myModel.renderPart(groupName);

		if (groupName.equals("LoaderBody_Cube")) {
			MachineModRenderHelper.renderBakedModel(modelBody);

		} else if (groupName.equals("Arm2_Cube.002")) {
			MachineModRenderHelper.renderBakedModel(modelArm2);

		} else if (groupName.equals("Bucket_Cube.003")) {
			MachineModRenderHelper.renderBakedModel(modelBucket);

		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	public ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_LOADER_TEXTURE_LOCATION);
	}
}
