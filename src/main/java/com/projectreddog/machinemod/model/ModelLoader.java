package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelLoader extends ModelTransportable {
	// fields
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;
	private String groupNameBody = "Body";
	private String groupNameArm = "Arm";

	private String groupNameBucket = "Bucket";

	public ModelLoader() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/loader.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// this.renderGroupObject(groupNameBody);

		GL11.glTranslatef(0f, 0f, .80f);
		this.renderGroupObject(groupNameBody);

		GL11.glTranslatef(0f, -2.8f, -1.7f);
		if (entity != null) {
			GL11.glRotatef(((EntityLoader) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject(groupNameArm);
		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		GL11.glTranslatef(0f, 1.8f, -2.85f);
		if (entity != null) {
			if (((EntityLoader) entity).Attribute1 < -30) {
				GL11.glRotatef((((EntityLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
			}
		}
		this.renderGroupObject(groupNameBucket);
	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));
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
