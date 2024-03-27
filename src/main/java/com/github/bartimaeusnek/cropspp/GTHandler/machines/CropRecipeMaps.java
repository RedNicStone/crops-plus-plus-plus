package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.machines.RecipeMapScanner;
import gregtech.core.sound.GTSoundEvents;

public class CropRecipeMaps {
    public static final RecipeMap<SimpleRecipeBuilder> GENE_EXTRACTOR_RECIPES =
        new RecipeMapScanner("scanner", 2, 1, 0, 0,
            new SimpleRecipeBuilder(), false)
        .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
        .setSlotOverlay(false, false, true, GuiTextures.SCANNER_OVERLAY)
        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
        .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> REPLICATOR_RECIPES =
            new RecipeMapScanner("scanner", 2, 1, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
                    .setSlotOverlay(false, false, true, GuiTextures.SCANNER_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> SYNTHESIZER_RECIPES =
            new RecipeMapScanner("scanner", 2, 1, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
                    .setSlotOverlay(false, false, true, GuiTextures.SCANNER_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> WEED_PICKER_RECIPES =
            new RecipeMapScanner("scanner", 2, 1, 0, 0,
                    new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
                    .setSlotOverlay(false, false, true, GuiTextures.SCANNER_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);
}
