package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageLiquidPipeToClient implements IMessage {

	public int entityid;

	public double currPosX = 0;
	public double currPosY = 0;
	public double currPosZ = 0;
	public int liquidAmount = 0;
	public int fluidID = -1;

	public MachineModMessageLiquidPipeToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageLiquidPipeToClient(double currPosX, double currPosY, double currPosZ, int liquidAmount, int fluidID) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");

		this.currPosX = currPosX;
		this.currPosY = currPosY;
		this.currPosZ = currPosZ;
		this.liquidAmount = liquidAmount;
		this.fluidID = fluidID;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.currPosX = buf.readDouble();
		this.currPosY = buf.readDouble();
		this.currPosZ = buf.readDouble();
		this.liquidAmount = buf.readInt();
		this.fluidID = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeDouble(currPosX);
		buf.writeDouble(currPosY);
		buf.writeDouble(currPosZ);
		buf.writeInt(liquidAmount);
		buf.writeInt(fluidID);

	}

}
