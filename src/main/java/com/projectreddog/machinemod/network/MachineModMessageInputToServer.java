package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageInputToServer implements IMessage {

	public int entityid;

	public boolean isPlayerAccelerating = false;
	public boolean isPlayerBreaking = false;
	public boolean isPlayerTurningRight = false;
	public boolean isPlayerTurningLeft = false;
	public boolean isPlayerPushingSprintButton = false;
	public boolean isPlayerPushingJumpButton = false;

	public boolean isPlayerPushingSegment1Up = false; // default numpad 7
	public boolean isPlayerPushingSegment1Down = false; // default numpad 1
	public boolean isPlayerPushingSegment2Up = false; // default numpad 8
	public boolean isPlayerPushingSegment2Down = false;// default numpad 2
	public boolean isPlayerPushingSegment3Up = false;// default numpad 9
	public boolean isPlayerPushingSegment3Down = false;// default numpad 3

	public boolean isPlayerPushingTurretRight = false;// default numpad 6
	public boolean isPlayerPushingTurretLeft = false;// default numpad 7
	public boolean isPlayerPushingUnload = false;// default numpad 7

	public MachineModMessageInputToServer() {

	}

	public MachineModMessageInputToServer(int entityId, boolean isPlayerAccelerating, boolean isPlayerBreaking, boolean isPlayerTurningRight, boolean isPlayerTurningLeft, boolean isPlayerPushingSprintButton, boolean isPlayerPushingJumpButton, boolean isPlayerPushingSegment1Up, boolean isPlayerPushingSegment1Down, boolean isPlayerPushingSegment2Up, boolean isPlayerPushingSegment2Down, boolean isPlayerPushingSegment3Up, boolean isPlayerPushingSegment3Down, boolean isPlayerPushingTurretRight, boolean isPlayerPushingTurretLeft, boolean isPlayerPushingUnload) {
		super();
		this.isPlayerAccelerating = isPlayerAccelerating;
		this.isPlayerBreaking = isPlayerBreaking;
		this.isPlayerTurningRight = isPlayerTurningRight;
		this.isPlayerTurningLeft = isPlayerTurningLeft;
		this.entityid = entityId;
		this.isPlayerPushingSprintButton = isPlayerPushingSprintButton;
		this.isPlayerPushingJumpButton = isPlayerPushingJumpButton;

		this.isPlayerPushingSegment1Up = isPlayerPushingSegment1Up;
		this.isPlayerPushingSegment1Down = isPlayerPushingSegment1Down;
		this.isPlayerPushingSegment2Up = isPlayerPushingSegment2Up;
		this.isPlayerPushingSegment2Down = isPlayerPushingSegment2Down;
		this.isPlayerPushingSegment3Up = isPlayerPushingSegment3Up;
		this.isPlayerPushingSegment3Down = isPlayerPushingSegment3Down;

		this.isPlayerPushingTurretRight = isPlayerPushingTurretRight;
		this.isPlayerPushingTurretLeft = isPlayerPushingTurretLeft;
		this.isPlayerPushingUnload = isPlayerPushingUnload;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityid = buf.readInt();
		this.isPlayerAccelerating = buf.readBoolean();
		this.isPlayerBreaking = buf.readBoolean();
		this.isPlayerTurningRight = buf.readBoolean();
		this.isPlayerTurningLeft = buf.readBoolean();
		this.isPlayerPushingSprintButton = buf.readBoolean();
		this.isPlayerPushingJumpButton = buf.readBoolean();
		this.isPlayerPushingSegment1Up = buf.readBoolean();
		this.isPlayerPushingSegment1Down = buf.readBoolean();
		this.isPlayerPushingSegment2Up = buf.readBoolean();
		this.isPlayerPushingSegment2Down = buf.readBoolean();
		this.isPlayerPushingSegment3Up = buf.readBoolean();
		this.isPlayerPushingSegment3Down = buf.readBoolean();

		this.isPlayerPushingTurretRight = buf.readBoolean();
		this.isPlayerPushingTurretLeft = buf.readBoolean();
		this.isPlayerPushingUnload = buf.readBoolean();
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

		buf.writeBoolean(this.isPlayerPushingSegment1Up);
		buf.writeBoolean(this.isPlayerPushingSegment1Down);
		buf.writeBoolean(this.isPlayerPushingSegment2Up);
		buf.writeBoolean(this.isPlayerPushingSegment2Down);
		buf.writeBoolean(this.isPlayerPushingSegment3Up);
		buf.writeBoolean(this.isPlayerPushingSegment3Down);
		buf.writeBoolean(this.isPlayerPushingTurretRight);
		buf.writeBoolean(this.isPlayerPushingTurretLeft);
		buf.writeBoolean(this.isPlayerPushingUnload);

	}

}
