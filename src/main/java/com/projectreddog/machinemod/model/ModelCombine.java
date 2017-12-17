package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelCombine extends ModelBase {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelCombine() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/combine.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// renderGroupObject(MachineModModelHelper.ALL_PARTS);

		renderGroupObject("FrontWheel.R_Cylinder.003");
		renderGroupObject("FrontWheel.L_Cylinder.002");
		renderGroupObject("RearWheel.R_Cylinder.001");
		renderGroupObject("RearWheel.L_Cylinder");
		renderGroupObject("Body_Object.7");

		renderGroupObject("Boom_Object.1");

		renderGroupObject("CornHead_Object.28");
		GL11.glTranslatef(0f, -1.02f, -4.3f);

		if (entity instanceof EntityCombine) {
			EntityCombine ec = (EntityCombine) entity;
			GL11.glRotatef(ec.Attribute2, 1, 0, 0);
		}

		renderGroupObject("CornAuger_Plane.001");

		// renderGroupObject("Cylinder");

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

		return new ResourceLocation("machinemod", Reference.MODEL_COMBINE_TEXTURE_LOCATION);
	}

}
