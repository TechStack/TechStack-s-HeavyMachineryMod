package com.projectreddog.machinemod.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageTEInventoryChangedToClient implements IMessage {

	public int x;
	public int y;
	public int z;

	public int slot;
	public ItemStack is;
	public int extraInt;

	public MachineModMessageTEInventoryChangedToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageTEInventoryChangedToClient(int x, int y, int z, int slot, ItemStack is, int extraInt) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.x = x;
		this.y = y;
		this.z = z;
		this.slot = slot;
		this.is = is;
		this.extraInt = extraInt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.extraInt = buf.readInt();

		this.slot = buf.readInt();

		ItemStack itemstack = null;
		short short1 = buf.readShort();

		if (short1 >= 0) {
			byte b0 = buf.readByte();
			short short2 = buf.readShort();
			itemstack = new ItemStack(Item.getItemById(short1), b0, short2);

			int i = buf.readerIndex();
			byte b01 = buf.readByte();

			if (b01 == 0) {
				itemstack.setTagCompound(null);
			} else {
				buf.readerIndex(i);
				try {
					itemstack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), NBTSizeTracker.INFINITE));
				} catch (IOException e) {
					// will toss error when it passes end of stream
					// e.printStackTrace();
				}
			}

			try {
				itemstack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), NBTSizeTracker.INFINITE));
			} catch (IOException e) {
				// will toss error when it passes end of stream
				// e.printStackTrace();
			}
		}
		this.is = itemstack;

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient to bytes");
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(extraInt);

		buf.writeInt(this.slot);
		if (this.is == null) {

			buf.writeShort(-1);
		} else {
			buf.writeShort(Item.getIdFromItem(this.is.getItem()));
			buf.writeByte(this.is.getCount());
			buf.writeShort(this.is.getMetadata());
			CompoundNBT nbttagcompound = null;

			if (this.is.getItem().isDamageable() || this.is.getItem().getShareTag()) {
				nbttagcompound = this.is.getTagCompound();
			}

			if (nbttagcompound == null) {
				buf.writeByte(0);
			} else {
				try {
					CompressedStreamTools.write(nbttagcompound, new ByteBufOutputStream(buf));
				} catch (IOException ioexception) {
					throw new EncoderException(ioexception);
				}
			}

		}

	}

}
