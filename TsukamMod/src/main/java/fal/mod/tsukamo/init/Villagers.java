package fal.mod.tsukamo.init;

import java.util.Random;
import fal.mod.tsukammo.TsukamMod;
import fal.mod.tsukammo.TsukamModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

@ObjectHolder(TsukamMod.MODID)
public final class Villagers{
	private Villagers(){
	}
	
	public static VillagerCareer Tsukammo_san;
	
	public static void registerCarrer(){
		Villagers.Tsukammo_san=new VillagerCareer(VillagerRegistry.FARMER,"tsukammo_san")
				.addTrade(1,new EntityVillager.EmeraldForItems(TsukamModItems.TSUKAMMO_BLOCK,new PriceInfo(6,58)))
				.addTrade(2,new TradeItemForEnchantedShovel(TsukamModItems.TSUKAMMO_BLOCK,new PriceInfo(16,32)))
				.addTrade(3,new TradeItemForSpeedPotion(TsukamModItems.TSUKAMMO_BLOCK,new PriceInfo(4,16)))
				.addTrade(4,new TradeItemForObsidian(TsukamModItems.TSUKAMMO_BLOCK,new PriceInfo(24,42)));
	}
	
	public static abstract class TradeListBase implements ITradeList{
		public PriceInfo	priceInfo;
		public Item			pay;
		
		public TradeListBase(Item pay,PriceInfo priceInfo){
			this.pay=pay;
			this.priceInfo=priceInfo;
		}
	}
	
	public static class TradeItemForEnchantedShovel extends TradeListBase{
		public TradeItemForEnchantedShovel(Item pay,PriceInfo priceInfo){
			super(pay,priceInfo);
		}
		
		@Override
		public void addMerchantRecipe(IMerchant merchant,MerchantRecipeList recipeList,Random rnd){
			boolean diamond=rnd.nextDouble()<0.125;
			ItemStack item=new ItemStack(diamond?Items.DIAMOND_SHOVEL:Items.IRON_SHOVEL);
			int price=diamond?(int)(1.5*priceInfo.getPrice(rnd)):priceInfo.getPrice(rnd);
			
			item.setStackDisplayName("road");
			
			int level=(int)(price*20.0/priceInfo.getSecond()+rnd.nextInt(11)-5);
			EnchantmentHelper.addRandomEnchantment(rnd,item,level,false);
			
			recipeList.add(new MerchantRecipe(
					new ItemStack(pay,price),
					item));
		}
	}
	
	public static class TradeItemForSpeedPotion extends TradeListBase{
		public TradeItemForSpeedPotion(Item pay,PriceInfo priceInfo){
			super(pay,priceInfo);
		}
		
		@Override
		public void addMerchantRecipe(IMerchant merchant,MerchantRecipeList recipeList,Random rnd){
			ItemStack item=new ItemStack(Items.POTIONITEM);
			item.setStackDisplayName("growup");
			PotionUtils.addPotionToItemStack(item,PotionTypes.STRONG_SWIFTNESS);
			recipeList.add(new MerchantRecipe(
					new ItemStack(pay,priceInfo.getPrice(rnd)),
					item));
		}
	}
	
	public static class TradeItemForObsidian extends TradeListBase{
		public TradeItemForObsidian(Item pay,PriceInfo priceInfo){
			super(pay,priceInfo);
		}
		@Override
		public void addMerchantRecipe(IMerchant merchant,MerchantRecipeList recipeList,Random rnd){
			ItemStack item=new ItemStack(Blocks.OBSIDIAN,10);
			item.setStackDisplayName("warpgate");
			recipeList.add(new MerchantRecipe(
					new ItemStack(pay,priceInfo.getPrice(rnd)),
					item));
		}
	}
}
