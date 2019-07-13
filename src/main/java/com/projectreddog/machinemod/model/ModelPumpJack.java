package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityPumpJack;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelPumpJack extends ModelTransportable {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelPumpJack() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/pumpjack.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();

		float primaryRotation = 0;
		float secondaryRotation = 0;
		float tertiaryRotation = 0;
		if (entity != null) {
			primaryRotation = ((EntityPumpJack) entity).Attribute1;

		}

		// primaryRotation = 100;

		if (primaryRotation < 90) {
			secondaryRotation = ((90 - primaryRotation + 90) / 2) - 90;
		}
		// if (primaryRotation > 90 && primaryRotation < 180) {
		// secondaryRotation = ((180 - (90 - (primaryRotation - 90))) - 180);
		// } else {
		// // secondaryRotation = 22.5f - ((primaryRotation - 180) * .25f);
		// }
		//
		// if (primaryRotation <= 90) {
		// tertiaryRotation = (primaryRotation * .75f) * -1;
		// // tertiaryRotation = -40f;
		// }

		// tertiaryRotation = secondaryRotation * -1 * 2;
		// green = 2.5
		// yellow = 1
		// primaryRotation = 0;
		this.renderGroupObject("Stand_Cube");
		GL11.glTranslatef(0f, 2f, -2.86f);
		GL11.glRotatef(primaryRotation * -1, 1, 0, 0);
		this.renderGroupObject("CounterWeight_Cylinder.001");
		GL11.glTranslatef(0f, -1f, 0f);
		GL11.glRotatef(secondaryRotation * -1, 1, 0, 0);
		this.renderGroupObject("VertArm_Cube.002");
		// GL11.glRotatef(secondaryRotation * 1, 1, 0, 0);

		GL11.glTranslatef(0f, 1f, 0f);
		GL11.glRotatef(primaryRotation, 1, 0, 0);
		// GL11.glTranslatef(0f, -1.5f, 3f);

		GL11.glTranslatef(0f, 2.75f, 0f);
		// GL11.glRotatef(secondaryRotation, 1, 0, 0);
		this.renderGroupObject("Arm_Cube.001");

		GL11.glRotatef(tertiaryRotation, 1, 0, 0);

		GL11.glTranslatef(0f, -4f, 4.7f);
		this.renderGroupObject("Pipe_Cylinder");

		if (entity != null) {

			GL11.glRotatef(((EntityPumpJack) entity).Attribute1, 1, 0, 0);
		}
		// this.renderGroupObject("ARM_Cube.003");

		// GL11.glTranslatef(0f, -0.25f, -12.75f);

		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		// GL11.glTranslatef(0f, 1.8f, -1.9f);
		// if (entity != null) {
		// if (((EntityLoader) entity).Attribute1 < -30) {
		// GL11.glRotatef((((EntityLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
		// }
		// }

		// this.renderGroupObject("Wheel_Cylinder");

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

		return new ResourceLocation("machinemod", Reference.MODEL_PUMP_JACK_TEXTURE_LOCATION);
	}
}
