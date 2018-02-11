package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageTEIntFieldToClient implements IMessage {

	public int x;
	public int y;
	public int z;

	public int field;
	public int value;

	public MachineModMessageTEIntFieldToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageTEIntFieldToClient(int x, int y, int z, int field, int value) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.x = x;
		this.y = y;
		this.z = z;
		this.field = field;
		this.value = value;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.field = buf.readInt();

		this.value = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(field);

		buf.writeInt(value);

	}

}
