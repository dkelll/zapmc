package zap.sorting.categories;

import net.minecraft.item.*;
import net.minecraft.component.DataComponentTypes;
import zap.sorting.CategoryRule;
import java.util.ArrayList;
import java.util.List;

public class FoodCategories {
    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();
        rules.add(new CategoryRule("food", 300, stack ->
            stack.contains(DataComponentTypes.FOOD)));
        return rules;
    }
}
