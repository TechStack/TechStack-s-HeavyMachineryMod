package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageEntityCurrentTargetPosToClient implements IMessage {

	public int entityid;

	public double currPosX = 0;
	public double currPosY = 0;
	public double currPosZ = 0;
	public double angleArm1 = 0;
	public double angleArm2 = 0;
	public double angleArm3 = 0;
	public double mainBodyRotation = 0;

	public MachineModMessageEntityCurrentTargetPosToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageEntityCurrentTargetPosToClient(int entityid, double currPosX, double currPosY, double currPosZ, double armAngle1, double armAngle2, double armAngle3, double mainBodyRotation) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.entityid = entityid;
		this.currPosX = currPosX;
		this.currPosY = currPosY;
		this.currPosZ = currPosZ;
		this.angleArm1 = armAngle1;
		this.angleArm2 = armAngle2;
		this.angleArm3 = armAngle3;
		this.mainBodyRotation = mainBodyRotation;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.entityid = buf.readInt();
		this.currPosX = buf.readDouble();
		this.currPosY = buf.readDouble();
		this.currPosZ = buf.readDouble();
		this.angleArm1 = buf.readDouble();
		this.angleArm2 = buf.readDouble();
		this.angleArm3 = buf.readDouble();
		this.mainBodyRotation = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeInt(entityid);
		buf.writeDouble(currPosX);
		buf.writeDouble(currPosY);
		buf.writeDouble(currPosZ);
		buf.writeDouble(angleArm1);
		buf.writeDouble(angleArm2);
		buf.writeDouble(angleArm3);
		buf.writeDouble(mainBodyRotation);

	}

}
