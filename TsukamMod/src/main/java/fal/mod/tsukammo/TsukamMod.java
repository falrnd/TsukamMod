package fal.mod.tsukammo;

import org.apache.logging.log4j.Logger;
import fal.mod.tsukamo.init.Villagers;
import fal.mod.util.OreDictionaryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=TsukamMod.MODID,name=TsukamMod.NAME,version=TsukamMod.VERSION)
public final class TsukamMod{
	public static final String	MODID	="tsukammod";
	public static final String	NAME	="TsukamMod";
	public static final String	VERSION	="1.0.0";
	
	@Instance(MODID)
	public static TsukamMod instance;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		logger=event.getModLog();
		logger.info("preInit");
	}
	@Mod.EventHandler
	public void init(@SuppressWarnings("unused") FMLInitializationEvent event){
		logger.info("init");
		OreDictionaryHandler.registerOreDictionary();
		Villagers.registerCarrer();
	}
	
	@Mod.EventHandler
	public void postInit(@SuppressWarnings("unused") FMLPostInitializationEvent event){
		logger.info("postInit");
	}
}
