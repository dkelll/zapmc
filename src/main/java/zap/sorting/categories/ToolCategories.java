package zap.sorting.categories;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import zap.sorting.CategoryRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories for tools, sorted by material quality and type.
 * Priority order: Netherite > Diamond > Iron > Gold > Stone > Wood
 */
public class ToolCategories {

    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();

        // Netherite tools (priority 100-109)
        rules.add(new CategoryRule("netherite_pickaxe", 100, stack ->
            isTool(stack, "pickaxe") && isNetherite(stack)));
        rules.add(new CategoryRule("netherite_axe", 101, stack ->
            isTool(stack, "axe") && isNetherite(stack)));
        rules.add(new CategoryRule("netherite_shovel", 102, stack ->
            isTool(stack, "shovel") && isNetherite(stack)));
        rules.add(new CategoryRule("netherite_hoe", 103, stack ->
            isTool(stack, "hoe") && isNetherite(stack)));

        // Diamond tools (priority 110-119)
        rules.add(new CategoryRule("diamond_pickaxe", 110, stack ->
            isTool(stack, "pickaxe") && isDiamond(stack)));
        rules.add(new CategoryRule("diamond_axe", 111, stack ->
            isTool(stack, "axe") && isDiamond(stack)));
        rules.add(new CategoryRule("diamond_shovel", 112, stack ->
            isTool(stack, "shovel") && isDiamond(stack)));
        rules.add(new CategoryRule("diamond_hoe", 113, stack ->
            isTool(stack, "hoe") && isDiamond(stack)));

        // Iron tools (priority 120-129)
        rules.add(new CategoryRule("iron_pickaxe", 120, stack ->
            isTool(stack, "pickaxe") && isIron(stack)));
        rules.add(new CategoryRule("iron_axe", 121, stack ->
            isTool(stack, "axe") && isIron(stack)));
        rules.add(new CategoryRule("iron_shovel", 122, stack ->
            isTool(stack, "shovel") && isIron(stack)));
        rules.add(new CategoryRule("iron_hoe", 123, stack ->
            isTool(stack, "hoe") && isIron(stack)));

        // Golden tools (priority 130-139)
        rules.add(new CategoryRule("golden_pickaxe", 130, stack ->
            isTool(stack, "pickaxe") && isGolden(stack)));
        rules.add(new CategoryRule("golden_axe", 131, stack ->
            isTool(stack, "axe") && isGolden(stack)));
        rules.add(new CategoryRule("golden_shovel", 132, stack ->
            isTool(stack, "shovel") && isGolden(stack)));
        rules.add(new CategoryRule("golden_hoe", 133, stack ->
            isTool(stack, "hoe") && isGolden(stack)));

        // Stone tools (priority 140-149)
        rules.add(new CategoryRule("stone_pickaxe", 140, stack ->
            isTool(stack, "pickaxe") && isStone(stack)));
        rules.add(new CategoryRule("stone_axe", 141, stack ->
            isTool(stack, "axe") && isStone(stack)));
        rules.add(new CategoryRule("stone_shovel", 142, stack ->
            isTool(stack, "shovel") && isStone(stack)));
        rules.add(new CategoryRule("stone_hoe", 143, stack ->
            isTool(stack, "hoe") && isStone(stack)));

        // Wooden tools (priority 150-159)
        rules.add(new CategoryRule("wooden_pickaxe", 150, stack ->
            isTool(stack, "pickaxe") && isWooden(stack)));
        rules.add(new CategoryRule("wooden_axe", 151, stack ->
            isTool(stack, "axe") && isWooden(stack)));
        rules.add(new CategoryRule("wooden_shovel", 152, stack ->
            isTool(stack, "shovel") && isWooden(stack)));
        rules.add(new CategoryRule("wooden_hoe", 153, stack ->
            isTool(stack, "hoe") && isWooden(stack)));

        return rules;
    }

    private static boolean isTool(ItemStack stack, String toolType) {
        String id = getItemId(stack);
        return id.contains(toolType);
    }

    private static boolean isNetherite(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("netherite");
    }

    private static boolean isDiamond(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("diamond");
    }

    private static boolean isIron(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("iron");
    }

    private static boolean isGolden(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("golden") || id.contains("gold");
    }

    private static boolean isStone(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("stone");
    }

    private static boolean isWooden(ItemStack stack) {
        String id = getItemId(stack);
        return id.contains("wooden") || id.contains("wood");
    }

    private static String getItemId(ItemStack stack) {
        Identifier id = Registries.ITEM.getId(stack.getItem());
        return id.toString().toLowerCase();
    }
}
