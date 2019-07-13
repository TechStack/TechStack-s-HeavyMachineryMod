package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityLaserMiner;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelLaserMiner extends ModelTransportable {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelLaserMiner() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/laserminer.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.renderGroupObject("Body");
		// this.renderGroupObject("Arm1");
		// this.renderGroupObject("Arm2");
		this.renderGroupObject("ArmRails");
		float RotateAmt = 0;

		this.renderGroupObject("Impeller1");
		this.renderGroupObject("Impeller2");

		if (entity instanceof EntityLaserMiner) {
			EntityLaserMiner laserMiner = (EntityLaserMiner) entity;
			GL11.glTranslatef(0f, laserMiner.Attribute1 / 10f, 0f);

			if (laserMiner.isBeingRidden() && laserMiner.currentFuelLevel > 0) {
				RotateAmt = (entity.world.getGameTime() % 60) * -6;
			}

		}

		this.renderGroupObject("Arm3");

		RotateAmt = RotateAmt + 45f;
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, -1.80647f, 0f);
		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);

		this.renderGroupObject("Laser2");
		GL11.glRotatef(RotateAmt, 0f, 0f, -1f);
		GL11.glTranslatef(.775f, 0f, 0f);
		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);
		this.renderGroupObject("Laser2");
		GL11.glRotatef(RotateAmt, 0f, 0f, -1f);
		GL11.glTranslatef(.775f, 0f, 0f);

		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);
		this.renderGroupObject("Laser2");
		GL11.glRotatef(RotateAmt, 0f, 0f, -1f);
		GL11.glTranslatef(.775f * -4, 0f, 0f);

		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);
		this.renderGroupObject("Laser2");
		GL11.glRotatef(RotateAmt, 0f, 0f, -1f);
		GL11.glTranslatef(.775f, 0f, 0f);

		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);
		this.renderGroupObject("Laser2");
		GL11.glRotatef(RotateAmt, 0f, 0f, -1f);
		GL11.glPopMatrix();
	}

	public void renderGroupObject(String groupName) {
		IBakedModel IB = modelParts.get(groupName);
		if (IB != null) {
			MachineModModelHelper.renderBakedModel(IB);
		} else {
			throw new IllegalArgumentException("The Object: " + groupName + " was not found in :" + modelParts.toString());
		}

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

		return new ResourceLocation("machinemod", Reference.MODEL_BEAM_TEXTURE_LOCATION);
	}

}
