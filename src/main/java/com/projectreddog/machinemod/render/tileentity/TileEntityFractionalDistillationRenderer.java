package com.projectreddog.machinemod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModFractionalDistillation;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelFractionalDistillation;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityFractionalDistillation;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

//test
public class TileEntityFractionalDistillationRenderer extends TileEntitySpecialRenderer {

	private ModelFractionalDistillation teModel = new ModelFractionalDistillation();
	private static ResourceLocation resourceLocation;

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int i) {

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
		if (tileentity instanceof TileEntityFractionalDistillation && tileentity.getWorld().getBlockState(tileentity.getPos()).getBlock() == ModBlocks.machinefractionaldistillation) {
			EnumFacing ef = (EnumFacing) tileentity.getWorld().getBlockState(tileentity.getPos()).getValue(BlockMachineModFractionalDistillation.FACING);
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
		}
		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		// GL11.glDisableClientState(GL11.GL_LIGHTING);

		GL11.glPopMatrix();

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_FRACTIONAL_DISTILATION_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

}
