package com.projectreddog.machinemod.utility;

import net.minecraft.client.resources.I18n;

public class DeprecatedWrapper {
	// The intent of this class is to allow me to wrap deprecated method calls here for when they are later removed.
	// This should allow for simpler updates when they are removed because this class should be the spot to update not others.

	public static String translateToLocal(String InputKey) {
		return I18n.format(InputKey);
	}

}
