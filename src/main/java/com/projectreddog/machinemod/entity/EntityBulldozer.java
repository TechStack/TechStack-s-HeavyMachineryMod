package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBulldozer extends EntityMachineModRideable {

	public double bladeOffset = 2.0d;
	
	public EntityBulldozer(World world){
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
		  digMethodA();
			
	  }
  }

  
  public void digMethodA(){

	   
	    int yOffset =0;
		double bladeOffsetX =(bladeOffset * MathHelper.cos((float) ((yaw+90) * Math.PI / 180.0D)));
		double bladeOffsetZ= (bladeOffset * MathHelper.sin((float) ((yaw+90) * Math.PI / 180.0D))); 
	  
		if (this.riddenByEntity != null && this.isPlayerPushingSprintButton){
			yOffset=-1;
		}

		if (this.riddenByEntity != null && this.isPlayerPushingJumpButton){
			yOffset=+1;
		}
		
		
		int x =(int)(this.posX+bladeOffsetX-.5d);
		int y = (int)Math.round(this.posY +yOffset);
		int z= (int)(this.posZ+bladeOffsetZ-.5d);
		BlockPos bp = new BlockPos(x, y, z);
		if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass ||worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand ){
			worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp,worldObj.getBlockState(bp)  , 0);
			worldObj.setBlockToAir(bp);
		}
		

		double bladeOffsetX2 =(1 * MathHelper.cos((float) ((yaw+90+90) * Math.PI / 180.0D)));
		double bladeOffsetZ2= (1 * MathHelper.sin((float) ((yaw+90+90) * Math.PI / 180.0D))); 
	  
		x =(int)(this.posX+bladeOffsetX+bladeOffsetX2-.5d );
		z= (int)(this.posZ+bladeOffsetZ+bladeOffsetZ2-.5d);
		bp = new BlockPos(x, y, z);
		if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass ||worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand ){
			worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp,worldObj.getBlockState(bp)  , 0);
			worldObj.setBlockToAir(bp);
		}
		x =(int)(this.posX+bladeOffsetX-bladeOffsetX2-.5d);
		 z= (int)(this.posZ+bladeOffsetZ-bladeOffsetZ2-.5d);
		 bp = new BlockPos(x, y, z);
			if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass ||worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand ){
				worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp,worldObj.getBlockState(bp)  , 0);
				worldObj.setBlockToAir(bp);
			}
		
  }
  
  public int BladePos1X;
  public int BladePos1Z;
  public int BladePos2X;
  public int BladePos2Z;
  public int BladePos3X;
  public int BladePos3Z;
  

  public void setBladePosFromYaw()
  {
	  if ((yaw >=0 && yaw < 23) || yaw >337  ){
		  BladePos1X = (int) Math.round(posX );
		  BladePos1Z = (int) Math.round(posZ+bladeOffset);
		  BladePos2X = (int) Math.round(posX +1);
		  BladePos2Z = (int) Math.round(posZ+bladeOffset);
		  BladePos3X = (int) Math.round(posX -1);
		  BladePos3Z = (int) Math.round(posZ+bladeOffset);
	  }
	  else if (yaw >=23 && yaw < 69){
		  
		  
		  BladePos1X = (int) Math.round(posX +bladeOffset);
		  BladePos1Z = (int) Math.round(posZ+bladeOffset);
		  BladePos2X = (int) Math.round(posX +bladeOffset)+1;
		  BladePos2Z = (int) Math.round(posZ+bladeOffset)+1;
		  BladePos3X = (int) Math.round(posX +bladeOffset-1);
		  BladePos3Z = (int) Math.round(posZ+bladeOffset)-1;
		  
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
