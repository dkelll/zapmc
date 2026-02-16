package zap.sorting.categories;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import zap.sorting.CategoryRule;

import java.util.ArrayList;
import java.util.List;

public class ArmorCategories {

    public static List<CategoryRule> getRules() {
        List<CategoryRule> rules = new ArrayList<>();

        // Netherite armor (200-203), Diamond (210-213), Iron (220-223),
        // Golden (230-233), Leather (240-243)
        String[] materials = {"netherite", "diamond", "iron", "golden", "leather"};
        String[] slots = {"helmet", "chestplate", "leggings", "boots"};

        for (int m = 0; m < materials.length; m++) {
            for (int s = 0; s < slots.length; s++) {
                final String material = materials[m];
                final String slot = slots[s];
                int priority = 200 + (m * 10) + s;

                rules.add(new CategoryRule(material + "_" + slot, priority, stack -> {
                    String id = getItemId(stack);
                    return id.contains(material) && id.contains(slot);
                }));
            }
        }

        return rules;
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString().toLowerCase();
    }
}
