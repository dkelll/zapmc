package zap.sorting.categories;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import zap.sorting.CategoryRule;
import java.util.ArrayList;
import java.util.List;

public class BlockCategories {
    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();

        // Wood types: oak, spruce, birch, jungle, acacia, dark_oak, etc.
        String[] woods = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak",
                          "mangrove", "cherry", "bamboo", "crimson", "warped"};

        for (int i = 0; i < woods.length; i++) {
            final String wood = woods[i];
            int basePriority = 500 + (i * 10);

            rules.add(new CategoryRule(wood + "_planks", basePriority, stack ->
                isBlockItem(stack) && getItemId(stack).equals("minecraft:" + wood + "_planks")));
            rules.add(new CategoryRule(wood + "_stairs", basePriority + 1, stack ->
                isBlockItem(stack) && getItemId(stack).contains(wood) && getItemId(stack).contains("stairs")));
            rules.add(new CategoryRule(wood + "_slabs", basePriority + 2, stack ->
                isBlockItem(stack) && getItemId(stack).contains(wood) && getItemId(stack).contains("slab")));
            rules.add(new CategoryRule(wood + "_blocks", basePriority + 3, stack ->
                isBlockItem(stack) && getItemId(stack).contains(wood)));
        }

        // General block category for everything else
        rules.add(new CategoryRule("blocks", 700, stack -> isBlockItem(stack)));

        return rules;
    }

    private static boolean isBlockItem(ItemStack stack) {
        return stack.getItem() instanceof BlockItem;
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString().toLowerCase();
    }
}
