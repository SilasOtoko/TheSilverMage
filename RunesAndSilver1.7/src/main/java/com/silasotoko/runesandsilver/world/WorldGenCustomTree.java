package com.silasotoko.runesandsilver.world;

import java.util.Random;

import com.silasotoko.runesandsilver.RunesAndSilver;
import com.silasotoko.runesandsilver.Init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenCustomTree extends WorldGenAbstractTree{
	private Block wood;
    private Block leaves;

    private int woodMeta;
    private int leavesMeta;

    public WorldGenCustomTree()
    {
        super(false);
     
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
       while (world.isAirBlock(x, y, z) && y > 2)
       {
    	   y--;
       }
       
       Block block = world.getBlock(x,  y, z);
       
       if(block != Blocks.grass && block != Blocks.dirt)
       {
    	   return false;
       }else{
    	   for (int i = -2; i <= 2; i++)
    	   {
    		   for(int j = -2; j <= 2; j++)
    		   {
    			   if (world.isAirBlock(x + i, y - 1, z + j) && world.isAirBlock(x + i, y - 2, z + j) && !world.isAirBlock(x + i, y, z + j))
    			   {
    				   return false;
    			   }
    		   }
    	   }
    	   
    	   //trunk length
    	   int baselength = 4 + random.nextInt(6);
    	   //how many branches
    	   int branches = 2 + random.nextInt(4);
    	   
    	   int h = 1;
    	   
    	   block.onPlantGrow(world, x, y - 1, z, x, y, z);
    	   
    	   for (int i = 0; i < baselength; i++)
    	   {
    		   buildBlock(world, x, y + h, z, Blocks.log, 1);
    		   h++;
    	   }
    	   
    	   int c = 1;
    	   for (int i = 0; i < branches; i++)
    	   {
    		   generateBranch(world, random, x, y + h, z, c);
    		   c++;
    		   h+=2;
    	   }
    	   
    	   generateTop(world, x, y + h, z);
    	   return true;
       }
                    /*TODO: setBlockAndMetadata()
                    this.setBlockAndNotifyAdequately(world, x + 3, y + 0, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 3, y + 0, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 3, y + 0, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 3, y + 1, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 3, y + 6, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 4, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 8, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 1, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 1, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 1, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 2, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 2, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 2, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 3, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 3, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 3, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 4, z + 5, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 4, z + 6, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 4, z + 7, this.wood, this.woodMeta);
                    this.setBlockAndNotifyAdequately(world, x + 4, y + 6, z + 2, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 4, y + 6, z + 3, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 4, y + 6, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 4, y + 7, z + 8, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 5, this.wood, this.woodMeta);
            		//this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 6, RunesAndSilver.ForestTreeCore.blockID);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 8, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 8, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 2, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 2, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 2, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 3, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 3, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 3, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 4, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 4, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 4, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 3, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 6, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 5, y + 8, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 4, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 8, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 1, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 1, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 1, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 2, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 2, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 2, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 3, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 3, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 3, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 4, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 6, y + 5, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 7, y + 0, z + 5, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 7, y + 0, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 7, y + 0, z + 7, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 7, y + 1, z + 6, this.wood, this.woodMeta);
            		this.setBlockAndNotifyAdequately(world, x + 7, y + 5, z + 5, this.wood, this.woodMeta);
            return false;*/
    }
    
    //on top of "branches"
    public void generateTop(World world, int x, int y, int z)
    {
    	for(int i = -1; i < 2; i++)
    	{
    		for(int j = -1; j < 2; j++)
    		{
    			buildBlock(world, x + i, y, z + j, Blocks.leaves, 0);
    		}
    	}
    	buildBlock(world, x, y, z, Blocks.log, 0);
    	buildBlock(world, x, y + 1, z, Blocks.log, 0);
    	buildBlock(world, x + 1, y + 1, z, Blocks.leaves, 0);
    	buildBlock(world, x - 1, y + 1, z, Blocks.leaves, 0);
    	buildBlock(world, x, y + 1, z - 1, Blocks.leaves, 0);
    	buildBlock(world, x, y + 1, z + 1, Blocks.leaves, 0);
    	buildBlock(world, x, y + 2, z, Blocks.leaves, 0);
    }
    
    //area between trunk and top
    public void generateBranch(World world, Random random, int x, int y, int z, int p)
    {
    	for(int i = -1; i < 2; i++)
    	{
    		for(int j = -1; j < 2; j++)
    		{
    			buildBlock(world, x + i, y, z + j, Blocks.leaves, 0);
    		}
    	}
    	buildBlock(world, x + 1, y + 1, z, Blocks.leaves, 0);
    	buildBlock(world, x - 1, y + 1, z, Blocks.leaves, 0);
    	buildBlock(world, x, y + 1, z - 1, Blocks.leaves, 0);
    	buildBlock(world, x, y + 1, z + 1, Blocks.leaves, 0);
    	buildBlock(world, x, y + 2, z, Blocks.log, 0);
    	buildBlock(world, x, y + 1, z, Blocks.log, 0);
    }
    
    public void buildBlock(World world, int x, int y, int z, Block block, int meta)
    {
    	if(world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves(world, x, y, z))
    	{
    		world.setBlock(x, y, z, block, meta, 2);
    	}
    }
                  
}
