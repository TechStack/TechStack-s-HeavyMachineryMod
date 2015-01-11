package com.projectreddog.machinemod.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGui;
import com.projectreddog.machinemod.utility.LogHelper;

public class KeyInputEventHandler {



	@SubscribeEvent
	public void handleKeyInputevent(InputEvent.KeyInputEvent event){
		 //LogHelper.info("Called KB Event");

		boolean sendPacket =false;
		if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntityMachineModRideable){

			 sendPacket =true;
			EntityMachineModRideable e =(EntityMachineModRideable)  Minecraft.getMinecraft().thePlayer.ridingEntity;

			
			
			if (Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed() ){
				// 	player pressed forward & is in my entity send network message to server
				 e.isPlayerAccelerating=true; 		 
			} else {
				 e.isPlayerAccelerating=false; 	
			}
				
			if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
				e.isPlayerPushingJumpButton=true;
			}else{
				e.isPlayerPushingJumpButton=false;
			}
			
			

			if (Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed() ){
				// 	player pressed forward & is in my entity send network message to server
				 e.isPlayerPushingSprintButton=true; 		 
			} else {
				 e.isPlayerPushingSprintButton=false; 	
			}
			
			

			if (Minecraft.getMinecraft().gameSettings.keyBindBack.getIsKeyPressed() ){
				// 	player pressed back & is in my entity send network message to server
				 e.isPlayerBreaking=true;
				 e.isPlayerAccelerating=false;// for cases where both accel & break are pressed
			}else {
				 e.isPlayerBreaking=false;
			}
			
			
			
			if (Minecraft.getMinecraft().gameSettings.keyBindLeft.getIsKeyPressed() ){
				// 	player pressed left & is in my entity send network message to server
				e.isPlayerTurningLeft=true;
				e.isPlayerTurningRight =false;				 
			} else{
				e.isPlayerTurningLeft=false;
			}
			if (Minecraft.getMinecraft().gameSettings.keyBindRight.getIsKeyPressed() ){
				// 	player pressed right & is in my entity send network message to server
				
				e.isPlayerTurningRight =true;
				e.isPlayerTurningLeft=false;
			} else{
				e.isPlayerTurningRight =false;

			}
			
			if (Minecraft.getMinecraft().gameSettings.keyBindInventory.isPressed()){
				ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServerOpenGui(e.getEntityId(), true));
				
			}
			
			
			if (sendPacket){
			// LogHelper.info("NETWORKPACKET SENDING: VEL:" + e.velocity +" Yaw: " + e.yaw);
			 
			// LogHelper.info("NETWORKPACKET SENDING: ACC:" + e.isPlayerAccelerating + " BRAKE: " + e.isPlayerBreaking +" Left: "+ e.isPlayerTurningLeft +" RIght:"+e.isPlayerTurningRight);

			 ModNetwork.simpleNetworkWrapper.sendToServer(new MachineModMessageInputToServer( e.getEntityId(),e.isPlayerAccelerating,e.isPlayerBreaking,e.isPlayerTurningRight, e.isPlayerTurningLeft,e.isPlayerPushingSprintButton,e.isPlayerPushingJumpButton));
			 
			}
				
		}



	}
}
