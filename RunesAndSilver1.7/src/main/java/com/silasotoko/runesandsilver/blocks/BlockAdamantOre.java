package com.silasotoko.runesandsilver.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.Init.ModItems;
import com.silasotoko.runesandsilver.Reference.Reference;
import com.silasotoko.runesandsilver.item.ItemRS;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAdamantOre extends BlockRS{
	protected IIcon blockIcon;
	
    public BlockAdamantOre(Material material)
    {
        super(Material.rock);
        this.setBlockName("AdamantOre");
        this.setHardness(10.0F).setResistance(50.0F).setStepSound(soundTypeStone);
        this.setCreativeTab(RunesAndSilver.tabRunesAndSilver);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return ModItems.AdamantOre;
    }
    
    public int quantityDroppedWithBonus(int par1, Random rand)
    {
        return this.quantityDropped(rand) + rand.nextInt(par1 + 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random rand)
    {
        return 1 + rand.nextInt(1);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon registerIcons(IIconRegister reg) { // Make sure to import IconRegister!
			return blockIcon = reg.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
		 }

public void dropBlockAsItemWithChance (World world, int par2, int par3, int par4, int par5, float par6, int par7) 
{
		super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);
}
		
		private Random rand = new Random();
		@Override // World, meta, fortune
		public int getExpDrop(IBlockAccess access, int par1, int par2)
		{
		    if (this.getItemDropped(par1, rand, par2) != Item.getItemFromBlock(this))
		    {
		        return 1 + rand.nextInt(5);
		    }
		    return 0;
		}
}
