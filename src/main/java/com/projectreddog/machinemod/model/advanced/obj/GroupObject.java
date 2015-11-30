package com.projectreddog.machinemod.model.advanced.obj;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GroupObject {
	public String name;
	public ArrayList<Face> faces = new ArrayList<Face>();
	public int glDrawingMode;

	public GroupObject() {
		this("");
	}

	public GroupObject(String name) {
		this(name, -1);
	}

	public GroupObject(String name, int glDrawingMode) {
		this.name = name;
		this.glDrawingMode = glDrawingMode;
	}

	@SideOnly(Side.CLIENT)
	public void render() {
		if (faces.size() > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldRenderer = tessellator.getWorldRenderer();
			//worldRenderer.startDrawing(glDrawingMode);
			worldRenderer.func_181668_a(glDrawingMode,DefaultVertexFormats.field_181703_c);

			render(tessellator);
			tessellator.draw();
		}
	}

	@SideOnly(Side.CLIENT)
	public void render(Tessellator tessellator) {
		if (faces.size() > 0) {
			for (Face face : faces) {
				face.addFaceForRender(tessellator);
			}
		}
	}
}