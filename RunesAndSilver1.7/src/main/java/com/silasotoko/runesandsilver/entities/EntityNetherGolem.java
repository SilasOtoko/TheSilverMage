package com.silasotoko.runesandsilver.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.silasotoko.runesandsilver.Init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityNetherGolem extends EntityCreature {
	private int attackTimer;
    private int homeCheckTimer = 0;
    private int holdRoseTick;
    public static boolean[] carryableBlocks = new boolean[4096];
    

	public EntityNetherGolem(World par1World){
		super(par1World);
		this.isImmuneToFire = true;
        this.setSize(1.4F, 2.9F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
	}

    protected void entityInit()
    {
        super.entityInit();
            this.dataWatcher.addObject(19, new Byte((byte)0));
            this.dataWatcher.addObject(20, new Byte((byte)0));
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("carried", (short)this.getCarried());
        par1NBTTagCompound.setShort("carriedData", (short)this.getCarryingData());
      
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setCarried(par1NBTTagCompound.getShort("carried"));
        this.setCarryingData(par1NBTTagCompound.getShort("carriedData"));
   
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick()
    {
        super.updateAITick();
    }

    @Override
    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
    	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D); //Recomended speed
  
    }

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int par1)
    {
        return par1;
    }

    @Override
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.attackTimer > 0)
        {
            --this.attackTimer;
        }
        if (this.holdRoseTick > 0)
        {
            --this.holdRoseTick;
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
        {
            j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
            Block block = this.worldObj.getBlock(i, j, k);

            if (block.getMaterial() != Material.air)
            {
                this.worldObj.spawnParticle("tilecrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D);
            }
        }
        
        for (int l = 0; l < 4; ++l)
        {
            i = MathHelper.floor_double(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            j = MathHelper.floor_double(this.posY);
            k = MathHelper.floor_double(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));

            if (this.worldObj.getBlock(i, j, k).getMaterial() == Material.air && this.worldObj.getBiomeGenForCoords(i, k).getFloatTemperature(i, j, k) > 0.8F && Blocks.fire.canPlaceBlockAt(this.worldObj, i, j, k))
            {
                this.worldObj.setBlock(i, j, k, Blocks.fire);
            }
        }
        
        
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    @Override
    public boolean canAttackClass(Class par1Class)
    {
    	return super.canAttackClass(par1Class);
    }
    
    protected void collideWithEntity(Entity par1Entity)
    {
        if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0)
        {
            this.setAttackTarget((EntityLivingBase)par1Entity);
        }

        super.collideWithEntity(par1Entity);
    }

    

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        this.attackTimer = 10;
        this.worldObj.setEntityState(this, (byte)4);
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag)
        {
            par1Entity.motionY += 0.4000000059604645D;
        }

        this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 4)
        {
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
        }
        else if (par1 == 11)
        {
            this.holdRoseTick = 400;
        }
        else
        {
            super.handleHealthUpdate(par1);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer()
    {
        return this.attackTimer;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.irongolem.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.irongolem.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(2);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(ModItems.SilverOre, 2);
        }

        k = 3 + this.rand.nextInt(3);

        for (int l = 0; l < k; ++l)
        {
            this.dropItem(ModItems.EmberOre, 2);
        }
    }
    
    /**
     * Set the id of the block an enderman carries
     */
    public void setCarried(int par1)
    {
        this.dataWatcher.updateObject(19, Byte.valueOf((byte)(par1 & 255)));
    }

    /**
     * Get the id of the block an enderman carries
     */
    public int getCarried()
    {
        return this.dataWatcher.getWatchableObjectByte(19);
    }

    /**
     * Set the metadata of the block an enderman carries
     */
    public void setCarryingData(int par1)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 255)));
    }

    /**
     * Get the metadata of the block an enderman carries
     */
    public int getCarryingData()
    {
        return this.dataWatcher.getWatchableObjectByte(20);
    }
    
   
    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);
    }

    protected boolean canDespawn()
    {
        return true;
    }

    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	public void initCreature() {
	}
	
	static
    {
        //carryableBlocks[Block.fire.blockID] = true;
        
    }
	
	
	
	protected void addRandomArmor()
    {
        super.addRandomArmor();

       
    }
	
	public int getHoldRoseTick()
    {
        return this.holdRoseTick;
    }

	public void setHoldingRose(boolean par1)
    {
        this.holdRoseTick = par1 ? 400 : 0;
        this.worldObj.setEntityState(this, (byte)11);
    }

	
	
}
