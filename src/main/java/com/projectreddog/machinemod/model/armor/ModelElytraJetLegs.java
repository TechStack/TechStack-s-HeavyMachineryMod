package com.projectreddog.machinemod.model.armor;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelElytraJetLegs extends ModelBiped {
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;
	public boolean isElytraFlying = false;

	public ModelElytraJetLegs() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/elytrajetleg.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		// super.render(entity, f, f1, f2, f3, f4, f5);
		GL11.glPushMatrix();

		if (isSneak) {
			if (isElytraFlying) {

				GL11.glRotatef(0f, 1, 0, 0);
				GL11.glTranslatef(0f, 1, .5f);
			} else {
				GL11.glRotatef(30f, 1, 0, 0);
				GL11.glTranslatef(0f, 1, .125f);
			}

		} else {

			GL11.glTranslatef(0f, 1, .25f);
		}
		this.renderGroupObject("Cube_Cube.001");

		if (isSneak && isElytraFlying) {
			if (f2 % 6 < 3) {
				this.renderGroupObject("Flame2_Cube.002");
			} else {
				this.renderGroupObject("Flame1_Cube");
			}
		}
		if (isSneak) {
			GL11.glTranslatef(0f, 0, 0.01f);
		}
		GL11.glTranslatef(0f, 0, .03f);

		this.renderGroupObject("Belt_Cube.003");
		GL11.glPopMatrix();
	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));

	}
}
