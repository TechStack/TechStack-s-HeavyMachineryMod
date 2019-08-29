package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageTETowerCranePosToClientHandler implements IMessageHandler<MachineModMessageTETowerCranePosToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageTETowerCranePosToClient message, MessageContext ctx) {
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

	public void processMessage(MachineModMessageTETowerCranePosToClient message) {
		if (message != null) {
			if (Minecraft.getMinecraft().world != null) {
				if (Minecraft.getMinecraft().player != null) {

					TileEntity TileEntity = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));

					if (TileEntity != null) {

						if (TileEntity instanceof TileEntityTowerCrane) {

							TileEntityTowerCrane tetc = (TileEntityTowerCrane) TileEntity;
							tetc.state = message.state;
							tetc.armRotation = message.armRotation;
							tetc.gantryPos = message.gantryPos;
							tetc.wenchPos = message.wenchPos;
							tetc.targetArmRotation = message.targetArmRotation;
							tetc.targetGantryPos = message.targetGantryPos;
							tetc.targetWenchPos = message.targetWenchPos;
							tetc.currentX = message.currentX;
							tetc.currentY = message.currentY;
							tetc.currentZ = message.currentZ;

						}
					}
				}
			}
		}
	}

}
