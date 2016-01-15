package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

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
	// private IFlexibleBakedModel modelBody;
	// private IFlexibleBakedModel modelArm2;
	// private IFlexibleBakedModel modelBucket;
	private String nameBody = "LoaderBody_Cube";
	private String nameArm = "Arm2_Cube.002";
	private String nameBucket = "Bucket_Cube.003";

	private HashMap<String, IFlexibleBakedModel> modelParts;

	public ModelLoader() {

		try {
			myOBJModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/loader.obj"));
			// fullModel = myOBJModel.bake(myOBJModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetter);
			// modelBody =

			modelParts.put(nameBody, myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of(nameBody), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));
			modelParts.put(nameArm, myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of(nameArm), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));
			modelParts.put(nameBucket, myOBJModel.bake(new OBJModel.OBJState(ImmutableList.of(nameBucket), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();

		this.renderGroupObject(nameBody);
		// GL11.glTranslatef(0f, -1.5f, -0.5f);
		GL11.glTranslatef(0f, -2.25f, -1.05f);
		if (entity != null) {

			GL11.glRotatef(((EntityLoader) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject(nameArm);
		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		GL11.glTranslatef(0f, 1.8f, -1.9f);
		if (entity != null) {
			if (((EntityLoader) entity).Attribute1 < -30) {
				GL11.glRotatef((((EntityLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
			}
		}
		this.renderGroupObject(nameBucket);

	}

	public void renderGroupObject(String groupName) {
		// myModel.renderPart(groupName);

		MachineModRenderHelper.renderBakedModel(modelParts.get(groupName));

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
