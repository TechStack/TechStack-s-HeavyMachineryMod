package com.projectreddog.machinemod.render.machines;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.projectreddog.machinemod.entity.EntityUnderGroundLoader;
import com.projectreddog.machinemod.model.ModelUnderGroundLoader;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;

public class RenderUnderGroundLoader extends EntityRenderer {

	protected EntityModel modelundergroundLoader;

	private ItemRenderer itemRenderer;

	public RenderUnderGroundLoader(EntityRendererManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelundergroundLoader = new ModelUnderGroundLoader();
		itemRenderer = Minecraft.getInstance().getItemRenderer();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		float f2 = pitch;
		float f3 = pitch;

		if (f3 < 0.0F) {
			f3 = 0.0F;
		}

		if (f2 > 0.0F) {
			// GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F *
			// (float)((EntityBulldozer) entity).getForwardDirection(), 1.0F,
			// 0.0F, 0.0F);
		}

		float f4 = 0.75F;
		// GL11.glScalef(f4, f4, f4);
		// GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);

		GL11.glScalef(-1.0F, -1.0F, 1.0F);

		this.modelundergroundLoader.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.translatef(-2f, -0.00F, -1.57F);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(.5f, .5f, .5f);
		EntityUnderGroundLoader eL = ((EntityUnderGroundLoader) entity);

		boolean even = true;
		int count = 0;
		for (int i = 0; i < eL.SIZE; i++) {
			ItemStack is = eL.inventory.getStackInSlot(i);
			if (is != null && !is.isEmpty()) {
				// ItemEntity customitem = new ItemEntity(eDT.worldObj);
				// customitem.hoverStart = 0f;
				// customitem.setItemEntityStack(is);
				IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);

				if (count > 4) {
					count = 0;
					GlStateManager.translatef(-4f, 0.0F, 0F);
					GlStateManager.translatef(0, 0.0F, .5F);

				}
				GlStateManager.translatef(1.1F, 0.0F, 0F);
				count += 1;

				GL11.glRotatef(45, 1, 1, 0);

				GlStateManager.enableRescaleNormal();

				if (ibakedmodel.isBuiltInRenderer()) {

					ItemStackTileEntityRenderer.instance.renderByItem(is);

				} else {
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder worldrenderer = tessellator.getBuffer();
					worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
					this.renderManager.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
					Direction[] aDirection = Direction.values();
					int j = aDirection.length;

					for (int k = 0; k < j; ++k) {
						Direction Direction = aDirection[k];
						this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, Direction, new Random()), -1, is);

					}

					this.RenderHelper_a(worldrenderer, ibakedmodel.getQuads(null, null, new Random()), -1, is);
					tessellator.draw();
				}
				GL11.glRotatef(-45, 1, 1, 0);
				even = !even;
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
				// TODO: 1.14 removed?
				// if (EntityRenderer.anaglyphEnable) {
//					j = TextureUtil.anaglyphColor(j);
				// }

				j |= -16777216;
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_UNDER_GROUND_LOADER_TEXTURE_LOCATION);
	}

}
