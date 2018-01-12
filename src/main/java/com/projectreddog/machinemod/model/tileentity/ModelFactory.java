// Date: 12/24/2014 5:11:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.projectreddog.machinemod.model.tileentity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelFactory extends ModelBase {
	// fields
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	private List<AnimationState> anim = new ArrayList<AnimationState>();

	private int currentAnimIndex = 0;
	private int time = 0;

	public ModelFactory() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/factory.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		anim.add(new AnimationState(0, 0, 0));

		anim.add(new AnimationState(0, 0, 0));

		anim.add(new AnimationState(0, 0, 0));

		anim.add(new AnimationState(0, 90, -90));
		anim.add(new AnimationState(0, 90, -90));
		anim.add(new AnimationState(0, 90, -90));

	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));

	}

	public void render(TileEntity entity, float f, float f1, float f2, float partialTicks, float f4, float f5) {
		// super.render(null, f, f1, f2, f3, f4, f5);
		time++;
		Boolean validindex = currentAnimIndex <= anim.size() - 2;// Minus 2 so we can transition from state A to B
		renderGroupObject("Base");
		if (validindex) {
			GL11.glRotatef((anim.get(currentAnimIndex).sholderAngleTarget - anim.get(currentAnimIndex).sholderAngleTarget) / time, 0, 1, 0);
		}
		renderGroupObject("Sholder");

		GL11.glTranslatef(0, 2.425f, -.55f);

		if (validindex) {
			GL11.glRotatef((anim.get(currentAnimIndex).upperArmAngleTarget - anim.get(currentAnimIndex).upperArmAngleTarget) / time, 1, 0, 0);

		}

		renderGroupObject("UpperArm");
		// if (validindex) {
		// GL11.glRotatef(anim.get(currentAnimIndex).upperArmAngleTarget / time * -1, 1, 0, 0);
		//
		// }

		GL11.glTranslatef(0, 0f, -1.26f);
		if (validindex) {
			GL11.glRotatef(anim.get(currentAnimIndex).handAngleTarget / time, 1, 0, 0);

		}
		// GL11.glRotatef(((time / 5) % 60f) * -1, 1, 0, 0);

		// GL11.glRotatef(45f, 1, 0, 0);
		renderGroupObject("Hand");

		if (time > 100) {
			currentAnimIndex++;
			time = 0;
			if (currentAnimIndex > anim.size() - 1) {
				currentAnimIndex = 0;

			}
		}
		// Hand
		// Base
		// UpperArm
		// Sholder

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

		return new ResourceLocation("machinemod", Reference.MODEL_FACTORY_TEXTURE_LOCATION);
	}

	private class AnimationState {
		private float sholderAngleTarget;
		private float upperArmAngleTarget;
		private float handAngleTarget;

		public AnimationState(float sholderAngleTarget, float upperArmAngleTarget, float handAngleTarget) {
			super();
			this.sholderAngleTarget = sholderAngleTarget;
			this.upperArmAngleTarget = upperArmAngleTarget;
			this.handAngleTarget = handAngleTarget;
		}
	}

}
