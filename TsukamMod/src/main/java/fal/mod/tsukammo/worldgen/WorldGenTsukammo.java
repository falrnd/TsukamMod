package fal.mod.tsukammo.worldgen;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class WorldGenTsukammo extends WorldGenAbstractTree{
	public static final IBlockState	rock	=net.minecraft.init.Blocks.STONE.getDefaultState();
	public static final IBlockState	leaves	=fal.mod.tsukammo.TsukamModBlocks.TSUKAMMO.getDefaultState();
	
	private boolean beRock;
	
	public WorldGenTsukammo(boolean notify,boolean beRock){
		super(notify);
		this.beRock=beRock;
	}
	
	@Override
	public boolean generate(World worldIn,Random rand,BlockPos pos){
		final int minHeight=1;
		// Check if tree fits in world
		if(pos.getY()==0||pos.getY()+minHeight>=worldIn.getHeight())
			return false;
		
		IBlockState state=worldIn.getBlockState(pos.down());
		
		if(state.getBlock().canSustainPlant(state,worldIn,pos.down(),EnumFacing.UP,(IPlantable)Blocks.SAPLING)&&pos.getY()<worldIn.getHeight()-minHeight-1){
			generateLeaves(worldIn,pos);
			if(beRock){
				state.getBlock().onPlantGrow(state,worldIn,pos.down(),pos);
				generateTrunk(worldIn,pos);
			}
			return true;
		}
		return false;
	}
	
	private void generateLeaves(World parWorld,BlockPos pos){
		setLeave(parWorld,pos.north());
		setLeave(parWorld,pos.south());
		setLeave(parWorld,pos.east());
		setLeave(parWorld,pos.west());
	}
	private void setLeave(World parWorld,BlockPos pos){
		if(fal.mod.tsukammo.TsukamModBlocks.TSUKAMMO.canPlaceBlockAt(parWorld,pos)){
			setBlockAndNotifyAdequately(parWorld,pos,leaves);
		}
	}
	
	private static IBlockState air=Blocks.AIR.getDefaultState();
	private void generateTrunk(World world,BlockPos pos){
		world.setBlockState(pos,air,4);
		
		if(isReplaceable(world,pos)){
			setBlockAndNotifyAdequately(world,pos,rock);
		}
	}
}
