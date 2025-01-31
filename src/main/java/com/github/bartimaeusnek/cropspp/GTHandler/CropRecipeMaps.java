package com.github.bartimaeusnek.cropspp.GTHandler;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.machines.RecipeMapScanner;
import gregtech.core.sound.GTSoundEvents;

public class CropRecipeMaps {
    public static final RecipeMap<SimpleRecipeBuilder> GENE_EXTRACTOR_RECIPES =
            new RecipeMapScanner(CropMachineNames.GENE_EXTRACTOR, 2, 1, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
                    .setSlotOverlay(false, false, true, CropGuiTextures.SEED_BAG_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> REPLICATOR_RECIPES =
            new RecipeMapScanner(CropMachineNames.REPLICATOR, 1, 1, 1, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.CANISTER_OVERLAY)
                    .setSlotOverlay(false, false, true, CropGuiTextures.SEED_BAG_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> SYNTHESIZER_RECIPES =
            new RecipeMapScanner(CropMachineNames.SYNTHESIZER, 4, 1, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> WEED_PICKER_RECIPES =
            new RecipeMapScanner(CropMachineNames.WEED_PICKER, 4, 0, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, true, GuiTextures.SCANNER_OVERLAY)
                    .setSound(GTSoundEvents.ELECTROLYZER);
}
