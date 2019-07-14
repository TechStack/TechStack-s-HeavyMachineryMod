package com.projectreddog.machinemod.render.machines;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.model.ModelGrader;
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
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;

public class RenderGrader extends EntityRenderer {

	protected EntityModel modelGrader;

	private ItemRenderer itemRenderer;

	public RenderGrader(EntityRendererManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelGrader = new ModelGrader();
		itemRenderer = Minecraft.getInstance().getItemRenderer();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		EntityGrader eL = ((EntityGrader) entity);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + (float) eL.calcOffsetX(2.0), (float) y, (float) z + (float) eL.calcOffsetZ(2.0));

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
		this.modelGrader.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.translate(-3.5f, -.6F, -4.4F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(.5f, .5f, .5f);

		boolean even = true;
		int count = 0;
		for (int i = 0; i < eL.SIZE; i++) {
			ItemStack is = eL.inventory.getStackInSlot(i);
			if (!is.isEmpty()) {
				// EntityItem customitem = new EntityItem(eDT.worldObj);
				// customitem.hoverStart = 0f;
				// customitem.setEntityItemStack(is);
				IBakedModel ibakedmodel = itemRenderer.getItemModelMesher().getItemModel(is);

				// if (count > 4) {
				// count = 0;
				// GlStateManager.translate(-3.5f, 0.0F, 0F);
				// GlStateManager.translate(0, 0.0F, .5F);
				//
				// }
				GlStateManager.translate(1.25F, 0.0F, 0F);
				count += 1;

				GL11.glRotatef(45, 1, 1, 0);

				GlStateManager.enableRescaleNormal();

				if (ibakedmodel.isBuiltInRenderer()) {

					TileEntityItemStackRenderer.instance.renderByItem(is);

				} else {
					Tessellator tessellator = Tessellator.getInstance();
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

				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}

				j |= -16777216;
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_GRADER_TEXTURE_LOCATION);
	}

}
