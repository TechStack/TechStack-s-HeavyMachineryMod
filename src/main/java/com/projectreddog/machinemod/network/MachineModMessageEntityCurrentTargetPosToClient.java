package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageEntityCurrentTargetPosToClient implements IMessage {

	public int entityid;

	public double currPosX = 0;
	public double currPosY = 0;
	public double currPosZ = 0;

	public MachineModMessageEntityCurrentTargetPosToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageEntityCurrentTargetPosToClient(int entityid, double currPosX, double currPosY, double currPosZ) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.entityid = entityid;
		this.currPosX = currPosX;
		this.currPosY = currPosY;
		this.currPosZ = currPosZ;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.entityid = buf.readInt();
		this.currPosX = buf.readDouble();
		this.currPosY = buf.readDouble();
		this.currPosZ = buf.readDouble();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeInt(entityid);
		buf.writeDouble(currPosX);
		buf.writeDouble(currPosY);
		buf.writeDouble(currPosZ);

	}

}
