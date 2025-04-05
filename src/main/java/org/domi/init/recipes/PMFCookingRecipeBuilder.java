package org.domi.init.recipes;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.domi.PMF;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PMFCookingRecipeBuilder {

    /**
     * 화로 레시피를 생성합니다.
     *
     * @param consumer    레시피 소비자
     * @param ingredient  요리할 재료
     * @param result      결과 아이템
     * @param experience  경험치
     * @param cookingTime 요리 시간
     */
    public static void createSmeltingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {/* implementation omitted for shortness */}

    /**
     * 모닥불 요리 레시피를 생성합니다.
     *
     * @param consumer    레시피 소비자
     * @param ingredient  요리할 재료
     * @param result      결과 아이템
     * @param experience  경험치
     * @param cookingTime 요리 시간
     */
    public static void createCampfireRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {/* implementation omitted for shortness */}

    /**
     * 훈연기 요리 레시피를 생성합니다.
     *
     * @param consumer    레시피 소비자
     * @param ingredient  요리할 재료
     * @param result      결과 아이템
     * @param experience  경험치
     * @param cookingTime 요리 시간
     */
    public static void createSmokingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {/* implementation omitted for shortness */}

    /**
     * 용광로 요리 레시피를 생성합니다.
     *
     * @param consumer    레시피 소비자
     * @param ingredient  요리할 재료
     * @param result      결과 아이템
     * @param experience  경험치
     * @param cookingTime 요리 시간
     */
    public static void createBlastingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int cookingTime) {/* implementation omitted for shortness */}

    /**
     * 모든 유형의 요리 레시피(화로, 모닥불, 훈연기, 용광로)를 한번에 생성합니다.
     *
     * @param consumer     레시피 소비자
     * @param ingredient   요리할 재료
     * @param result       결과 아이템
     * @param experience   경험치
     * @param smeltingTime 화로 요리 시간
     * @param campfireTime 모닥불 요리 시간
     * @param smokingTime  훈연기 요리 시간
     * @param blastingTime 용광로 요리 시간
     */
    /**
     * 모든 유형의 요리 레시피(화로, 모닥불, 훈연기, 용광로)를 한번에 생성합니다.
     *
     * @param consumer     레시피 소비자
     * @param ingredient   요리할 재료
     * @param result       결과 아이템
     * @param experience   경험치
     * @param smeltingTime 화로 요리 시간
     * @param campfireTime 모닥불 요리 시간
     * @param smokingTime  훈연기 요리 시간
     * @param blastingTime 용광로 요리 시간
     */
    public static void createAllCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int smeltingTime, int campfireTime, int smokingTime, int blastingTime) {
        // 화로 레시피 생성
        createSmeltingRecipe(consumer, ingredient, result, experience, smeltingTime);

        // 모닥불 레시피 생성
        createCampfireRecipe(consumer, ingredient, result, experience, campfireTime);

        // 훈연기 레시피 생성
        createSmokingRecipe(consumer, ingredient, result, experience, smokingTime);

        // 용광로 레시피 생성
        createBlastingRecipe(consumer, ingredient, result, experience, blastingTime);
    }

    /**
     * 기본 시간 값을 사용하여 모든 유형의 요리 레시피를 생성합니다.
     * 화로: 기본 시간
     * 모닥불: 화로의 3배
     * 훈연기: 화로의 절반
     * 용광로: 화로의 절반
     *
     * @param consumer     레시피 소비자
     * @param ingredient   요리할 재료
     * @param result       결과 아이템
     * @param experience   경험치
     * @param smeltingTime 화로 요리 시간
     */
    public static void createAllCookingRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float experience, int smeltingTime) {
        int campfireTime = smeltingTime * 3;
        int smokingTime = smeltingTime / 2;
        int blastingTime = smeltingTime / 2;

        createAllCookingRecipes(consumer, ingredient, result, experience, smeltingTime, campfireTime, smokingTime, blastingTime);
    }

    /**
     * 모양이 있는 조합대 레시피를 생성합니다.
     *
     * @param consumer 레시피 소비자
     * @param result 결과 아이템
     * @param count 결과 아이템 개수
     * @param pattern 조합 패턴(최대 3줄, 각 줄은 최대 3글자)
     * @param keys 패턴에 사용된 문자와 해당 재료 매핑
     * @param criterionItem 레시피 해금 조건으로 사용할 아이템
     */
    public static void createShapedRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                          List<String> pattern, Map<Character, ItemLike> keys,
                                          ItemLike criterionItem) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result, count);

        // 패턴 설정
        for (String row : pattern) {
            builder.pattern(row);
        }

        // 키 매핑 설정
        for (Map.Entry<Character, ItemLike> entry : keys.entrySet()) {
            builder.define(entry.getKey(), entry.getValue());
        }

        // 레시피 해금 조건 및 저장
        builder.unlockedBy("has_" + getItemName(criterionItem), has(criterionItem))
                .save(consumer);
    }

    /**
     * 모양이 없는 조합대 레시피를 생성합니다.
     *
     * @param consumer 레시피 소비자
     * @param result 결과 아이템
     * @param count 결과 아이템 개수
     * @param ingredients 필요한 재료 목록
     * @param criterionItem 레시피 해금 조건으로 사용할 아이템
     */
    public static void createShapelessRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                             List<ItemLike> ingredients, ItemLike criterionItem) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result, count);

        for (ItemLike ingredient : ingredients) {
            builder.requires(ingredient);
        }

        builder.unlockedBy("has_" + getItemName(criterionItem), has(criterionItem))
                .save(consumer);
    }

    /**
     * 태그를 사용하는 모양이 없는 조합대 레시피를 생성합니다.
     *
     * @param consumer 레시피 소비자
     * @param result 결과 아이템
     * @param count 결과 아이템 개수
     * @param tagIngredients 태그 재료 목록
     * @param itemIngredients 일반 아이템 재료 목록
     * @param criterionItem 레시피 해금 조건으로 사용할 아이템
     */
    public static void createShapelessRecipeWithTags(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                                     Map<TagKey<Item>, Integer> tagIngredients,
                                                     List<ItemLike> itemIngredients, ItemLike criterionItem) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result, count);

        // 태그 재료 추가
        for (Map.Entry<TagKey<Item>, Integer> entry : tagIngredients.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                builder.requires(entry.getKey());
            }
        }

        // 일반 아이템 재료 추가
        for (ItemLike ingredient : itemIngredients) {
            builder.requires(ingredient);
        }

        // 레시피 해금 조건 및 저장
        builder.unlockedBy("has_" + getItemName(criterionItem), has(criterionItem))
                .save(consumer);
    }

    /**
     * 커스텀 이름으로 모양이 있는 조합대 레시피를 생성합니다.
     *
     * @param consumer 레시피 소비자
     * @param result 결과 아이템
     * @param count 결과 아이템 개수
     * @param pattern 조합 패턴
     * @param keys 패턴에 사용된 문자와 해당 재료 매핑
     * @param criterionItem 레시피 해금 조건으로 사용할 아이템
     * @param recipeName 레시피 이름
     */
    public static void createShapedRecipeWithCustomName(Consumer<FinishedRecipe> consumer, ItemLike result, int count,
                                                        List<String> pattern, Map<Character, ItemLike> keys,
                                                        ItemLike criterionItem, String recipeName) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result, count);

        // 패턴 설정
        for (String row : pattern) {
            builder.pattern(row);
        }

        // 키 매핑 설정
        for (Map.Entry<Character, ItemLike> entry : keys.entrySet()) {
            builder.define(entry.getKey(), entry.getValue());
        }

        // 레시피 해금 조건 및 저장 (커스텀 이름 사용)
        builder.unlockedBy("has_" + getItemName(criterionItem), has(criterionItem))
                .save(consumer, new ResourceLocation(PMF.MODID, recipeName));
    }

    /**
     * 아이템 이름을 가져옵니다.
     */
    private static String getItemName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }

    /**
     * 아이템 획득 조건을 생성합니다.
     */
    private static InventoryChangeTrigger.TriggerInstance has(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

}