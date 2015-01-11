package com.projectreddog.machinemod.creativetab;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMachineMod  {
//all creative tabls should define icon & name
	
	public static final CreativeTabs MACHINEMOD_TAB = new CreativeTabs(Reference.MOD_ID) 
	{
		
		@Override
		public Item getTabIconItem() {
			
			return ModItems.bulldozer;
		}
		
		@Override
		public String getTranslatedTabLabel(){
			return "Machine Mod";
		}
	};
}
