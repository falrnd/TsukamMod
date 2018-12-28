package fal.mod.util;

import fal.mod.tsukammo.TsukamModBlocks;
import fal.mod.tsukammo.blocks.BlockCompressedTsukammo;
import fal.mod.tsukammo.blocks.BlockTsukammo;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public final class RegistryHandler{
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> event){
		event.getRegistry().registerAll(
				new BlockTsukammo(),
				new BlockCompressedTsukammo());
	}
	
	private static class BurnableItemBlock extends ItemBlock{
		private int burntime;
		public BurnableItemBlock(Block block,int burntime){
			super(block);
			this.burntime=burntime;
		}
		@Override
		public int getItemBurnTime(ItemStack itemStack){
			return burntime;
		}
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event){
		event.getRegistry().registerAll(
				new BurnableItemBlock(TsukamModBlocks.TSUKAMMO,100)
						.setRegistryName(TsukamModBlocks.TSUKAMMO.getRegistryName()),
				new BurnableItemBlock(TsukamModBlocks.TSUKAMMO_BLOCK,1000)
						.setRegistryName(TsukamModBlocks.TSUKAMMO_BLOCK.getRegistryName()));
	}
}
