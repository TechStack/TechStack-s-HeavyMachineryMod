package com.projectreddog.machinemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageTEInventoryChangedToClientHandler implements IMessageHandler<MachineModMessageTEInventoryChangedToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageTEInventoryChangedToClient message, MessageContext ctx) {
		// LogHelper.info("on message MachineModMessageEntityInventoryChangedToClient");
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

	public void processMessage(MachineModMessageTEInventoryChangedToClient message) {
		if (message != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				if (Minecraft.getMinecraft().thePlayer != null) {

					TileEntity TileEntity = Minecraft.getMinecraft().theWorld.getTileEntity(new BlockPos(message.x, message.y, message.z));

					if (TileEntity != null) {

						if (TileEntity instanceof IInventory) {
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
							IInventory ii = (IInventory) TileEntity;
							ii.setInventorySlotContents(message.slot, message.is);
							// }

						}
					}
				}
			}
		}
	}

}
