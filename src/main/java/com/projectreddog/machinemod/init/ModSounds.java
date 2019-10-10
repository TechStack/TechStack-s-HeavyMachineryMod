package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class ModSounds {

	public static final SoundEvent ENGINE_IDLE = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "engineidle")).setRegistryName("engineidle");
	public static final SoundEvent ENGINE_REV = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "enginerev")).setRegistryName("enginerev");

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().register(ModSounds.ENGINE_IDLE);
		event.getRegistry().register(ModSounds.ENGINE_REV);

	}

	public static void init(Object mod) {

	}
}
