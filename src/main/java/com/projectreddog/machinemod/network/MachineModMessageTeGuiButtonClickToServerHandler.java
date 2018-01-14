package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.iface.ITEGuiButtonHandler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageTeGuiButtonClickToServerHandler implements IMessageHandler<MachineModMessageTEGuiButtonClickToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageTEGuiButtonClickToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageTEGuiButtonClickToServer message, MessageContext ctx) {

		TileEntity te = ctx.getServerHandler().player.world.getTileEntity(new BlockPos(message.posX, message.posY, message.posZ));

		if (te != null) {

			if (te instanceof ITEGuiButtonHandler) {
				((ITEGuiButtonHandler) te).HandleGuiButton(message.buttionid, ctx.getServerHandler().player);

			}
		}
	}
}
