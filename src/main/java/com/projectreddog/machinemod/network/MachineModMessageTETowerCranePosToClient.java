package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageTETowerCranePosToClient implements IMessage {

	public int x;
	public int y;
	public int z;

	public int state;

	public double armRotation;
	public double gantryPos;
	public double wenchPos;

	public double targetArmRotation;
	public double targetGantryPos;
	public double targetWenchPos;

	public int currentX;
	public int currentY;
	public int currentZ;

	public MachineModMessageTETowerCranePosToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageTETowerCranePosToClient(int x, int y, int z, int state, double armRotation, double gantryPos, double wenchPos, double targetArmRotation, double targetGantryPos, double targetWenchPos, int currentX, int currentY, int currentZ) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.x = x;
		this.y = y;
		this.z = z;
		this.state = state;
		this.armRotation = armRotation;
		this.gantryPos = gantryPos;
		this.wenchPos = wenchPos;
		this.targetArmRotation = targetArmRotation;
		this.targetGantryPos = targetGantryPos;
		this.targetWenchPos = targetWenchPos;
		this.currentX = currentX;
		this.currentY = currentY;
		this.currentZ = currentZ;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.state = buf.readInt();
		this.armRotation = buf.readDouble();
		this.gantryPos = buf.readDouble();
		this.wenchPos = buf.readDouble();
		this.targetArmRotation = buf.readDouble();
		this.targetGantryPos = buf.readDouble();
		this.targetWenchPos = buf.readDouble();
		this.currentX = buf.readInt();
		this.currentY = buf.readInt();
		this.currentZ = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(state);
		buf.writeDouble(armRotation);
		buf.writeDouble(gantryPos);
		buf.writeDouble(wenchPos);
		buf.writeDouble(targetArmRotation);
		buf.writeDouble(targetGantryPos);
		buf.writeDouble(targetWenchPos);
		buf.writeInt(currentX);
		buf.writeInt(currentY);
		buf.writeInt(currentZ);
	}

}
