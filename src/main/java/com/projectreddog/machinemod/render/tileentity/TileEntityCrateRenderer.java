package com.projectreddog.machinemod.render.tileentity;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.tileentity.ModelCrate;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
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

public class TileEntityCrateRenderer extends TileEntitySpecialRenderer {

	private ModelCrate teModel = new ModelCrate();
	private static ResourceLocation resourceLocation;
	private RenderItem itemRenderer;
	private RenderManager renderManager;

	public TileEntityCrateRenderer() {
		renderManager = Minecraft.getMinecraft().getRenderManager();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();

	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int i) {

		Tessellator tessellator = Tessellator.getInstance();

		// Minecraft.getMinecraft().entityRenderer.enableLightMap() and
		// .disableLightMap(),

		GL11.glPushMatrix();

		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glTranslated(x + .5f, y, z + .5f);
		GL11.glScalef(.5F, .5F, .5F);
		this.bindTexture(getResourceLocation());

		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glScalef(2f, 2f, 2f);
		// GL11.glScalef(2f, 2f, 2f);
		// GL11.glTranslated(-.5f, .5f, -.5f);
		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		// GL11.glDisableClientState(GL11.GL_LIGHTING);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (tileentity instanceof TileEntityCrate) {
			TileEntityCrate te = (TileEntityCrate) tileentity;
			int count = 0;
			int inventoryIndex = 0;
			ItemStack is = te.getStackInSlot(inventoryIndex);
			if (is != null) {
				// EntityItem customitem = new EntityItem(eDT.worldObj);
				// customitem.hoverStart = 0f;
				// customitem.setEntityItemStack(is);
				IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);
				GL11.glRotatef(te.rotAmt, 0, 1, 0);

				GlStateManager.translate(-.25F, 0.25F, -.25F);
				GL11.glScalef(.5F, .5F, .5F);
				//

				GlStateManager.enableRescaleNormal();
				if (ibakedmodel.isBuiltInRenderer()) {

					TileEntityItemStackRenderer.instance.renderByItem(is);

				} else {
					VertexBuffer worldrenderer = tessellator.getBuffer();
					worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
					this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
					EnumFacing[] aenumfacing = EnumFacing.values();
					int j = aenumfacing.length;

					for (int k = 0; k < j; ++k) {
						EnumFacing enumfacing = aenumfacing[k];
						this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, enumfacing, 0), -1, is);

					}
					this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, null, 0), -1, is);
					tessellator.draw();
				}

			}
		}

		GL11.glPopMatrix();

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_CRATE_TEXTURE_LOCATION);
		}
		return resourceLocation;
	}

	private void RenderHelper_B(VertexBuffer p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
		p_175033_1_.addVertexData(p_175033_2_.getVertexData());
		p_175033_1_.putColor4(p_175033_3_);
		this.RenderHelper_C(p_175033_1_, p_175033_2_);
	}

	private void RenderHelper_C(VertexBuffer p_175038_1_, BakedQuad p_175038_2_) {
		Vec3i vec3i = p_175038_2_.getFace().getDirectionVec();
		p_175038_1_.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}

	private void RenderHelper_a(VertexBuffer p_175032_1_, List p_175032_2_, int p_175032_3_, ItemStack p_175032_4_) {
		boolean flag = p_175032_3_ == -1 && p_175032_4_ != null;
		BakedQuad bakedquad;
		int j;

		for (Iterator iterator = p_175032_2_.iterator(); iterator.hasNext(); this.RenderHelper_B(p_175032_1_, bakedquad, j)) {
			bakedquad = (BakedQuad) iterator.next();
			j = p_175032_3_;

			if (flag && bakedquad.hasTintIndex()) {
				// TODO Fix Color
				// j = p_175032_4_.getItem().getColorFromItemStack(p_175032_4_, bakedquad.getTintIndex());

				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}

				j |= -16777216;
			}
		}
	}
}
