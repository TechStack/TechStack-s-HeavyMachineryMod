package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageEntityCurrentTargetPosToClientHandler implements IMessageHandler<MachineModMessageEntityCurrentTargetPosToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityCurrentTargetPosToClient message, MessageContext ctx) {
		// LogHelper.info("in machineModMessageEntityToClient Handler");
		// LogHelper.info("Message data" + message);
		// LogHelper.info("on message MachineModMessageEntityToClientHandler");
		if (Minecraft.getInstance().world != null) {
			if (Minecraft.getInstance().world.isRemote) {

				Minecraft.getInstance().addScheduledTask(new Runnable() {
					public void run() {
						processMessage(message);
					}
				});
			}
		}
		return null;
	}

	public void processMessage(MachineModMessageEntityCurrentTargetPosToClient message) {
		if (message != null) {
			if (Minecraft.getInstance().world != null) {
				if (Minecraft.getInstance().player != null) {
					Entity entity = Minecraft.getInstance().world.getEntityByID(message.entityid);

					if (entity != null) {

						if (entity instanceof EntityMachineModRideable) {
							// its ridden by this player (avoid some hacks)
							((EntityExcavator) entity).currPosX = message.currPosX;
							((EntityExcavator) entity).currPosY = message.currPosY;
							((EntityExcavator) entity).currPosZ = message.currPosZ;
							((EntityExcavator) entity).angleArm1 = message.angleArm1;
							((EntityExcavator) entity).angleArm2 = message.angleArm2;
							((EntityExcavator) entity).angleArm3 = message.angleArm3;
							((EntityExcavator) entity).mainBodyRotation = message.mainBodyRotation;
							// LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER"
							// );
						}
					}
				}
			}
		}
	}

}
