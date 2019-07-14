package com.projectreddog.machinemod.render.tileentity;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.model.tileentity.ModelCrate;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;

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
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
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
	public void render(TileEntity tileentity, double x, double y, double z, float f, int i, float a) {

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
		String str = "";
		if (tileentity instanceof TileEntityCrate) {
			TileEntityCrate te = (TileEntityCrate) tileentity;
			int count = 0;
			int inventoryIndex = 0;
			ItemStack is = te.getStackInSlot(inventoryIndex);
			if (is != null && !is.isEmpty()) {
				str = FormatAmount(is.getCount() + te.AmtInReserve);
				// EntityItem customitem = new EntityItem(eDT.worldObj);
				// customitem.hoverStart = 0f;
				// customitem.setEntityItemStack(is);
				IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);
				GL11.glRotatef(te.rotAmt, 0, 1, 0);

				GlStateManager.translate(-.25F, 0.05F, -.25F);
				GL11.glScalef(.5F, .5F, .5F);
				//

				GlStateManager.enableRescaleNormal();
				if (ibakedmodel.isBuiltInRenderer()) {

					TileEntityItemStackRenderer.instance.renderByItem(is);

				} else {
					BufferBuilder worldrenderer = tessellator.getBuffer();
					worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
					this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
					Direction[] aDirection = Direction.values();
					int j = aDirection.length;

					for (int k = 0; k < j; ++k) {
						Direction Direction = aDirection[k];
						this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, Direction, 0), -1, is);

					}
					this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, null, 0), -1, is);
					tessellator.draw();
				}

			}
			GL11.glPopMatrix();
			float f2 = this.renderManager.playerViewY;
			float f1 = this.renderManager.playerViewX;
			boolean flag1 = this.renderManager.options.thirdPersonView == 2;
			// String str = "" + is.stackSize;
			Entity entity = this.rendererDispatcher.entity;
			double d0 = te.getDistanceSq(entity.posX, entity.posY, entity.posZ);
			// the 400 on the next line is the square of 20 *20 // save 1 math operation by pre calc
			if (d0 <= (double) (400) && Minecraft.getMinecraft().player.isSneaking()) {
				if (!str.equals("")) {
					EntityRenderer.drawNameplate(this.renderManager.getFontRenderer(), str, (float) x + .5f, (float) y + .75f, (float) z + .5f, 0, f2, f1, flag1, false);
				}
			}
		}

	}

	public String FormatAmount(double amount) {
		if (amount >= 1000000000) {
			return "" + (Math.round((amount / 1000000000) * 10.0) / 10.0) + "B";
		} else if (amount >= 1000000) {
			return "" + (Math.round((amount / 1000000) * 10.0) / 10.0) + "M";
		} else if (amount >= 1000) {
			return "" + (Math.round((amount / 1000) * 10.0) / 10.0) + "K";
		} else {
			return "" + ((int) amount) + "";
		}

	}

	public static ResourceLocation getResourceLocation() {
		if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("machinemod", Reference.MODEL_CRATE_TEXTURE_LOCATION);
		}
		return resourceLocation;
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
}
