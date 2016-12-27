package com.projectreddog.machinemod.init;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeyBindings {

	public static KeyBinding KeyBindSegment1Up;
	public static KeyBinding KeyBindSegment1Down;
	public static KeyBinding KeyBindSegment2Up;
	public static KeyBinding KeyBindSegment2Down;
	public static KeyBinding KeyBindSegment3Up;
	public static KeyBinding KeyBindSegment3Down;
	public static KeyBinding KeyBindTurretRight;
	public static KeyBinding KeyBindTurretLeft;

	public static void init() {

		KeyBindSegment1Up = new KeyBinding("key.segment1.up", Keyboard.KEY_NUMPAD7, "key.machinemod.segment");
		KeyBindSegment1Down = new KeyBinding("key.segment1.down", Keyboard.KEY_NUMPAD1, "key.machinemod.segment");
		KeyBindSegment2Up = new KeyBinding("key.segment2.up", Keyboard.KEY_NUMPAD8, "key.machinemod.segment");
		KeyBindSegment2Down = new KeyBinding("key.segment2.down", Keyboard.KEY_NUMPAD2, "key.machinemod.segment");
		KeyBindSegment3Up = new KeyBinding("key.segment3.up", Keyboard.KEY_NUMPAD9, "key.machinemod.segment");
		KeyBindSegment3Down = new KeyBinding("key.segment3.down", Keyboard.KEY_NUMPAD3, "key.machinemod.segment");
		KeyBindTurretRight = new KeyBinding("key.turret.right", Keyboard.KEY_NUMPAD6, "key.machinemod.segment");
		KeyBindTurretLeft = new KeyBinding("key.turret.left", Keyboard.KEY_NUMPAD4, "key.machinemod.segment");

		ClientRegistry.registerKeyBinding(KeyBindSegment1Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment1Down);
		ClientRegistry.registerKeyBinding(KeyBindSegment2Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment2Down);
		ClientRegistry.registerKeyBinding(KeyBindSegment3Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment3Down);
		ClientRegistry.registerKeyBinding(KeyBindTurretRight);
		ClientRegistry.registerKeyBinding(KeyBindTurretLeft);

	}

}
