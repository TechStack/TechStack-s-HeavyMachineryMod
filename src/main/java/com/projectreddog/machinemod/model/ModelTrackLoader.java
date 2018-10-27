// Date: 12/24/2014 5:11:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelTrackLoader extends ModelTransportable {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelTrackLoader() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/trackloader.obj"));
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
		// GL11.glScaled(.09d, .09d, .09d);
		// GL11.glRotatef(90, 1, 0, 0);
		GL11.glTranslatef(0f, 0f, .75f);

		this.renderGroupObject("Body");

		// this.renderGroupObject("Bucket_Cube.002");

		GL11.glPushMatrix();

		GL11.glTranslatef(0f, -2.75f, -1.5f);
		if (entity != null) {
			float rotamt = (float) (((EntityMachineModRideable) entity).Attribute1);
			// if (rotamt > 5) {
			// rotamt = 5;
			// }
			GL11.glRotatef(rotamt, 1, 0, 0);

		}
		this.renderGroupObject("FrontArms");

		GL11.glTranslatef(0f, 2.35f, -2.35f);
		this.renderGroupObject("FrontBucket");
		//

		GL11.glPopMatrix();

		GL11.glTranslatef(0f, -.5f, 1.25f);
		if (entity != null) {
			float rotamt = (float) (((EntityMachineModRideable) entity).Attribute1);
			if (rotamt > 5) {
				rotamt = 5;
			}
			GL11.glRotatef(rotamt, 1, 0, 0);

		}
		this.renderGroupObject("BackArmsBottom");

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

		return new ResourceLocation("machinemod", Reference.MODEL_BULLDOZER_TEXTURE_LOCATION);
	}

}
