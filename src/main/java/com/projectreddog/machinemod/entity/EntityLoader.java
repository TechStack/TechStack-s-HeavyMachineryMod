

package com.projectreddog.machinemod.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

public class EntityLoader extends EntityMachineModRideable implements IInventory {


	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	private ItemStack[] inventory;

	public EntityLoader(World world){
		super(world);

		setSize(2.5f, 2);
		inventory = new ItemStack[54];
	}



	@Override
	public void onUpdate(){
		super.onUpdate();
		if (!worldObj.isRemote){
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
			collidedEntitiesInList(list); 
		}


	}

	public AxisAlignedBB getBoundingBox(){
		return boundingBox;
	}

	private void collidedEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity)par1List.get(i);
			if (entity != null){
				if (entity instanceof EntityItem){
					ItemStack is = ((EntityItem)entity).getEntityItem().copy();

					ItemStack is1 = addToinventory(is);

					if (is1 != null && is1.stackSize != 0)
					{
						((EntityItem) entity).setEntityItemStack(is1);
					}else{
						entity.setDead();
					}
				}
			}
		}  
	}


	//adds to this objects inventory if it can 
	// any remaining amount will be returned 
	private ItemStack addToinventory( ItemStack is)
	{
		int i = getSizeInventory();

		for (int j = 0; j < i && is != null && is.stackSize > 0; ++j)
		{
			if (is !=null){

				if (getStackInSlot(j) != null){
					if (getStackInSlot(j).getItem()== is.getItem() ){
						//same item remove from is put into slot any amt not to excede stack max
						if ( getStackInSlot(j).stackSize< getStackInSlot(j).getMaxStackSize() ){
							// we have room to add to this stack
							if (is.stackSize <=getStackInSlot(j).getMaxStackSize()-getStackInSlot(j).stackSize )
							{
								///all of the stack will fit in this slot do so.

								setInventorySlotContents(j, new ItemStack(getStackInSlot(j).getItem(), getStackInSlot(j).stackSize +is.stackSize) );
								is =null;
							}else{
								// we have more 
								int countRemain = is.stackSize-(getStackInSlot(j).getMaxStackSize()-getStackInSlot(j).stackSize);
								setInventorySlotContents(j, new ItemStack(is.getItem(), getStackInSlot(j).getMaxStackSize()) );
								is.stackSize=countRemain;
							}

						}
					}
				}else{
					//nothign in slot so set contents
					setInventorySlotContents(j, new ItemStack(is.getItem(), is.stackSize) );
					is =null;
				}
				
			}

		}


		return null;

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
	public boolean interactFirst(EntityPlayer player) // should be proper class
	{

		super.interactFirst(player);
		//LogHelper.info("TEST");
		// moved open gui call to the networkhandler
		//player.openGui(MachineMod.instance, Reference.GUI_LOADER, worldObj, (int) this.getEntityId(), (int) 0,(int) 0);
		return true;
	}


	@Override
	public Item getItemToBeDropped()
	{

		// need to drop additional items from the inventory of the item
		Random rand = new Random();


		for (int i = 0; i < this.getSizeInventory(); i++) {
			ItemStack item = this.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(worldObj,
						posX + rx, posY + ry, posZ + rz,
						item);

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				worldObj.spawnEntityInWorld(entityItem);
				//item.stackSize = 0;
				this.setInventorySlotContents(i, null);

			}
		}

		return ModItems.loader;
	}


	@Override 
	public int getSizeInventory(){
		return inventory.length; 
	}

	@Override
	public ItemStack getStackInSlot(int slot){
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack){
		inventory[slot]=stack;
		if (stack != null && stack.stackSize> getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
	}
	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null ){
			if (stack.stackSize <= amt){
				setInventorySlotContents(slot, null);
			}else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0){
					setInventorySlotContents(slot, null);
				}

			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null){
			setInventorySlotContents(slot,null);
		}
		return stack;
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// check if the player is near the entity.
		return player.getDistanceSq(posX , posY , posZ ) < 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory",tagCompound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openInventory(EntityPlayer playerIn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < inventory.length; ++i) {
			inventory[i] = null;
		}
	}
	
	@Override
	public float getMaxAngle() {
		return 0;
	}
	
	@Override
	public float getMinAngle() {
		return -90;
	}

}

