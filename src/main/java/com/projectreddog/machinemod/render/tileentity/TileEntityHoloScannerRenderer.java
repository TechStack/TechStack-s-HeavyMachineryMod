package com.projectreddog.machinemod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModHoloScanner;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelHoloScanner;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityHoloScanner;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityHoloScannerRenderer extends TileEntitySpecialRenderer {

	private ModelHoloScanner teModel = new ModelHoloScanner();
	private static ResourceLocation resourceLocation;
	private RenderItem itemRenderer;
	private RenderManager renderManager;

	public TileEntityHoloScannerRenderer() {
		renderManager = Minecraft.getMinecraft().getRenderManager();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();

	}

	@Override
	public boolean isGlobalRenderer(TileEntity te) {
		return true;
	}

	@Override
	public void render(TileEntity tileentity, double x, double y, double z, float f, int i, float a) {

		Tessellator tessellator = Tessellator.getInstance();

		// Minecraft.getMinecraft().entityRenderer.enableLightMap() and
		// .disableLightMap(),

		GL11.glPushMatrix();

		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glTranslated(x + .5f, y, z + .5f);
		GL11.glScalef(.5F, .5F, .5F);
		this.bindTexture(getResourceLocation());
		EnumFacing ef;
		if (tileentity.getWorld().getBlockState(tileentity.getPos()).getBlock() == ModBlocks.machineholoscanner) {
			ef = (EnumFacing) tileentity.getWorld().getBlockState(tileentity.getPos()).getValue(BlockMachineModHoloScanner.FACING);
		} else {
			ef = EnumFacing.NORTH;
		}
		switch (ef) {
		case NORTH:

			GL11.glRotatef(180f, 0, 1, 0);
			break;
		case SOUTH:

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

		GL11.glPopMatrix();
		if (tileentity instanceof TileEntityHoloScanner) {
			TileEntityHoloScanner tehs = (TileEntityHoloScanner) tileentity;
			if (tehs.areaBB != null) {

				GL11.glPushMatrix();
				GL11.glTranslated(x + .5f, y, z + .5f);

				MachineModModelHelper.DrawBoundingBox(tehs.areaBB);
				GL11.glPopMatrix();
			}
		}

// render the bounding box of this area it will scan.

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_HOLOSCANNER_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

}
