package zap.sorting.categories;

import net.minecraft.registry.Registries;
import net.minecraft.item.ItemStack;
import zap.sorting.CategoryRule;
import java.util.ArrayList;
import java.util.List;

public class MaterialCategories {
    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();

        rules.add(new CategoryRule("diamonds", 800, stack ->
            getItemId(stack).contains("diamond") && !isTool(stack) && !isArmor(stack)));
        rules.add(new CategoryRule("emeralds", 801, stack ->
            getItemId(stack).contains("emerald")));
        rules.add(new CategoryRule("iron_ingots", 810, stack ->
            getItemId(stack).contains("iron_ingot")));
        rules.add(new CategoryRule("gold_ingots", 811, stack ->
            getItemId(stack).contains("gold_ingot")));
        rules.add(new CategoryRule("copper_ingots", 812, stack ->
            getItemId(stack).contains("copper")));
        rules.add(new CategoryRule("netherite", 813, stack ->
            getItemId(stack).contains("netherite") && !isTool(stack) && !isArmor(stack)));
        rules.add(new CategoryRule("redstone", 820, stack ->
            getItemId(stack).contains("redstone")));
        rules.add(new CategoryRule("lapis", 821, stack ->
            getItemId(stack).contains("lapis")));
        rules.add(new CategoryRule("coal", 822, stack ->
            getItemId(stack).contains("coal")));

        return rules;
    }

    private static boolean isTool(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("pickaxe") || id.contains("axe") ||
               id.contains("shovel") || id.contains("hoe");
    }

    private static boolean isArmor(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("helmet") || id.contains("chestplate") ||
               id.contains("leggings") || id.contains("boots");
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString().toLowerCase();
    }
}
