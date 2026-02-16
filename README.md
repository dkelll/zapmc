# Zap Inventory Sorter

A Fabric mod for Minecraft 1.21.11 that adds inventory sorting and management features for both player inventories and containers.

## Features

### Player Inventory

- **Sort Button** - Sorts your main inventory (excluding hotbar, armor, and offhand) by category priority
  - Position: Right side of inventory screen
  - Organizes items by type: weapons → tools → armor → food → potions → blocks → materials

### Container Management

All container screens (chests, barrels, shulker boxes) include four management buttons:

- **Extract [E]** - Pulls items from container that match items in your inventory
- **Restock [R]** - Deposits only items that already exist in the container (smart deposit)
- **Deposit [D]** - Deposits all items from your inventory into the container
- **Sort [S]** - Sorts the container contents by the same category system

### Sorting System

Items are organized by a weighted category system with priority-based sorting:

**Priority Order:**
1. Weapons (swords, bows, shields)
2. Tools (by material: netherite → diamond → iron → gold → stone → wood)
3. Armor (by material: netherite → diamond → iron → gold → leather)
4. Food
5. Potions & tipped arrows
6. Blocks (grouped by wood type for building materials)
7. Materials (ingots, gems, coal, redstone)
8. Miscellaneous items

Within each category, items are further sorted by item ID for consistent grouping.

## Installation

### Requirements
- Minecraft 1.21.11
- Fabric Loader 0.18.4+
- Fabric API

### Server & Client
Both server and client need the mod installed. Players must have the mod in their local mods folder - it cannot be downloaded from the server automatically.

### Steps
1. Install Fabric Loader for Minecraft 1.21.11
2. Download Fabric API
3. Download Zap mod JAR
4. Place both JARs in your `mods/` folder
5. Launch Minecraft

## Usage

- Open your inventory and click the **S** button on the right to sort
- Open any container (chest, barrel, shulker box) to see the **[E] [R] [D] [S]** buttons
- Hover over buttons for tooltips explaining what each does

## Contributing

Contributions are welcome! To contribute:

1. **Fork the repository** - Click the "Fork" button on GitHub
2. **Create a feature branch** - `git checkout -b feature/your-feature-name`
3. **Make your changes** - Follow the existing code style
4. **Test thoroughly** - Ensure your changes work in both single-player and multiplayer
5. **Commit your changes** - `git commit -am 'Add some feature'`
6. **Push to your fork** - `git push origin feature/your-feature-name`
7. **Open a Pull Request** - Submit a PR to the main branch

### Development Setup

See the project structure documentation for details on how the mod is organized. Key files:
- Sorting logic: `src/main/java/zap/sorting/`
- UI buttons: `src/client/java/zap/mixin/client/`
- Networking: `src/main/java/zap/network/`

Check `docs/TODO.md` for planned features that need implementation.

## License

MIT
