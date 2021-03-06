package com.silasotoko.runesandsilver.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.silasotoko.runesandsilver.RunesAndSilver;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNatureRuneBag extends ItemRS {
	public ItemNatureRuneBag(){
		super();
		this.setMaxDamage(20);
	    this.setMaxStackSize(1);
		this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
		this.setUnlocalizedName("NatureRunePowderBag");
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
                if (applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer))
                {
                    if (!par3World.isRemote)
                    {
                        par3World.playAuxSFX(2005, par4, par5, par6, 0);
                    }

                    return true;
                }

            else if (par1ItemStack.getItemDamage() == 3)
            {
                Block i1 = par3World.getBlock(par4, par5, par6);
                int j1 = par3World.getBlockMetadata(par4, par5, par6);

                if (i1 == Blocks.log && BlockLog.func_150165_c(j1) == 3)
                {
                    if (par7 == 0)
                    {
                        return false;
                    }

                    if (par7 == 1)
                    {
                        return false;
                    }

                    if (par7 == 2)
                    {
                        --par6;
                    }

                    if (par7 == 3)
                    {
                        ++par6;
                    }

                    if (par7 == 4)
                    {
                        --par4;
                    }

                    if (par7 == 5)
                    {
                        ++par4;
                    }

                    if (par3World.isAirBlock(par4, par5, par6))
                    {
                        int k1 = Blocks.cocoa.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
                        par3World.setBlock(par4, par5, par6, Blocks.cocoa, k1, 2);

                        if (!par2EntityPlayer.capabilities.isCreativeMode)
                        {
                            --par1ItemStack.stackSize;
                        }
                        
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public static boolean func_96604_a(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4)
    {
        return applyBonemeal(par0ItemStack, par1World, par2, par3, par4, FakePlayerFactory.getMinecraft((WorldServer)par1World));
    }

    public static boolean applyBonemeal(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4, EntityPlayer player)
    {
        Block block = par1World.getBlock(par2, par3, par4);

        BonemealEvent event = new BonemealEvent(player, par1World, block, par2, par3, par4);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Result.ALLOW)
        {
            if (!par1World.isRemote)
            {
            	par0ItemStack.damageItem(1, player);
            }
            return true;
        }

        if (block instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)block;

            if (igrowable.func_149851_a(par1World, par2, par3, par4, par1World.isRemote))
            {
                if (!par1World.isRemote)
                {
                    if (igrowable.func_149852_a(par1World, par1World.rand, par2, par3, par4))
                    {
                        igrowable.func_149853_b(par1World, par1World.rand, par2, par3, par4);
                    }

                    --par0ItemStack.stackSize;
                }

                return true;
            }
        }

        return false;
    }

    @SideOnly(Side.CLIENT)
    public static void func_150918_a(World p_150918_0_, int p_150918_1_, int p_150918_2_, int p_150918_3_, int p_150918_4_)
    {
        if (p_150918_4_ == 0)
        {
            p_150918_4_ = 15;
        }

        Block block = p_150918_0_.getBlock(p_150918_1_, p_150918_2_, p_150918_3_);

        if (block.getMaterial() != Material.air)
        {
            block.setBlockBoundsBasedOnState(p_150918_0_, p_150918_1_, p_150918_2_, p_150918_3_);

            for (int i1 = 0; i1 < p_150918_4_; ++i1)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                p_150918_0_.spawnParticle("happyVillager", (double)((float)p_150918_1_ + itemRand.nextFloat()), (double)p_150918_2_ + (double)itemRand.nextFloat() * block.getBlockBoundsMaxY(), (double)((float)p_150918_3_ + itemRand.nextFloat()), d0, d1, d2);
            }
        }
        else
        {
            for (int i1 = 0; i1 < p_150918_4_; ++i1)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                p_150918_0_.spawnParticle("happyVillager", (double)((float)p_150918_1_ + itemRand.nextFloat()), (double)p_150918_2_ + (double)itemRand.nextFloat() * 1.0f, (double)((float)p_150918_3_ + itemRand.nextFloat()), d0, d1, d2);
            }
        }
    }
    
}
