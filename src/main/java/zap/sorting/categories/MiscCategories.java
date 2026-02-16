package zap.sorting.categories;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import zap.sorting.CategoryRule;
import java.util.ArrayList;
import java.util.List;

public class MiscCategories {
    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();

        rules.add(new CategoryRule("swords", 50, stack ->
            getItemId(stack).contains("sword")));
        rules.add(new CategoryRule("bows", 60, stack -> {
            String id = getItemId(stack);
            return id.contains("bow") || id.contains("crossbow");
        }));
        rules.add(new CategoryRule("shields", 61, stack ->
            getItemId(stack).contains("shield")));
        rules.add(new CategoryRule("fishing_rods", 62, stack ->
            getItemId(stack).contains("fishing_rod")));
        rules.add(new CategoryRule("shears", 63, stack ->
            getItemId(stack).contains("shears")));
        rules.add(new CategoryRule("flint_and_steel", 64, stack ->
            getItemId(stack).contains("flint_and_steel")));
        rules.add(new CategoryRule("torches", 900, stack ->
            getItemId(stack).contains("torch")));
        rules.add(new CategoryRule("buckets", 910, stack ->
            getItemId(stack).contains("bucket")));
        rules.add(new CategoryRule("books", 920, stack ->
            getItemId(stack).contains("book")));

        return rules;
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString().toLowerCase();
    }
}
