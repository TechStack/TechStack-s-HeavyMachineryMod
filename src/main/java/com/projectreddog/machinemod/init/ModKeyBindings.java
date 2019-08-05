package com.projectreddog.machinemod.init;

import org.lwjgl.glfw.GLFW;

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
	public static KeyBinding KeyBindUnload;

	public static void init() {

		KeyBindSegment1Up = new KeyBinding("key.segment1.up", GLFW.GLFW_KEY_KP_7, "key.machinemod.segment");
		KeyBindSegment1Down = new KeyBinding("key.segment1.down", GLFW.GLFW_KEY_KP_1, "key.machinemod.segment");
		KeyBindSegment2Up = new KeyBinding("key.segment2.up", GLFW.GLFW_KEY_KP_8, "key.machinemod.segment");
		KeyBindSegment2Down = new KeyBinding("key.segment2.down", GLFW.GLFW_KEY_KP_2, "key.machinemod.segment");
		KeyBindSegment3Up = new KeyBinding("key.segment3.up", GLFW.GLFW_KEY_KP_9, "key.machinemod.segment");
		KeyBindSegment3Down = new KeyBinding("key.segment3.down", GLFW.GLFW_KEY_KP_3, "key.machinemod.segment");
		KeyBindTurretRight = new KeyBinding("key.turret.right", GLFW.GLFW_KEY_KP_6, "key.machinemod.segment");
		KeyBindTurretLeft = new KeyBinding("key.turret.left", GLFW.GLFW_KEY_KP_4, "key.machinemod.segment");
		KeyBindUnload = new KeyBinding("key.unload", GLFW.GLFW_KEY_U, "key.machinemod.special");

		ClientRegistry.registerKeyBinding(KeyBindSegment1Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment1Down);
		ClientRegistry.registerKeyBinding(KeyBindSegment2Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment2Down);
		ClientRegistry.registerKeyBinding(KeyBindSegment3Up);
		ClientRegistry.registerKeyBinding(KeyBindSegment3Down);
		ClientRegistry.registerKeyBinding(KeyBindTurretRight);
		ClientRegistry.registerKeyBinding(KeyBindTurretLeft);
		ClientRegistry.registerKeyBinding(KeyBindUnload);

	}

}
