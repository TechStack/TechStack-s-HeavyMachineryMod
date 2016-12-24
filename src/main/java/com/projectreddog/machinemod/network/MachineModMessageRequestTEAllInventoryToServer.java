package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageRequestTEAllInventoryToServer implements IMessage {

	public int x;
	public int y;
	public int z;

	public MachineModMessageRequestTEAllInventoryToServer() {

	}

	public MachineModMessageRequestTEAllInventoryToServer(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

	}

}
