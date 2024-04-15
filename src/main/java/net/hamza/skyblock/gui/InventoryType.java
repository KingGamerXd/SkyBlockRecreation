package net.hamza.skyblock.gui;

public enum InventoryType {
    SMALL(9),
    MEDIUM(27),
    LARGE(54);

    private final int slots;

    InventoryType(int slots) {
        this.slots = slots;
    }

    public int getSlots() {
        return slots;
    }

}
