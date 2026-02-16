package zap.sorting;

import net.minecraft.item.ItemStack;
import java.util.function.Predicate;

/**
 * Represents a single sorting category rule with priority and matching logic.
 * Lower priority values are sorted first (appear earlier in inventory).
 */
public class CategoryRule {
    private final String name;
    private final int priority;
    private final Predicate<ItemStack> matcher;

    public CategoryRule(String name, int priority, Predicate<ItemStack> matcher) {
        this.name = name;
        this.priority = priority;
        this.matcher = matcher;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public boolean matches(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        return matcher.test(stack);
    }

    @Override
    public String toString() {
        return "CategoryRule{name='" + name + "', priority=" + priority + "}";
    }
}
