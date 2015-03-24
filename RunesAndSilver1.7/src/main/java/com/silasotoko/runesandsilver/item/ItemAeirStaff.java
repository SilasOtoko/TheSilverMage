package com.silasotoko.runesandsilver.item;

import javax.swing.Icon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.Reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAeirStaff extends ItemSword {
	
	private Object damageVsEntity;
	private Object knockbackStrength;
	private IIcon[] Texture = new IIcon[4];
	
	
	public ItemAeirStaff()
	{
		super(RunesAndSilver.DoruwoodMaterial);
		this.maxStackSize = 1;
		this.setMaxDamage(500);
		this.setFull3D();
		this.damageVsEntity = 4.00F;
		this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
		this.setUnlocalizedName("AeirStaff");
	}
	
	public void onUpdate(ItemStack itemstack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if(itemstack.isItemEnchanted()==false){
			itemstack.addEnchantment(Enchantment.knockback, 5);
		}
	}
	
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return false;
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		return true;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);

        
        
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        float f = (float)j / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        
        if ((double)f < 0.1D)
        {
            return;
        }
        
        if (f > 6.0F)
        {
        	f = 4.0F;
        }
        
       
        if ((f >= 4.0F) && (f < 6.0F))
        {
        	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 90, 20));
        	par1ItemStack.damageItem(8, par3EntityPlayer);
        	par2World.playSoundAtEntity(par3EntityPlayer, "random.orb", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        }
        
        if ((f >= 2.5F) && (f < 4.0F))
        {
        	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 60, 15));
        	par1ItemStack.damageItem(6, par3EntityPlayer);
        	par2World.playSoundAtEntity(par3EntityPlayer, "random.orb", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        }
        
        if ((f >= 1.0F) && (f < 2.5F))
        {
        	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 50, 10));
        	par1ItemStack.damageItem(4, par3EntityPlayer);
        	par2World.playSoundAtEntity(par3EntityPlayer, "random.orb", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        }
        
        else
        {

        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 50, 4));
            par1ItemStack.damageItem(2, par3EntityPlayer);
           par2World.playSoundAtEntity(par3EntityPlayer, "random.orb", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

        }
        
        
    }

	 public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        return par1ItemStack;
	    }

	 public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }

	    /**
	     * returns the action that specifies what animation to play when the items is being used
	     */
	    public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.bow;
	    }

	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
	        MinecraftForge.EVENT_BUS.post(event);

	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        if (par3EntityPlayer.capabilities.isCreativeMode || !par3EntityPlayer.capabilities.isCreativeMode)
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	            
	        }

	        return par1ItemStack;
	    }

	    /**
	     * Return the enchantability factor of the item, most of the time is based on material.
	     */
	    public int getItemEnchantability()
	    {
	        return 1;
	    }
	    
	    public boolean canAttackWithItem()
	    {
	        return true;
	    }
	    
	    @SideOnly(Side.CLIENT)

		public void registerIcons(IIconRegister iconRegister)

		
	    {
			itemIcon = iconRegister.registerIcon(Reference.MOD_ID+ ":" + (this.getUnlocalizedName().substring(5) + "_0"));
	        for (int N = 0; N < 4; N++)
	                  {
	     	   this.Texture[N] = iconRegister.registerIcon(Reference.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + "_" + N));
	                  }
		}
		public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
		{
		                         if(player.getItemInUse() == null) return this.itemIcon;
		                         int Pulling = stack.getMaxItemUseDuration() - useRemaining;
		                         if (Pulling >= 50)
		                         {
		                                         return Texture[3];
		                         }
		                         else if (Pulling > 34)
		                         {
		                                         return Texture[2];
		                         }
		                         else if (Pulling > 18)
		                         {
		                                         return Texture[1];
		                         }
		                         return Texture[0];
		                         }
	
	/*@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	/*@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}*/
}
