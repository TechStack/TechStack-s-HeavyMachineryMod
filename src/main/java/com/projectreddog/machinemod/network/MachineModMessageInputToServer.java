package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageInputToServer implements IMessage{

	public int entityid;

	public boolean isPlayerAccelerating=false;
	public boolean isPlayerBreaking =false;
	public boolean isPlayerTurningRight=false;
	public boolean isPlayerTurningLeft=false;
	public boolean isPlayerPushingSprintButton =false;
	public boolean isPlayerPushingJumpButton = false;
	public MachineModMessageInputToServer()
	{
		
	}
	
	public MachineModMessageInputToServer(int entityId, boolean isPlayerAccelerating, boolean isPlayerBreaking, boolean isPlayerTurningRight,boolean isPlayerTurningLeft ,boolean isPlayerPushingSprintButton, boolean isPlayerPushingJumpButton) {
		super();
		this.isPlayerAccelerating = isPlayerAccelerating;
		this.isPlayerBreaking=isPlayerBreaking;
		this.isPlayerTurningRight=isPlayerTurningRight;
		this.isPlayerTurningLeft = isPlayerTurningLeft;
		this.entityid =entityId;
		this.isPlayerPushingSprintButton=isPlayerPushingSprintButton;
		this.isPlayerPushingJumpButton = isPlayerPushingJumpButton;
	}

	
	
	@Override
	public void fromBytes(ByteBuf buf) {
        this.entityid= buf.readInt();
        this.isPlayerAccelerating=buf.readBoolean();
        this.isPlayerBreaking =buf.readBoolean();
        this.isPlayerTurningRight=buf.readBoolean();
        this.isPlayerTurningLeft = buf.readBoolean();
        this.isPlayerPushingSprintButton = buf.readBoolean();
        this.isPlayerPushingJumpButton = buf.readBoolean();

	}

	@Override
	public void toBytes(ByteBuf buf) {
        buf.writeInt(entityid);
        buf.writeBoolean(isPlayerAccelerating);
        buf.writeBoolean(isPlayerBreaking);
        buf.writeBoolean(isPlayerTurningRight);
        buf.writeBoolean(isPlayerTurningLeft);
        buf.writeBoolean(isPlayerPushingSprintButton);
        buf.writeBoolean(isPlayerPushingJumpButton);
        
	}

}
