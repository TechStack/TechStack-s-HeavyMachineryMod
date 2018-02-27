package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityContinuousMiner;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.entity.EntityTrackLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageInputToServerOpenGuiHandler implements IMessageHandler<MachineModMessageInputToServerOpenGui, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageInputToServerOpenGui message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageInputToServerOpenGui message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().player.world.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityMachineModRideable) {
				if (((EntityMachineModRideable) entity).getControllingPassenger() == ctx.getServerHandler().player) {
					// its ridden by this player (avoid some hacks)
					if (message.isOpenGui) {

						if (entity instanceof EntityDumpTruck) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_DUMP_TRUCK, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityLoader) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_LOADER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityTractor) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_TRACTOR, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntitySemiTractor) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_WIDEBEDTRUCK, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityCombine) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_COMBINE, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityPaver) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_PAVER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityGrader) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_GRADER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityBagger) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_BAGGER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityExcavator) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_EXCAVATOR, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityChopper) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_CHOPPER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityContinuousMiner) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_CONTINUOUSMINER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						} else if (entity instanceof EntityTrackLoader) {
							((EntityPlayer) entity.getControllingPassenger()).openGui(MachineMod.instance, Reference.GUI_TRACK_LOADER, entity.world, (int) entity.getEntityId(), (int) 0, (int) 0);
						}

					}

				}
			}
		}
	}
}
