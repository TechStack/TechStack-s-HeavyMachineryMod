package com.projectreddog.machinemod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModFactory;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelFactory;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityFactoryRenderer extends TileEntitySpecialRenderer {

	private ModelFactory teModel = new ModelFactory();
	private static ResourceLocation resourceLocation;

	@Override
	public void render(TileEntity tileentity, double x, double y, double z, float partialTicks, int i, float a) {

		Tessellator tessellator = Tessellator.getInstance();

		GL11.glPushMatrix();

		GL11.glTranslated(x + .5d, y, z + .5d);

		this.bindTexture(getResourceLocation());
		EnumFacing ef;
		if (tileentity.getWorld().getBlockState(tileentity.getPos()).getBlock() == ModBlocks.machinefactory) {
			ef = (EnumFacing) tileentity.getWorld().getBlockState(tileentity.getPos()).getValue(BlockMachineModFactory.FACING);
		} else {
			ef = EnumFacing.NORTH;
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
			GL11.glRotatef(270f, 0, 1, 0);
			break;
		case WEST:
			GL11.glRotatef(90f, 0, 1, 0);

			break;
		default:
			// should never happen because we are constrained to the horizontal plane so just break with no addtional rotation applied
			break;
		}

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, partialTicks, 0.0F, 0.0625F);

		GL11.glPopMatrix();

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_FACTORY_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

}
