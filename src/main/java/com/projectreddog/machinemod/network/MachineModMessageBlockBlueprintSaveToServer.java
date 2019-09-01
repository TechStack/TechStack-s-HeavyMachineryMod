package com.projectreddog.machinemod.network;

import java.nio.charset.Charset;

import com.projectreddog.machinemod.utility.LogHelper;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageBlockBlueprintSaveToServer implements IMessage {

	public String fileName;
	public int pos1X;
	public int pos1Y;
	public int pos1Z;

	public int pos2X;
	public int pos2Y;
	public int pos2Z;

	public MachineModMessageBlockBlueprintSaveToServer() {

	}

	public MachineModMessageBlockBlueprintSaveToServer(String fileName, int pos1X, int pos1Y, int pos1Z, int pos2X, int pos2Y, int pos2Z) {
		super();
		this.fileName = fileName;
		this.pos1X = pos1X;
		this.pos1Y = pos1Y;
		this.pos1Z = pos1Z;

		this.pos2X = pos2X;
		this.pos2Y = pos2Y;
		this.pos2Z = pos2Z;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int lenght = buf.readInt();

		this.fileName = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();

		LogHelper.info(" From Bytes : " + fileName);

		this.pos1X = buf.readInt();
		this.pos1Y = buf.readInt();
		this.pos1Z = buf.readInt();
		this.pos2X = buf.readInt();
		this.pos2Y = buf.readInt();
		this.pos2Z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.fileName.length());
		buf.writeCharSequence(this.fileName, Charset.forName("UTF-8"));
		LogHelper.info(" to Bytes : " + fileName);

		buf.writeInt(this.pos1X);
		buf.writeInt(this.pos1Y);
		buf.writeInt(this.pos1Z);
		buf.writeInt(this.pos2X);
		buf.writeInt(this.pos2Y);
		buf.writeInt(this.pos2Z);

	}

}
