package com.projectreddog.machinemod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityToClient;

public class EntityMachineModRideable extends Entity {

	public double velocity;
	public float yaw;

	public boolean isPlayerAccelerating=false;
	public boolean isPlayerBreaking =false;
	public boolean isPlayerTurningRight=false;
	public boolean isPlayerTurningLeft=false;
	public boolean isPlayerPushingSprintButton=false;
	public boolean isPlayerPushingJumpButton=false;
	public double TargetposX;
	public double TargetposY;
	public double TargetposZ;
	public float TargetYaw;
    public int MoveTickCount;
	public int YawTickCount;
	public AxisAlignedBB BoundingBox;
	public float Attribute1;// multipurpose variable use defined in extended class controled by sprint & space (down / up)
	public EntityMachineModRideable(World world){
		super(world);
		setSize (1.5F , 0.6F); // should be overridden in Extened version.
		this.stepHeight=1;

	}

	public double getMaxVelocity(){
		// created as method so extending class can easily override to allow for different speeds per machine
		return 0.2d;
	}
	
	//1.8
//	@Override
//	public AxisAlignedBB getBoundingBox(){
//		return this.BoundingBox;
//	}

	
	//1.8
//	@Override 
//	public AxisAlignedBB getCollisionBox(Entity entity){
//		if (entity != riddenByEntity){
//			return entity.boundingBox; 
//		}
//		else{
//			return null;// do not colide with the rider
//		}
//	}

	@Override 
	public boolean canBeCollidedWith(){
		return !isDead;
	}
	
 public Item getItemToBeDropped()
 {
	 return null;
 }

	@Override
	public boolean interactFirst(EntityPlayer player) // should be proper class
	{
		if (!worldObj.isRemote && riddenByEntity==null){
			// server side and no rider
			
			if (player.isSneaking()){
				if ( getItemToBeDropped()!= null ){
					this.dropItem(getItemToBeDropped(), 1);
					this.setDead();
				}
			
			}else{
			player.mountEntity(this);
			}
		}
		return true;
	}

	@Override
	public double getMountedYOffset(){
		// should be overriden in exteneded class if not default;
		return -0.15;
	}
	
	public double getMountedXOffset(){
		// should be overriden in exteneded class if not default;
		return 0;
	}
	
	public double getMountedZOffset(){
		// should be overriden in exteneded class if not default;
		return 0;
	}
	
	public void updateServer() {
		
		//New for gravity
//		this.motionY -= 0.03999999910593033D;
//		if (onGround){
//
//            this.motionY *= -0.5D;
//
//		}
		if (posY<0){
			this.setDead();
		}
		
		if (worldObj.isAirBlock(new BlockPos((int) (posX-.5d), (int) posY , (int)(posZ-.5d ))) ){
			// in air block so fall i'll actually park the entity inside the block below just a little bit.
		 this.motionY-= 0.03999999910593033D;
	
		}else{
		
		 this.motionY =0;
		 this.posY =(int) this.posY+1;
		}
	
		
		
		// end New for gravity
		
		if (riddenByEntity != null){

	
		if ( isPlayerAccelerating){
			this.velocity += .1d;
		}
		if ( isPlayerBreaking){
			this.velocity -= .1d;
		}
		if (isPlayerTurningRight){
			yaw +=1.5d;
		}
		if (isPlayerTurningLeft){
			yaw -=1.5d;
		}
		
		}
		if ( isPlayerPushingJumpButton ){
			Attribute1-=  1;
			if (Attribute1 < getMinAngle())
			{
				Attribute1 = getMinAngle();
			}
		}else if (isPlayerPushingSprintButton){
			Attribute1+=  1;
			if (Attribute1 > getMaxAngle())
			{
				Attribute1 = getMaxAngle();
			}
		}
		
		//end take user input
		
		// Clamp values to max / min values as needed 
		if (this.velocity> this.getMaxVelocity()){
			this.velocity = this.getMaxVelocity();
		}else if(this.velocity < this.getMaxVelocity()*-1){
			this.velocity=this.getMaxVelocity()*-1; 
		}
		if (this.velocity <0.0001d && this.velocity > 0.0d){
			this.velocity=0d;
			
		}else if (this.velocity >-0.0001d && this.velocity < 0.0d){
			this.velocity=0d;
		}
		if (this.yaw>360 ){
			this.yaw =this.yaw-360;
		}else  if (this.yaw<0 ){
			this.yaw =360-this.yaw;
		}
		//END Clamp values to max / min values as needed
		
		// calc x & Z offsets needed for the given rotation & velocity
		double speedX =(velocity * MathHelper.cos((float) ((yaw+90) * Math.PI / 180.0D)));
		double speedZ= (velocity * MathHelper.sin((float) ((yaw+90)* Math.PI / 180.0D))); 
		//double speedY=0;
		
		
		motionX = speedX;
		motionZ = speedZ;
		this.velocity*=.90;// apply friction
		setRotation(this.yaw, this.rotationPitch);

	
		
		
//		motionY= speedY;
		//setPosition( posX+speedX,posY+motionY, posZ+speedZ);
		moveEntity( motionX,motionY,  motionZ);
		
		

		
		
        ModNetwork.simpleNetworkWrapper.sendToAllAround((new MachineModMessageEntityToClient( this.getEntityId(),this.posX,this.posY,this.posZ,this.yaw,this.Attribute1)), new TargetPoint(worldObj.provider.getDimensionId(), posX, posY, posZ, 80));
	}
	
	public void updateClient(){
		//updateServer();
		
		//this.noClip = true;
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		  
	
//		
//		if(TargetYaw!=yaw){
//			YawTickCount=3;
//		}
//		
		
			this.motionX = (this.TargetposX -this.posX )/(3);
			this.motionY = (this.TargetposY -this.posY )/(3);
			this.motionZ = (this.TargetposZ -this.posZ )/(3);
			if (this.motionX > 1 || this.motionY> 1 || this.motionZ > 1){
				// in cases of desync override the smoothing effect and just put the entity in place
				// this resolves the issue of the entity jumping after placed.
				this.motionX *=3;
				this.motionY *=3;
				this.motionZ *=3;
			}
			
			setPosition( posX+motionX,posY+motionY, posZ+motionZ);

//
//		
//		if(YawTickCount>0){
//			
//			this.rotationYaw += (TargetYaw -yaw)/YawTickCount;
//			YawTickCount--;
//		}
//		if (YawTickCount==0){
//			this.rotationYaw=TargetYaw;
//		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	// override the set position and rotation function to avoid MC from setting the postion of the entity so i can handle it
	// in my network handler ... avoids jitter
	public void func_180426_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_ ,boolean bool){
		
	}
	 @SideOnly(Side.CLIENT)
	    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
	    {
		 
	    }
	 @SideOnly(Side.CLIENT)
	    public void setPositionAndRotation(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
	    {
		 
	    }
	 
	
	@Override
	public void onUpdate(){
	 super.onUpdate();
		if(!worldObj.isRemote){
			//server side
			updateServer();
		
		}else{
			// client

			updateClient();
		}

	}
	
	
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * this.velocity;
			double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * this.velocity;
			this.riddenByEntity.setPosition(this.posX + d0 +this.getMountedXOffset(), this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1+this.getMountedZOffset());
			//this.riddenByEntity.setRotationYawHead(this.yaw);
		}
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub

	}
	
	public float getMaxAngle() {
		return 0;
	}
	

	public float getMinAngle() {
		return 0;
	}
	public double calcOffsetX(double distance){
		return(distance * MathHelper.cos((float) ((this.yaw+90) * Math.PI / 180.0D)));
	}
	public double calcOffsetZ(double distance){
		return (distance * MathHelper.sin((float) ((this.yaw+90)* Math.PI / 180.0D))); 

	}
}
