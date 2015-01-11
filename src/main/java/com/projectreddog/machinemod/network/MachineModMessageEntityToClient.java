package com.projectreddog.machinemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MachineModMessageEntityToClient implements IMessage{
	
	public int entityid;

	public double posX=0;
	public double posY=0;
	public double posZ=0;
	public float yaw=0;
	public float Attribute1=0;
	public MachineModMessageEntityToClient()
	{
		
	}
	
	
	
	
	
	public MachineModMessageEntityToClient(int entityid, double posX,
			double posY, double posZ, float yaw, float Attribute1) {
		super();
		this.entityid = entityid;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.yaw = yaw;
		this.Attribute1 = Attribute1;
	}





	@Override
	public void fromBytes(ByteBuf buf) {
        this.entityid= buf.readInt();
        this.posX=buf.readDouble();
        this.posY =buf.readDouble();
        this.posZ=buf.readDouble();
        this.yaw = buf.readFloat();
        this.Attribute1 =buf.readFloat();

	}

	@Override
	public void toBytes(ByteBuf buf) {
        buf.writeInt(entityid);
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);
        buf.writeFloat(yaw);
        buf.writeFloat(Attribute1);
        
	}

	
	

}
