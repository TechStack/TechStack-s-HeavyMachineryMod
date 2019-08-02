package com.projectreddog.machinemod.render.machines;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.model.ModelBagger;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
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

public class RenderBagger extends EntityRenderer {

	float wheelRadius = 10f;
	protected EntityModel modelBagger;

	private ItemRenderer itemRenderer;

	public RenderBagger(EntityRendererManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelBagger = new ModelBagger();
		itemRenderer = Minecraft.getInstance().getItemRenderer();

	}

	@Override
	public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ) {
		return true; // super.shouldRender(entity, camera, camX, camY, camZ) ||
						// (entity.riddenByEntity != null);
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
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelBagger.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// GlStateManager.translate(-1.4f, -0.25F, -.85F);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(.5f, .5f, .5f);
		EntityBagger eL = ((EntityBagger) entity);

		boolean even = true;
		int count = 0;
		// GlStateManager.translate(-.0f, 1.0F, 0F);
		GL11.glRotatef(90f, 0, 0, 1);
		GlStateManager.translatef(0f, -1.5F, 0F);

		wheelRadius = 8.f;
		for (int i = 0; i < eL.SIZE; i++) {
			ItemStack is = eL.inventory.getStackInSlot(i);
			if (!is.isEmpty()) {
				// ItemEntity customitem = new ItemEntity(eDT.worldObj);
				// customitem.hoverStart = 0f;
				// customitem.setItemEntityStack(is);
				IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);

				if (count > 16) {
					count = 0;
					GlStateManager.translatef(0f, 1F, 0F);

				}
				// GlStateManager.translate(.7F, 0.0F, 0F);
				count += 1;

				GL11.glRotatef(22.5f, 0, 1, 0);
				GlStateManager.translatef(wheelRadius, 0.0F, 0F);
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
				GlStateManager.translatef(wheelRadius * -1, 0.0F, 0F);

				// GL11.glRotatef(-45, 1, 1, 0);
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
				// j = p_175032_4_.getItem().getColorFromItemStack(p_175032_4_,
				// bakedquad.getTintIndex());
//TODO: 1.14 removed?
				// if (EntityRenderer.anaglyphEnable) {
//					j = TextureUtil.anaglyphColor(j);
				// }

				j |= -16777216;
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_BAGGER_TEXTURE_LOCATION);
	}

}
