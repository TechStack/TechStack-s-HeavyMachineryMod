package com.projectreddog.machinemod.network;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageBlockBlueprintSelectionToServer implements IMessage {

	public int x;
	public int y;
	public int z;

	public String fileName;

	public MachineModMessageBlockBlueprintSelectionToServer() {

	}

	public MachineModMessageBlockBlueprintSelectionToServer(String fileName, int x, int y, int z) {
		super();
		this.fileName = fileName;
		this.x = x;
		this.y = y;
		this.z = z;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int lenght = buf.readInt();
		this.fileName = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.fileName.length());
		buf.writeCharSequence(this.fileName, Charset.forName("UTF-8"));
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

	}

}
