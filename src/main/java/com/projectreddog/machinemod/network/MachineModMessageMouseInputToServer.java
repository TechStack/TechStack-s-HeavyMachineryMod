package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageMouseInputToServer implements IMessage {

	public int entityid;

	public int posX;
	public int posY;
	public int posZ;

	public MachineModMessageMouseInputToServer() {

	}

	public MachineModMessageMouseInputToServer(int entityId, int x, int y, int z) {
		super();
		this.entityid = entityId;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityid = buf.readInt();
		this.posX = buf.readInt();
		this.posY = buf.readInt();
		this.posZ = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityid);
		buf.writeInt(this.posX);
		buf.writeInt(this.posY);
		buf.writeInt(this.posZ);

	}

}
