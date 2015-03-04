package com.projectreddog.machinemod.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.projectreddog.machinemod.container.ContainerCanner;
import com.projectreddog.machinemod.container.ContainerCentrifuge;
import com.projectreddog.machinemod.container.ContainerCombine;
import com.projectreddog.machinemod.container.ContainerDistiller;
import com.projectreddog.machinemod.container.ContainerDumpTruck;
import com.projectreddog.machinemod.container.ContainerFermenter;
import com.projectreddog.machinemod.container.ContainerLoader;
import com.projectreddog.machinemod.container.ContainerPaver;
import com.projectreddog.machinemod.container.ContainerScreen;
import com.projectreddog.machinemod.container.ContainerTractor;
import com.projectreddog.machinemod.container.ContainerWideBedTruck;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityCentrifuge;
import com.projectreddog.machinemod.tileentities.TileEntityDistiller;
import com.projectreddog.machinemod.tileentities.TileEntityFermenter;
import com.projectreddog.machinemod.tileentities.TileEntityFuelPump;
import com.projectreddog.machinemod.tileentities.TileEntityScreen;

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
		} else if (id == Reference.GUI_PAVER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityPaver) {

					return new ContainerPaver(player.inventory, (EntityPaver) entity);
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

		else if (id == Reference.GUI_CANNER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFuelPump) {

					return new ContainerCanner(player.inventory, (TileEntityFuelPump) entity);
				}
			}
		}

		else if (id == Reference.GUI_DISTILLER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityDistiller) {

					return new ContainerDistiller(player.inventory, (TileEntityDistiller) entity);
				}
			}
		}

		else if (id == Reference.GUI_FERMENTER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFermenter) {

					return new ContainerFermenter(player.inventory, (TileEntityFermenter) entity);
				}
			}
		} else if (id == Reference.GUI_SCREEN) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityScreen) {

					return new ContainerScreen(player.inventory, (TileEntityScreen) entity);
				}
			}
		} else if (id == Reference.GUI_CENTRIFUGE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityCentrifuge) {

					return new ContainerCentrifuge(player.inventory, (TileEntityCentrifuge) entity);
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
		} else if (id == Reference.GUI_PAVER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityPaver) {
					return new GuiPaver(player.inventory, (EntityPaver) entity);
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

		else if (id == Reference.GUI_CANNER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFuelPump) {
					return new GuiCanner(player.inventory, (TileEntityFuelPump) entity);
				}
			}
		}

		else if (id == Reference.GUI_DISTILLER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityDistiller) {
					return new GuiDistiller(player.inventory, (TileEntityDistiller) entity);
				}
			}
		}

		else if (id == Reference.GUI_FERMENTER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFermenter) {
					return new GuiFermenter(player.inventory, (TileEntityFermenter) entity);
				}
			}
		}

		else if (id == Reference.GUI_SCREEN) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityScreen) {
					return new GuiScreen(player.inventory, (TileEntityScreen) entity);
				}
			}
		} else if (id == Reference.GUI_CENTRIFUGE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityCentrifuge) {
					return new GuiCentrifuge(player.inventory, (TileEntityCentrifuge) entity);
				}
			}
		}

		return null;

	}
}