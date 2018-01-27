package com.projectreddog.machinemod.entity.ai;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.BlockPos;

public class EntityAiNearestAttackablePlayerInDarkWithExp<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {

	protected final Class<T> targetClass;
	private final int targetChance;
	/** Instance of EntityAINearestAttackableTargetSorter. */
	protected final EntityAINearestAttackableTarget.Sorter sorter;
	protected final Predicate<? super T> targetEntitySelector;
	protected T targetEntity;

	public EntityAiNearestAttackablePlayerInDarkWithExp(EntityCreature creature, Class classTarget) {
		this(creature, classTarget, true);
		// TODO Auto-generated constructor stub
	}

	public EntityAiNearestAttackablePlayerInDarkWithExp(EntityCreature creature, Class<T> classTarget, boolean checkSight) {
		this(creature, classTarget, checkSight, false);
	}

	public EntityAiNearestAttackablePlayerInDarkWithExp(EntityCreature creature, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
		this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate) null);
	}

	public EntityAiNearestAttackablePlayerInDarkWithExp(EntityCreature creature, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate<? super T> targetSelector) {
		super(creature, classTarget, chance, checkSight, onlyNearby, targetSelector);
		this.targetClass = classTarget;
		this.targetChance = chance;
		this.sorter = new EntityAINearestAttackableTarget.Sorter(creature);
		this.setMutexBits(1);
		this.targetEntitySelector = new Predicate<T>() {
			public boolean apply(@Nullable T p_apply_1_) {
				if (p_apply_1_ == null) {
					return false;
				} else if (targetSelector != null && !targetSelector.apply(p_apply_1_)) {
					return false;
				} else {
					return !EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : EntityAiNearestAttackablePlayerInDarkWithExp.this.isSuitableTarget(p_apply_1_, false);
				}
			}
		};
	}

	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.targetEntity);

		// super.startExecuting();
	}

	public boolean shouldExecute() {
		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
			return false;
		} else if (this.targetClass != EntityPlayer.class && this.targetClass != EntityPlayerMP.class) {
			List<T> list = this.taskOwner.world.<T> getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

			if (list.isEmpty()) {
				return false;
			} else {
				Collections.sort(list, this.sorter);
				this.targetEntity = list.get(0);
				return true;
			}
		} else {
			T ep = (T) this.taskOwner.world.getNearestAttackablePlayer(this.taskOwner.posX, this.taskOwner.posY + (double) this.taskOwner.getEyeHeight(), this.taskOwner.posZ, this.getTargetDistance(), this.getTargetDistance(), new Function<EntityPlayer, Double>() {
				@Nullable
				public Double apply(@Nullable EntityPlayer p_apply_1_) {
					ItemStack itemstack = p_apply_1_.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

					if (itemstack.getItem() == Items.SKULL) {
						int i = itemstack.getItemDamage();
						boolean flag = EntityAiNearestAttackablePlayerInDarkWithExp.this.taskOwner instanceof EntitySkeleton && i == 0;
						boolean flag1 = EntityAiNearestAttackablePlayerInDarkWithExp.this.taskOwner instanceof EntityZombie && i == 2;
						boolean flag2 = EntityAiNearestAttackablePlayerInDarkWithExp.this.taskOwner instanceof EntityCreeper && i == 4;

						if (flag || flag1 || flag2) {
							return 0.5D;
						}
					}

					return 1.0D;
				}
			}, (Predicate<EntityPlayer>) this.targetEntitySelector);
			if (ep instanceof EntityPlayer) {
				EntityPlayer entPlayer = (EntityPlayer) ep;
				if (entPlayer.experienceTotal > 0) {
					if (entPlayer.world.getLight(new BlockPos(entPlayer.posX, entPlayer.posY, entPlayer.posZ)) == 0) {
						this.targetEntity = ep;
					}
				}
			}

			return this.targetEntity != null;
		}
	}
}
