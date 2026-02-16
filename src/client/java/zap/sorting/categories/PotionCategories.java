package zap.sorting.categories;

import net.minecraft.item.*;
import zap.sorting.CategoryRule;
import java.util.ArrayList;
import java.util.List;

public class PotionCategories {
    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();
        rules.add(new CategoryRule("potions", 400, stack ->
            stack.getItem() instanceof PotionItem));
        rules.add(new CategoryRule("splash_potions", 401, stack ->
            stack.getItem() instanceof SplashPotionItem));
        rules.add(new CategoryRule("lingering_potions", 402, stack ->
            stack.getItem() instanceof LingeringPotionItem));
        rules.add(new CategoryRule("tipped_arrows", 403, stack ->
            stack.getItem() instanceof TippedArrowItem));
        return rules;
    }
}
