package com.silasotoko.runesandsilver.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.Init.ModItems;

public class ItemElfSight extends ItemRS {
	public ItemElfSight(){
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
		this.setUnlocalizedName("ElfSight");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer Player){
		Player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 3));
		world.playSoundAtEntity(Player, "random.breath", 0.9F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		return new ItemStack(ModItems.RuneDust);
}
}
