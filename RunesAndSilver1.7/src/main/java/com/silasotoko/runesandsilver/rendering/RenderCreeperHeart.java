package com.silasotoko.runesandsilver.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//@SideOnly(Side.CLIENT)
public class RenderCreeperHeart extends RenderSnowball
{

	public RenderCreeperHeart(Item par1Item, int par2) {
		super(par1Item, par2);
	}
	public RenderCreeperHeart(Item par1Item)
	{
	this(par1Item, 0);
	}
    
	
}