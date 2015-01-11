package com.projectreddog.machinemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.utility.LogHelper;

public class MachineModMessageEntityToClientHandler implements IMessageHandler<MachineModMessageEntityToClient,IMessage>{

	@Override
	public IMessage onMessage(MachineModMessageEntityToClient message, MessageContext ctx) {


		Entity entity=Minecraft.getMinecraft().theWorld.getEntityByID( message.entityid);

		if (entity!= null ){

			if (entity instanceof EntityMachineModRideable )
			{
				//its ridden by this player (avoid some hacks) 
				( (EntityMachineModRideable) entity).TargetposX = message.posX;
				( (EntityMachineModRideable) entity).TargetposY = message.posY;
				( (EntityMachineModRideable) entity).TargetposZ = message.posZ;
				( (EntityMachineModRideable) entity).rotationYaw  =  message.yaw;
				( (EntityMachineModRideable) entity).yaw  =  message.yaw;
				( (EntityMachineModRideable) entity).Attribute1  =  message.Attribute1;


			//	LogHelper.info("RECIEVED ENTITY PACKET FROM SERVER" );
			}
		}	


		return null;
	}

}
