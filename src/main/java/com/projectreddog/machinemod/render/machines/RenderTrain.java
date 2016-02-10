package com.projectreddog.machinemod.render.machines;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityTrain;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;

public class RenderTrain extends Render {

	protected ModelBase modelLoader;

	private RenderItem itemRenderer;

	public RenderTrain(RenderManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderLoader constructor");
		shadowSize = 1F;
		this.modelLoader = new ModelMinecart();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		double prevY = y;
		double lastY = y;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + .5f, (float) z);
		GL11.glRotatef(180 - yaw - 90, 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		this.bindTexture(new ResourceLocation("minecart"));
		GlStateManager.scale(-.1F, -.1F, -.1F);

		this.modelLoader.render(entity, 0.0F, 0.0F, -0.0F, 0.0F, 0.0F, 1F);

		EntityTrain eL = ((EntityTrain) entity);

		boolean even = true;
		int count = 0;
		for (int i = 0; i < eL.getSizeInventory(); i++) {
			ItemStack is = eL.getStackInSlot(i);
			if (is != null) {
				if (entity.worldObj.isAirBlock(new BlockPos(x, prevY - 1, z - 1))) {
					prevY = prevY - 1;
				}
				GlStateManager.translate(-22F, lastY - prevY, 0F);
				this.modelLoader.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 1F);
				count += 1;
				// GL11.glRotatef(45, 1, 1, 0);
				GlStateManager.enableRescaleNormal();
				lastY = prevY;
			}
		}

		GL11.glPopMatrix();
	}

	private void RenderHelper_B(WorldRenderer p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
		p_175033_1_.addVertexData(p_175033_2_.getVertexData());
		p_175033_1_.putColor4(p_175033_3_);
		this.RenderHelper_C(p_175033_1_, p_175033_2_);
	}

	private void RenderHelper_C(WorldRenderer p_175038_1_, BakedQuad p_175038_2_) {
		Vec3i vec3i = p_175038_2_.getFace().getDirectionVec();
		p_175038_1_.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}

	private void RenderHelper_a(WorldRenderer p_175032_1_, List p_175032_2_, int p_175032_3_, ItemStack p_175032_4_) {
		boolean flag = p_175032_3_ == -1 && p_175032_4_ != null;
		BakedQuad bakedquad;
		int j;

		for (Iterator iterator = p_175032_2_.iterator(); iterator.hasNext(); this.RenderHelper_B(p_175032_1_, bakedquad, j)) {
			bakedquad = (BakedQuad) iterator.next();
			j = p_175032_3_;

			if (flag && bakedquad.hasTintIndex()) {
				j = p_175032_4_.getItem().getColorFromItemStack(p_175032_4_, bakedquad.getTintIndex());

				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}

				j |= -16777216;
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_LOADER_TEXTURE_LOCATION);
	}

}
