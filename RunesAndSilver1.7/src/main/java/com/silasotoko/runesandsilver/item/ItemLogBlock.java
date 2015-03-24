package com.silasotoko.runesandsilver.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemLogBlock extends ItemBlock{
	
	public static final String[] logs = new String[] {"Doruwood"};
	
	public ItemLogBlock(Block block){
		super(block);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("");
	}
	
	public String getUnlocalizedName(ItemStack itemstack){
		int i = itemstack.getItemDamage();
		if (i < 0 || i >= logs.length){
			i = 0;
		}
		
		return super.getUnlocalizedName() + "." + logs[i];
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	
}
