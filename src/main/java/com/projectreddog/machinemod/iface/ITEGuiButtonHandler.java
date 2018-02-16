package com.projectreddog.machinemod.iface;

import net.minecraft.entity.player.EntityPlayer;

public abstract interface ITEGuiButtonHandler {
	public abstract void HandleGuiButton(int buttonId, EntityPlayer player);
}
