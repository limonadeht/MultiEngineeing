package com.lyle.multiengineering.block;

import java.util.List;

import com.lyle.multiengineering.MultiEngineeing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockFluidTab extends BlockContainer
{


	public BlockFluidTab()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(1.0F);
	}

	//プレイヤーの右クリック処理
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
	/*手持ちアイテム*/
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        /*このブロックのTileEntity*/
        TileFluidTab tile = (TileFluidTab) par1World.getTileEntity(par2, par3, par4);

        /*
         * ちなみに、この段階ではworld.isRemoteの判定を行っていない。
         * 意図的にサーバ・クライアントで同じ処理がそれぞれ行われるようにしている。
         */
        if (tile != null)
        {
        	//TileEntityの液体タンクに入っている液体を取得
    		FluidStack fluid = tile.productTank.getFluid();

        	if (itemstack == null)//素手
        	{
        		//表示用の文字列をとりあえず作成
        		String s = "";

        		if (fluid != null && fluid.getFluid() != null)
        		{
        			s = "Fluid current in the tab : " + fluid.getFluid().getLocalizedName(fluid);
        		}
        		else
        		{
        			s = "No fluid in the tab";
        		}

        		/*
        		 * チャット表示時はリモートワールドの判定を挟む。
        		 * そうしないと、サーバ・クライアントで二重にメッセージが出てしまう。
        		 */
        		if (!par1World.isRemote) par5EntityPlayer.addChatMessage(new ChatComponentText(s));

        		return true;
        	}
        	else
        	{
    			//このメソッドにより、手持ちのアイテムが液体容器に登録されたアイテムかどうか、及び入っている液体を取得する。
    			FluidStack fluid2 =  FluidContainerRegistry.getFluidForFilledItem(itemstack);

    			//満たされた液体コンテナが手持ちの場合
        		if (fluid2 != null && fluid2.getFluid() != null)
        		{
        			/*
    				 * fillメソッドの第二引数にfalseを入れた場合、実際に液体をタンクに入れるのではなく、
    				 * タンクに投入可能な液体の量をシュミレートして値を返す。
    				 */
    				int put = tile.fill(ForgeDirection.UNKNOWN, fluid2, false);

    				//全量投入可能なときのみ
    				if (put == fluid2.amount)
    				{
    					//今度は液体を液体タンクに入れるので、第二引数はtrueにする。
    					tile.fill(ForgeDirection.UNKNOWN, fluid2, true);

                    //液体容器を空にして、空容器を得るメソッド。
    					ItemStack emptyContainer = FluidContainerRegistry.drainFluidContainer(itemstack);
    					if (emptyContainer != null)
    					{
    						if (!par5EntityPlayer.inventory.addItemStackToInventory(emptyContainer.copy()))
        		        	{
        		        		par5EntityPlayer.entityDropItem(emptyContainer.copy(), 1);
        		        	}
    					}

    					//プレイヤーの手持ちアイテムを減らす処理
    					if (!par5EntityPlayer.capabilities.isCreativeMode && itemstack.stackSize-- <= 0)
    	                                {
    	            	                	par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
    	                                }

    				        //更新を伝える処理
    				        //TileEntityを更新した場合、このように更新処理を挟まないと見た目に反映しない。
    				        tile.markDirty();
    	        		        par5EntityPlayer.inventory.markDirty();
    	        		        par1World.markBlockForUpdate(par2, par3, par4);

    	        		        //効果音の発生
    	        		        par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);

    	        		        return true;
    				}
        		}
        		else
        		{
        			//液体タンクに何かしら入っている時
        			if (fluid != null && fluid.getFluid() != null)
        			{
        				if (fluid.amount < 1000) return true;

        				/*
        				 * このメソッドにより、手持ちのアイテムを空容器として指定した液体を入れた「液体で満たされた容器アイテム」を取得している。
        				 * 液体容器に登録された液体の量も判定されるため、とりあえず1000mB（バケツの容量）で判定。
        				 * また、タンク内の液体が1000未満の場合は処理を中断する。
        				 */
        				ItemStack get = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid.getFluid(), 1000), itemstack);

        				if (get != null)
        				{
        				/*
            				 * タンクの液体の減少処理
            				 * タンク容量 > 1000 であることを事前にチェック済みのためにここではシュミレート無しとしたが、
            				 * fillの場合と同様に、シュミレートで投入可能量を確かめでから行っても良いと思う。
            				 */
            				tile.drain(ForgeDirection.UNKNOWN, 1000, true);

            				//プレイヤーに、先に取得した「液体で満たされた容器アイテム」を与える処理
        					if (!par5EntityPlayer.inventory.addItemStackToInventory(get.copy()))
        		        	{
        		        		par5EntityPlayer.entityDropItem(get.copy(), 1);
        		        	}

            				//プレイヤーの手持ちアイテムを減らす処理
        				if (!par5EntityPlayer.capabilities.isCreativeMode && itemstack.stackSize-- <= 0)
        	                        {
        	            	        par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
        	                        }

        				//更新を伝える処理
        				//TileEntityを更新した場合、このように更新処理を挟まないと見た目に反映しない。
        				tile.markDirty();
        	        		par5EntityPlayer.inventory.markDirty();
        	        		par1World.markBlockForUpdate(par2, par3, par4);

        	        		//効果音の発生
        	        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        				}

    	        		        return true;
        			}
        			else
        			{
        				//アイテムが液体入り容器でなく、かつタンクが空だった場合は何もしない
        				return true;
        			}
        		}
        	}
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int a) {

	return new TileFluidTab();
    }

    /*=== レンダー関係  ===*/

    //当たり判定を設定。大釜のように中にエンティティが入り込める。
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        float f = 0.0675F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBoundsForItemRender();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    //追加したブロックレンダークラスのIDを入れる
    @Override
    public int getRenderType()
    {
        return MultiEngineeing.FluidTankRenderId;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = Blocks.planks.getIcon(0, 0);
        //ここで登録するテクスチャは破壊時のエフェクトくらいにしか使用されない。なのでバニラ木材を流用。
    }


}
