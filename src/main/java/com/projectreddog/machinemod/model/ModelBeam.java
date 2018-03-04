package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelBeam extends ModelBase {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelBeam() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/beam.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		GL11.glPushMatrix();

		float RotateAmt = (entity.world.getTotalWorldTime() % 60) * 6;
		GL11.glRotatef(180f, 0, 180f, 0);
		// GL11.glTranslatef(-1.15f, -1.125f, 3.25f);

		GL11.glScalef(.3f, .3f, 1000f);
		GlStateManager.disableLighting();
		// GlStateManager.disableCull();
		GlStateManager.enableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

		this.renderGroupObject("Beam_Cylinder");
		GL11.glRotatef(RotateAmt, 0f, 0f, 1f);
		GL11.glTranslatef(0f, 1f, 0);
		this.renderGroupObject("Beam_Cylinder");
		GL11.glTranslatef(1f, -1f, 0);
		this.renderGroupObject("Beam_Cylinder");
		GL11.glTranslatef(-1f, -1f, 0);
		this.renderGroupObject("Beam_Cylinder");
		GL11.glTranslatef(-1f, 1f, 0);
		this.renderGroupObject("Beam_Cylinder");

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
		GL11.glPopMatrix();
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

		return new ResourceLocation("machinemod", Reference.MODEL_BEAM_TEXTURE_LOCATION);
	}

}
