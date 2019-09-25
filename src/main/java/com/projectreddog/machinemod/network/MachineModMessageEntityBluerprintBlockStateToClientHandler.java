package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageEntityBluerprintBlockStateToClientHandler implements IMessageHandler<MachineModMessageEntityBluerprintBlockStateToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageEntityBluerprintBlockStateToClient message, MessageContext ctx) {
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

	public void processMessage(MachineModMessageEntityBluerprintBlockStateToClient message) {
		if (message != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			if (Minecraft.getMinecraft().world != null) {
				TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(x, y, z));
				if (te instanceof TileEntityTowerCrane) {
					TileEntityTowerCrane tetc = (TileEntityTowerCrane) te;
					tetc.BlockBluePrintArray = message.blockBlueprintArray;

					tetc.dx = message.blockBlueprintArray.length;
					tetc.dy = message.blockBlueprintArray[0].length;
					tetc.dz = message.blockBlueprintArray[0][0].length;

				}
			}

		}

	}

}
