package com.projectreddog.machinemod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

public class BlockMachineModFalling  extends BlockMachineMod {
	public static boolean fallInstantly;

	private float motionX=0f;
	private float motionZ=0f;
	private float motionY=0f;

	public BlockMachineModFalling()
	{
		super();

	}
	
	public int getRandomizedUpdateTime(World world){
		// the following line may be needed ( config option perhaps) to slow the update of the falling blocks
		//return  this.tickRate(world)*5 +   (1 + (int)(Math.random()*10));
		return  this.tickRate(world);
	}
	
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	onBlockAdded(worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
    
    /// my 1.7 version 
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleUpdate(new BlockPos( x, y, z), this,getRandomizedUpdateTime(world) );
		
	}
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	onNeighborBlockChange( worldIn, pos.getX(), pos.getY(), pos.getZ(),  neighborBlock);
    }
    
    /// my 1.7 version     
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		world.scheduleUpdate(new BlockPos( x, y, z), this,getRandomizedUpdateTime(world));
		
		
		
		if (world.getBlockState(new BlockPos( x, y+1, z)).getBlock()== ModBlocks.machinemodblastedstone){
			world.scheduleUpdate(new BlockPos( x, y+1, z), this,getRandomizedUpdateTime(world));
		}
	}



	/**
	 * Ticks the block if it's been scheduled
	 */
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	updateTick(worldIn, pos.getX(), pos.getY(), pos.getZ(),  rand);

    }
    
    // my 1.7 version
	public void updateTick(World world, int x, int y, int z, Random p_149674_5_)
	{
		if (!world.isRemote)
		{
			this.fall(world, x, y, z);
		}
	}

	private void fall(World world, int x, int y, int z)
	{
		if (canFallMore(world, x, y , z) && y >= 0)
		{
			byte b0 = 32;
			
			BlockPos pos = new BlockPos (x, y , z); 
			if (!fallInstantly && world.isAreaLoaded(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0)))
			{
				if (!world.isRemote)
				{
					
					//LogHelper.info("X:" + this.motionX + " Y:" + this.motionY + " Z:"+ this.motionZ);
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.getDefaultState());
					//TS removed
					// this.func_149829_a(entityfallingblock);
					
					entityfallingblock.motionX+=this.motionX;
					entityfallingblock.motionY+=this.motionY;
					entityfallingblock.motionZ+=this.motionZ;
				
					//world.setBlockToAir(x, y, z);


					world.spawnEntityInWorld(entityfallingblock);
				}
			}
			else
			{
				
				BlockPos bp = new BlockPos(x, y, z);
				world.setBlockToAir(bp);

				while (canFallMore(world, x, y - 1, z) && y > 0)
				{
					--y;
				}

				if (y > 0)
				{
					 bp = new BlockPos(x, y, z);

					world.setBlockState(bp, this.getDefaultState());
				}
			}
		}
	}
	//TS removed
	//protected void func_149829_a(EntityFallingBlock p_149829_1_) {}


	public  boolean canFallMore(World world, int x, int y, int z)
	{
		this.motionX =0;
		this.motionZ =0;
		this.motionY=0;
		Block block2 ;
		BlockPos bp = new BlockPos(x, y-1, z);
		Block block = world.getBlockState(bp).getBlock();
		if (block== Blocks.air ||block == Blocks.fire)// test fall down this x z
		{

			return true;
		}

		for (int i=-1; i<2 ; i++){
			for (int j=-1; j<2 ; j++){

				if ((MathHelper.abs_int(i) ==1 && j==0) || (i ==0 && MathHelper.abs_int(j)==1) )
				{
					
					bp = new BlockPos(x+i, y, z+j);
					 block = world.getBlockState(bp).getBlock();
							
					if (block== Blocks.air ||block == Blocks.fire)// test fall down this x+1 z
					{
						

						
						bp = new BlockPos(x+i, y-1, z+j);
						 block2 = world.getBlockState(bp).getBlock();
						 
						if (block2== Blocks.air ||block2 == Blocks.fire)
						{

//							this.motionX =i*1f;
//							this.motionZ =j*1f;
//							this.motionY =1f;
							bp = new BlockPos(x, y, z);
							
							
							world.setBlockToAir(bp);
							

							bp = new BlockPos(x+i, y-1, z+j);
							world.setBlockState(bp, this.getDefaultState());
							return false;
						}		
					}
				}
			}
		}


		return false;

	}







	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World world)
	{
		return 2;
	}

}
