package com.projectreddog.machinemod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.tileentity.ModelWellHead;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityWellHeadRenderer extends TileEntityRenderer {

	private ModelWellHead teModel = new ModelWellHead();
	private static ResourceLocation resourceLocation;

	@Override
	public void render(TileEntity tileentity, double x, double y, double z, float f, int i, float a) {

		Tessellator tessellator = Tessellator.getInstance();

		// Minecraft.getInstance().entityRenderer.enableLightMap() and
		// .disableLightMap(),

		GL11.glPushMatrix();

		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslated(x + .5d, y, z + .5d);

		// GL11.glEnable(GL11.GL_DEPTH_TEST);

		// GL11.glEnableClientState(GL11.GL_LIGHTING);

		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		this.bindTexture(getResourceLocation());
		GL11.glScalef(-.5F, -.5F, .5F);

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		// GL11.glDisableClientState(GL11.GL_LIGHTING);

		GL11.glPopMatrix();

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_WELL_HEAD_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

}
