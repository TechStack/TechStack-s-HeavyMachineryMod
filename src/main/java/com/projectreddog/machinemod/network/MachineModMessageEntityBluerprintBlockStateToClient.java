package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.utility.BlockBlueprintHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageEntityBluerprintBlockStateToClient implements IMessage {

	public IBlockState[][][] blockBlueprintArray;
	public int x;
	public int y;
	public int z;

	public MachineModMessageEntityBluerprintBlockStateToClient() {

	}

	public MachineModMessageEntityBluerprintBlockStateToClient(int x, int y, int z, IBlockState[][][] blockBlueprintArray) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;

		this.blockBlueprintArray = blockBlueprintArray;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.blockBlueprintArray = BlockBlueprintHelper.ReadBlockStateArrayFromByteBuff(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		BlockBlueprintHelper.WriteBlockStateArrayToByteBuff(buf, blockBlueprintArray);
	}

}
