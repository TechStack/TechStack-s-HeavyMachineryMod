package com.projectreddog.machinemod.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;

public class MachineModMessageInputToServerOpenGuiHandler  implements IMessageHandler<MachineModMessageInputToServerOpenGui,IMessage>{


	@Override
	public IMessage onMessage(MachineModMessageInputToServerOpenGui message, MessageContext ctx) {

		Entity entity=	ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityid);

		if (entity!= null ){

			if (entity instanceof EntityMachineModRideable )
			{
				if (( (EntityMachineModRideable) entity).riddenByEntity == ctx.getServerHandler().playerEntity)
				{
					//its ridden by this player (avoid some hacks) 
					if (message.isOpenGui){
						
						((EntityPlayer)entity.riddenByEntity).openGui(MachineMod.instance, Reference.GUI_DUMP_TRUCK, entity.worldObj, (int) entity.getEntityId(), (int) 0,(int) 0);

					}
					
				}
			}
		}
		return null;
	}
}
