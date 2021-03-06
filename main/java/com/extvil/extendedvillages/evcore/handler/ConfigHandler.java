package com.extvil.extendedvillages.evcore.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler 
{
	public static Configuration config;
	public static int MillerID = 10;
	public static int FisherID = 11;
	public static int BakerID = 12;
	public static int MinerID = 13;
	public static int ScientistID = 18;
	public static int TannerID = 25;
	public static int PalID = 26;

	
	public static boolean CustomRender = true;
	
	public static void LoadFile(File configFile)
	{
		config = new Configuration(configFile);
		
		config.load();
		syncConfig();
		
		FMLCommonHandler.instance().bus().register(new ConfigChanger());
	}
	
	public static void syncConfig()
	{
		MillerID = config.getInt("Miller Villager ID", "villagers", MillerID, 0, 300, "Villager ID for Miller");
		FisherID = config.getInt("Fisher Villager ID", "villagers", FisherID, 0, 300, "Villager ID for Fisher");
		BakerID = config.getInt("Baker Villager ID", "villagers", BakerID, 0, 300, "Villager ID for Baker");
		MinerID = config.getInt("Miner Villager ID", "villagers", MinerID, 0, 300, "Villager ID for Miner");
		ScientistID = config.getInt("Scientist Villager ID", "villagers", ScientistID, 0, 300, "Villager ID for Scientist");
		TannerID = config.getInt("Tanner Villager ID", "villagers", TannerID, 0, 300, "Villager ID for Tanner");
		PalID = config.getInt("Pal Villager ID", "villagers", PalID, 0, 300, "Villager ID for Pal");
		CustomRender = config.getBoolean("Custom Render", "customrender", CustomRender, "Enable/Disable custom Render. Turn this off if you want to use a texture-pack");
		
		if(config.hasChanged())	
		config.save();
	}
	
	public static class ConfigChanger {

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals("extvil"))
				syncConfig();
		}
	}
}
