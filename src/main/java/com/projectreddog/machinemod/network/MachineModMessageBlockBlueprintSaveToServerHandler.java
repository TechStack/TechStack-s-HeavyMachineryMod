package com.projectreddog.machinemod.network;

import java.util.UUID;

import com.projectreddog.machinemod.utility.BlockBlueprintHelper;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.Entity;
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

			BlockPos bp1 = BlockBlueprintHelper.Point1Map.get(tmpUUID);
			LogHelper.info(bp1);
			BlockPos bp2 = BlockBlueprintHelper.Point2Map.get(tmpUUID);
			LogHelper.info(bp2);
			LogHelper.info("Calling save");
			BlockBlueprintHelper.ScanBlocks(entity.world, bp1, bp2, tmpUUID.toString() + "-" + message.fileName + ".blockblueprint");
			LogHelper.info("Save complete");

		}
	}
}
