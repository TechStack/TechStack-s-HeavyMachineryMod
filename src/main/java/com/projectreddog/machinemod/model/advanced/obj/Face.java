package com.projectreddog.machinemod.model.advanced.obj;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Face {
	public Vertex[] vertices;
	public Vertex[] vertexNormals;
	public Vertex faceNormal;
	public TextureCoordinate[] textureCoordinates;

	@SideOnly(Side.CLIENT)
	public void addFaceForRender(Tessellator tessellator) {
		addFaceForRender(tessellator, 0.0005F);
	}

	@SideOnly(Side.CLIENT)
	public void addFaceForRender(Tessellator tessellator, float textureOffset) {
		if (faceNormal == null) {
			faceNormal = this.calculateFaceNormal();
		}

		// 1.8 Specific removed setting normals from tessellator
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		// worldrenderer.set
		// tessellator.setNormal(faceNormal.x, faceNormal.y, faceNormal.z);
		// set normal function?
		// 1.8 (faceNormal.x, faceNormal.y, faceNormal.z);

		// int i1 = this.vertexFormat.getNextOffset() >> 2;
		// int j1 = (this.vertexCount - 4) * i1 + this.vertexFormat.getNormalOffset() / 4;
		//
		// LogHelper.info(worldrenderer.getVertexFormat());
		worldrenderer.normal(faceNormal.x, faceNormal.y, faceNormal.z);

		//
		float averageU = 0F;
		float averageV = 0F;

		if ((textureCoordinates != null) && (textureCoordinates.length > 0)) {
			for (int i = 0; i < textureCoordinates.length; ++i) {
				averageU += textureCoordinates[i].u;
				averageV += textureCoordinates[i].v;
			}

			averageU = averageU / textureCoordinates.length;
			averageV = averageV / textureCoordinates.length;
		}

		float offsetU, offsetV;
		int i = 0;

		// worldrenderer.pos(vertices[0].x, vertices[0].y, vertices[0].z);
		// worldrenderer.endVertex();
		for (i = 0; i < vertices.length; ++i) {

			if ((textureCoordinates != null) && (textureCoordinates.length > 0)) {
				offsetU = textureOffset;
				offsetV = textureOffset;

				if (textureCoordinates[i].u > averageU) {
					offsetU = -offsetU;
				}
				if (textureCoordinates[i].v > averageV) {
					offsetV = -offsetV;
				}

				// 1.8 worldrenderer.addVertexWithUV(vertices[i].x, vertices[i].y, vertices[i].z, textureCoordinates[i].u + offsetU, textureCoordinates[i].v + offsetV);

				worldrenderer.pos(vertices[i].x, vertices[i].y, vertices[i].z);
				worldrenderer.tex(textureCoordinates[i].u + offsetU, textureCoordinates[i].v + offsetV);

				// worldrenderer.endVertex();
			} else {
				// 1.8 worldrenderer.addVertex(vertices[i].x, vertices[i].y, vertices[i].z);

				worldrenderer.pos(vertices[i].x, vertices[i].y, vertices[i].z);
				worldrenderer.endVertex();
			}

		}

	}

	public Vertex calculateFaceNormal() {
		// 1.8 cast doubles and removed vector helper
		Vec3 v1 = new Vec3((double) vertices[1].x - vertices[0].x, (double) vertices[1].y - vertices[0].y, (double) vertices[1].z - vertices[0].z);
		Vec3 v2 = new Vec3((double) vertices[2].x - vertices[0].x, (double) vertices[2].y - vertices[0].y, (double) vertices[2].z - vertices[0].z);
		Vec3 normalVector = null;

		normalVector = v1.crossProduct(v2).normalize();

		return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
	}
}