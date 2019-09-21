package com.projectreddog.machinemod.render.tileentity;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModTowerCrane;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelTowerCrane;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class TileEntityTowerCraneRenderer extends TileEntitySpecialRenderer {

	private RenderItem itemRenderer;
	private RenderManager renderManager;
	private boolean renderBlueprint = false;

	public TileEntityTowerCraneRenderer() {
		super();
		renderManager = Minecraft.getMinecraft().getRenderManager();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	private ModelTowerCrane teModel = new ModelTowerCrane();
	private static ResourceLocation resourceLocation;
	private static ResourceLocation blockBlueprintResourceLocation;

	@Override
	public boolean isGlobalRenderer(TileEntity te) {
		return true;
	}

	@Override
	public void render(TileEntity tileentity, double x, double y, double z, float f, int i, float a) {

		// Minecraft.getMinecraft().entityRenderer.enableLightMap() and
		// .disableLightMap(),

		GL11.glPushMatrix();

		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		// GL11.glEnable(GL11.GL_DEPTH_TEST);

		// GL11.glEnableClientState(GL11.GL_LIGHTING);

		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		EnumFacing ef;
		if (tileentity.getWorld().getBlockState(tileentity.getPos()).getBlock() == ModBlocks.machinetowercrane) {
			ef = (EnumFacing) tileentity.getWorld().getBlockState(tileentity.getPos()).getValue(BlockMachineModTowerCrane.FACING);
		} else {
			ef = EnumFacing.NORTH;
		}
		switch (ef) {
		case NORTH:
			// no rotate?
			break;
		case SOUTH:
			// rotate to south
			// TODO test the state to see if we are active if norotate 180
			// GL11.glRotatef(180f, 0, 1, 0);
			break;
		case EAST:
			// TODO test the state to see if we are active if norotate 1
			// GL11.glRotatef(90f, 0, 1, 0);
			break;
		case WEST:
			// TODO test the state to see if we are active if norotate 1
			// GL11.glRotatef(270f, 0, 1, 0);
			break;
		default:
			// should never happen because we are constrained to the horizontal plane so just break with no addtional rotation applied
			break;
		}

		if (tileentity instanceof TileEntityTowerCrane) {
			TileEntityTowerCrane tc = (TileEntityTowerCrane) tileentity;
			renderBlueprint = !tc.isRunning();
		}

		if (renderBlueprint) {

			GL11.glPushMatrix();

			// GL11.glTranslated(x + 2d + 1, y - 57, z + -18d);
			GL11.glTranslatef((float) x, (float) y - 56, (float) z);

			// GL11.glTranslatef(0f, 0, -18f);
			GL11.glScalef(1f, 1f, 1f);
//		GL11.glScalef(2F, 2F, 2F);

			if (tileentity instanceof TileEntityTowerCrane) {
				TileEntityTowerCrane tetc = (TileEntityTowerCrane) tileentity;

				if (tetc.BlockBluePrintArray != null) {
					for (int bx = 0; bx < tetc.BlockBluePrintArray.length; bx++) {
						GL11.glTranslated(1, 0, 0);

						for (int by = 0; by < tetc.BlockBluePrintArray[bx].length; by++) {
							GL11.glTranslated(0, 1, 0);

							for (int bz = 0; bz < tetc.BlockBluePrintArray[bx][by].length; bz++) {
								GL11.glTranslated(0, 0, 1);
								IBlockState blockStateToRender = tetc.BlockBluePrintArray[bx][by][bz];

								if (blockStateToRender.getBlock() != Blocks.AIR) {

									Minecraft.getMinecraft().renderEngine.bindTexture(getBlockBlueprintResourceLocation());
									GlStateManager.disableLighting();
									GL11.glEnable(GL11.GL_BLEND);
									// GL11.glDepthMask(false);
									GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
									// GL11.glDisable(GL11.GL_DEPTH_TEST);
									// GL11.glDisable(GL11.GL_ALPHA_TEST);

									Tessellator tessellator = Tessellator.getInstance();
									BufferBuilder bufferBuilder = tessellator.getBuffer();
									bufferBuilder.begin(7, DefaultVertexFormats.BLOCK);

									BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
									BlockPos bp = tetc.getPos();
									int bpX = bp.getX();
									int bpY = bp.getY();
									int bpZ = bp.getZ();

									bpX = 0;// bpX + bx;
									bpY = 60;// bpY + by;
									bpZ = 0;// bpZ + bz;
									bp = new BlockPos(bpX, bpY, bpZ);
									// Correct offset here :
									// blockRendererDispatcher.getBlockModelRenderer().renderModel(this.getWorld(), blockRendererDispatcher.getModelForState(blockStateToRender), blockStateToRender, new BlockPos(0, 0, 0), bufferBuilder, false);
									blockRendererDispatcher.getBlockModelRenderer().renderModel(this.getWorld(), blockRendererDispatcher.getModelForState(blockStateToRender), blockStateToRender, bp, bufferBuilder, true);

									tessellator.draw();
								}
							}
							GL11.glTranslated(0, 0, -tetc.BlockBluePrintArray[bx][by].length);

						}
						GL11.glTranslated(0, -tetc.BlockBluePrintArray[bx].length, 0);

					}

					GL11.glTranslated(-tetc.BlockBluePrintArray.length, 0, 0);

				}
			}

			GlStateManager.enableLighting();
			GL11.glDisable(GL11.GL_BLEND);
			// GL11.glEnable(GL11.GL_DEPTH_TEST);
			// GL11.glDepthMask(true);
			GL11.glPopMatrix();
		}

		this.bindTexture(getResourceLocation());

		GL11.glTranslated(x + .5d, y, z + .5d);
		GL11.glScalef(-1F, -1F, 1F);

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(-1F, -1F, 1F);

		// GL11.glScalef(.5f, .5f, .5f);
		ItemStack is;
		if (tileentity instanceof TileEntityTowerCrane) {
			TileEntityTowerCrane tetc = (TileEntityTowerCrane) tileentity;
			if (tetc.state == 2 || tetc.state == 3 || tetc.state == 4) {
				if (tetc.getClawHolding() != null) {
					is = tetc.getClawHolding();
				} else {
					is = ItemStack.EMPTY;
				}
			} else {
				is = ItemStack.EMPTY;
			}
		} else {
			is = ItemStack.EMPTY;
		}
		if (!is.isEmpty()) {

			IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);
			// GL11.glRotatef(45, 1, 1, 0);

			GlStateManager.enableRescaleNormal();

			if (ibakedmodel.isBuiltInRenderer()) {

				TileEntityItemStackRenderer.instance.renderByItem(is);

			} else {
				Tessellator tessellator = Tessellator.getInstance();

				BufferBuilder worldrenderer = tessellator.getBuffer();
				worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
				this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				EnumFacing[] aenumfacing = EnumFacing.values();
				int j = aenumfacing.length;

				for (int k = 0; k < j; ++k) {
					EnumFacing enumfacing = aenumfacing[k];
					// this.RenderHelper_a(worldrenderer, ibakedmodel.getFaceQuads(enumfacing), -1, is);
					this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, enumfacing, 0), -1, is);

				}

				this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, null, 0), -1, is);
				tessellator.draw();
			}
		}

		GL11.glPopMatrix();

	}

	private void RenderHelper_B(BufferBuilder p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
		p_175033_1_.addVertexData(p_175033_2_.getVertexData());
		p_175033_1_.putColor4(p_175033_3_);
		this.RenderHelper_C(p_175033_1_, p_175033_2_);
	}

	private void RenderHelper_C(BufferBuilder p_175038_1_, BakedQuad p_175038_2_) {
		Vec3i vec3i = p_175038_2_.getFace().getDirectionVec();
		p_175038_1_.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}

	private void RenderHelper_a(BufferBuilder p_175032_1_, List p_175032_2_, int p_175032_3_, ItemStack p_175032_4_) {
		boolean flag = p_175032_3_ == -1 && p_175032_4_ != null;
		BakedQuad bakedquad;
		int j;

		for (Iterator iterator = p_175032_2_.iterator(); iterator.hasNext(); this.RenderHelper_B(p_175032_1_, bakedquad, j)) {
			bakedquad = (BakedQuad) iterator.next();
			j = p_175032_3_;

			if (flag && bakedquad.hasTintIndex()) {
				// j = p_175032_4_.getItem().getColorFromItemStack(p_175032_4_, bakedquad.getTintIndex());

				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}

				j |= -16777216;
			}
		}
	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_LOADER_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

	public static ResourceLocation getBlockBlueprintResourceLocation() {
		if (blockBlueprintResourceLocation == null) {
			blockBlueprintResourceLocation = new ResourceLocation("machinemod", Reference.BLOCK_BLUEPRINT_TRANSPARENT_LOCATION);
		}
		return blockBlueprintResourceLocation;
	}

}
