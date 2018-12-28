package fal.mod.util;

import fal.mod.tsukammo.TsukamModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler{
	
	@SubscribeEvent
	public static void registerModels(@SuppressWarnings("unused") ModelRegistryEvent event){
		registerModel(
				Item.getItemFromBlock(TsukamModBlocks.TSUKAMMO),
				Item.getItemFromBlock(TsukamModBlocks.TSUKAMMO_BLOCK));
	}
	
	private static void registerModel(Item...items){
		for(Item item:items)
			ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}
}
