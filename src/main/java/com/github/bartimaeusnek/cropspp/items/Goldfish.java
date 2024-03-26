package com.github.bartimaeusnek.cropspp.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Goldfish extends ItemFood {


    public Goldfish() {
        super(1, 2, false);
        this.setCreativeTab(CreativeTab.cpp);
        this.setUnlocalizedName("foodGoldfish");
//        this.setTextureName("bpp:foodGoldfish");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Hozuki no Reitetsu! A truely hellish planimal?!");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumActionResult onItemUseFirst(EntityPlayer aPlayer, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        aPlayer.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.ghast.scream")), 5, (float) 0.5);
        return EnumActionResult.SUCCESS;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer aPlayer) {
        aPlayer.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.ghast.scream")), 5, (float) 0.5);
        super.onFoodEaten(stack, worldIn, aPlayer);
    }

}
