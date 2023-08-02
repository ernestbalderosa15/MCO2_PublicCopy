package VMF;

import java.util.ArrayList;

/**
 * The VendingMachineFactory.FriedRiceVM class represents a special vending machine for selling fried rice.
 * It extends the VendingMachine class and provides additional functionality for managing fried rice slots.
 */
public class FriedRiceVM extends VendingMachine {
    private int riceOccupied = 0;         // The number of slots occupied by rice
    private int meatOccupied = 0;         // The number of slots occupied by meat
    private int eggOccupied = 0;          // The number of slots occupied by egg
    private int condimentsOccupied = 0;   // The number of slots occupied by condiments

    /**
     * Constructs a new VendingMachineFactory.FriedRiceVM object with the specified machine name and slot capacity.
     *
     * @param machineName the name of the vending machine
     * @param slotCapacity the capacity of each slot in the vending machine
     */
    public FriedRiceVM(String machineName, int slotCapacity) {
        super(machineName, 13, slotCapacity);
    }

    /**
     * Passes the slot type to the selected slot and keeps track of the number of slots occupied by each type.
     *
     * @param selectedSlot the selected slot to set the slot type
     * @param slotType the type of the slot (e.g., "Rice", "Meat", "Egg", or "Condiments")
     */
    public void passSlotType(FrmSlot selectedSlot, String slotType) {
        selectedSlot.setSlotType(slotType);
        if (slotType.equals("Rice")) {
            riceOccupied++;
        } else if (slotType.equals("Meat")) {
            meatOccupied++;
        } else if (slotType.equals("Egg")) {
            eggOccupied++;
        } else if (slotType.equals("Condiments")) {
            condimentsOccupied++;
        }
    }

    /**
     * Returns the number of slots occupied by a specific slot type.
     *
     * @param slotType the type of the slot (e.g., "Rice", "Meat", "Egg", or "Condiments")
     * @return the number of slots occupied by the specified slot type
     */
    public int getSlotTypeOccupied(String slotType) {
        int toReturn = 0;
        if (slotType.equals("Rice")) {
            toReturn = riceOccupied;
        } else if (slotType.equals("Meat")) {
            toReturn = meatOccupied;
        } else if (slotType.equals("Egg")) {
            toReturn = eggOccupied;
        } else if (slotType.equals("Condiments")) {
            toReturn = condimentsOccupied;
        }
        return toReturn;
    }

    @Override
    public boolean getMachineType() {
        return true;
    }

    /**
     * Buys special items from the selected slots and deducts the price from the collected money.
     *
     * @param slotsToBuy the list of selected slots to buy items from
     * @param collected the money collected for the purchase
     */
    public void buySpecialItem(ArrayList<FrmSlot> slotsToBuy, ArrayList<Float> collected) {
        for (FrmSlot slot : slotsToBuy) {
            slot.getItem().getPrice();
        }
    }
}
