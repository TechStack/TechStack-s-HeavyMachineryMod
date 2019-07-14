package com.projectreddog.machinemod.iface;

import net.minecraft.entity.player.PlayerEntity;

public abstract interface ITEGuiButtonHandler {
	public abstract void HandleGuiButton(int buttonId, PlayerEntity player);
}
