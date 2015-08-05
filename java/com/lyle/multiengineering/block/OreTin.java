package com.lyle.multiengineering.block;

import java.util.Random;

import com.lyle.multiengineering.MultiEngineeing;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class OreTin extends Block
{


	public OreTin()
	{
		super(Material.iron);
		this.setHardness(1.4F); //硬さ
		this.setResistance(1.0F); //爆破耐性
		this.setStepSound(Block.soundTypeStone); //音
	}

	//ドロップするブロック
	//@Override
	public Block getBlockDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return MultiEngineeing.OreTin;
	}

	//ドロップする数
	@Override
	public int quantityDropped(Random rand)
	{
		return 1;
	}

	//ブロックを右クリック
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float disX, float disY, float disZ)
	{
		return false;
	}



}
