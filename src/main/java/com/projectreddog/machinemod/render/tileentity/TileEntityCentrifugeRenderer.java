package com.projectreddog.machinemod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModCentrifuge;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelCentrifuge;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class TileEntityCentrifugeRenderer extends TileEntitySpecialRenderer {

	private ModelCentrifuge teModel = new ModelCentrifuge();
	private static ResourceLocation resourceLocation;

	@Override
	public void render(TileEntity tileentity, double x, double y, double z, float f, int i, float a) {

		Tessellator tessellator = Tessellator.getInstance();

		// Minecraft.getMinecraft().entityRenderer.enableLightMap() and
		// .disableLightMap(),

		GL11.glPushMatrix();

		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslated(x + .5d, y, z + .5d);

		// GL11.glEnable(GL11.GL_DEPTH_TEST);

		// GL11.glEnableClientState(GL11.GL_LIGHTING);

		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		this.bindTexture(getResourceLocation());
		GL11.glScalef(-.5F, -.5F, .5F);
		Direction ef;
		if (tileentity.getWorld().getBlockState(tileentity.getPos()).getBlock() == ModBlocks.machinecentrifuge) {
			ef = (Direction) tileentity.getWorld().getBlockState(tileentity.getPos()).getValue(BlockMachineModCentrifuge.FACING);
		} else {
			ef = Direction.NORTH;
		}
		switch (ef) {
		case NORTH:
			// no rotate?
			break;
		case SOUTH:
			// rotate to south
			GL11.glRotatef(180f, 0, 1, 0);
			break;
		case EAST:
			GL11.glRotatef(90f, 0, 1, 0);
			break;
		case WEST:
			GL11.glRotatef(270f, 0, 1, 0);
			break;
		default:
			// should never happen because we are constrained to the horizontal plane so just break with no addtional rotation applied
			break;
		}

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		// GL11.glDisableClientState(GL11.GL_LIGHTING);

		GL11.glPopMatrix();

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_TILEENTITY_CENRIFUGE_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

}
