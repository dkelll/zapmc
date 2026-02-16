package zap.sorting;

import net.minecraft.item.ItemStack;
import zap.sorting.categories.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Main categorizer that matches items to their appropriate category.
 * Uses a priority-based rule system where rules are checked in order
 * and the first matching rule is used.
 */
public class ItemCategorizer {
    private static final List<CategoryRule> RULES = new ArrayList<>();
    private static final CategoryRule MISC_RULE = new CategoryRule("misc", 9999, stack -> true);

    static {
        // Initialize all category rules
        // Rules are checked in order, first match wins
        RULES.addAll(ToolCategories.getRules());
        RULES.addAll(ArmorCategories.getRules());
        RULES.addAll(FoodCategories.getRules());
        RULES.addAll(PotionCategories.getRules());
        RULES.addAll(BlockCategories.getRules());
        RULES.addAll(MaterialCategories.getRules());
        RULES.addAll(MiscCategories.getRules());
    }

    /**
     * Categorizes an item stack by finding the first matching rule.
     * @param stack The item stack to categorize
     * @return The matching category rule, or MISC if no match found
     */
    public static CategoryRule categorize(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return MISC_RULE;
        }

        return RULES.stream()
            .filter(rule -> rule.matches(stack))
            .findFirst()
            .orElse(MISC_RULE);
    }

    /**
     * Gets all registered category rules (for debugging/config purposes).
     */
    public static List<CategoryRule> getAllRules() {
        return new ArrayList<>(RULES);
    }
}
