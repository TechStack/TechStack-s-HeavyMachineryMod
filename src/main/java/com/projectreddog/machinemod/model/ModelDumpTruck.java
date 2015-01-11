	package com.projectreddog.machinemod.model;

	import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.projectreddog.machinemod.model.advanced.AdvancedModelLoader;
import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

public class ModelDumpTruck  extends ModelBase
	{
	  //fields
		private IModelCustom myModel;
	  
	  public ModelDumpTruck()
	  {
			
			 
			// LogHelper.info("LOADING dump truck MODEL!");
			 myModel = AdvancedModelLoader.loadModel(new ResourceLocation(  Reference.MOD_ID.toLowerCase(), "models/dumptruck.obj"));
	     // casinoTexture = new ResourceLocation("modid", "textures/casinoTexture.png");
	      
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	//    myModel.renderAll();
	    

	  }

	  public void renderGroupObject(String groupName){
		  myModel.renderPart(groupName);
		  
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5,e);
	  }

	}

