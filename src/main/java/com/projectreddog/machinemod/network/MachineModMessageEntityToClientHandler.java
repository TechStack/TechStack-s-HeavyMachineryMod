package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageEntityToClientHandler implements IMessageHandler<MachineModMessageEntityToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityToClient message, MessageContext ctx) {
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

	public void processMessage(MachineModMessageEntityToClient message) {
		if (message != null) {
			if (Minecraft.getInstance().world != null) {
				if (Minecraft.getInstance().player != null) {
					Entity entity = Minecraft.getInstance().world.getEntityByID(message.entityid);

					if (entity != null) {

						if (entity instanceof EntityMachineModRideable) {
							// its ridden by this player (avoid some hacks)
							((EntityMachineModRideable) entity).TargetposX = message.posX;
							((EntityMachineModRideable) entity).TargetposY = message.posY;
							((EntityMachineModRideable) entity).TargetposZ = message.posZ;
							((EntityMachineModRideable) entity).rotationYaw = message.yaw;
							((EntityMachineModRideable) entity).yaw = message.yaw;
							((EntityMachineModRideable) entity).Attribute1 = message.Attribute1;
							((EntityMachineModRideable) entity).Attribute2 = message.Attribute2;
							((EntityMachineModRideable) entity).currentFuelLevel = message.currentFuelLevel;
							((EntityMachineModRideable) entity).clientTicksSinceLastServerPulse = 0;
							// LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER" + ((EntityMachineModRideable) entity).TargetposX);

							// LogHelper.info("message" + message.posX + "target" + ((EntityMachineModRideable) entity).TargetposX + "actual" + ((EntityMachineModRideable) entity).posX);
							// LogHelper.info("SHOULD RENDER" + ((EntityMachineModRideable) entity).shouldRenderInPass(0));
							// LogHelper.info("CHUNK Added?" + ((EntityMachineModRideable) entity).addedToChunk);

						}
					} else {
						// LogHelper.info("RECIEVED NULL ENTITY PACKET FROM SERVER" + message.entityid);

					}
				}
			}
		}
	}

}
