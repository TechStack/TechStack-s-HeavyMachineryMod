package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityUnderGroundLoader;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelUnderGroundLoader extends ModelTransportable {
	// fields
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;
	private String groupNameBody = "Body";
	private String groupNameArm = "Arm";

	private String groupNameBucket = "Bucket";

	public ModelUnderGroundLoader() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/undergroundloader.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// this.renderGroupObject(groupNameBody);

		GL11.glTranslatef(0f, 0f, -.45f);
		this.renderGroupObject(groupNameBody);

		GL11.glTranslatef(0f, -2.6f, -1.6f);
		if (entity != null) {
			GL11.glRotatef(((EntityUnderGroundLoader) entity).Attribute1, 1, 0, 0);
		}
		// this.renderGroupObject(groupNameArm);

		this.renderGroupObject(groupNameArm);

		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		GL11.glTranslatef(0f, 1.22f, -2.17f);
		if (entity != null) {
			if (((EntityUnderGroundLoader) entity).Attribute1 < -30) {
				GL11.glRotatef((((EntityUnderGroundLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
			}
		}
		// this.renderGroupObject(groupNameBucket);
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
		return new ResourceLocation("machinemod", Reference.MODEL_UNDER_GROUND_LOADER_TEXTURE_LOCATION);
	}
}
