package com.silasotoko.runesandsilver.proxy;

import com.silasotoko.runesandsilver.Init.ModItems;
import com.silasotoko.runesandsilver.entities.EntityCreeperHeart;
import com.silasotoko.runesandsilver.rendering.RenderCreeperHeart;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperHeart.class, new RenderCreeperHeart(ModItems.CreeperCore));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
}
