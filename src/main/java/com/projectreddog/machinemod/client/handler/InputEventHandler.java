package com.projectreddog.machinemod.client.handler;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.init.ModKeyBindings;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGui;
import com.projectreddog.machinemod.network.MachineModMessageMouseInputToServer;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputEventHandler {

	BlockPos lastBlockPos = null; // holds last block the player looked at

	@SubscribeEvent
	public void MouseInputEvent(InputEvent.MouseInputEvent event) {

		if (Minecraft.getInstance().player.getRidingEntity() != null) {
			if (Minecraft.getInstance().player.getRidingEntity() instanceof EntityExcavator) {
				Entity e = Minecraft.getInstance().player.getRidingEntity();
				// playre riding a excavator so we should check which block they are looking at.
				RayTraceResult currentMouseOver;
				// LogHelper.info("MIE" + event);
				// currentMouseOver = Minecraft.getInstance().objectMouseOver;
				currentMouseOver = rayTrace(7);
				// look position
				// Minecraft.getInstance().theWorld.rayTraceBlocks(Minecraft.getInstance().thePlayer.getPosition(),Minecraft.getInstance().thePlayer.getPosition().addv)
				if (currentMouseOver != null && currentMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {

					// return blockpos of this movingbojectposition
					BlockPos currentBlockpos = currentMouseOver.getBlockPos();

					if (Minecraft.getInstance().player.world.getBlockState(currentBlockpos).getBlock().getMaterial(Minecraft.getInstance().player.world.getBlockState(currentBlockpos)) != Material.AIR) {
						// this.effectRenderer.addBlockHitEffects(blockpos, this.objectMouseOver);
						// this.thePlayer.swingItem();

						// Player is looking at a block check if its the same as the "Last block" if it is then we can
						if (lastBlockPos != currentBlockpos) {
							// Its a new block tell the server which block this player is looking at.
							ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageMouseInputToServer(e.getEntityId(), currentBlockpos.getX(), currentBlockpos.getY(), currentBlockpos.getZ()));
							// save for use on the client
							((EntityExcavator) e).targetBlockPos = currentBlockpos;
							lastBlockPos = currentBlockpos;
						}
					}
				}
			}
		}
	}

	// test
	@SubscribeEvent
	public void handleKeyInputevent(InputEvent.KeyInputEvent event) {
		// LogHelper.info("Called KB Event");

		boolean sendPacket = false;
		if (Minecraft.getInstance().player.getRidingEntity() instanceof EntityMachineModRideable) {

			sendPacket = true;
			EntityMachineModRideable e = (EntityMachineModRideable) Minecraft.getInstance().player.getRidingEntity();

			if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown()) {
				// player pressed forward & is in my entity send network message
				// to server
				e.isPlayerAccelerating = true;
			} else {
				e.isPlayerAccelerating = false;
			}

			if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()) {
				e.isPlayerPushingJumpButton = true;
			} else {
				e.isPlayerPushingJumpButton = false;
			}

			if (Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown()) {
				// player pressed forward & is in my entity send network message
				// to server
				e.isPlayerPushingSprintButton = true;
			} else {
				e.isPlayerPushingSprintButton = false;
			}

			if (Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown()) {
				// player pressed back & is in my entity send network message to
				// server
				e.isPlayerBreaking = true;
				e.isPlayerAccelerating = false;// for cases where both accel &
				// break are pressed
			} else {
				e.isPlayerBreaking = false;
			}

			if (Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown()) {
				// player pressed left & is in my entity send network message to
				// server
				e.isPlayerTurningLeft = true;
				e.isPlayerTurningRight = false;
			} else {
				e.isPlayerTurningLeft = false;
			}
			if (Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown()) {
				// player pressed right & is in my entity send network message
				// to server

				e.isPlayerTurningRight = true;
				e.isPlayerTurningLeft = false;
			} else {
				e.isPlayerTurningRight = false;

			}

			// Test segment(s) & turret inputs

			if (ModKeyBindings.KeyBindSegment1Down.isKeyDown()) {
				e.isPlayerPushingSegment1Down = true;
				e.isPlayerPushingSegment1Up = false;

			} else {
				e.isPlayerPushingSegment1Down = false;
			}
			if (ModKeyBindings.KeyBindSegment1Up.isKeyDown()) {
				e.isPlayerPushingSegment1Up = true;
				e.isPlayerPushingSegment1Down = false;

			} else {
				e.isPlayerPushingSegment1Up = false;
			}

			if (ModKeyBindings.KeyBindSegment2Down.isKeyDown()) {
				e.isPlayerPushingSegment2Down = true;
				e.isPlayerPushingSegment2Up = false;

			} else {
				e.isPlayerPushingSegment2Down = false;
			}
			if (ModKeyBindings.KeyBindSegment2Up.isKeyDown()) {
				e.isPlayerPushingSegment2Up = true;
				e.isPlayerPushingSegment2Down = false;

			} else {
				e.isPlayerPushingSegment2Up = false;
			}

			if (ModKeyBindings.KeyBindSegment3Down.isKeyDown()) {
				e.isPlayerPushingSegment3Down = true;
				e.isPlayerPushingSegment3Up = false;

			} else {
				e.isPlayerPushingSegment3Down = false;
			}
			if (ModKeyBindings.KeyBindSegment3Up.isKeyDown()) {
				e.isPlayerPushingSegment3Up = true;
				e.isPlayerPushingSegment3Down = false;

			} else {
				e.isPlayerPushingSegment3Up = false;
			}

			if (ModKeyBindings.KeyBindTurretLeft.isKeyDown()) {
				e.isPlayerPushingTurretLeft = true;
				e.isPlayerPushingTurretRight = false;

			} else {
				e.isPlayerPushingTurretLeft = false;
			}
			if (ModKeyBindings.KeyBindTurretRight.isKeyDown()) {
				e.isPlayerPushingTurretRight = true;
				e.isPlayerPushingTurretLeft = false;

			} else {
				e.isPlayerPushingTurretRight = false;
			}

			if (ModKeyBindings.KeyBindUnload.isKeyDown()) {
				e.isPlayerPushingUnloadButton = true;

			} else {
				e.isPlayerPushingUnloadButton = false;
			}

			if (Minecraft.getInstance().gameSettings.keyBindInventory.isPressed()) {
				ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServerOpenGui(e.getEntityId(), true));

			}

			if (sendPacket) {
				// LogHelper.info("NETWORKPACKET SENDING: VEL:" + e.velocity
				// +" Yaw: " + e.yaw);

				// LogHelper.info("NETWORKPACKET SENDING: ACC:" +
				// e.isPlayerAccelerating + " BRAKE: " + e.isPlayerBreaking
				// +" Left: "+ e.isPlayerTurningLeft
				// +" RIght:"+e.isPlayerTurningRight);

				ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServer(e.getEntityId(), e.isPlayerAccelerating, e.isPlayerBreaking, e.isPlayerTurningRight, e.isPlayerTurningLeft, e.isPlayerPushingSprintButton, e.isPlayerPushingJumpButton, e.isPlayerPushingSegment1Up, e.isPlayerPushingSegment1Down, e.isPlayerPushingSegment2Up, e.isPlayerPushingSegment2Down, e.isPlayerPushingSegment3Up, e.isPlayerPushingSegment3Down, e.isPlayerPushingTurretRight, e.isPlayerPushingTurretLeft, e.isPlayerPushingUnloadButton));

			}

		}

	}

	@OnlyIn(Dist.CLIENT)
	/**
	 * Performs a ray trace for the distance specified * Args: distance
	 */
	public RayTraceResult rayTrace(double par1) {
		Vec3d var4 = Minecraft.getInstance().player.getPositionEyes(1);
		Vec3d var5 = Minecraft.getInstance().player.getLook(1);
		Vec3d var6 = var4.addVector(var5.x * par1, var5.y * par1, var5.z * par1);
		return Minecraft.getInstance().world.rayTraceBlocks(var4, var6);
	}

}
