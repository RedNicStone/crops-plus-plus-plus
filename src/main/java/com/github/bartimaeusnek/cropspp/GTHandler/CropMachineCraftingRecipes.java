package com.github.bartimaeusnek.cropspp.GTHandler;

import gregtech.loaders.recipe.MetaTileEntityLoader;
import static gregtech.loaders.recipe.CraftingComponent.*;

public class CropMachineCraftingRecipes {
    public static void register() {
        //CropItemList.weedingTrowel.set(Ic2Items.weedingTrowel);
        //CropItemList.Spade.set(CppItems.itemSpadeStack);
        //GT_ModHandler.addCraftingRecipe(CppItems.itemSpadeStack, bitsd, new Object[]{"fPh", "PWP", " S ", 'P', OrePrefixes.plateDense.get(Materials.Steel), 'W', CropItemList.weedingTrowel, 'S', OrePrefixes.stickLong.get(Materials.Wood)});

//                new ItemStack(itemLens),
//                GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.MIRRORED | GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.REVERSIBLE,
//                new Object[]{
//                        " fL",
//                        " Sr",
//                        "S  ",
//                        'L', GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Glass, 1L),
//                        'S', new ItemStack(Items.stick)
//                }
//        );
//        GT_ModHandler.addCraftingRecipe(
//                GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Glass, 1L),
//                GT_ModHandler.RecipeBits.NOT_REMOVABLE,
//                new Object[]{
//                        "FfF",
//                        "FGF",
//                        "FDF",
//                        'F', new ItemStack(Items.flint),
//                        'G', new ItemStack(Blocks.glass),
//                        'D', new ItemStack(Items.diamond)
//                }
//        );

        MetaTileEntityLoader.registerMachineRecipe(CropMetaTileEntities.GENE_EXTRACTOR,
                "SES", "WMW", "CSC",
                'M', HULL, 'E', EMITTER, 'S', SENSOR, 'W', CABLE, 'C', CIRCUIT);

        MetaTileEntityLoader.registerMachineRecipe(CropMetaTileEntities.REPLICATOR,
                "CFC", "CMC", "EWE",
                'M', HULL, 'F', FIELD_GENERATOR, 'E', EMITTER, 'C', CIRCUIT, 'W', WIRE_ELECTRIC);

        MetaTileEntityLoader.registerMachineRecipe(CropMetaTileEntities.SYNTHESIZER,
                "FCF", "CMC", "EEE",
                'M', HULL, 'F', FIELD_GENERATOR, 'E', EMITTER, 'C', CIRCUIT, 'W', WIRE_ELECTRIC);

    }
}
