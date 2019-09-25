package com.projectreddog.machinemod.network;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageFileListToClient implements IMessage {

	public String[] blueprints;
	public String[] Owners;
	public String[] DisplayName;

	public MachineModMessageFileListToClient() {
	}

	public MachineModMessageFileListToClient(String[] blueprints, String[] owners, String[] displayName) {
		super();
		this.blueprints = blueprints;
		this.Owners = owners;
		this.DisplayName = displayName;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		int numBlue = buf.readInt();
		this.blueprints = new String[numBlue];
		for (int i = 0; i < numBlue; i++) {

			int lenght = buf.readInt();

			this.blueprints[i] = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();
		}

		int numOwn = buf.readInt();
		this.Owners = new String[numOwn];
		for (int i = 0; i < numOwn; i++) {

			int lenght = buf.readInt();

			this.Owners[i] = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();
		}

		int numDisplay = buf.readInt();
		this.DisplayName = new String[numDisplay];
		for (int i = 0; i < numDisplay; i++) {

			int lenght = buf.readInt();

			this.DisplayName[i] = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();
		}

	}

	@Override
	public void toBytes(ByteBuf buf) {

		int numBlue = this.blueprints.length;
		// write number of blueprints

		buf.writeInt(numBlue);
		for (int i = 0; i < numBlue; i++) {
			buf.writeInt(this.blueprints[i].length());
			buf.writeCharSequence(this.blueprints[i], Charset.forName("UTF-8"));
		}

		numBlue = this.Owners.length;
		// write number of blueprints

		buf.writeInt(numBlue);
		for (int i = 0; i < numBlue; i++) {
			buf.writeInt(this.Owners[i].length());
			buf.writeCharSequence(this.Owners[i], Charset.forName("UTF-8"));
		}

		numBlue = this.DisplayName.length;
		// write number of blueprints

		buf.writeInt(numBlue);
		for (int i = 0; i < numBlue; i++) {
			buf.writeInt(this.DisplayName[i].length());
			buf.writeCharSequence(this.DisplayName[i], Charset.forName("UTF-8"));
		}

	}

}
