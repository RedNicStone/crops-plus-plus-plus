package com.github.bartimaeusnek.cropspp.items;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.croploadcore.Operators;
import com.google.common.collect.Sets;
import ic2.api.item.IC2Items;
import ic2.core.IC2;
import ic2.core.crop.TileEntityCrop;
import ic2.core.item.type.CropResItemType;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemBppSpade extends ItemTool {
    private static Set BlocksAffected = Sets.newHashSet(Blocks.GRASS,
            Blocks.DIRT,
            Blocks.SNOW_LAYER,
            Blocks.FARMLAND,
            Blocks.MYCELIUM,
            Block.getBlockFromItem(IC2Items.getItem("crop_stick").getItem()));

    public ItemBppSpade() {
        super(1.0F,1f, Item.ToolMaterial.IRON, BlocksAffected);
        this.setUnlocalizedName("Spade");
        //this.setTextureName("bpp:itemSpade");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.cpp);
        this.setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Weeding Trowel, Shovel and Hoe in one Item!");
        tooltip.add("Has a higher chanche of yielding seedbags!");
        tooltip.add("Indestructible");
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (!IC2.platform.isSimulating())
            return EnumActionResult.PASS;
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            if (crop.getCrop() != null) {
                if (crop.getCrop().getProperties().getTier() >= 1) {
                    float i = crop.getCrop().getProperties().getTier() + 5f * ((((-crop.getStatResistance()) / 2f) + crop.getStatGain() + crop.getStatGrowth()) / 21f);
                    if (MyRandom.intrandom(100, 0) <= 100 * Operators.csig(i, 12, false)) {
                        if (crop.getCrop().getGain(crop) != null && crop.getCrop().canBeHarvested(crop))
                            StackUtil.dropAsEntity(world,pos, crop.getCrop().getGain(crop));
                        StackUtil.dropAsEntity(world,pos, crop.generateSeeds(crop.getCrop(), crop.getStatGrowth(), crop.getStatGain(), crop.getStatResistance(), crop.getScanLevel()));
                    }
                } else {
                    ItemStack drop = ItemName.crop_res.getItemStack(CropResItemType.weed).copy();
                    drop.setCount(crop.getCurrentSize());
                    StackUtil.dropAsEntity(world, pos, drop);
                    if (!(crop.getCrop().getId() == "weed")) {
                        if (crop.getCurrentSize() == crop.getCrop().getMaxSize())
                            StackUtil.dropAsEntity(world, pos, crop.generateSeeds(crop.getCrop(), crop.getStatGrowth(), crop.getStatGain(), crop.getStatResistance(), crop.getScanLevel()));
                    } else if (crop.getCrop().getGain(crop) != null && crop.getCrop().canBeHarvested(crop))
                        StackUtil.dropAsEntity(world, pos, crop.getCrop().getGain(crop));
                }
                crop.reset();
                return EnumActionResult.SUCCESS;
            }
            crop.reset();
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing != EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR && (world.getBlockState(pos).getBlock() == Blocks.GRASS || world.getBlockState(pos).getBlock() == Blocks.DIRT || world.getBlockState(pos).getBlock() == Blocks.MYCELIUM)) {
            world.setBlockState(pos, Blocks.FARMLAND.getBlockState().getBaseState());
            return EnumActionResult.SUCCESS;
        } else if (facing != EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR  && (world.getBlockState(pos).getBlock() == Blocks.FARMLAND)) {
            world.setBlockState(pos, Blocks.DIRT.getBlockState().getBaseState());
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.PASS;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase player) {
        return true;
    }

}

