package com.extvil.extendedvillages.evcore;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import com.extvil.extendedvillages.evcore.handler.ConfigHandler;
import com.extvil.extendedvillages.evworldgen.blocks.BlockFakeSandStone;
import com.extvil.extendedvillages.evworldgen.components.ComponentBakery;
import com.extvil.extendedvillages.evworldgen.components.ComponentFishHut;
import com.extvil.extendedvillages.evworldgen.components.ComponentWindmill;
import com.extvil.extendedvillages.evworldgen.structurehandlers.BakeryHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.FishHutHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.WindMillHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerBakeryTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerFishHutTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerWindMillTradeHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;



@Mod (modid = "extvil", name = "Extended_Villages", version = "1.7.10-1.2", guiFactory = "com.extvil.extendedvillages.evcore.ExtendedVillagesGUIFactory")


public class ExtendedVillages 
{
	public static String modid = "extvil";	
	@SidedProxy(clientSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesClient", serverSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesProxy")
	public static ExtendedVillagesProxy proxy;

	@Mod.Instance("extvil")
	public static ExtendedVillages instance;

    public static final ResourceLocation MILLER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Miller.png");
    public static final ResourceLocation FISHER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Fisher.png");
    public static final ResourceLocation BAKER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Baker.png");
	
	
	public static Block SmoothSand;
	public static Block DecoSand;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{   
		ConfigHandler.LoadFile(event.getSuggestedConfigurationFile());

		SmoothSand = new BlockFakeSandStone(0, 0).setHardness(1.0F).setBlockName("SmoothSand").setBlockTextureName("sandstone_smooth");
		DecoSand = new BlockFakeSandStone(0, 0).setHardness(1.0F).setBlockName("DecoSand").setBlockTextureName("sandstone_carved");
		
		
		GameRegistry.registerBlock(SmoothSand, "SmoothSand");
		GameRegistry.registerBlock(DecoSand, "DecoSand");

	}
	
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
		
		int millvillager = ConfigHandler.MillerID;
        VillagerRegistry.instance().registerVillagerId(millvillager);
        VillagerRegistry.instance().registerVillageTradeHandler(millvillager, new VillagerWindMillTradeHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new WindMillHandler());
		VillagerRegistry.instance().registerVillagerSkin(millvillager, this.MILLER_TEXTURE);
		
		MapGenStructureIO.func_143031_a(ComponentWindmill.class, "extvil:Windmill");
		
		int fishvillager = ConfigHandler.FisherID;
        VillagerRegistry.instance().registerVillagerId(fishvillager);
        VillagerRegistry.instance().registerVillageTradeHandler(fishvillager, new VillagerFishHutTradeHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new FishHutHandler());
		VillagerRegistry.instance().registerVillagerSkin(fishvillager, this.FISHER_TEXTURE);
		
		MapGenStructureIO.func_143031_a(ComponentFishHut.class, "extvil:FishHut");
		
		int backeryvillager = ConfigHandler.BakerID;
        VillagerRegistry.instance().registerVillagerId(backeryvillager);
        VillagerRegistry.instance().registerVillageTradeHandler(backeryvillager, new VillagerBakeryTradeHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new BakeryHandler());
		VillagerRegistry.instance().registerVillagerSkin(backeryvillager, this.BAKER_TEXTURE);
		
		MapGenStructureIO.func_143031_a(ComponentBakery.class, "extvil:Bakery");
	}
}
