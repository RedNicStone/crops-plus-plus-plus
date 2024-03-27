package com.github.bartimaeusnek.cropspp.GTHandler;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.MetaTileEntities;

import java.util.HashMap;
import java.util.Map;

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
        registerCropMachine(
                GENE_EXTRACTOR,
                itemIDStart += GENE_EXTRACTOR.length,
                CropMachineNames.GENE_EXTRACTOR,
                CropRecipeMaps.GENE_EXTRACTOR_RECIPES,
                Textures.SCANNER_OVERLAY
        );

        registerCropMachine(
                REPLICATOR,
                itemIDStart += REPLICATOR.length,
                CropMachineNames.REPLICATOR,
                CropRecipeMaps.REPLICATOR_RECIPES,
                Textures.SCANNER_OVERLAY
        );

        registerCropMachine(
                SYNTHESIZER,
                itemIDStart += SYNTHESIZER.length,
                CropMachineNames.SYNTHESIZER,
                CropRecipeMaps.SYNTHESIZER_RECIPES,
                Textures.SCANNER_OVERLAY
        );

        registerCropMachine(
                WEED_PICKER,
                itemIDStart += WEED_PICKER.length,
                CropMachineNames.WEED_PICKER,
                CropRecipeMaps.WEED_PICKER_RECIPES,
                Textures.SCANNER_OVERLAY
        );
    }

    private static void registerCropMachine(SimpleMachineMetaTileEntity[] machines,
                                            int startId,
                                            String name,
                                            RecipeMap<?> map,
                                            ICubeRenderer texture) {
        MetaTileEntities.registerSimpleMetaTileEntity(
                machines, startId, name, map, texture,
                true, GTCompat::cropsId, GTUtility.hvCappedTankSizeFunction);
    }
}
