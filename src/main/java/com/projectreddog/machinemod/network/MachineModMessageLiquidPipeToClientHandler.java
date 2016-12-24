package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageLiquidPipeToClientHandler implements IMessageHandler<MachineModMessageLiquidPipeToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageLiquidPipeToClient message, MessageContext ctx) {
		// LogHelper.info("in machineModMessageEntityToClient Handler");
		// LogHelper.info("Message data" + message);
		// LogHelper.info("on message MachineModMessageEntityToClientHandler");
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

	public void processMessage(MachineModMessageLiquidPipeToClient message) {
		if (message != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				if (Minecraft.getMinecraft().thePlayer != null) {
					TileEntity entity = Minecraft.getMinecraft().theWorld.getTileEntity(new BlockPos(message.currPosX, message.currPosY, message.currPosZ));
					if (entity != null) {
						if (entity instanceof TileEntityLiquidPipe) {
							if (message.fluidID == -1) {
								((TileEntityLiquidPipe) entity).setFluid(null);
							} else {
								((TileEntityLiquidPipe) entity).setFluid(new FluidStack(FluidRegistry.getFluid(message.fluidID), message.liquidAmount));
							}
						}
					}
				}
			}
		}
	}

}
