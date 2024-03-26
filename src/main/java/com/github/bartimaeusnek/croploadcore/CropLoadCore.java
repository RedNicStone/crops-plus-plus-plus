package com.github.bartimaeusnek.croploadcore;

import ic2.api.crops.CropSoilType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(
        modid = CropLoadCore.MODID, name = CropLoadCore.MODNAME, version = CropLoadCore.VERSION,
        dependencies = "after:ztones;" +
                " after:galacticraftcore;" +
                " after:galacticraftplanets;" +
                " after:ic2;" +
                " after:mantle;" +
                " after:natura;" +
                " after:tconstruct;" +
                " after:biomesoplenty;" +
                " after:thaumcraft;" +
                " after:extratrees;" +
                " after:witchery;" +
                " after:gregtech;" +
                " after:dreamcraft;" +
                " after:thaumicbases"
)


public class CropLoadCore {

    public static final String MODID = "croploadcore";
    public static final String MODNAME = "CropLoadCore";
    public static final String VERSION = "@version@";
    public static final Logger CLClogger = LogManager.getLogger("CropLoadCore");

    @Mod.Instance(value = "CropLoadCore")
    public static CropLoadCore instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preinit) {
        ModsLoaded.check_init("bartimaeusnek's mods");
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent init) {
        OreDict.register();
    }

    @Mod.EventHandler
    public void ServerStartedEvent(FMLServerStartedEvent event){
        for (Block b : ForgeRegistries.BLOCKS.getValuesCollection()){
            try {
                if (b.canSustainPlant((IBlockState) b.getBlockState().getBaseState(), (IBlockAccess) FMLCommonHandler.instance().getMinecraftServerInstance().worlds[0], BlockPos.ORIGIN, EnumFacing.UP, new BlockCarrot()))
                    EnumHelper.addEnum(CropSoilType.class, b.getUnlocalizedName(), new Class[]{Block.class}, b);
            }catch (Exception e){}
        }
    }
}
