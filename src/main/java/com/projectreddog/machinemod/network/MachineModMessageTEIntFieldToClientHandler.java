package com.projectreddog.machinemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageTEIntFieldToClientHandler implements IMessageHandler<MachineModMessageTEIntFieldToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageTEIntFieldToClient message, MessageContext ctx) {
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

	public void processMessage(MachineModMessageTEIntFieldToClient message) {
		if (message != null) {
			if (Minecraft.getInstance().world != null) {
				if (Minecraft.getInstance().player != null) {

					TileEntity TileEntity = Minecraft.getInstance().world.getTileEntity(new BlockPos(message.x, message.y, message.z));

					if (TileEntity != null) {

						if (TileEntity instanceof IInventory) {

							IInventory ii = (IInventory) TileEntity;
							ii.setField(message.field, message.value);

						}
					}
				}
			}
		}
	}

}
