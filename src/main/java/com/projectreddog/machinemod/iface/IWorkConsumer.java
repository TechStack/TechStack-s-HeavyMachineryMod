package com.projectreddog.machinemod.iface;

public abstract interface IWorkConsumer {
	// Amount in is the amount the Provider can provide this tick.
	// return value is the amount left over that this consumer can not consume this tick and should remain in the provider.
	public abstract int appyWork(int Amount);

	public abstract boolean isWorkNeeded();

	public abstract int amountCanConsume();
}
