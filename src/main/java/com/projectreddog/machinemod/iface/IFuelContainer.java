package com.projectreddog.machinemod.iface;

import net.minecraft.util.Direction;

public abstract interface IFuelContainer {
	public abstract int addFluid(int amount);

	public abstract boolean canAcceptFluid();

	public abstract Direction outputDirection();

}