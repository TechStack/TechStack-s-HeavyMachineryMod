package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageInputToServerOpenGui implements IMessage{

	public int entityid;

	public boolean isOpenGui=false;
	
	public MachineModMessageInputToServerOpenGui()
	{
		
	}
	
	public MachineModMessageInputToServerOpenGui(int entityId, boolean isOpenGui) {
		super();
		this.isOpenGui = isOpenGui;
		this.entityid =entityId;
		
	}

	
	
	@Override
	public void fromBytes(ByteBuf buf) {
        this.entityid= buf.readInt();
        this.isOpenGui=buf.readBoolean();

	}

	@Override
	public void toBytes(ByteBuf buf) {
        buf.writeInt(entityid);
        buf.writeBoolean(isOpenGui);
        
	}

}
