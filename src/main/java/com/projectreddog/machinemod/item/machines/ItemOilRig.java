package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

public class ItemOilRig extends ItemMachineMod {

	public ModelTransportable mt;

	public ItemOilRig() {
		super();
		this.setUnlocalizedName("oilrig");
		this.maxStackSize = 1;

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{

			if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(pos), BiomeDictionary.Type.OCEAN) && pos.getY()> 60 && world.isAirBlock(pos.up())  && world.getBlockState(pos).getBlock()==Blocks.water){
				
				// LogHelper.info("Item used on loader!");
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				EntityMachineModRideable entityOilRig = getEntityToSpawn(world);
				entityOilRig.setPosition(x + .5d, y + 1.0d, z + .5d);
				entityOilRig.prevPosX = x + .5d;
				entityOilRig.prevPosY = y + 1.0d;
				entityOilRig.prevPosZ = z + .5d;
				result = world.spawnEntityInWorld(entityOilRig);
				// LogHelper.info("Spawn entity resutl:" + result );
				if (result && !player.capabilities.isCreativeMode) {
					stack.stackSize--;
				}
			}
			else{
				player.addChatComponentMessage(new ChatComponentText("You can only place an oil rig on an ocean's surface!"));

			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityOilRig(world);

	}

}
