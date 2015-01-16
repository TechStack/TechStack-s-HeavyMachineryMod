package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTractor extends EntityMachineModRideable {

	public double bladeOffset = 2.0d;
	
	public EntityTractor(World world){
		super(world);
		setSize (1.5F , 2F); 
	}
	 
   @Override
   /**
    * Returns the Y offset from the entity's position for any entity riding this one.
    */
   public double getMountedYOffset()
   {
       return (double)this.height * 0.35D;
   }

   @Override
   public Item getItemToBeDropped()
   {
	   return ModItems.bulldozer;
   }
  
  @Override
  public void onUpdate(){
	  super.onUpdate();
	  if (!worldObj.isRemote){
		 // digMethodA();
			
	  }
  }

  
	
//	  /**
//     * Sets the forward direction of the entity.
//     */
//    public void setForwardDirection(int value)
//    {
//        this.dataWatcher.updateObject(18, Integer.valueOf(value));
//    }
//
//    /**
//     * Gets the forward direction of the entity.
//     */
//    public int getForwardDirection()
//    {
//        return this.dataWatcher.getWatchableObjectInt(18);
//    }
}
