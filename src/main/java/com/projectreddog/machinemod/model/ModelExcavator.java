package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelExcavator extends ModelTransportable {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelExcavator() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/excavator.obj"));
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
		// myModel.renderAll();
		// DEBUG CODE !

		// render tracks before anything
		GL11.glRotatef(entity.rotationYaw * -1, 0.0F, 1.0F, 0.0F);

		this.renderGroupObject("Object.1");
		// rotate rest of machine to correct orentation (first)

		if (entity != null) {
			// (180.0F - ((EntityMachineModRideable) entity).yaw + )
			float calcRot = (float) (((EntityExcavator) entity).mainBodyRotation - 180 - ((EntityMachineModRideable) entity).yaw);
			// if (calcRot > 360) {
			// calcRot = 360 - calcRot;
			// } else if (calcRot < 0) {
			// calcRot = 360 + calcRot;
			// }
			// GL11.glTranslatef(0, 0, 1);
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			// GL11.glRotatef((float) ((EntityExcavator) entity).mainBodyRotation, 0, 1, 0f);
			if (((EntityExcavator) entity).getControllingPassenger() != null && ((EntityExcavator) entity).getControllingPassenger() == Minecraft.getMinecraft().player) {
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4d(1, 1, 1, .50d);

			}
		}

		this.renderGroupObject("Object.6");
		this.renderGroupObject("Object.5");
		this.renderGroupObject("Object.7");
		this.renderGroupObject("Object.3");
		this.renderGroupObject("Object.4");
		this.renderGroupObject("Object.2");
		this.renderGroupObject("Object.8");
		// GL11.glTranslatef(-.5f, EntityExcavator.armPiviotUp, EntityExcavator.armPiviotForward);
		if (entity != null) {

			GL11.glRotatef((float) ((((EntityExcavator) entity).angleArm1 * -1)), 1, 0, 0);

			// GL11.glRotatef((float) , 1, 0, 0);
			// change to rotate
			// GL11.glTranslatef(0f, , 0f);

		}
		// GL11.glTranslatef(0, 0, -.5f);

		GL11.glTranslatef(0, 0, 0);

		this.renderGroupObject("Object.9");
		if (entity != null) {
			GL11.glTranslatef(0f, 6.6f, 7.75f);
			GL11.glRotatef((float) ((((EntityExcavator) entity).angleArm2 * -1)), 1, 0, 0);
		}
		this.renderGroupObject("Object.10");
		if (entity != null) {
			GL11.glTranslatef(0f, -4.85f, .55f);
			GL11.glRotatef((float) ((EntityExcavator) entity).angleArm3 * -1, 1, 0, 0f);
		}
		this.renderGroupObject("Object.11");

		// this.renderGroupObject("Wrecking_ball_Sphere");

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

		return new ResourceLocation("machinemod", Reference.MODEL_EXCAVATOR_TEXTURE_LOCATION);
	}

}
