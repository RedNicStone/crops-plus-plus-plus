package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.api.util.Mods;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CropMetaTileEntities {
    private static final Map<String, Boolean> MID_TIER = new HashMap<>();
    private static final Map<String, Boolean> HIGH_TIER = new HashMap<>();

    public static final SimpleMachineMetaTileEntity[] GENE_EXTRACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] SYNTHESIZER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static final SimpleMachineMetaTileEntity[] WEED_PICKER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];

    public static void init() {
        GTLog.logger.info("Registering MetaTileEntities");

        int itemIDStart = 11000;
        registerSimpleMetaTileEntity(
                GENE_EXTRACTOR,
                itemIDStart += GENE_EXTRACTOR.length,
                "gene_extractor",
                CropRecipeMaps.GENE_EXTRACTOR_RECIPES,
                Textures.SCANNER_OVERLAY,
                true);
    }

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                     int startId,
                                                     String name,
                                                     RecipeMap<?> map,
                                                     ICubeRenderer texture,
                                                     boolean hasFrontFacing,
                                                     Function<Integer, Integer> tankScalingFunction) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTUtility::gregtechId,
                tankScalingFunction);
    }

    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                     int startId,
                                                     String name,
                                                     RecipeMap<?> map,
                                                     ICubeRenderer texture,
                                                     boolean hasFrontFacing) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing,
                GTUtility.defaultTankSizeFunction);
    }

    public static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
                                                    int startId,
                                                    String name,
                                                    RecipeMap<?> map,
                                                    ICubeRenderer texture,
                                                    boolean hasFrontFacing,
                                                    Function<String, ResourceLocation> resourceId,
                                                    Function<Integer, Integer> tankScalingFunction) {
        registerMetaTileEntities(machines, startId, name,
                (tier, voltageName) -> new SimpleMachineMetaTileEntity(
                        resourceId.apply(String.format("%s.%s", name, voltageName)), map, texture, tier, hasFrontFacing,
                        tankScalingFunction));
    }

    /**
     * @param mteCreator Takes tier and voltage name for the machine, and outputs MTE to register
     */
    public static void registerMetaTileEntities(
            MetaTileEntity[] machines,
            int startId,
            String name,
            BiFunction<Integer, String, MetaTileEntity> mteCreator) {
        for (int i = 0; i < machines.length - 1; i++) {
            if (i > 4 && !getMidTier(name)) continue;
            if (i > 7 && !getHighTier(name)) break;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            machines[i + 1] = registerMetaTileEntity(startId + i, mteCreator.apply(i + 1, voltageName));
        }
    }

    public static <T extends MetaTileEntity> T registerMetaTileEntity(int id, T sampleMetaTileEntity) {
        if (sampleMetaTileEntity instanceof IMultiblockAbilityPart) {
            MultiblockAbility.registerMultiblockAbility(((IMultiblockAbilityPart<?>) sampleMetaTileEntity).getAbility(), sampleMetaTileEntity);
        }
        if (sampleMetaTileEntity instanceof MultiblockControllerBase && Mods.JustEnoughItems.isModLoaded()) {
            if (((MultiblockControllerBase) sampleMetaTileEntity).shouldShowInJei()) {
                MultiblockInfoCategory.registerMultiblock((MultiblockControllerBase) sampleMetaTileEntity);
            }
        }
        GregTechAPI.MTE_REGISTRY.register(id, sampleMetaTileEntity.metaTileEntityId, sampleMetaTileEntity);
        return sampleMetaTileEntity;
    }

    @SuppressWarnings("unused")
    public static void setMidTier(String key, boolean enabled) {
        MID_TIER.put(key, enabled);
    }

    @SuppressWarnings("unused")
    public static void setHighTier(String key, boolean enabled) {
        HIGH_TIER.put(key, enabled);
        if (!GregTechAPI.isHighTier()) {
            throw new IllegalArgumentException(
                    "Cannot set High-Tier machine without high tier being enabled in GregTechAPI.");
        }
    }

    public static boolean getMidTier(String key) {
        return MID_TIER.getOrDefault(key, true);
    }

    public static boolean getHighTier(String key) {
        return HIGH_TIER.getOrDefault(key, GregTechAPI.isHighTier());
    }
}
