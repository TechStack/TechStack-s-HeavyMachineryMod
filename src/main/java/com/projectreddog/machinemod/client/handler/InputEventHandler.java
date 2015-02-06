package com.projectreddog.machinemod.client.handler;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGui;
import com.projectreddog.machinemod.network.MachineModMessageMouseInputToServer;

public class InputEventHandler {

	BlockPos lastBlockPos = null; // holds last block the player looked at

	@SubscribeEvent
	public void MouseInputEvent(InputEvent.MouseInputEvent event) {

		if (Minecraft.getMinecraft().thePlayer.ridingEntity != null) {
			if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityExcavator) {
				Entity e = Minecraft.getMinecraft().thePlayer.ridingEntity;
				// playre riding a excavator so we should check which block they are looking at.
				MovingObjectPosition currentMouseOver;
				// LogHelper.info("MIE" + event);
				// currentMouseOver = Minecraft.getMinecraft().objectMouseOver;
				currentMouseOver = rayTrace(7);
				// look position
				// Minecraft.getMinecraft().theWorld.rayTraceBlocks(Minecraft.getMinecraft().thePlayer.getPosition(),Minecraft.getMinecraft().thePlayer.getPosition().addv)
				if (currentMouseOver != null && currentMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {

					// return blockpos of this movingbojectposition
					BlockPos currentBlockpos = currentMouseOver.func_178782_a();

					if (Minecraft.getMinecraft().thePlayer.worldObj.getBlockState(currentBlockpos).getBlock().getMaterial() != Material.air) {
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

	@SubscribeEvent
	public void handleKeyInputevent(InputEvent.KeyInputEvent event) {
		// LogHelper.info("Called KB Event");

		boolean sendPacket = false;
		if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityMachineModRideable) {

			sendPacket = true;
			EntityMachineModRideable e = (EntityMachineModRideable) Minecraft.getMinecraft().thePlayer.ridingEntity;

			if (Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed()) {
				// player pressed forward & is in my entity send network message
				// to server
				e.isPlayerAccelerating = true;
			} else {
				e.isPlayerAccelerating = false;
			}

			if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()) {
				e.isPlayerPushingJumpButton = true;
			} else {
				e.isPlayerPushingJumpButton = false;
			}

			if (Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed()) {
				// player pressed forward & is in my entity send network message
				// to server
				e.isPlayerPushingSprintButton = true;
			} else {
				e.isPlayerPushingSprintButton = false;
			}

			if (Minecraft.getMinecraft().gameSettings.keyBindBack.getIsKeyPressed()) {
				// player pressed back & is in my entity send network message to
				// server
				e.isPlayerBreaking = true;
				e.isPlayerAccelerating = false;// for cases where both accel &
				// break are pressed
			} else {
				e.isPlayerBreaking = false;
			}

			if (Minecraft.getMinecraft().gameSettings.keyBindLeft.getIsKeyPressed()) {
				// player pressed left & is in my entity send network message to
				// server
				e.isPlayerTurningLeft = true;
				e.isPlayerTurningRight = false;
			} else {
				e.isPlayerTurningLeft = false;
			}
			if (Minecraft.getMinecraft().gameSettings.keyBindRight.getIsKeyPressed()) {
				// player pressed right & is in my entity send network message
				// to server

				e.isPlayerTurningRight = true;
				e.isPlayerTurningLeft = false;
			} else {
				e.isPlayerTurningRight = false;

			}

			if (Minecraft.getMinecraft().gameSettings.keyBindInventory.isPressed()) {
				ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServerOpenGui(e.getEntityId(), true));

			}

			if (sendPacket) {
				// LogHelper.info("NETWORKPACKET SENDING: VEL:" + e.velocity
				// +" Yaw: " + e.yaw);

				// LogHelper.info("NETWORKPACKET SENDING: ACC:" +
				// e.isPlayerAccelerating + " BRAKE: " + e.isPlayerBreaking
				// +" Left: "+ e.isPlayerTurningLeft
				// +" RIght:"+e.isPlayerTurningRight);

				ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServer(e.getEntityId(), e.isPlayerAccelerating, e.isPlayerBreaking, e.isPlayerTurningRight, e.isPlayerTurningLeft, e.isPlayerPushingSprintButton, e.isPlayerPushingJumpButton));

			}

		}

	}

	@SideOnly(Side.CLIENT)
	/**
	 * Performs a ray trace for the distance specified *
	 *  Args: distance
	 */
	public MovingObjectPosition rayTrace(double par1) {
		Vec3 var4 = Minecraft.getMinecraft().thePlayer.getPositionEyes(1);
		Vec3 var5 = Minecraft.getMinecraft().thePlayer.getLook(1);
		Vec3 var6 = var4.addVector(var5.xCoord * par1, var5.yCoord * par1, var5.zCoord * par1);
		return Minecraft.getMinecraft().theWorld.rayTraceBlocks(var4, var6);
	}

}
