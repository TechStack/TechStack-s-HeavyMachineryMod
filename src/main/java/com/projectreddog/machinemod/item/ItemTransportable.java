package com.projectreddog.machinemod.item;

import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelTransportable;

public abstract class ItemTransportable extends ItemMachineMod {

	public abstract ModelTransportable getModel();

	public abstract EntityMachineModRideable getEntityToSpawn(World world);
}
