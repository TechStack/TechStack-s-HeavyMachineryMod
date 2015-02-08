package com.projectreddog.machinemod.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.projectreddog.machinemod.container.ContainerCombine;
import com.projectreddog.machinemod.container.ContainerDumpTruck;
import com.projectreddog.machinemod.container.ContainerLoader;
import com.projectreddog.machinemod.container.ContainerTractor;
import com.projectreddog.machinemod.container.ContainerWideBedTruck;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

public class GuiHandler implements IGuiHandler {
	// returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		if (id == Reference.GUI_DUMP_TRUCK) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityDumpTruck) {

					return new ContainerDumpTruck(player.inventory, (EntityDumpTruck) entity);
				}
			}
		} else if (id == Reference.GUI_LOADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityLoader) {

					return new ContainerLoader(player.inventory, (EntityLoader) entity);
				}
			}
		} else if (id == Reference.GUI_TRACTOR) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityTractor) {

					return new ContainerTractor(player.inventory, (EntityTractor) entity);
				}
			}
		} else if (id == Reference.GUI_WIDEBEDTRUCK) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityWideBedTruck) {

					return new ContainerWideBedTruck(player.inventory, (EntityWideBedTruck) entity);
				}
			}
		} else if (id == Reference.GUI_COMBINE) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityCombine) {

					return new ContainerCombine(player.inventory, (EntityCombine) entity);
				}
			}
		}
		return null;
	}

	// returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		if (id == Reference.GUI_DUMP_TRUCK) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityDumpTruck) {
					return new GuiDumpTruck(player.inventory, (EntityDumpTruck) entity);
				}
			}
		} else if (id == Reference.GUI_LOADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityLoader) {
					return new GuiLoader(player.inventory, (EntityLoader) entity);
				}
			}
		} else if (id == Reference.GUI_TRACTOR) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityTractor) {
					return new GuiTractor(player.inventory, (EntityTractor) entity);
				}
			}
		} else if (id == Reference.GUI_WIDEBEDTRUCK) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityWideBedTruck) {
					return new GuiWideBedTruck(player.inventory, (EntityWideBedTruck) entity);
				}
			}
		} else if (id == Reference.GUI_COMBINE) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityCombine) {
					return new GuiCombine(player.inventory, (EntityCombine) entity);
				}
			}
		}

		return null;

	}
}