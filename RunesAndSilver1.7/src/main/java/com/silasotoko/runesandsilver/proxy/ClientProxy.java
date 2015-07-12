package com.silasotoko.runesandsilver.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.silasotoko.runesandsilver.Init.ModItems;
import com.silasotoko.runesandsilver.entities.EntityCreeperHeart;
import com.silasotoko.runesandsilver.entities.EntityForestWalker;
import com.silasotoko.runesandsilver.entities.EntityNetherGolem;
import com.silasotoko.runesandsilver.entities.EntityRunestoneGolem;
import com.silasotoko.runesandsilver.entities.EntitySnowGolem;
import com.silasotoko.runesandsilver.entities.ModelForestWalker;
import com.silasotoko.runesandsilver.entities.ModelNetherGolem;
import com.silasotoko.runesandsilver.entities.ModelRunestoneGolem;
import com.silasotoko.runesandsilver.entities.ModelSnowGolem;
import com.silasotoko.runesandsilver.entities.RenderForestWalker;
import com.silasotoko.runesandsilver.entities.RenderNetherGolem;
import com.silasotoko.runesandsilver.entities.RenderRunestoneGolem;
import com.silasotoko.runesandsilver.entities.RenderSnowGolem;
import com.silasotoko.runesandsilver.rendering.RenderCreeperHeart;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperHeart.class, new RenderCreeperHeart(ModItems.CreeperCore));
		RenderingRegistry.registerEntityRenderingHandler(EntityForestWalker.class, new RenderForestWalker(new ModelForestWalker(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnowGolem.class, new RenderSnowGolem(new ModelSnowGolem(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherGolem.class, new RenderNetherGolem(new ModelNetherGolem(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRunestoneGolem.class, new RenderRunestoneGolem(new ModelRunestoneGolem(), 0.5F));
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
