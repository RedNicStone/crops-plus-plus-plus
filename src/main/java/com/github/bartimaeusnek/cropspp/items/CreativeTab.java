package com.github.bartimaeusnek.cropspp.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class CreativeTab extends CreativeTabs {

    public final static CreativeTab cpp = new CreativeTab();

    public CreativeTab() {
        super("Cropspp");
    }

    @Override
    public ItemStack getTabIconItem() {
       /* if (ModsLoaded.Natura)
            return OreDict.ISget("cropSaguaroBerry");
        else */return new ItemStack(CppItems.Goldfisch);
    }

}
