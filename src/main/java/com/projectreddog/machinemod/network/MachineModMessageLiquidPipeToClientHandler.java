package com.projectreddog.machinemod.network;

import java.util.Map;

import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
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

	public void processMessage(MachineModMessageLiquidPipeToClient message) {
		if (message != null) {
			if (Minecraft.getInstance().world != null) {
				if (Minecraft.getInstance().player != null) {
					TileEntity entity = Minecraft.getInstance().world.getTileEntity(new BlockPos(message.currPosX, message.currPosY, message.currPosZ));
					if (entity != null) {
						if (entity instanceof TileEntityLiquidPipe) {
							if (message.fluidID == -1) {
								((TileEntityLiquidPipe) entity).setFluid(null);
							} else {

								// BEGIN HACK FOR no DIRECT ACCESS to ID & NO STRING can be sent via bytebuff
								Fluid fluid = null;

								for (Map.Entry<Fluid, Integer> FluidMap : FluidRegistry.getRegisteredFluidIDs().entrySet()) {
									if (FluidMap.getValue() == message.fluidID) {
										fluid = FluidMap.getKey();
									}
								}
								if (fluid == null) {
									((TileEntityLiquidPipe) entity).setFluid(null);
								} else {
									// END HACK FOR no DIRECT ACCESS to ID & NO STRING can be sent via bytebuff
									((TileEntityLiquidPipe) entity).setFluid(new FluidStack(fluid, message.liquidAmount));
								}
							}
						}
					}
				}
			}
		}
	}

}
