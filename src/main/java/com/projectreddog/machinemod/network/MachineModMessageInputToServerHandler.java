package com.projectreddog.machinemod.network;

import javax.xml.ws.handler.MessageContext;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class MachineModMessageInputToServerHandler implements IMessageHandler<MachineModMessageInputToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageInputToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageInputToServer message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().player.world.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityMachineModRideable) {
				if (((EntityMachineModRideable) entity).getControllingPassenger() == ctx.getServerHandler().player) {
					// its ridden by this player (avoid some hacks)
					((EntityMachineModRideable) entity).isPlayerAccelerating = message.isPlayerAccelerating;
					((EntityMachineModRideable) entity).isPlayerBreaking = message.isPlayerBreaking;
					((EntityMachineModRideable) entity).isPlayerTurningLeft = message.isPlayerTurningLeft;
					((EntityMachineModRideable) entity).isPlayerTurningRight = message.isPlayerTurningRight;
					((EntityMachineModRideable) entity).isPlayerPushingSprintButton = message.isPlayerPushingSprintButton;
					((EntityMachineModRideable) entity).isPlayerPushingJumpButton = message.isPlayerPushingJumpButton;

					((EntityMachineModRideable) entity).isPlayerPushingSegment1Up = message.isPlayerPushingSegment1Up;
					((EntityMachineModRideable) entity).isPlayerPushingSegment1Down = message.isPlayerPushingSegment1Down;
					((EntityMachineModRideable) entity).isPlayerPushingSegment2Up = message.isPlayerPushingSegment2Up;
					((EntityMachineModRideable) entity).isPlayerPushingSegment2Down = message.isPlayerPushingSegment2Down;
					((EntityMachineModRideable) entity).isPlayerPushingSegment3Up = message.isPlayerPushingSegment3Up;
					((EntityMachineModRideable) entity).isPlayerPushingSegment3Down = message.isPlayerPushingSegment3Down;

					((EntityMachineModRideable) entity).isPlayerPushingTurretRight = message.isPlayerPushingTurretRight;
					((EntityMachineModRideable) entity).isPlayerPushingTurretLeft = message.isPlayerPushingTurretLeft;

					((EntityMachineModRideable) entity).isPlayerPushingUnloadButton = message.isPlayerPushingUnload;

				}
			}
		}
	}
}
