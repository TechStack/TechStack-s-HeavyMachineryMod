package com.projectreddog.machinemod.render.machines;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachment;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentTrencher;
import com.projectreddog.machinemod.model.ModelTractor;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTractor extends EntityRenderer {

	protected EntityModel modelTractor;

	public RenderTractor(EntityRendererManager renderManager) {

		super(renderManager);
		shadowSize = 1F;
		this.modelTractor = new ModelTractor();

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
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
		this.modelTractor.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (!((EntityTractor) entity).inventory.getStackInSlot(0).isEmpty()) {
			if (((EntityTractor) entity).inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachment) {
				if (((EntityTractor) entity).inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlow) {

					((ModelTractor) this.modelTractor).renderGroupObject("Plow");
				} else if (((EntityTractor) entity).inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlanter) {
					GL11.glTranslated(0f, -.2f, 0f);

					((ModelTractor) this.modelTractor).renderGroupObject("Planter");

				} else if (((EntityTractor) entity).inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentSprayer) {
					((ModelTractor) this.modelTractor).renderGroupObject("Spreader");

				} else if (((EntityTractor) entity).inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentTrencher) {
					((ModelTractor) this.modelTractor).renderGroupObject("Ditcher");

				}

			}
		}
		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("machinemod", Reference.MODEL_TRACTOR_TEXTURE_LOCATION);
	}

}
