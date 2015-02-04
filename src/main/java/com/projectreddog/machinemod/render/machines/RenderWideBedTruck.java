package com.projectreddog.machinemod.render.machines;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.item.ItemTransportable;
import com.projectreddog.machinemod.model.ModelBulldozer;
import com.projectreddog.machinemod.model.ModelDumpTruck;
import com.projectreddog.machinemod.model.ModelTransportable;
import com.projectreddog.machinemod.model.ModelWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

public class RenderWideBedTruck extends Render {

	protected ModelBase modelWideBedTruck;
	protected ModelTransportable modelCarriedEntity;
	private RenderItem itemRenderer;

	public RenderWideBedTruck(RenderManager renderManager) {

		super(renderManager);

		// LogHelper.info("in RenderDumpTruck constructor");
		shadowSize = 1F;
		this.modelWideBedTruck = new ModelWideBedTruck();
		itemRenderer = Minecraft.getMinecraft().getRenderItem();

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
		this.modelWideBedTruck.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.translate(0f, -0.72F, +4F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		// GL11.glScalef(.75f, .75f, .75f);
		// attempt to render the items in inventory
		EntityWideBedTruck eDT = ((EntityWideBedTruck) entity);
		GL11.glRotatef(180, 0, 1, 0);
		boolean even = true;
		int count = 0;
		for (int i = 0; i < eDT.getSizeInventory(); i++) {
			ItemStack is = eDT.getStackInSlot(i);
			if (is != null) {
				if (is.getItem() instanceof ItemTransportable) {
					ItemTransportable it = (ItemTransportable) is.getItem();
					modelCarriedEntity = (it).getModel();
					this.bindTexture(((ItemTransportable) is.getItem()).getModel().getTexture());
					((ItemTransportable) is.getItem()).getModel().render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				}
			}
		}

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("machinemod", Reference.MODEL_DUMPTRUCK_TEXTURE_LOCATION);
	}

}
