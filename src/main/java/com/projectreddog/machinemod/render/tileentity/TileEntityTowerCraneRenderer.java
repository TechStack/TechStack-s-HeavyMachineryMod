package com.projectreddog.machinemod.render.tileentity;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.block.BlockMachineModTowerCrane;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.model.tileentity.ModelTowerCrane;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.client.Minecraft;
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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;

public class TileEntityTowerCraneRenderer extends TileEntitySpecialRenderer {

	private RenderItem itemRenderer;
	private RenderManager renderManager;

	public TileEntityTowerCraneRenderer() {
		super();
		renderManager = Minecraft.getMinecraft().getRenderManager();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	private ModelTowerCrane teModel = new ModelTowerCrane();
	private static ResourceLocation resourceLocation;

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
		GL11.glTranslated(x + .5d, y, z + .5d);

		// GL11.glEnable(GL11.GL_DEPTH_TEST);

		// GL11.glEnableClientState(GL11.GL_LIGHTING);

		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		this.bindTexture(getResourceLocation());
		GL11.glScalef(-1F, -1F, 1F);
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

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(-1F, -1F, 1F);

		// GL11.glScalef(.5f, .5f, .5f);
		ItemStack is;
		if (tileentity instanceof TileEntityTowerCrane) {
			TileEntityTowerCrane tetc = (TileEntityTowerCrane) tileentity;
			if (tetc.state == 2 || tetc.state == 3 || tetc.state == 4) {
				is = new ItemStack(tetc.BlockBluePrintArray[tetc.currentX][tetc.currentY][tetc.currentZ].getBlock(), 1, tetc.BlockBluePrintArray[tetc.currentX][tetc.currentY][tetc.currentZ].getBlock().damageDropped(tetc.BlockBluePrintArray[tetc.currentX][tetc.currentY][tetc.currentZ]));
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

}
