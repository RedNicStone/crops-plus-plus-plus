package com.github.bartimaeusnek.cropspp.items;

import ic2.core.crop.TileEntityCrop;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBppLens extends Item {

    public ItemBppLens() {
        super();
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.cpp);
        this.setUnlocalizedName("cppLens");
        this.setMaxStackSize(1);
        //this.setTextureName("bpp:itemLens");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("A Simple Lens to determine Crop Species");
        tooltip.add("Indestructible.");
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer aPlayer, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote)
            return EnumActionResult.PASS;
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityCrop) {
            if (aPlayer != null && ((TileEntityCrop) te).getCrop() != null && ((TileEntityCrop) te).getCrop().getId() != null)
                aPlayer.sendMessage(new TextComponentString("This is a " + ((TileEntityCrop) te).getCrop().getId()));
            else
                return EnumActionResult.PASS;
            if (((TileEntityCrop) te).getScanLevel() < 1) {
                ((TileEntityCrop) te).setScanLevel((byte) 1);
                ((TileEntityCrop) te).markDirty();
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
