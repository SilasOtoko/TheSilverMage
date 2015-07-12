package com.silasotoko.runesandsilver.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityWaterTalisman extends EntityThrowable{

	public EntityWaterTalisman(World world) {
		super(world);
		
	}
	
	public EntityWaterTalisman(World par1World, EntityLivingBase par3EntityPlayer)
	{
		super(par1World, par3EntityPlayer);
	}
	
	@SideOnly(Side.CLIENT)
	public EntityWaterTalisman(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		// TODO Auto-generated method stub
		
	}

	
}