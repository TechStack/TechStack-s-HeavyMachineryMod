package com.projectreddog.machinemod.utility;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableList;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.obj.OBJModel;

public class MachineModModelHelper {
	public static void renderBakedModel(IFlexibleBakedModel bakedModel) {
		Tessellator tessellator = Tessellator.getInstance();

		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.begin(GL11.GL_QUADS, bakedModel.getFormat());
		for (BakedQuad bakedQuad : bakedModel.getGeneralQuads()) {
			worldrenderer.addVertexData(bakedQuad.getVertexData());

		}
		// alt version if ever needed
		// for (BakedQuad bakedQuad : bakedModel.getGeneralQuads()) {
		// LightUtil.renderQuadColor(worldrenderer, bakedQuad, -1);
		//
		// }

		tessellator.draw();
	}

	public static final String ALL_PARTS = "ALL";

	// public static HashMap<String, IFlexibleBakedModel> getModelsForGroups(ResourceLocation modelLocation, OBJModel objModel) {
	public static HashMap<String, IFlexibleBakedModel> getModelsForGroups(OBJModel objModel) {

		HashMap<String, IFlexibleBakedModel> modelParts = new HashMap<String, IFlexibleBakedModel>();

		// IResource from;
		// IResourceManager manager;
		// final Pattern WHITE_SPACE = Pattern.compile("\\s+");
		// BufferedReader lobjReader;
		//
		// ResourceLocation lobjFrom;
		// InputStreamReader lobjStream;
		// String currentLine = "";
		// List<String> groupList = Lists.newArrayList();
		//
		// ResourceLocation file = new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath());
		// IResource resource = null;
		//
		// manager = Minecraft.getMinecraft().getResourceManager();
		//
		// try {
		// resource = manager.getResource(file);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// try {
		// if (modelLocation.getResourcePath().startsWith("models/block/"))
		// resource = manager.getResource(new ResourceLocation(file.getResourceDomain(), "models/item/" + file.getResourcePath().substring("models/block/".length())));
		// else if (modelLocation.getResourcePath().startsWith("models/item/"))
		// resource = manager.getResource(new ResourceLocation(file.getResourceDomain(), "models/block/" + file.getResourcePath().substring("models/item/".length())));
		// } catch (Exception e2) {
		//
		// }
		//
		// }
		// try
		//
		// {
		// lobjFrom = resource.getResourceLocation();
		// lobjStream = new InputStreamReader(resource.getInputStream(), Charsets.UTF_8);
		// lobjReader = new BufferedReader(lobjStream);
		//
		// for (;;) {
		//
		// currentLine = lobjReader.readLine();
		//
		// if (currentLine == null)
		// break;// leave the loop
		//
		// if (currentLine.isEmpty() || currentLine.startsWith("#"))
		// continue;//
		//
		// String[] fields = WHITE_SPACE.split(currentLine, 2);
		// String key = fields[0];
		// String data = fields[1];
		// String[] splitData = WHITE_SPACE.split(data);
		// if (key.equalsIgnoreCase("g") || key.equalsIgnoreCase("o")) {
		// groupList.clear();
		// if (key.equalsIgnoreCase("g")) {
		// String[] splitSpace = data.split(" ");
		// for (String s : splitSpace)
		// groupList.add(s);
		// } else {
		// groupList.add(data);
		// }
		// }
		// }
		// } catch (
		//
		// IOException e)
		//
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// if (!groupList.isEmpty())
		//
		// {
		// for (String key : groupList) {
		// modelParts.put(key, objModel.bake(new OBJModel.OBJState(ImmutableList.of(key), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));
		//
		// }
		// } else
		//
		// {
		// modelParts.put("ALL", objModel.bake(objModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));
		// }

		if (!objModel.getMatLib().getGroups().keySet().isEmpty()) {
			for (String key : objModel.getMatLib().getGroups().keySet()) {
				String k = key;
				if (!modelParts.containsKey(key)) {
					modelParts.put(k, objModel.bake(new OBJModel.OBJState(ImmutableList.of(k), false), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));
				}
			}
		}

		modelParts.put(ALL_PARTS, objModel.bake(objModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, Reference.textureGetterFlipV));

		return modelParts;
	}
}
