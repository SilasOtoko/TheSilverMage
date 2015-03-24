package com.silasotoko.runesandsilver;

import com.silasotoko.runesandsilver.Init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class TabRS extends CreativeTabs {

	public TabRS(String lable) {
		super(lable);
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		
		return ModItems.ForestWalkerFigurine;
	}

}
