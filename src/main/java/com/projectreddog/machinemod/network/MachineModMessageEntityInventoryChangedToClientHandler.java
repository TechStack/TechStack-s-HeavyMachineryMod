package com.projectreddog.machinemod.network;

import javax.xml.ws.handler.MessageContext;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class MachineModMessageEntityInventoryChangedToClientHandler implements IMessageHandler<MachineModMessageEntityInventoryChangedToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityInventoryChangedToClient message, MessageContext ctx) {
		// LogHelper.info("on message MachineModMessageEntityInventoryChangedToClient");
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

	public void processMessage(MachineModMessageEntityInventoryChangedToClient message) {
		if (message != null) {
			if (Minecraft.getInstance().world != null) {
				if (Minecraft.getInstance().player != null) {

					Entity entity = Minecraft.getInstance().world.getEntityByID(message.entityid);

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
							if (message.is.isEmpty()) {
								// updated inbound itemstack is empty so remove whatever is in this slot!
								eMMR.inventory.extractItem(message.slot, eMMR.inventory.getStackInSlot(message.slot).getCount(), false);

							} else if (!message.is.areItemStacksEqual(eMMR.inventory.getStackInSlot(message.slot), message.is)) {
								eMMR.inventory.extractItem(message.slot, eMMR.inventory.getStackInSlot(message.slot).getCount(), false);
								eMMR.inventory.insertItem(message.slot, message.is, false);
							}
							// }

						}
					}
				}
			}
		}
	}

}
