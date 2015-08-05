package com.lyle.multiengineering;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

@Mod( modid = MultiEngineeing.MODID, version = MultiEngineeing.VERSION, acceptedMinecraftVersions = "[1.7,)" )
public class MultiEngineeing
{
	@Mod.Instance("MultiEngineeing")
	public static final String MODID = "Multi-Engineeing";
	public static final String VERSION = "Alpha-1.0.0";
	public static final String acceptedMinecraftVersions = "[1.7,)";

	public static Item AOPItem;

	@EventHandler
	public void preInit( FMLPreInitializationEvent e )
	{
		//アイテムのインスタンス生成
				AOPItem = new Item()
				.setCreativeTab(MultiEngineeingTab)/*クリエイティブのタブ*/
				.setUnlocalizedName("AOP")/*システム名の登録*/
				.setTextureName("multiengineeing:aop_item")/*テクスチャの指定*/
				/*.setHasSubtypes(true)*//*ダメージ値等で複数の種類のアイテムを分けているかどうか。デフォルトfalse*/
				/*.setMaxDamage(256)*//*耐久値の設定。デフォルト0*/
				/*.setFull3D()*//*３D表示で描画させる。ツールや骨、棒等。*/
				/*.setContainerItem(Items.stick)*//*クラフト時にアイテムを返却できるようにしている際の返却アイテムの指定。*/
				/*.setPotionEffect(PotionHelper.ghastTearEffect)*//*指定文字列に対応した素材として醸造台で使える。PotionHelper参照のこと。*/
				/*.setNoRepair()*//*修理レシピを削除し、金床での修繕を出来なくする*/
				.setMaxStackSize(99);/*スタックできる量。デフォルト64*/
				//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
				GameRegistry.registerItem(AOPItem, "AOP");
	}

	@EventHandler
	public void Init( FMLInitializationEvent e )
	{

	}

	public static CreativeTabs MultiEngineeingTab
    = new CreativeTabs("MultiEngineeing")
{
    public Item getTabIconItem()
    {
        return Items.stick;
    }
};

}


