# Zap Inventory Sorter - TODO

Future features planned for implementation.

## Player Inventory Features

- [ ] **Dropoff Button** - Deposits items to nearby chests (excluding hotbar/armor/offhand)
  - Shift-click variant: only drops off items already in chests
  - Note: Set aside for now - may be too far from vanilla gameplay

- [ ] **Favorite Items** - Alt-click to favorite items (prevents them from moving with buttons)

- [ ] **F to Switch** - Press F to swap item with offhand

- [ ] **Item Linking** - Shift+T to link items to chat

- [ ] **Delete Items** - Ctrl+Delete to delete items

- [ ] **Hotbar Changer** - Press X to show inventory above hotbar

## Container Features

- [ ] **Filter/Search Button** - Search bar for filtering container items
  - Set aside for later iteration

## Sorting Enhancements

- [ ] **Per-category sorting rules**
  - Food: sort by saturation/hunger value (not just item ID)
  - Tools: sort by durability remaining
  - Potions: sort by effect type
  - Arrows: sort by effect type

## Quality of Life

- [ ] **Better Crafting Shift-Clicking** - Shift-click moves items to crafting grid instead of hotbar

- [ ] **Right-click add to shulker box**

- [ ] **Rotate arrow types**

## Technical Improvements

- [ ] **Language/Translation support (i18n)**
  - Move hardcoded English strings to lang files
  - Support multiple languages

- [ ] **Configuration system**
  - Adjustable button positions (X/Y coordinates)
  - Toggle individual buttons on/off
  - Invert shift behavior
  - Debug mode to find GUI classnames
  - Whitelist/blacklist for specific container types
  - Auto-detect chests by block ID, mod ID, or tile entity

- [ ] **Additional container support**
  - Hoppers
  - Dispensers/Droppers
  - Other modded containers

## Notes

Features marked as "set aside" are deprioritized but not cancelled. They may be revisited based on user feedback and gameplay balance considerations.
