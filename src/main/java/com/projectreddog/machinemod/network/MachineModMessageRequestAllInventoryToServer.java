package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageRequestAllInventoryToServer implements IMessage {

	public int entityid;

	
	public MachineModMessageRequestAllInventoryToServer() {

	}

	public MachineModMessageRequestAllInventoryToServer(int entityId) {
		super();
		this.entityid = entityId;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityid = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityid);
	}

}
