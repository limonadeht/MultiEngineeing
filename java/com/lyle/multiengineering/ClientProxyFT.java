package com.lyle.multiengineering;

import com.lyle.multiengineering.block.TileFluidTab;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.world.World;

public class ClientProxyFT extends CommonProxyFT
{


	@Override
	public void registerTileEntity()
    {
		RenderingRegistry.registerBlockHandler(new RenderFluidTankBlock());
		ClientRegistry.registerTileEntity(TileFluidTab.class, "TileFluidTab", new TileEntityFluidTabRenderer());
    }

	@Override
	public int getRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public World getClientWorld() {

		return FMLClientHandler.instance().getClient().theWorld;
	}


}
