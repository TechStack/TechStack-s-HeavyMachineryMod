// Date: 12/24/2014 5:11:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelLawnmower extends ModelTransportable {
	// fields
	public OBJModel myOBJModel;
	private IFlexibleBakedModel fullModel;
	private HashMap<String, IFlexibleBakedModel> modelParts;

	public ModelLawnmower() {

		try {
			myOBJModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/lawnmower.obj"));

			// IModel texturedModel = ((OBJModel) myOBJModel.retexture(ImmutableMap.of("#lawnmower", "machinemod:models/lawnmower"))).process(ImmutableMap.of("flip-v", "true"));
			// IModel texturedModel = myOBJModel;// .process(ImmutableMap.of("flip-v", "true"));// .retexture(ImmutableMap.of("#lawnmower", "machinemod:model/modellawnmower")));

			// myBakedModel = texturedModel.bake(myOBJModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, textureGetter);
			// myBakedModel = texturedModel.bake(myOBJModel.getDefaultState(), DefaultVertexFormats.POSITION_TEX_NORMAL, textureGetter);

			// fullModel = myOBJModel.bake(myOBJModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV);

			modelParts = MachineModModelHelper.getModelsForGroups(myOBJModel);

			// can use a list strings as a OBJModel.OBJState Turning those group objects on or off accordngly

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		renderGroupObject(MachineModModelHelper.ALL_PARTS);
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

		return new ResourceLocation("machinemod", Reference.MODEL_LAWNMOWER_TEXTURE_LOCATION);
	}
}
