package com.github.bartimaeusnek.cropspp;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.cropspp.GTHandler.CropMachineCraftingRecipes;
import com.github.bartimaeusnek.cropspp.commands.EnableDebug;
import com.github.bartimaeusnek.cropspp.croploader.CropLoader;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.github.bartimaeusnek.cropspp.GTHandler.CropMetaTileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = Cropspp.modID, name = Cropspp.name, version = Cropspp.version,
        dependencies = "required-after:ic2; "
                + "required-after:croploadcore@0.1.4; "
                + "after:galacticraftcore; "
                + "after:mantle; "
                + "after:natura; "
                + "after:tconstruct; "
                + "after:biomesoplenty; "
                + "after:thaumcraft; "
                + "after:extratrees; "
                + "after:witchery; "
                + "after:gregtech; "
                + "after:twilightforest; "
)

public final class Cropspp {
    public static final String name = "Crops++";
    public static final String version = "@version@";
    public static final String modID = "berriespp";
    public static final Logger cpplogger = LogManager.getLogger(name);

    @Instance(value = "Crops++")
    public static Cropspp instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent preinit) {
        CropLoader.load(preinit);
        CppItems.register_Items();
        MinecraftForge.EVENT_BUS.register(CropLoader.EventSubscriber.class);
        if (ModsLoaded.GT) {
            CropMetaTileEntities.register();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent init) {
//        if (ModsLoaded.GT) {
//            final CppFluids CppFluids = new CppFluids();
//        }
        CppItems.OreDictItems();
        CppItems.register_recipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postinit) {
        if (ModsLoaded.GT) {
            CropMachineCraftingRecipes.register();
        }
    }

    @EventHandler
    public void onFMLServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new EnableDebug());
    }
}
