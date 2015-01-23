package com.projectreddog.machinemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;

public class MachineModMessageEntityInventoryChangedToClientHandler implements IMessageHandler<MachineModMessageEntityInventoryChangedToClient, IMessage> {

	@Override
	public IMessage onMessage(MachineModMessageEntityInventoryChangedToClient message, MessageContext ctx) {
		// LogHelper.info("in machineModMessageEntityToClient Handler");
		// LogHelper.info("Message data" + message);
		if (message != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityid);

				if (entity != null) {

					if (entity instanceof EntityMachineModRideable) {
//						// its ridden by this player (avoid some hacks)
//						((EntityMachineModRideable) entity).TargetposX = message.posX;
//						((EntityMachineModRideable) entity).TargetposY = message.posY;
//						((EntityMachineModRideable) entity).TargetposZ = message.posZ;
//						((EntityMachineModRideable) entity).rotationYaw = message.yaw;
//						((EntityMachineModRideable) entity).yaw = message.yaw;
//						((EntityMachineModRideable) entity).Attribute1 = message.Attribute1;
//
//						// LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER"
//						// );
						if (entity instanceof EntityLoader){
							EntityLoader eL = (EntityLoader) entity;
							eL.setInventorySlotContents(message.slot, message.is);
						}
						if (entity instanceof EntityDumpTruck){
							EntityDumpTruck eDT = (EntityDumpTruck) entity;
							eDT.setInventorySlotContents(message.slot, message.is);
						}
						
					}
				}
			}
		}
		return null;
	}

}
