package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.tileentities.TileEntityCrate;

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

							IInventory ii = (IInventory) TileEntity;
							ii.setInventorySlotContents(message.slot, message.is);
							// Handle Extra data
							if (TileEntity instanceof TileEntityCrate) {
								// cartes use extraint to store the AmtInReserve to send to the client.
								((TileEntityCrate) TileEntity).AmtInReserve = message.extraInt;
							}

						}
					}
				}
			}
		}
	}

}
