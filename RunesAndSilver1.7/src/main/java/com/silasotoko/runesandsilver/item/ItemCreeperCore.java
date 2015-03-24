package com.silasotoko.runesandsilver.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.entities.EntityCreeperHeart;

public class ItemCreeperCore extends ItemRS {
	public ItemCreeperCore(){
		super();
		this.maxStackSize = 16;
		this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
		this.setUnlocalizedName("CreeperCore");
		this.setTextureName("CreeperCore");
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote){
            par2World.spawnEntityInWorld(new EntityCreeperHeart(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
	}
}
