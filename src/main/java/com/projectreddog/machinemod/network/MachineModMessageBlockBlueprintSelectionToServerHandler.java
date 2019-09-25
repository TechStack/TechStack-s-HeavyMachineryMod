package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageBlockBlueprintSelectionToServerHandler implements IMessageHandler<MachineModMessageBlockBlueprintSelectionToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageBlockBlueprintSelectionToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageBlockBlueprintSelectionToServer message, MessageContext ctx) {

		EntityPlayerMP player = ctx.getServerHandler().player;

		if (player != null) {
			BlockPos bp = new BlockPos(message.x, message.y, message.z);
			TileEntity te = player.world.getTileEntity(bp);
			if (te instanceof TileEntityTowerCrane) {
				TileEntityTowerCrane tetc = (TileEntityTowerCrane) te;
				tetc.setFileName(message.fileName);

				if (!tetc.isRunning()) {
					// start this thing if it is not running.
					// tetc.setRunning(true);
				}

				LogHelper.info(" file set to :" + tetc.getFileName());
			}
		}
	}
}
