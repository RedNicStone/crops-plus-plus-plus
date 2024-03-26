package com.github.bartimaeusnek.cropspp.items;

import ic2.core.crop.TileEntityCrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Weedmaker extends Item {

    public Weedmaker() {
        super();
        this.setUnlocalizedName("Weedmaker");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.cpp);
        this.setMaxDamage(0);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote)
            return EnumActionResult.PASS;
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            crop.setCrop(ic2.core.crop.IC2Crops.weed);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

}
