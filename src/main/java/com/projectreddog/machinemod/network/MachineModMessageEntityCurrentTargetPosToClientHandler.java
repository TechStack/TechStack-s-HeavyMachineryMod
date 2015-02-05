package com.projectreddog.machinemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;

public class MachineModMessageEntityCurrentTargetPosToClientHandler implements IMessageHandler<MachineModMessageEntityCurrentTargetPosToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityCurrentTargetPosToClient message, MessageContext ctx) {
		// LogHelper.info("in machineModMessageEntityToClient Handler");
		// LogHelper.info("Message data" + message);
		// LogHelper.info("on message MachineModMessageEntityToClientHandler");
		if (Minecraft.getMinecraft().theWorld != null) {
			if (Minecraft.getMinecraft().theWorld.isRemote) {

				Minecraft.getMinecraft().addScheduledTask(new Runnable() {
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
			if (Minecraft.getMinecraft().theWorld != null) {
				if (Minecraft.getMinecraft().thePlayer != null) {
					Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityid);

					if (entity != null) {

						if (entity instanceof EntityMachineModRideable) {
							// its ridden by this player (avoid some hacks)
							((EntityExcavator) entity).currPosX = message.currPosX;
							((EntityExcavator) entity).currPosY = message.currPosY;
							((EntityExcavator) entity).currPosZ = message.currPosZ;

							// LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER"
							// );
						}
					}
				}
			}
		}
	}

}
