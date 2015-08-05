package com.lyle.multiengineering;

import com.lyle.multiengineering.armor.AOPItemArmor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

@Mod( modid = MultiEngineeing.MODID, version = MultiEngineeing.VERSION, acceptedMinecraftVersions = "[1.7,)" )
public class MultiEngineeing
{
	@Mod.Instance("MultiEngineeing")
	public static final String MODID = "Multi-Engineeing";
	public static final String VERSION = "Alpha-1.0.0";
	public static final String acceptedMinecraftVersions = "[1.7,)";

	public static Item AOPItem;

	public static Item AOPItemHelmet;
	public static Item AOPItemChestplate;
	public static Item AOPItemLeggings;
	public static Item AOPItemBoots;

	public static Item SFX2400ItemSword;

	public static Item SFX2400ItemHoe;

	public static Item SFX2400ItemSpade;

	public static Item SFX2400ItemAxe;

	public static Item SFX2400ItemPickaxe;

	@EventHandler
	public void preInit( FMLPreInitializationEvent e )
	{


		//マテリアル追加（ツール）
		Item.ToolMaterial toolSFX = EnumHelper.addToolMaterial("toolSaga", 3, 2000, 600.0F, 600.0F, 30);
		toolSFX.customCraftingMaterial = MultiEngineeing.AOPItem;


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
				.setMaxStackSize(1);/*スタックできる量。デフォルト64*/
				//アイテムの登録。登録文字列はMOD内で被らなければ何でも良い。
				GameRegistry.registerItem(AOPItem, "AOP");


				//マテリアル追加（防具）
				ItemArmor.ArmorMaterial armorAOP = EnumHelper.addArmorMaterial("armorAOP", 50, new int[]{300,300,300,300}, 30);

				//修理用アイテムの設定
				armorAOP.customCraftingMaterial = MultiEngineeing.AOPItem;

				//アーマー
				AOPItemHelmet = new AOPItemArmor(armorAOP,0,0,"saga").setUnlocalizedName("AOPItemHelmet").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:aop_item");
				GameRegistry.registerItem(AOPItemHelmet,"sagaItemHelmet");

				AOPItemChestplate = new AOPItemArmor(armorAOP,0,1,"saga").setUnlocalizedName("AOPItemChestplate").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:aop_item");
				GameRegistry.registerItem(AOPItemChestplate,"sagaItemChestplate");

				AOPItemLeggings = new AOPItemArmor(armorAOP,0,2,"saga").setUnlocalizedName("AOPItemLeggings").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:aop_item");
				GameRegistry.registerItem(AOPItemLeggings,"sagaItemLeggings");

				AOPItemBoots = new AOPItemArmor(armorAOP,0,3,"saga").setUnlocalizedName("AOPItemBoots").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:aop_item");
				GameRegistry.registerItem(AOPItemBoots,"sagaItemBoots");


				//Sword
				SFX2400ItemSword = new ItemSword(toolSFX).setUnlocalizedName("SFX2400ItemSword").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_2400_sword");
				GameRegistry.registerItem(SFX2400ItemSword,"SFX2400ItemSword");

				//Hoe
				SFX2400ItemHoe = new ItemHoe(toolSFX).setUnlocalizedName("SFX2400ItemHoe").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_2400_hoe");
				GameRegistry.registerItem(SFX2400ItemHoe,"SFX2400ItemHoe");

				//Shovel
				SFX2400ItemSpade = new ItemSpade(toolSFX).setUnlocalizedName("SFX2400ItemSpade").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_2400_shovel");
				GameRegistry.registerItem(SFX2400ItemSpade,"SFX2400ItemSpade");

				//Axe
				SFX2400ItemAxe = new com.lyle.multiengineering.tool.SFX2400ItemAxe(toolSFX).setUnlocalizedName("SFX2400ItemAxe").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_2400_axe");
				GameRegistry.registerItem(SFX2400ItemAxe,"SFX2400ItemAxe");

				//Pickaxe
				SFX2400ItemPickaxe = new com.lyle.multiengineering.tool.SFX2400ItemPickaxe(toolSFX).setUnlocalizedName("SFX2400ItemPickaxe").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_2400_pickaxe");
				GameRegistry.registerItem(SFX2400ItemPickaxe,"SFX2400ItemPickaxe");

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
        return MultiEngineeing.AOPItem;
    }
};

}


