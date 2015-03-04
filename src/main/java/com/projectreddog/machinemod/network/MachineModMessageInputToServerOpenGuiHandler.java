package com.projectreddog.machinemod.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

public class MachineModMessageInputToServerOpenGuiHandler implements IMessageHandler<MachineModMessageInputToServerOpenGui, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageInputToServerOpenGui message, final MessageContext ctx) {

		ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageInputToServerOpenGui message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityMachineModRideable) {
				if (((EntityMachineModRideable) entity).riddenByEntity == ctx.getServerHandler().playerEntity) {
					// its ridden by this player (avoid some hacks)
					if (message.isOpenGui) {

						if (entity instanceof EntityDumpTruck) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_DUMP_TRUCK, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityLoader) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_LOADER, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityTractor) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_TRACTOR, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityWideBedTruck) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_WIDEBEDTRUCK, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityCombine) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_COMBINE, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityPaver) {
							((EntityPlayer) entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_PAVER, entity.worldObj, (int) entity.getEntityId(), (int) 0, (int) 0);
						}

					}

				}
			}
		}
	}
}
