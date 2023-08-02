package VMF;

import java.util.*;

/**
 * The VendingMachineFactory class is responsible for creating and managing vending machines.
 * It allows creating vending machines, disposing of vending machines, and retrieving all vending machines.
 */
public class VendingMachineFactory {

    /**
     * A list to store all the vending machines created by the factory.
     */
    private List<VendingMachine> vendingMachines;

    /**
     * A list to store all the special FriedRiceVM vending machines created by the factory.
     */
    private List<FriedRiceVM> friedRiceVMS;

    /**
     * Constructs a new VendingMachineFactory object.
     * Initializes the lists for vending machines and special FriedRiceVMs.
     */
    public VendingMachineFactory() {
        vendingMachines = new ArrayList<>();
        friedRiceVMS = new ArrayList<>();
    }

    /**
     * Creates a new vending machine by prompting the user for input.
     *
     * @param machineName  the name of the vending machine
     * @param numSlots     the number of slots in the vending machine
     * @param slotCapacity the maximum capacity of each slot
     * @return the created vending machine
     */
    public VendingMachine createVendingMachine(String machineName, int numSlots, int slotCapacity) {
        VendingMachine vendingMachine = new VendingMachine(machineName, numSlots, slotCapacity);
        vendingMachines.add(vendingMachine);
        vendingMachine.printSlots();
        return vendingMachine;
    }

    /**
     * Creates a new special FriedRiceVM vending machine.
     *
     * @param machineName the name of the special FriedRiceVM
     * @param slotCap     the maximum capacity of each slot in the special FriedRiceVM
     * @return the created special FriedRiceVM vending machine
     */
    public FriedRiceVM createSpecialVM(String machineName, int slotCap) {
        FriedRiceVM friedRiceVM = new FriedRiceVM(machineName, slotCap);
        friedRiceVMS.add(friedRiceVM);
        return friedRiceVM;
    }

    /**
     * Returns a list of all vending machines created by the factory.
     *
     * @return the list of vending machines
     */
    public List<VendingMachine> getAllVendingMachines() {
        return vendingMachines;
    }

    /**
     * Returns a list of all special FriedRiceVM vending machines created by the factory.
     *
     * @return the list of special FriedRiceVM vending machines
     */
    public List<FriedRiceVM> getAllFriedRiceVMS() {
        return friedRiceVMS;
    }

    /**
     * Disposes of a vending machine by removing it from the factory's list of vending machines.
     *
     * @param vendingMachine the vending machine to dispose of
     */
    public void disposeVendingMachine(VendingMachine vendingMachine) {
        vendingMachines.remove(vendingMachine);
    }

    // Other utility methods...

}
