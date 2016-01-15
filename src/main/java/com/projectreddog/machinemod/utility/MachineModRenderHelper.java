package com.projectreddog.machinemod.utility;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraftforge.client.model.IFlexibleBakedModel;

public class MachineModRenderHelper {
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
}
