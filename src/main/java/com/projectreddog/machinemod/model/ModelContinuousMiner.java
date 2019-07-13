package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityContinuousMiner;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelContinuousMiner extends ModelTransportable {
	// fields
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;
	private String groupNameBody = "Object.1";
	private String groupNameArm = "Object.2";

	private String groupNameBucket = "Object.3";

	public ModelContinuousMiner() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/continuousminer.obj"));
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
		boolean isOn = false;
		boolean isEntityPassed = (entity != null);
		// GL11.glTranslatef(0f, 0f, .80f);
		float amt = 0;

		if (entity instanceof EntityContinuousMiner) {
			amt = ((EntityContinuousMiner) entity).Attribute1 + 15;

			if (((EntityContinuousMiner) entity).isBeingRidden()) {
				isOn = true;
			}
		}
		this.renderGroupObject("Body");

		// NEED TRANSLATE & ROTATE (2x rotate)

		GL11.glTranslatef(-.75f, -.65f, -4.5f);
		GL11.glRotatef(33, 1, 0, 0);
		if (isEntityPassed && isOn) {
			GL11.glRotatef(entity.ticksExisted * -10, 0, 1, 0);
		}

		this.renderGroupObject("Impeller1");
		if (isEntityPassed && isOn) {
			GL11.glRotatef(entity.ticksExisted * 10, 0, 1, 0);
		}

		GL11.glTranslatef(1.5f, 0, 0);

		if (isEntityPassed && isOn) {
			GL11.glRotatef(entity.ticksExisted * 10, 0, 1, 0);
		}
		this.renderGroupObject("Impeller2");

		if (isEntityPassed && isOn) {
			GL11.glRotatef(entity.ticksExisted * -10, 0, 1, 0);
		}
		GL11.glTranslatef(-.75f, 0, 0);

		GL11.glRotatef(-33, 1, 0, 0);
		GL11.glTranslatef(0f, .65f, 4f);

		GL11.glTranslatef(0, -1.8f, -2.6f);
		if (isOn) {
			GL11.glRotatef(amt, 1, 0, 0);
		}

		this.renderGroupObject("Arm");
		GL11.glTranslatef(0, -1.4f, -4.75f);

		if (isOn) {
			GL11.glRotatef(entity.ticksExisted * 50, 1, 0, 0);
			this.renderGroupObject("CutterHeadOutter");
			GL11.glRotatef(entity.ticksExisted * -100, 1, 0, 0);
			this.renderGroupObject("CutterHeadInner");
		} else {

			this.renderGroupObject("CutterHeadOutter");
			this.renderGroupObject("CutterHeadInner");
		}

	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));
	}

	private void setRotation(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
	}

	public ResourceLocation getTexture() {
		return new ResourceLocation("machinemod", Reference.MODEL_CONTINUOUSMINER_TEXTURE_LOCATION);
	}
}
