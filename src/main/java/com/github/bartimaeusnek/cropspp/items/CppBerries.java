package com.github.bartimaeusnek.cropspp.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class CppBerries extends ItemFood {

//    public IIcon[] icons;

    public String[] textureNames = new String[]{"huckle", "sugarbeet"/*, more names*/};

    public CppBerries() {
        super(1, 0.4F, false);
        this.setCreativeTab(CreativeTab.cpp);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setMaxStackSize(16);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 16;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return (new StringBuilder()).append("item.berry.").append(textureNames[itemstack.getItemDamage()]).toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs par2CreativeTabs, NonNullList list) {
        if (par2CreativeTabs == this.getCreativeTab())
            for (int i = 0; i < textureNames.length; ++i) {
                list.add(new ItemStack(this, 1, i));
            }
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        switch (stack.getItemDamage()) {
            case 0: {
                tooltip.add("Huckle-dae-Duckle-dae-Doo");
                tooltip.add("A sweet treat!");
                break;
            }
            case 1: {
                tooltip.add("A sweet beet, commonly found in France, Germany, Russia and China.");
                tooltip.add("Industrial Sugar!");
                break;
            }
            //other cases
        }
    }
}
