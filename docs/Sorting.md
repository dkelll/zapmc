# Zap Sorting System - Category Structure

## Overview
The sorting system uses a weighted, rule-based approach where items are categorized and sorted by priority.

## File Structure
```
zap/
├── sorting/
│   ├── CategoryRule.java          # Core rule class
│   ├── ItemCategorizer.java       # Main categorizer
│   └── categories/
│       ├── ToolCategories.java    # Tools by material (priority 100-159)
│       ├── ArmorCategories.java   # Armor by material (priority 200-243)
│       ├── FoodCategories.java    # Food items (priority 300)
│       ├── PotionCategories.java  # Potions & arrows (priority 400-403)
│       ├── BlockCategories.java   # Blocks grouped by wood type (priority 500-700)
│       ├── MaterialCategories.java # Ingots, gems (priority 800-822)
│       └── MiscCategories.java    # Weapons, torches, etc. (priority 50-920)
```

## Priority Ranges
- **50-99**: Weapons (swords, bows, shields)
- **100-199**: Tools (by material: netherite → diamond → iron → gold → stone → wood)
- **200-299**: Armor (by material: netherite → diamond → iron → gold → leather)
- **300-399**: Food
- **400-499**: Potions and tipped arrows
- **500-699**: Blocks (grouped by wood type)
- **700-799**: General blocks
- **800-899**: Materials (ingots, gems, coal, redstone)
- **900-999**: Misc (torches, buckets, books)
- **9999**: Miscellaneous (catch-all)

## How It Works
1. When an item needs categorization, `ItemCategorizer.categorize()` is called
2. Rules are checked in order from all category files
3. The first matching rule is returned
4. Items are sorted by their category's priority (lower = earlier in inventory)
5. Within the same priority, items of the same material/type are grouped together

## Adding New Categories
1. Create a new file in `zap/sorting/categories/` (e.g., `CustomCategories.java`)
2. Add rules with appropriate priorities
3. Register in `ItemCategorizer.java` static block:
   ```java
   RULES.addAll(CustomCategories.getRules());
   ```

## Example Usage
```java
ItemStack diamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
CategoryRule rule = ItemCategorizer.categorize(diamondPickaxe);
// Returns: CategoryRule{name='diamond_pickaxe', priority=110}
```
