package com.projectreddog.machinemod.network;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageBlockBlueprintSaveToServer implements IMessage {

	public String fileName;
	public int pos1X;
	public int pos1Y;
	public int pos1Z;

	public MachineModMessageBlockBlueprintSaveToServer() {

	}

	public MachineModMessageBlockBlueprintSaveToServer(String fileName, int pos1X, int pos1Y, int pos1Z, int pos2X, int pos2Y, int pos2Z) {
		super();
		this.fileName = fileName;
		this.pos1X = pos1X;
		this.pos1Y = pos1Y;
		this.pos1Z = pos1Z;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int lenght = buf.readInt();

		this.fileName = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();

		this.pos1X = buf.readInt();
		this.pos1Y = buf.readInt();
		this.pos1Z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.fileName.length());
		buf.writeCharSequence(this.fileName, Charset.forName("UTF-8"));

		buf.writeInt(this.pos1X);
		buf.writeInt(this.pos1Y);
		buf.writeInt(this.pos1Z);

	}

}
