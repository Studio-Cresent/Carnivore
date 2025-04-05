package org.domi.init.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.domi.PMF;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PMFCookingRecipeBuilder {

    // 레시피를 저장할 리스트 추가
    private static final List<CookingRecipe> RECIPES = new ArrayList<>();

    // 레시피 데이터를 저장할 클래스
    public static class CookingRecipe {
        private final ItemLike ingredient;
        private final ItemLike result;
        private final float experience;
        private final int smeltingTime;
        private final boolean createAllTypes;
        private final int campfireTime;
        private final int smokingTime;
        private final int blastingTime;
        private final boolean isShapeless;
        private final List<ItemLike> shapelessIngredients;
        private final int count;
        private final boolean isShaped;
        private final List<String> pattern;
        private final Map<Character, ItemLike> keys;
        private final ItemLike unlockItem;

        private CookingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime,
                              boolean createAllTypes, int campfireTime, int smokingTime, int blastingTime,
                              boolean isShapeless, List<ItemLike> shapelessIngredients, int count,
                              boolean isShaped, List<String> pattern, Map<Character, ItemLike> keys,
                              ItemLike unlockItem) {
            this.ingredient = ingredient;
            this.result = result;
            this.experience = experience;
            this.smeltingTime = smeltingTime;
            this.createAllTypes = createAllTypes;
            this.campfireTime = campfireTime;
            this.smokingTime = smokingTime;
            this.blastingTime = blastingTime;
            this.isShapeless = isShapeless;
            this.shapelessIngredients = shapelessIngredients;
            this.count = count;
            this.isShaped = isShaped;
            this.pattern = pattern;
            this.keys = keys;
            this.unlockItem = unlockItem;
        }
    }

    /**
     * 화로 요리 레시피만 리스트에 등록합니다.
     */
    public static void registerSmeltingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        RECIPES.add(new CookingRecipe(
                ingredient, result, experience, smeltingTime,
                false, 0, 0, 0,
                false, null, 0,
                false, null, null,
                ingredient
        ));
    }

    /**
     * 모든 요리 레시피를 리스트에 등록합니다.
     */
    public static void registerAllCookingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        RECIPES.add(new CookingRecipe(
                ingredient, result, experience, smeltingTime,
                true, smeltingTime * 3, smeltingTime / 2, smeltingTime / 2,
                false, null, 0,
                false, null, null,
                ingredient
        ));
    }

    /**
     * 커스텀 시간 설정으로 모든 요리 레시피를 리스트에 등록합니다.
     */
    public static void registerAllCookingRecipe(ItemLike ingredient, ItemLike result, float experience,
                                                int smeltingTime, int campfireTime, int smokingTime, int blastingTime) {
        RECIPES.add(new CookingRecipe(
                ingredient, result, experience, smeltingTime,
                true, campfireTime, smokingTime, blastingTime,
                false, null, 0,
                false, null, null,
                ingredient
        ));
    }

    /**
     * 모양이 없는 조합 레시피를 리스트에 등록합니다.
     */
    public static void registerShapelessRecipe(ItemLike result, int count, List<ItemLike> ingredients, ItemLike unlockItem) {
        RECIPES.add(new CookingRecipe(
                null, result, 0, 0,
                false, 0, 0, 0,
                true, new ArrayList<>(ingredients), count,
                false, null, null,
                unlockItem
        ));
    }

    /**
     * 모양이 있는 조합 레시피를 리스트에 등록합니다.
     */
    public static void registerShapedRecipe(ItemLike result, int count, List<String> pattern,
                                            Map<Character, ItemLike> keys, ItemLike unlockItem) {
        RECIPES.add(new CookingRecipe(
                null, result, 0, 0,
                false, 0, 0, 0,
                false, null, count,
                true, new ArrayList<>(pattern), keys,
                unlockItem
        ));
    }

    /**
     * 등록된 모든 레시피를 생성합니다.
     */
    public static void buildAllRecipes(Consumer<FinishedRecipe> consumer) {
        for (CookingRecipe recipe : RECIPES) {
            if (recipe.isShapeless) {
                createShapelessRecipe(consumer, recipe.result, recipe.count, recipe.shapelessIngredients, recipe.unlockItem);
            } else if (recipe.isShaped) {
                createShapedRecipe(consumer, recipe.result, recipe.count, recipe.pattern, recipe.keys, recipe.unlockItem);
            } else if (recipe.createAllTypes) {
                createAllCookingRecipes(consumer, recipe.ingredient, recipe.result, recipe.experience,
                        recipe.smeltingTime, recipe.campfireTime, recipe.smokingTime, recipe.blastingTime);
            } else {
                // 화로 레시피만 등록
                createSmeltingRecipe(consumer, recipe.ingredient, recipe.result, recipe.experience, recipe.smeltingTime);
            }
        }
    }

    /**
     * 화로 레시피를 생성합니다.
     */
    public static void createSmeltingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_smelting"));
    }

    /**
     * 모닥불 요리 레시피를 생성합니다.
     */
    public static void createCampfireRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_campfire"));
    }

    /**
     * 훈연기 요리 레시피를 생성합니다.
     */
    public static void createSmokingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_smoking"));
    }

    /**
     * 용광로 요리 레시피를 생성합니다.
     */
    public static void createBlastingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_blasting"));
    }

    /**
     * 모든 유형의 요리 레시피(화로, 모닥불, 훈연기, 용광로)를 한번에 생성합니다.
     */
    public static void createAllCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int smeltingTime, int campfireTime, int smokingTime, int blastingTime) {
        createSmeltingRecipe(consumer, ingredient, result, experience, smeltingTime);
        createCampfireRecipe(consumer, ingredient, result, experience, campfireTime);
        createSmokingRecipe(consumer, ingredient, result, experience, smokingTime);
//        createBlastingRecipe(consumer, ingredient, result, experience, blastingTime);
    }

    /**
     * 기본 시간 값을 사용하여 모든 유형의 요리 레시피를 생성합니다.
     */
    public static void createAllCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        int campfireTime = smeltingTime * 3;
        int smokingTime = smeltingTime / 2;
        int blastingTime = smeltingTime / 2;
        createAllCookingRecipes(consumer, ingredient, result, experience, smeltingTime, campfireTime, smokingTime, blastingTime);
    }

    /**
     * 모양이 있는 조합대 레시피를 생성합니다.
     */
    public static void createShapedRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                          List<String> pattern, Map<Character, ItemLike> keys,
                                          ItemLike criterionItem) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, count);

        // 패턴 추가
        for (String row : pattern) {
            builder.pattern(row);
        }

        // 키 추가
        for (Map.Entry<Character, ItemLike> entry : keys.entrySet()) {
            builder.define(entry.getKey(), entry.getValue());
        }

        // 해금 조건 및 저장
        builder.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId));
    }

    /**
     * 모양이 없는 조합대 레시피를 생성합니다.
     */
    public static void createShapelessRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                             List<ItemLike> ingredients, ItemLike criterionItem) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count);

        // 재료 추가
        for (ItemLike ingredient : ingredients) {
            builder.requires(ingredient);
        }

        // 해금 조건 및 저장
        builder.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId));
    }
}
