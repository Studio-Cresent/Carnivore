package org.domi.init.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.domi.PMF;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PMFCookingRecipeBuilder {

    private static final List<CookingRecipe> RECIPES = new ArrayList<>();

    // 통합된 레시피 데이터를 저장할 클래스
    public static class CookingRecipe {
        // 기존 바닐라 필드
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

        // Farmer's Delight 전용 필드
        private final boolean isCutting;
        private final boolean isCookingPot;
        private final String toolTag;
        private final String soundEventId;
        private final ItemLike container;
        private final List<ItemLike> potIngredients;

        private CookingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime,
                              boolean createAllTypes, int campfireTime, int smokingTime, int blastingTime,
                              boolean isShapeless, List<ItemLike> shapelessIngredients, int count,
                              boolean isShaped, List<String> pattern, Map<Character, ItemLike> keys,
                              ItemLike unlockItem, boolean isCutting, boolean isCookingPot,
                              String toolTag, String soundEventId, ItemLike container, List<ItemLike> potIngredients) {
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
            this.isCutting = isCutting;
            this.isCookingPot = isCookingPot;
            this.toolTag = toolTag;
            this.soundEventId = soundEventId;
            this.container = container;
            this.potIngredients = potIngredients;
        }
    }

    // ==========================================
    // 기존 Ба닐라 레시피 등록 메서드
    // ==========================================
    public static void registerSmeltingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        RECIPES.add(new CookingRecipe(ingredient, result, experience, smeltingTime, false, 0, 0, 0, false, null, 0, false, null, null, ingredient, false, false, null, null, null, null));
    }

    public static void registerAllCookingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        RECIPES.add(new CookingRecipe(ingredient, result, experience, smeltingTime, true, smeltingTime * 3, smeltingTime / 2, smeltingTime / 2, false, null, 0, false, null, null, ingredient, false, false, null, null, null, null));
    }

    public static void registerAllCookingRecipe(ItemLike ingredient, ItemLike result, float experience, int smeltingTime, int campfireTime, int smokingTime, int blastingTime) {
        RECIPES.add(new CookingRecipe(ingredient, result, experience, smeltingTime, true, campfireTime, smokingTime, blastingTime, false, null, 0, false, null, null, ingredient, false, false, null, null, null, null));
    }

    public static void registerShapelessRecipe(ItemLike result, int count, List<ItemLike> ingredients, ItemLike unlockItem) {
        RECIPES.add(new CookingRecipe(null, result, 0, 0, false, 0, 0, 0, true, new ArrayList<>(ingredients), count, false, null, null, unlockItem, false, false, null, null, null, null));
    }

    public static void registerShapedRecipe(ItemLike result, int count, List<String> pattern, Map<Character, ItemLike> keys, ItemLike unlockItem) {
        RECIPES.add(new CookingRecipe(null, result, 0, 0, false, 0, 0, 0, false, null, count, true, new ArrayList<>(pattern), keys, unlockItem, false, false, null, null, null, null));
    }

    // ==========================================
    // Farmer's Delight 레시피 등록 메서드
    // ==========================================
    public static void registerCuttingRecipe(ItemLike ingredient, String toolTag, ItemLike result, int resultCount) {
        RECIPES.add(new CookingRecipe(ingredient, result, 0, 0, false, 0, 0, 0, false, null, resultCount, false, null, null, null, true, false, toolTag, "", null, null));
    }

    public static void registerCuttingRecipe(ItemLike ingredient, String toolTag, ItemLike result, int resultCount, String soundEventId) {
        RECIPES.add(new CookingRecipe(ingredient, result, 0, 0, false, 0, 0, 0, false, null, resultCount, false, null, null, null, true, false, toolTag, soundEventId, null, null));
    }

    public static void registerCookingPotRecipe(ItemLike result, ItemLike container, int cookingTime, float experience, ItemLike... ingredients) {
        RECIPES.add(new CookingRecipe(null, result, experience, cookingTime, false, 0, 0, 0, false, null, 0, false, null, null, null, false, true, null, null, container, Arrays.asList(ingredients)));
    }

    // ==========================================
    // DataGenerator에서 호출할 최종 빌드 메서드
    // ==========================================
    public static void buildAllRecipes(Consumer<FinishedRecipe> consumer) {
        for (CookingRecipe recipe : RECIPES) {
            if (recipe.isCutting) {
                createCuttingRecipe(consumer, recipe.ingredient, recipe.toolTag, recipe.result, recipe.count, recipe.soundEventId);
            } else if (recipe.isCookingPot) {
                createCookingPotRecipe(consumer, recipe.result, recipe.container, recipe.smeltingTime, recipe.experience, recipe.potIngredients);
            } else if (recipe.isShapeless) {
                createShapelessRecipe(consumer, recipe.result, recipe.count, recipe.shapelessIngredients, recipe.unlockItem);
            } else if (recipe.isShaped) {
                createShapedRecipe(consumer, recipe.result, recipe.count, recipe.pattern, recipe.keys, recipe.unlockItem);
            } else if (recipe.createAllTypes) {
                createAllCookingRecipes(consumer, recipe.ingredient, recipe.result, recipe.experience,
                        recipe.smeltingTime, recipe.campfireTime, recipe.smokingTime, recipe.blastingTime);
            } else {
                createSmeltingRecipe(consumer, recipe.ingredient, recipe.result, recipe.experience, recipe.smeltingTime);
            }
        }
        // ★ 중요: 모든 레시피를 생성한 후 리스트를 비워주어 중복 방지
        RECIPES.clear();
    }

    // ==========================================
    // 바닐라 레시피 생성 로직
    // ==========================================
    public static void createSmeltingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_smelting"));
    }

    public static void createCampfireRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_campfire"));
    }

    public static void createSmokingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_smoking"));
    }

    public static void createBlastingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId + "_from_blasting"));
    }

    public static void createAllCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int smeltingTime, int campfireTime, int smokingTime, int blastingTime) {
        createSmeltingRecipe(consumer, ingredient, result, experience, smeltingTime);
        createCampfireRecipe(consumer, ingredient, result, experience, campfireTime);
        createSmokingRecipe(consumer, ingredient, result, experience, smokingTime);
        // createBlastingRecipe(consumer, ingredient, result, experience, blastingTime);
    }

    public static void createShapedRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count, List<String> pattern, Map<Character, ItemLike> keys, ItemLike criterionItem) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, count);
        for (String row : pattern) builder.pattern(row);
        for (Map.Entry<Character, ItemLike> entry : keys.entrySet()) builder.define(entry.getKey(), entry.getValue());
        builder.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId));
    }

    public static void createShapelessRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count, List<ItemLike> ingredients, ItemLike criterionItem) {
        String resultId = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count);
        for (ItemLike ingredient : ingredients) builder.requires(ingredient);
        builder.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, new ResourceLocation(PMF.MODID, resultId));
    }

    // ==========================================
    // Farmer's Delight 레시피 생성 로직
    // ==========================================
    public static void createCuttingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, String toolTag, ItemLike result, int resultCount, String soundEventId) {
        ResourceLocation id = new ResourceLocation(PMF.MODID, ForgeRegistries.ITEMS.getKey(result.asItem()).getPath() + "_from_cutting");
        consumer.accept(new FDCuttingRecipe(id, ingredient.asItem(), toolTag, result.asItem(), resultCount, soundEventId));
    }

    public static void createCookingPotRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike container, int cookingTime, float experience, List<ItemLike> ingredients) {
        ResourceLocation id = new ResourceLocation(PMF.MODID, ForgeRegistries.ITEMS.getKey(result.asItem()).getPath() + "_from_cooking_pot");
        Item[] itemArray = new Item[ingredients.size()];
        for (int i = 0; i < ingredients.size(); i++) itemArray[i] = ingredients.get(i).asItem();
        Item containerItem = container != null ? container.asItem() : null;
        consumer.accept(new FDCookingPotRecipe(id, result.asItem(), containerItem, cookingTime, experience, itemArray));
    }

    // ---------------------------------------------------------
    // 내부 클래스: 도마 레시피 JSON 생성기
    // ---------------------------------------------------------
    private record FDCuttingRecipe(ResourceLocation id, Item ingredient, String toolTag, Item result, int resultCount, String soundEventId) implements FinishedRecipe {
        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("type", "farmersdelight:cutting");

            JsonArray ingredientsArray = new JsonArray();
            JsonObject ingredientObj = new JsonObject();
            ingredientObj.addProperty("item", ForgeRegistries.ITEMS.getKey(ingredient).toString());
            ingredientsArray.add(ingredientObj);
            json.add("ingredients", ingredientsArray);

            JsonObject toolObj = new JsonObject();
            toolObj.addProperty("tag", toolTag);
            json.add("tool", toolObj);

            JsonArray resultsArray = new JsonArray();
            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
            if (resultCount > 1) {
                resultObj.addProperty("count", resultCount);
            }
            resultsArray.add(resultObj);
            json.add("result", resultsArray);

            if (soundEventId != null && !soundEventId.isEmpty()) {
                json.addProperty("sound", soundEventId);
            }
        }

        @Override public ResourceLocation getId() { return id; }
        @Override public RecipeSerializer<?> getType() { return null; }
        @Nullable @Override public JsonObject serializeAdvancement() { return null; }
        @Nullable @Override public ResourceLocation getAdvancementId() { return null; }
    }

    // ---------------------------------------------------------
    // 내부 클래스: 요리 냄비 레시피 JSON 생성기
    // ---------------------------------------------------------
    private record FDCookingPotRecipe(ResourceLocation id, Item result, Item container, int cookingTime, float experience, Item[] ingredients) implements FinishedRecipe {
        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("type", "farmersdelight:cooking");

            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
            json.add("result", resultObj);

            JsonArray ingredientsArray = new JsonArray();
            for (Item item : ingredients) {
                JsonObject ingredientObj = new JsonObject();
                ingredientObj.addProperty("item", ForgeRegistries.ITEMS.getKey(item).toString());
                ingredientsArray.add(ingredientObj);
            }
            json.add("ingredients", ingredientsArray);

            if (container != null) {
                JsonObject containerObj = new JsonObject();
                containerObj.addProperty("item", ForgeRegistries.ITEMS.getKey(container).toString());
                json.add("container", containerObj);
            }
            json.addProperty("experience", experience);
            json.addProperty("cookingtime", cookingTime);
        }

        @Override public ResourceLocation getId() { return id; }
        @Override public RecipeSerializer<?> getType() { return null; }
        @Nullable @Override public JsonObject serializeAdvancement() { return null; }
        @Nullable @Override public ResourceLocation getAdvancementId() { return null; }
    }
}