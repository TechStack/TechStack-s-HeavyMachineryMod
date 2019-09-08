package com.projectreddog.machinemod.network;

import java.util.UUID;

import com.projectreddog.machinemod.tileentities.TileEntityHoloScanner;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageBlockBlueprintSaveToServerHandler implements IMessageHandler<MachineModMessageBlockBlueprintSaveToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageBlockBlueprintSaveToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageBlockBlueprintSaveToServer message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().player;

		if (entity != null) {

			// SAVE the file here please.
			UUID tmpUUID = entity.getUniqueID();

			BlockPos bp = new BlockPos(message.pos1X, message.pos1Y, message.pos1Z);

			TileEntity te = entity.world.getTileEntity(bp);

			if (te instanceof TileEntityHoloScanner) {
				TileEntityHoloScanner tehs = (TileEntityHoloScanner) te;

				LogHelper.info("Calling save");
				BlockBlueprintHelper.ScanBlocks(entity.world, tehs.point1, tehs.point2, tmpUUID.toString() + "~" + message.fileName + ".blockblueprint");
				LogHelper.info("Save complete");

			}

		}
	}
}
