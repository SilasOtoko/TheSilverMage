package com.silasotoko.runesandsilver.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityCreeperHeart extends EntityThrowable{

	public EntityCreeperHeart(World world) {
		super(world);
		
	}
	
	public EntityCreeperHeart(World par1World, EntityLivingBase par3EntityPlayer)
	{
		super(par1World, par3EntityPlayer);
	}
	
	@SideOnly(Side.CLIENT)
	public EntityCreeperHeart(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}


	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		
		float f = 3.0F;
		
		if(!this.worldObj.isRemote){
			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);
			this.setDead();
		}
		
	}

}
