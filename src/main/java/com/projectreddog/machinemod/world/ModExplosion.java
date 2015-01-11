package com.projectreddog.machinemod.world;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Lists;
import com.projectreddog.machinemod.init.ModBlocks;


public class ModExplosion extends Explosion {

	
    private World worldObj;
    private Random explosionRNG = new Random();
    private final double explosionX;
    private final double explosionY;
    private final double explosionZ;
    private final Entity exploder;
    private final float explosionSize;
    private List affectedBlockPositions;
    public boolean isSmoking;

	public ModExplosion(World world, Entity exploder, double explosionX,	double explosionY, double explosionZ, float explosionSize) {
		super(world, exploder, explosionX, explosionY, explosionZ, explosionSize,  false,true );

		worldObj=world;
		
		
        this.exploder = exploder;
        this.affectedBlockPositions = Lists.newArrayList();
		this.explosionX =explosionX;
		this.explosionY = explosionY;
		this.explosionZ = explosionZ;
        this.explosionSize = explosionSize;
        this.isSmoking = true;
	}
	

	@Override
	 public void doExplosionB(boolean p_77279_1_)
	    {

		  this.affectedBlockPositions=super.func_180343_e();
	        this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

	        if (this.explosionSize >= 2.0F && this.isSmoking)
	        {
	        	this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D,new int[0]);
	            
	            

	        }
	        else
	        {
	        	
	            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D,new int[0]);
	        }

	        Iterator iterator;
	        BlockPos blockpos;
	        int i;
	        int j;
	        int k;
	        Block block;

	        if (this.isSmoking)
	        {
	            iterator = this.affectedBlockPositions.iterator();

	            while (iterator.hasNext())
	            {
	            	blockpos = (BlockPos)iterator.next();
	                i = blockpos.getX();
	                j = blockpos.getY();
	                k = blockpos.getZ();
	                block = this.worldObj.getBlockState(blockpos).getBlock();

	                if (p_77279_1_)
	                {
	                    double d0 = (double)((float)i + this.worldObj.rand.nextFloat());
	                    double d1 = (double)((float)j + this.worldObj.rand.nextFloat());
	                    double d2 = (double)((float)k + this.worldObj.rand.nextFloat());
	                    double d3 = d0 - this.explosionX;
	                    double d4 = d1 - this.explosionY;
	                    double d5 = d2 - this.explosionZ;
	                    double d6 = (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
	                    d3 /= d6;
	                    d4 /= d6;
	                    d5 /= d6;
	                    double d7 = 0.5D / (d6 / (double)this.explosionSize + 0.1D);
	                    d7 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
	                    d3 *= d7;
	                    d4 *= d7;
	                    d5 *= d7;
	                    this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.explosionX * 1.0D) / 2.0D, (d1 + this.explosionY * 1.0D) / 2.0D, (d2 + this.explosionZ * 1.0D) / 2.0D, d3, d4, d5);
	                    this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
	                }

	                if (block.getMaterial() != Material.air)
	                {
	                	
	                	//TS DO NOT DROP BLOCKS !
//	                    if (block.canDropFromExplosion(this))
//	                    {
//	                        block.dropBlockAsItemWithChance(this.worldObj, i, j, k, this.worldObj.getBlockMetadata(i, j, k), 1.0F / this.explosionSize, 0);
//	                    }
	                    // TS change next line where it actually breaks the block instead do my call to set the block to the proper type
	                    //block.onBlockExploded(this.worldObj, i, j, k, this);
	                    
	                    changeBlockType(i,j,k, block);
	                }
	            }
	        }

	
	    }


	private void changeBlockType( int x, int y, int z,
			Block block) {
				
		if (block ==ModBlocks.machineexplosivepackeddrilledstone){
			// do the explosion!  if it's blasted stone so we can propigate the explosion on to the next block !
			block.onBlockExploded(this.worldObj, new BlockPos(x, y,z), this);
		}else {
			
			  if (!this.worldObj.isRemote)
              {
				  
				  //only spawn if its air above the block
//				  if (this.worldObj.isAirBlock(x,y+1, z)||this.worldObj.isAirBlock(x,y+2, z))
//				  {
//	                  EntityFallingBlock entityfallingblock = new EntityFallingBlock(this.worldObj, (double)((float)x + 0.5F), (double)((float)y + 0.7F), (double)((float)z + 0.5F), ModBlocks.machinemodblastesStone, 0);     
//	                  entityfallingblock.motionY=.6d;
//	                  this.worldObj.spawnEntityInWorld(entityfallingblock);
//				  }
    
  //              	 this.worldObj.setBlockToAir(x,y,z); 

                 this.worldObj.setBlockState(new BlockPos(x, y,z), ModBlocks.machinemodblastedstone.getDefaultState()); 
                  
              }
			  
		}	
		
	}


}
