package com.projectreddog.machinemod.render.machines;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.TextureUtil;
import com.projectreddog.machinemod.model.ModelContinuousMiner;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;

public class RenderContinuousMiner extends EntityRenderer {

	protected EntityModel modelContinuousMiner;

	private ItemRenderer itemRenderer;

	public RenderContinuousMiner(EntityRendererManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelContinuousMiner = new ModelContinuousMiner();
		itemRenderer = Minecraft.getInstance().getItemRenderer();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelContinuousMiner.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
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
		return new ResourceLocation("machinemod", Reference.MODEL_CONTINUOUSMINER_TEXTURE_LOCATION);
	}

}
