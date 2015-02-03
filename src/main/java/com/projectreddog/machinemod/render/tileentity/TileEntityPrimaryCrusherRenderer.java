package com.projectreddog.machinemod.render.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.projectreddog.machinemod.model.tileentity.ModelPrimaryCrusher;
import com.projectreddog.machinemod.reference.Reference;

public class TileEntityPrimaryCrusherRenderer extends TileEntitySpecialRenderer {

	private ModelPrimaryCrusher teModel = new ModelPrimaryCrusher();
	private static ResourceLocation resourceLocation ;
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int i) {

		Tessellator tessellator = Tessellator.getInstance();

		
		//Minecraft.getMinecraft().entityRenderer.enableLightMap() and .disableLightMap(),
		
		
		GL11.glPushMatrix();
	    GL11.glPushAttrib(GL11.GL_ENABLE_BIT);

	//	GL11.glEnable(GL11.GL_LIGHTING); 
	//	GL11.glEnable(GL12.GL_RESCALE_NORMAL); 
		GL11.glTranslated(x, y, z);
			
	//	GL11.glEnable(GL11.GL_SMOOTH);
		//GL11.glEnable(GL11.GL_DEPTH_TEST);

		//GL11.glEnableClientState(GL11.GL_LIGHTING);

	//	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		this.bindTexture( getResourceLocation()); 
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.teModel.render(tileentity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		// ((ModelTractor) this.modelTractor).renderGroupObject("Plow_Cube");
		//GL11.glDisableClientState(GL11.GL_LIGHTING);
	    GL11.glPopAttrib();

		GL11.glPopMatrix();
		
		
		
		
	}
	
	public static ResourceLocation getResourceLocation (){
		if (resourceLocation==null){
			resourceLocation =new ResourceLocation("machinemod", Reference.MODEL_TILEENTITY_PRIMARY_CRUSHER_TEXTURE_LOCATION);
		}
	 return 	resourceLocation;
	}

}
