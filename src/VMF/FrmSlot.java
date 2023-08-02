package VMF;

/**
 * The VendingMachineFactory.FrmSlot class represents a slot in a vending machine's inventory system
 * with additional features like slot type and separate selling flag.
 */
public class FrmSlot extends Slot {

    /**
     * The type of the slot.
     */
    String slotType;

    /**
     * A flag indicating whether the item in the slot is sold separately or not.
     */
    boolean isSoldSep;

    /**
     * Constructs a new VendingMachineFactory.FrmSlot object with the specified item, quantity, and capacity.
     *
     * @param item       the item associated with the slot
     * @param itemQuant  the initial quantity of the item in the slot
     * @param slotCap    the capacity of the slot
     */
    protected FrmSlot(Item item, int itemQuant, int slotCap) {
        super(item, itemQuant, slotCap);
    }

    /**
     * Sets the type of the slot and updates the separate selling flag accordingly.
     *
     * @param slotType the type of the slot (e.g., "Condiment" or any other type)
     */
    public void setSlotType(String slotType) {
        this.slotType = slotType;

        if ("Condiment".equals(slotType)) {
            isSoldSep = false;
        } else {
            isSoldSep = true;
        }
    }

    /**
     * Returns the type of the slot.
     *
     * @return the type of the slot
     */
    public String getSlotType() {
        return slotType;
    }
}
