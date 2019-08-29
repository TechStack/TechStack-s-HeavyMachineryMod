package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityUnderGroundDumpTruck;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelUnderGroundDumpTruck extends ModelBase {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelUnderGroundDumpTruck() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/undergroundtruck.obj"));
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
		// this.renderGroupObject("Truck_Base_Cube.002");

		GL11.glTranslatef(0f, 0, 0f);

		this.renderGroupObject("Body");

		GL11.glTranslatef(0f, -1.2f, 4.25f);
		if (entity != null) {
			GL11.glRotatef(((EntityUnderGroundDumpTruck) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject("Bed");
		// this.renderGroupObject("Bed_Cube.000");

	}

	public void renderGroupObject(String groupName) {
		IBakedModel IB = modelParts.get(groupName);
		if (IB != null) {
			MachineModModelHelper.renderBakedModel(IB);
		} else {
			throw new IllegalArgumentException("The Object: " + groupName + " was not found in :" + modelParts.toString());
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

		return new ResourceLocation("machinemod", Reference.MODEL_UNDERGROUND_DUMPTRUCK_TEXTURE_LOCATION);
	}
}
