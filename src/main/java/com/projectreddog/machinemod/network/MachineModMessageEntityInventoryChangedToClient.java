package com.projectreddog.machinemod.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageEntityInventoryChangedToClient implements IMessage {

	public int entityid;

	public int slot;
	public ItemStack is;

	public MachineModMessageEntityInventoryChangedToClient() {
		// LogHelper.info("in machineModMessageEntityToClientConstructor basic");
	}

	public MachineModMessageEntityInventoryChangedToClient(int entityid, int slot, ItemStack is) {
		super();
		// LogHelper.info("in machineModMessageEntityToClientConstructor with parms");
		this.entityid = entityid;
		this.slot = slot;
		this.is = is;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// LogHelper.info("in machineModMessageEntityToClient from bytes");
		this.entityid = buf.readInt();
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
					// 1.8 itemstack.setTagCompound(CompressedStreamTools.func_152456_a(new ByteBufInputStream(buf), NBTSizeTracker.INFINITE));

					itemstack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), NBTSizeTracker.INFINITE));
				} catch (IOException e) {
					// will toss error when it passes end of stream
					// e.printStackTrace();
				}
			}

			try {
				// 1.8 itemstack.setTagCompound(CompressedStreamTools.func_152456_a(new ByteBufInputStream(buf), NBTSizeTracker.INFINITE));

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
		buf.writeInt(entityid);

		buf.writeInt(this.slot);
		if (this.is == null) {

			buf.writeShort(-1);
		} else {
			buf.writeShort(Item.getIdFromItem(this.is.getItem()));
			buf.writeByte(this.is.stackSize);
			buf.writeShort(this.is.getMetadata());
			NBTTagCompound nbttagcompound = null;

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
