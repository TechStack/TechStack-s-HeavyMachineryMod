package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.world.World;

public abstract class ItemTransportable extends ItemMachineModMachine {

	public abstract ModelTransportable getModel();

	public abstract EntityMachineModRideable getEntityToSpawn(World world);
}
