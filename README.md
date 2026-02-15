# Quark Inventory Sorter (Fabric)

A Fabric mod that replicates all inventory sorting and management features from Quark's inventory sorting module.

## Features

### Player Inventory Features

- **Sort Button** - Sorts inventory (excluding hotbar) by category system
- **Dropoff Button** - Deposits items to nearby chests (excluding hotbar/armor/offhand)
  - Shift-click variant: only drops off items already in chests
- **Favorite Items** - Alt-click to favorite items (prevents them from moving with buttons)
- **F to Switch** - Press F to swap item with offhand
- **Item Linking** - Shift+T to link items to chat
- **Delete Items** - Ctrl+Delete to delete items
- **Hotbar Changer** - Press X to show inventory above hotbar

### Chest/Container Buttons

- **Extract Button** - Removes items from chest that already exist in inventory
- **Deposit Button** - Moves all inventory items into chest (excluding hotbar/armor/offhand)
- **Restock Button** - Only moves items that already exist in the chest
- **Sort Button** - Sorts the chest contents
- **Filter/Search Button** - Search bar for filtering chest items

### Sorting Algorithm

The mod uses a category-based sorting system with the following rules:

- Items are grouped by category (food, tools, armor, blocks, etc.)
- Per-category sorting rules:
  - **Food**: sorted by saturation/hunger value
  - **Tools**: sorted by quality/durability
  - **Potions/Arrows**: sorted by effects
  - Items grouped by type, then by damage value

### Configuration Options

- Adjustable button positions (X/Y coordinates)
- Toggle individual buttons on/off
- Invert shift behavior
- Debug mode to find GUI classnames
- Whitelist/blacklist for specific container types
- Auto-detect chests by block ID, mod ID, or tile entity

### Additional Features

- **Better Crafting Shift-Clicking** - Shift-click moves items to crafting grid instead of hotbar
- **Right-click add to shulker box**
- **Rotate arrow types**

## Installation

TBD

## License

TBD
