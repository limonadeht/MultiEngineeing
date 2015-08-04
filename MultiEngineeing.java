package com.lyle.multiengineering;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod( modid = MultiEngineeing.MODID, version = MultiEngineeing.VERSION, acceptedMinecraftVersions = "[1.7,)" )
public class MultiEngineeing
{
	@Mod.Instance("MultiEngineeing")
	public static final String MODID = "Multi-Engineeing";
	public static final String VERSION = "Alpha-1.0.0";
	public static final String acceptedMinecraftVersions = "[1.7,)";

	@EventHandler
	public void preInit( FMLPreInitializationEvent e )
	{

	}

	@EventHandler
	public void Init( FMLInitializationEvent e )
	{

	}

}
