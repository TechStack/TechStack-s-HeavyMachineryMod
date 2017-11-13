package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageEntityInventoryChangedToClientHandler implements IMessageHandler<MachineModMessageEntityInventoryChangedToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityInventoryChangedToClient message, MessageContext ctx) {
		// LogHelper.info("on message MachineModMessageEntityInventoryChangedToClient");
		if (Minecraft.getMinecraft().world != null) {
			if (Minecraft.getMinecraft().world.isRemote) {

				Minecraft.getMinecraft().addScheduledTask(new Runnable() {
					public void run() {
						processMessage(message);
					}
				});
			}
		}
		return null;
	}

	public void processMessage(MachineModMessageEntityInventoryChangedToClient message) {
		if (message != null) {
			if (Minecraft.getMinecraft().world != null) {
				if (Minecraft.getMinecraft().player != null) {

					Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityid);

					if (entity != null) {

						if (entity instanceof EntityMachineModRideable) {
							// // its ridden by this player (avoid some hacks)
							// ((EntityMachineModRideable) entity).TargetposX =
							// message.posX;
							// ((EntityMachineModRideable) entity).TargetposY =
							// message.posY;
							// ((EntityMachineModRideable) entity).TargetposZ =
							// message.posZ;
							// ((EntityMachineModRideable) entity).rotationYaw =
							// message.yaw;
							// ((EntityMachineModRideable) entity).yaw =
							// message.yaw;
							// ((EntityMachineModRideable) entity).Attribute1 =
							// message.Attribute1;
							//
							// //
							// LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER"
							// // );
							// if (entity instanceof EntityLoader) {
							// EntityLoader eL = (EntityLoader) entity;
							// eL.setInventorySlotContents(message.slot,
							// message.is);
							// }
							// if (entity instanceof EntityDumpTruck) {
							// EntityDumpTruck eDT = (EntityDumpTruck) entity;
							EntityMachineModRideable eMMR = (EntityMachineModRideable) entity;
							eMMR.setInventorySlotContents(message.slot, message.is);
							// }

						}
					}
				}
			}
		}
	}

}
