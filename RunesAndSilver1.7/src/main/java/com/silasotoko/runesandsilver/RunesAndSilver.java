
package com.silasotoko.runesandsilver;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

import com.silasotoko.runesandsilver.Init.ModBlocks;
import com.silasotoko.runesandsilver.Init.ModItems;
import com.silasotoko.runesandsilver.Reference.Reference;
import com.silasotoko.runesandsilver.configuration.ConfigurationHandler;
import com.silasotoko.runesandsilver.entities.EntityCreeperHeart;
import com.silasotoko.runesandsilver.proxy.CommonProxy;
import com.silasotoko.runesandsilver.proxy.IProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
public class RunesAndSilver {
	
	@Mod.Instance(Reference.MOD_ID)
	public static RunesAndSilver instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	public static CreativeTabs tabRunesAndSilver = new TabRS("Runes and Silver");
	
	
	private static int modEntityIndex = 0;
	
	//Materials
	public static ToolMaterial AdamantMaterial = EnumHelper.addToolMaterial("Adamant", 4, 3300, 12.0F, 6.0F, 12);
	public static ToolMaterial MithrilMaterial = EnumHelper.addToolMaterial("Mithril", 4, 2300, 10.0F, 5.0F, 11);
	public static ToolMaterial RuneMaterial = EnumHelper.addToolMaterial("Rune", 2, 500, 8.0F, 3.0F, 14);
	public static ToolMaterial SilverMaterial = EnumHelper.addToolMaterial("Silver", 2, 500, 7.0F, 3.0F, 14);
	public static ToolMaterial DoruwoodMaterial = EnumHelper.addToolMaterial("Doruwood", 2, 500, 4.0F, 1.0F, 16);
	public static ArmorMaterial Rune = EnumHelper.addArmorMaterial("Rune", 20, new int[]{2, 7, 5, 3}, 10);
	public static ArmorMaterial Adamant = EnumHelper.addArmorMaterial("Adamant", 55, new int[]{5, 10, 8, 5}, 12);
	public static ArmorMaterial Mithril = EnumHelper.addArmorMaterial("Mithril", 45, new int[]{4, 9, 7, 4}, 11);
	public static ArmorMaterial Silver = EnumHelper.addArmorMaterial("Silver", 25, new int[]{2, 7, 5, 3}, 10);
	public static ArmorMaterial Infused = EnumHelper.addArmorMaterial("Infused", 35, new int[]{3, 8, 6, 3}, 10);
	public static ArmorMaterial Doruwood = EnumHelper.addArmorMaterial("Doruwood", 15, new int[]{2, 6, 5, 2}, 30);
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		ModItems.init();
		ModBlocks.init();
		proxy.registerRenderers();
		
		EntityRegistry.registerModEntity(EntityCreeperHeart.class, "CreeperCore", ++modEntityIndex, this, 64, 10, true);
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
	
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
}
