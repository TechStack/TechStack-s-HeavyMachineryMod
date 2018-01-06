package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageTEGuiButtonClickToServer implements IMessage {

	public int posX;
	public int posY;
	public int posZ;
	public int buttionid;

	public MachineModMessageTEGuiButtonClickToServer() {

	}

	public MachineModMessageTEGuiButtonClickToServer(int x, int y, int z, int buttionid) {
		super();
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.buttionid = buttionid;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.posX = buf.readInt();
		this.posY = buf.readInt();
		this.posZ = buf.readInt();
		this.buttionid = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.posX);
		buf.writeInt(this.posY);
		buf.writeInt(this.posZ);
		buf.writeInt(this.buttionid);

	}

}
