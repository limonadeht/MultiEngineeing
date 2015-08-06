package com.lyle.multiengineering;

import com.lyle.multiengineering.block.TileFluidTab;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;

public class CommonProxyFT
{


	public void registerTileEntity()
    {
		GameRegistry.registerTileEntity(TileFluidTab.class, "TileFluidTab");
    }

	public int getRenderID()
	{
		return -1;
	}

	public World getClientWorld() {

		return null;
	}


}
