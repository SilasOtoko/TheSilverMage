package com.silasotoko.runesandsilver.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.google.common.collect.Sets;
import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.Reference.Reference;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNatureStaff extends ItemTool{
	
	private static final Set field_150916_c = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.clay, Blocks.farmland, Blocks.mycelium});
	
	public ItemNatureStaff(Item.ToolMaterial p_i45353_1_){
		super(1.0F, RunesAndSilver.DoruwoodMaterial, field_150916_c);
		this.setMaxStackSize(1);
		this.setMaxDamage(500);
		this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
		this.setUnlocalizedName("NatureStaff");
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Result.ALLOW)
            {
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }

            Block i1 = par3World.getBlock(par4, par5, par6);
            boolean air = par3World.isAirBlock(par4, par5 + 1, par6);

            //Forge: Change 0 to air, also BugFix: parens mismatch causing you to be able to hoe dirt under dirt/grass
            if (par7 == 0 || !air || (i1 != Blocks.dirt))
            {
            	if (par7 == 0 || !air || (i1 != Blocks.sand)){
            		if (par7 == 0 || !air || (i1 != Blocks.netherrack)){
            			if (par7 == 0 || !air || (i1 != Blocks.end_stone)){
            				if (par7 == 0 || (i1 != Blocks.cobblestone)){
            			
            	return false;
            				}else{
                      			 Block block1 = Blocks.mossy_cobblestone;
                                   par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                                   if (par3World.isRemote)
                                   {
                                       return true;
                                   }
                                   else
                                   {
                                       par3World.setBlock(par4, par5, par6, block1);
                                       par1ItemStack.damageItem(5, par2EntityPlayer);
                                       return true;
                                   }
                                 }
            			}else{
               			 Block block = Blocks.grass;
                            par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                            if (par3World.isRemote)
                            {
                                return true;
                            }
                            else
                            {
                                par3World.setBlock(par4, par5, par6, block);
                                par1ItemStack.damageItem(5, par2EntityPlayer);
                                return true;
                            }
                          }
            		}else{
            			 Block block = Blocks.grass;
                         par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                         if (par3World.isRemote)
                         {
                             return true;
                         }
                         else
                         {
                             par3World.setBlock(par4, par5, par6, block);
                             par1ItemStack.damageItem(5, par2EntityPlayer);
                             return true;
                         }
                       }
            		
               }else{
            	   Block block = Blocks.grass;
                   par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                   if (par3World.isRemote)
                   {
                       return true;
                   }
                   else
                   {
                       par3World.setBlock(par4, par5, par6, block);
                       par1ItemStack.damageItem(5, par2EntityPlayer);
                       return true;
                   }
                 }
            }
            
            else
            {
                Block block = Blocks.grass;
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                if (par3World.isRemote)
                {
                    return true;
                }
                else
                {
                    par3World.setBlock(par4, par5, par6, block);
                    par1ItemStack.damageItem(5, par2EntityPlayer);
                    return true;
                }
              }
        }
        }

        
	
	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
