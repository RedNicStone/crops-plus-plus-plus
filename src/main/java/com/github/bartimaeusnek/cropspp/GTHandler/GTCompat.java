package com.github.bartimaeusnek.cropspp.GTHandler;

import com.github.bartimaeusnek.cropspp.Cropspp;
import net.minecraft.util.ResourceLocation;

public class GTCompat {
    public static ResourceLocation cropsId(String path) {
        return new ResourceLocation(Cropspp.modID, path);
    }
}
