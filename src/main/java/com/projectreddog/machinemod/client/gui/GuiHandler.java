package com.projectreddog.machinemod.client.gui;

import com.projectreddog.machinemod.container.ContainerAssemblyTable;
import com.projectreddog.machinemod.container.ContainerBagger;
import com.projectreddog.machinemod.container.ContainerCanner;
import com.projectreddog.machinemod.container.ContainerCentrifuge;
import com.projectreddog.machinemod.container.ContainerChopper;
import com.projectreddog.machinemod.container.ContainerCombine;
import com.projectreddog.machinemod.container.ContainerContinuousMiner;
import com.projectreddog.machinemod.container.ContainerDistiller;
import com.projectreddog.machinemod.container.ContainerDumpTruck;
import com.projectreddog.machinemod.container.ContainerExcavator;
import com.projectreddog.machinemod.container.ContainerFermenter;
import com.projectreddog.machinemod.container.ContainerFractionalDistiller;
import com.projectreddog.machinemod.container.ContainerGrader;
import com.projectreddog.machinemod.container.ContainerLaserMiner;
import com.projectreddog.machinemod.container.ContainerLoader;
import com.projectreddog.machinemod.container.ContainerPaver;
import com.projectreddog.machinemod.container.ContainerPrimaryCrusher;
import com.projectreddog.machinemod.container.ContainerScreen;
import com.projectreddog.machinemod.container.ContainerTrackLoader;
import com.projectreddog.machinemod.container.ContainerTractor;
import com.projectreddog.machinemod.container.ContainerTurboFurnace;
import com.projectreddog.machinemod.container.ContainerWideBedTruck;
import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityContinuousMiner;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLaserMiner;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.entity.EntityTrackLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;
import com.projectreddog.machinemod.tileentities.TileEntityCentrifuge;
import com.projectreddog.machinemod.tileentities.TileEntityDistiller;
import com.projectreddog.machinemod.tileentities.TileEntityFermenter;
import com.projectreddog.machinemod.tileentities.TileEntityFractionalDistillation;
import com.projectreddog.machinemod.tileentities.TileEntityFuelPump;
import com.projectreddog.machinemod.tileentities.TileEntityPrimaryCrusher;
import com.projectreddog.machinemod.tileentities.TileEntityScreen;
import com.projectreddog.machinemod.tileentities.TileEntityTurboFurnace;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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
		} else if (id == Reference.GUI_BAGGER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityBagger) {

					return new ContainerBagger(player.inventory, (EntityBagger) entity);
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
				if (entity instanceof EntitySemiTractor) {

					return new ContainerWideBedTruck(player.inventory, (EntitySemiTractor) entity);
				}
			}
		} else if (id == Reference.GUI_CHOPPER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityChopper) {

					return new ContainerChopper(player.inventory, (EntityChopper) entity);
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
		} else if (id == Reference.GUI_ASSEMBLY_TABLE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityAssemblyTable) {

					return new ContainerAssemblyTable(player.inventory, (TileEntityAssemblyTable) entity);
				}
			}
		} else if (id == Reference.GUI_FRACTIONALDISTILLATION) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFractionalDistillation) {

					return new ContainerFractionalDistiller(player.inventory, (TileEntityFractionalDistillation) entity);
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
		} else if (id == Reference.GUI_TURBO_FURNACE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityTurboFurnace) {

					return new ContainerTurboFurnace(player.inventory, (TileEntityTurboFurnace) entity);
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
		} else if (id == Reference.GUI_GRADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityGrader) {

					return new ContainerGrader(player.inventory, (EntityGrader) entity);
				}
			}
		} else if (id == Reference.GUI_EXCAVATOR) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityExcavator) {

					return new ContainerExcavator(player.inventory, (EntityExcavator) entity);
				}
			}
		} else if (id == Reference.GUI_PRIMARY_CRUSHER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityPrimaryCrusher) {

					return new ContainerPrimaryCrusher(player.inventory, (TileEntityPrimaryCrusher) entity);
				}
			}
		} else if (id == Reference.GUI_CONTINUOUSMINER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityContinuousMiner) {

					return new ContainerContinuousMiner(player.inventory, (EntityContinuousMiner) entity);
				}
			}
		} else if (id == Reference.GUI_TRACK_LOADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityTrackLoader) {

					return new ContainerTrackLoader(player.inventory, (EntityTrackLoader) entity);
				}
			}
		}

		else if (id == Reference.GUI_LASAERMINER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityLaserMiner) {

					return new ContainerLaserMiner(player.inventory, (EntityLaserMiner) entity);
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
		} else if (id == Reference.GUI_BAGGER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityBagger) {
					return new GuiBagger(player.inventory, (EntityBagger) entity);
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
				if (entity instanceof EntitySemiTractor) {
					return new GuiWideBedTruck(player.inventory, (EntitySemiTractor) entity);
				}
			}
		} else if (id == Reference.GUI_CHOPPER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityChopper) {
					return new GuiChopper(player.inventory, (EntityChopper) entity);
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
		} else if (id == Reference.GUI_ASSEMBLY_TABLE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityAssemblyTable) {
					return new GuiAssemblyTable(player.inventory, (TileEntityAssemblyTable) entity);
				}
			}
		} else if (id == Reference.GUI_FRACTIONALDISTILLATION) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFractionalDistillation) {
					return new GuiFractionalDistiller(player.inventory, (TileEntityFractionalDistillation) entity);
				}
			}
		} else if (id == Reference.GUI_FERMENTER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityFermenter) {
					return new GuiFermenter(player.inventory, (TileEntityFermenter) entity);
				}
			}
		} else if (id == Reference.GUI_TURBO_FURNACE) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityTurboFurnace) {
					return new GuiTurboFurnace(player.inventory, (TileEntityTurboFurnace) entity);
				}
			}
		} else if (id == Reference.GUI_SCREEN) {

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
		} else if (id == Reference.GUI_GRADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityGrader) {
					return new GuiGrader(player.inventory, (EntityGrader) entity);
				}
			}
		} else if (id == Reference.GUI_EXCAVATOR) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityExcavator) {
					return new GuiExcavator(player.inventory, (EntityExcavator) entity);
				}
			}
		} else if (id == Reference.GUI_PRIMARY_CRUSHER) {

			TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
			if (entity != null) {
				if (entity instanceof TileEntityPrimaryCrusher) {
					return new GuiPrimaryCrusher(player.inventory, (TileEntityPrimaryCrusher) entity);
				}
			}
		} else if (id == Reference.GUI_CONTINUOUSMINER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityContinuousMiner) {
					return new GuiContinuousMiner(player.inventory, (EntityContinuousMiner) entity);
				}
			}
		} else if (id == Reference.GUI_TRACK_LOADER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityTrackLoader) {
					return new GuiTrackLoader(player.inventory, (EntityTrackLoader) entity);
				}
			}
		} else if (id == Reference.GUI_LASAERMINER) {

			Entity entity = world.getEntityByID(x);
			if (entity != null) {
				if (entity instanceof EntityLaserMiner) {
					return new GuiLaserMiner(player.inventory, (EntityLaserMiner) entity);
				}
			}
		}

		return null;

	}
}