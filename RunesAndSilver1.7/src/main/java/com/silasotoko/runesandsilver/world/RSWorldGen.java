package com.silasotoko.runesandsilver.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.silasotoko.runesandsilver.Init.ModBlocks;

import cpw.mods.fml.common.IWorldGenerator;

public class RSWorldGen implements IWorldGenerator{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		switch (world.provider.dimensionId){
		case 0: //Overworld
			this.runGenerator(this.gen_adamant_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_mithril_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_ember_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_hydor_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_aeir_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_nature_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			this.runGenerator(this.gen_silver_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			break;
		case -1: //Nether
			break;
		case 1: //End
			break;
		}
	}
	private WorldGenerator gen_adamant_ore;
	private WorldGenerator gen_mithril_ore;
	private WorldGenerator gen_ember_ore;
	private WorldGenerator gen_hydor_ore;
	private WorldGenerator gen_aeir_ore;
	private WorldGenerator gen_nature_ore;
	private WorldGenerator gen_silver_ore;
	
	public RSWorldGen(){
		this.gen_adamant_ore = new WorldGenMinable(ModBlocks.OreAdamant, 8);
		this.gen_mithril_ore = new WorldGenMinable(ModBlocks.OreMithril, 8);
		this.gen_ember_ore = new WorldGenMinable(ModBlocks.OreEmber, 8);
		this.gen_hydor_ore = new WorldGenMinable(ModBlocks.OreHydor, 8);
		this.gen_aeir_ore = new WorldGenMinable(ModBlocks.OreAeir, 8);
		this.gen_nature_ore = new WorldGenMinable(ModBlocks.OreNature, 8);
		this.gen_silver_ore = new WorldGenMinable(ModBlocks.OreSilver, 8);
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight){
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		
		int heightDifference = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++){
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDifference);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}
}