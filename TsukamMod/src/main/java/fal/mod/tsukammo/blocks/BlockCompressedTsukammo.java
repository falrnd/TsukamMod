package fal.mod.tsukammo.blocks;

import fal.mod.tsukammo.TsukamMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCompressedTsukammo extends Block{
	public static final String	ITEMNAME="TsukammoBlock";
	public static final String	ITEMID	="tsukammo_block";
	
	public BlockCompressedTsukammo(){
		super(Material.GRASS);
		setSoundType(SoundType.PLANT);
		
		setCreativeTab(CreativeTabs.DECORATIONS);
		
		setUnlocalizedName(TsukamMod.NAME+"."+ITEMNAME);
		setRegistryName(ITEMID);
	}
}
