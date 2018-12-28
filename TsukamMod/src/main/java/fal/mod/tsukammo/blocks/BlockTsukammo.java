package fal.mod.tsukammo.blocks;

import java.util.Random;
import fal.mod.tsukammo.TsukamMod;
import fal.mod.tsukammo.worldgen.WorldGenTsukammo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockTsukammo extends BlockBush implements IGrowable{
	
	public static final int MAXAGE=9;
	@Override
	public int tickRate(World worldIn){
		return 100;
	}
	
	public static final PropertyInteger AGE=PropertyInteger.create("age",0,MAXAGE);
	
	public static final AxisAlignedBB AABB=Block.FULL_BLOCK_AABB;
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state,IBlockAccess source,BlockPos pos){
		return AABB;
	}
	
	public static final String	ITEMNAME="Tsukammo(Plant)";
	public static final String	ITEMID	="tsukammo";
	
	public BlockTsukammo(){
		super(Material.PLANTS);
		
		setTickRandomly(false);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.PLANT);
		setUnlocalizedName(TsukamMod.NAME+"."+ITEMNAME);
		setRegistryName(ITEMID);
		setHardness(0.0f);
		
		setDefaultState(this.blockState.getBaseState().withProperty(AGE,Integer.valueOf(0)));
	}
	
	@Override
	public void onBlockAdded(World worldIn,BlockPos pos,IBlockState state){
		super.onBlockAdded(worldIn,pos,state);
		worldIn.scheduleUpdate(pos,this,tickRate(worldIn));
	}
	
	@Override
	public void updateTick(World worldIn,BlockPos pos,IBlockState state,Random rand){
		super.updateTick(worldIn,pos,state,rand);
		worldIn.scheduleUpdate(pos,this,tickRate(worldIn));
		if(!worldIn.isRemote){
			grow(worldIn,pos,state,rand);
		}
	}
	
	@Override
	public boolean canGrow(World worldIn,BlockPos pos,IBlockState state,boolean isClient){
		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn,Random rand,BlockPos pos,IBlockState state){
		return true;
	}
	
	public void grow(World worldIn,BlockPos pos,IBlockState state,Random rand){
		worldIn.setBlockState(pos,state.cycleProperty(AGE),3);
		
		if(!worldIn.isAreaLoaded(pos,1))
			return;
		
		generateTree(worldIn,pos,state,rand,
				state.getValue(AGE).intValue()==MAXAGE);
	}
	@Override
	public void grow(World worldIn,Random rand,BlockPos pos,IBlockState state){
		grow(worldIn,pos,state,rand);
	}
	
	public void generateTree(World worldIn,BlockPos pos,IBlockState state,Random rand,boolean beRock){
		if(!TerrainGen.saplingGrowTree(worldIn,rand,pos)) return;
		
		WorldGenerator worldgenerator=new WorldGenTsukammo(true,beRock);
		
		//IBlockState iblockstate2=Blocks.AIR.getDefaultState();
		//worldIn.setBlockState(pos,iblockstate2,4);
		
		if(!worldgenerator.generate(worldIn,rand,pos)){
			worldIn.setBlockState(pos,state,4);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		return this.getDefaultState().withProperty(AGE,meta);
	}
	@Override
	public int getMetaFromState(IBlockState state){
		int i=0;
		i|=state.getValue(AGE).intValue();
		return i;
	}
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this,AGE);
	}
}
