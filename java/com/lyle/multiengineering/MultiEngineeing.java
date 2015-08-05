package com.lyle.multiengineering;

import com.lyle.multiengineering.armor.AOPItemArmor;
import com.lyle.multiengineering.block.OreCopper;
import com.lyle.multiengineering.block.OrePlatinum;
import com.lyle.multiengineering.block.OreTin;
import com.lyle.multiengineering.block.SFXBlockCropsIron;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;

@Mod( modid = MultiEngineeing.MODID, version = MultiEngineeing.VERSION, acceptedMinecraftVersions = "[1.7,)" )
public class MultiEngineeing
{

	@Mod.Instance("MultiEngineeing")
	public static final String MODID = "Multi-Engineeing";
	public static final String VERSION = "Alpha-1.0.0";
	public static final String acceptedMinecraftVersions = "[1.7,)";


	//Items
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
    public static Item SFXItemSeedIron;
	public static Item SFXItemCropsIron;
	public static Item SteelIngot;
	public static Item CopperIngot;
	public static Item TinIngot;
	public static Item PlatinumIngot;

	//Foods
	public static ItemFood SFXItemFood_S;

	//Blocks
	public static Block SFXBlockCropsIron;
	public static Block OreCopper;
	public static Block OreTin;
	public static Block OrePlatinum;


	public static ItemStack bronzeFromFML;



	@EventHandler
	public void preInit( FMLPreInitializationEvent e )
	{


		//OreDictionary
		//OreDictionary.registerOre("SteelIngot", new ItemStack(MultiEngineeing.SteelIngot, 1, 0));
		//OreDictionary.registerOre("CopperIngot", new ItemStack(MultiEngineeing.CopperIngot, 1, 0));
		//OreDictionary.registerOre("TinIngot", new ItemStack(MultiEngineeing.TinIngot, 1, 0));
		//OreDictionary.registerOre("PlatinumIngot", new ItemStack(MultiEngineeing.PlatinumIngot, 1, 0));


		SFXItemFood_S = ((ItemFood) new ItemFood(40, 5.0F, false).setUnlocalizedName("sagaItemFood_S").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_food"))
				.setAlwaysEdible().setPotionEffect(Potion.moveSpeed.id, 30, 3, 1.0F);
		GameRegistry.registerItem(SFXItemFood_S,"sagaItemFood_S");


		        //ブロック作物
				SFXBlockCropsIron = new SFXBlockCropsIron().setBlockName("SFXBlockCropsIron").setBlockTextureName("multiengineeing:sfx_crops");
				GameRegistry.registerBlock(SFXBlockCropsIron,"SFXBlockCropsIron");

				//アイテム種　ItemSeeds（植えた時に作成されるブロック，植えることができる場所）
				SFXItemSeedIron = new ItemSeeds(MultiEngineeing.SFXBlockCropsIron, Blocks.farmland).setUnlocalizedName("SFXItemSeedIron").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_seed");
				GameRegistry.registerItem(SFXItemSeedIron,"SFXItemSeedIron");

				//アイテム作物
				SFXItemCropsIron = new Item().setUnlocalizedName("SFXItemCropsIron").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:sfx_item_crops");
				GameRegistry.registerItem(SFXItemCropsIron,"SFXItemCropsIron");


		//Added Material）
		Item.ToolMaterial toolSFX = EnumHelper.addToolMaterial("toolSaga", 3, 2000, 600.0F, 600.0F, 30);
		toolSFX.customCraftingMaterial = MultiEngineeing.AOPItem;


		//Instance生成
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

				//Ingots
				SteelIngot = new Item().setUnlocalizedName("SteelIngot").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:steel_ingot");
				GameRegistry.registerItem(SteelIngot,"Steel Ingot");

				CopperIngot = new Item().setUnlocalizedName("CopperIngot").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:copper_ingot");
				GameRegistry.registerItem(CopperIngot,"Copper Ingot");

				TinIngot = new Item().setUnlocalizedName("TinIngot").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:tin_ingot");
				GameRegistry.registerItem(TinIngot,"Tin Ingot");

				PlatinumIngot = new Item().setUnlocalizedName("PlatinumIngot").setCreativeTab(MultiEngineeingTab).setTextureName("multiengineeing:platinum_ingot");
				GameRegistry.registerItem(PlatinumIngot,"Platinum Ingot");


				//Copper Ore
				OreCopper = new OreCopper().setBlockName("CopperOre").setCreativeTab(MultiEngineeingTab).setBlockTextureName("multiengineeing:copper_ore");
				GameRegistry.registerBlock(OreCopper,"CopperOre");

				//Tin Ore
				OreTin = new OreTin().setBlockName("TinOre").setCreativeTab(MultiEngineeingTab).setBlockTextureName("multiengineeing:tin_ore");
				GameRegistry.registerBlock(OreTin,"TinOre");

				//Platinum Ore
				OrePlatinum = new OrePlatinum().setBlockName("PlatinumOre").setCreativeTab(MultiEngineeingTab).setBlockTextureName("multiengineeing:platinum_ore");
				GameRegistry.registerBlock(OrePlatinum,"PlatinumOre");


				//OreGen
				lyleEventManager eventmanager = new lyleEventManager();
				GameRegistry.registerWorldGenerator(eventmanager, 0);


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


