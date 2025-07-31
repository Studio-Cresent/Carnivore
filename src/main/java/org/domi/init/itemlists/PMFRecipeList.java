package org.domi.init.itemlists;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.domi.init.recipes.PMFCookingRecipeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PMFRecipeList extends RecipeProvider {
    public PMFRecipeList(DataGenerator generator) {
        super(generator.getPackOutput());
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // 모든 요리 레시피 등록
        registerAllCookingRecipes();

        // 조합대 레시피 등록
        registerCraftingRecipes();

        // 등록된 모든 레시피 생성
        PMFCookingRecipeBuilder.buildAllRecipes(consumer);
    }

    /**
     * 모든 요리 레시피를 등록.
     */
    private void registerAllCookingRecipes() {
        // 스니퍼 고기 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_SNIFFER_MEAT.get(),
                PMFItemList.COOKED_SNIFFER_MEAT.get(),
                0.35f,
                200);

        // 박쥐 고기 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_BAT.get(),
                PMFItemList.COOKED_BAT.get(),
                0.35f,
                200);

        // 새 고기 레시피 (앵무새)
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_BIRD.get(),
                PMFItemList.COOKED_BIRD.get(),
                0.35f,
                200);

        //낙타
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_CAMEL_MEAT.get(),
                PMFItemList.COOKED_CAMEL_MEAT.get(),
                0.35f,
                200);

        // 토끼 발 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                Items.RABBIT_FOOT,
                PMFItemList.COOKED_RABBIT_FOOT.get(),
                0.35f,
                200);

        // 오징어 다리 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.SQUID_TENTACLE.get(),
                PMFItemList.COOKED_SQUID_TENTACLE.get(),
                0.35f,
                200);

        // 올챙이 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.TADPOLE.get(),
                PMFItemList.COOKED_TADPOLE.get(),
                0.35f,
                200);

        // 거북이 고기 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_TURTLE.get(),
                PMFItemList.COOKED_TURTLE.get(),
                0.35f,
                200);

        // 균사체 스테이크 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_MYCELIUM_BEEF.get(),
                PMFItemList.MYCELIUM_STEAK.get(),
                0.35f,
                200);

        // 아홀로틀 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_AXOLOTL.get(),
                PMFItemList.COOKED_AXOLOTL.get(),
                0.35f,
                200);

        // 말고기 스테이크 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_HORSE_MEAT.get(),
                PMFItemList.HORSE_MEAT_STEAK.get(),
                0.35f,
                200);

        // 고래 고기 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.RAW_WHALE_MEAT.get(),
                PMFItemList.COOKED_WHALE_MEAT.get(),
                0.35f,
                200);

        // 엔더마이트 덩어리 레시피
        PMFCookingRecipeBuilder.registerAllCookingRecipe(
                PMFItemList.MITE_CHUNK.get(),
                PMFItemList.COOKED_MITE_CHUNK.get(),
                0.35f,
                200);

    }

    /**
     * 조합대 레시피를 등록.
     */
    private void registerCraftingRecipes() {
        List<ItemLike> goldenSquidIngredients = new ArrayList<>();
        goldenSquidIngredients.add(PMFItemList.GLOW_SQUID_TENTACLE.get());
        for (int i = 0; i < 8; i++) {
            goldenSquidIngredients.add(Items.GOLD_INGOT);
        }

        PMFCookingRecipeBuilder.registerShapelessRecipe(
                PMFItemList.GOLDEN_SQUID_TENTACLE.get(),
                1,
                goldenSquidIngredients,
                PMFItemList.GLOW_SQUID_TENTACLE.get()
        );

        List<ItemLike> frogSpawnIngredients = new ArrayList<>();
        frogSpawnIngredients.add(Items.FROGSPAWN);
        frogSpawnIngredients.add(Items.FROGSPAWN);

        PMFCookingRecipeBuilder.registerShapelessRecipe(
                PMFItemList.FROGSPAWN.get(),
                1,
                frogSpawnIngredients,
                Items.FROGSPAWN  // 레시피북에 표시될 아이템
        );

    }
}
