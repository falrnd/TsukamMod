package fal.mod.util;

import fal.mod.tsukammo.TsukamModBlocks;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler{
	public static void registerOreDictionary(){
		OreDictionary.registerOre("blockTsukammo",TsukamModBlocks.TSUKAMMO_BLOCK);
	}
}
