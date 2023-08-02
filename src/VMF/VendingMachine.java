package VMF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * The VendingMachine class represents a vending machine that contains slots to hold items,
 * manages money, and facilitates buying and restocking operations.
 */
public class VendingMachine {
    /**
     * The name of the vending machine.
     */
    private final String machineName;

    /**
     * An ArrayList containing all the slots in the vending machine.
     */
    private final ArrayList<FrmSlot> slots;

    /**
     * The current number of slots in the vending machine.
     */
    private int numSlots;

    /**
     * The maximum capacity of each slot in the vending machine.
     */
    private final int slotCapacity;

    /**
     * The MoneyManager object responsible for managing money denominations and quantities in the vending machine.
     */
    private final MoneyManager moneyManager;

    /**
     * A flag indicating whether the vending machine is a special vending machine or not.
     * Default value is false.
     */
    protected boolean isSpecial = false;

    /**
     * An ArrayList that keeps track of the initialized slots in the vending machine.
     */
    public ArrayList<Integer> initializedSlots = new ArrayList<>();

    /**
     * Constructs a new VendingMachine object with the specified machine name, number of slots, and slot capacity.
     *
     * @param machineName  the name of the vending machine
     * @param numSlots     the number of slots in the vending machine
     * @param slotCapacity the maximum capacity of each slot
     */
    public VendingMachine(String machineName, int numSlots, int slotCapacity) {
        this.machineName = machineName;
        this.numSlots = numSlots;
        slots = new ArrayList<>(numSlots);
        this.slotCapacity = slotCapacity;
        moneyManager = new MoneyManager();
    }

    /**
     * Returns the FrmSlot object at the specified slot choice.
     *
     * @param slotChoice the slot choice
     * @return the FrmSlot object at the specified slot choice
     */
    public FrmSlot getSlot(int slotChoice) {
        return slots.get(slotChoice);
    }

    /**
     * Returns all the slots in the vending machine.
     *
     * @return an ArrayList of all the FrmSlots in the vending machine
     */
    public ArrayList<FrmSlot> getAllSlots() {
        return slots;
    }

    /**
     * Replenishes the money in the vending machine with the specified denomination and quantity.
     *
     * @param denomination the denomination of the money
     * @param quantity     the quantity of the money to replenish
     */
    public void replenishChange(Float denomination, int quantity) {
        moneyManager.replenishMoney(denomination, quantity);
    }

    /**
     * Checks if it is possible to give change for the specified amount using the available denominations.
     *
     * @param change the amount of change to be given
     * @return true if it is possible to give change, false otherwise
     */
    public boolean canGiveChange(float change) {
        // Check if there are enough denominations to give the change
        if (change > moneyManager.getTotalMoney()) {
            return false;
        }

        // Sort the available denominations in descending order
        ArrayList<Float> availableDenominations = new ArrayList<>(Arrays.asList(1f, 5f, 10f, 20f, 50f, 100f, 200f, 500f, 1000f));
        availableDenominations.sort(Collections.reverseOrder());

        // Check if the change can be given using available denominations
        for (float denomination : availableDenominations) {
            int quantity = moneyManager.getMoneyQuantity(denomination);
            while (change >= denomination && quantity > 0) {
                change -= denomination;
                quantity--;
            }
        }

        return change == 0;
    }

    /**
     * Returns the money with the specified amount to the money manager.
     *
     * @param amount the amount of money to be returned
     * @return an ArrayList of denominations that were returned
     */
    public ArrayList<Float> returnMoney(float amount) {
        ArrayList<Float> availableDenominations = new ArrayList<>(moneyManager.getAvailableDenominations());
        availableDenominations.sort(Collections.reverseOrder());
        ArrayList<Float> returnedMoney = new ArrayList<>();

        for (float denomination : availableDenominations) {
            int quantity = moneyManager.getMoneyQuantity(denomination);
            int count = 0;
            while (amount >= denomination && quantity > 0) {
                amount -= denomination;
                quantity--;
                count++;
            }
            if (count > 0) {
                moneyManager.replenishMoney(denomination, -count); // Subtract the returned money from the money manager
                returnedMoney.add(denomination);
                System.out.println("$" + denomination + " x " + count);
            }
        }
        return returnedMoney;
    }

    /**
     * Prints the details of all the slots in the vending machine.
     */
    public void printSlots() {
        System.out.println("Vending Machine Slots");
        System.out.println("---------------------");

        for (int i = 0; i < slots.size(); i++) {
            FrmSlot slot = slots.get(i);
            System.out.println(machineName + "Slot " + (i + 1) + ": " + slot.getItem().getName() +
                    " - $" + slot.getItem().getPrice() + " (" + slot.getItemQuantity() + " available)");
        }
    }

    /**
     * Prints the availability of money in the vending machine.
     */
    public void printMoneyAvailability() {
        moneyManager.printMoneyAvailability();
    }

    /**
     * Returns the money manager object of the vending machine.
     *
     * @return the money manager object
     */
    public MoneyManager getMoney() {
        return moneyManager;
    }

    /**
     * Returns the total sales of the vending machine.
     *
     * @return the total sales of the vending machine
     */
    public float getTotalSales() {
        float totalSales = 0;
        for (FrmSlot slot : slots) {
            totalSales += slot.getSlotRevenue();
        }
        return totalSales;
    }

    /**
     * Returns the name of the vending machine.
     *
     * @return the name of the vending machine
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Displays the starting inventory and current inventory of the vending machine.
     */
    public void displayInventories() {
        System.out.println("Starting Inventory");
        System.out.println("------------------");
        System.out.println("FrmSlot\tItem\tQuantity");

        System.out.println("\nCurrent Inventory");
        System.out.println("------------------");
        System.out.println("FrmSlot\tItem\tQuantity");

        for (int i = 0; i < slots.size(); i++) {
            FrmSlot slot = slots.get(i);
            Item item = slot.getItem();
            String itemName = item.getName();
            int quantity = slot.getItemQuantity();
            System.out.printf("%d\t%s\t%d%n", (i + 1), itemName, quantity);
        }
    }

    /**
     * Collects the profit from the vending machine and empties out the money.
     *
     * @return true if money is successfully collected, false otherwise
     */
    public boolean takeProfit() {
        float collectedMoney = this.getMoney().getTotalMoney();
        if (collectedMoney > 0) {
            System.out.println("Success! $" + collectedMoney + " has been collected.");
            moneyManager.emptyOutMoney();
            return true;
        } else {
            System.out.println("No more money left to collect. Please try again.");
            return false;
        }
    }

    /**
     * Returns the total number of items sold from the vending machine.
     *
     * @return the total number of items sold
     */
    public int getTotalSoldItems() {
        int totalItemsSold = 0;

        for (FrmSlot slot : slots) {
            totalItemsSold += slot.getItemSold_Slot();
        }

        return totalItemsSold;
    }

    /**
     * Replenishes the vending machine with money by specifying the quantity for each denomination.
     */
    public void replenishMachine() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Float> denominations = new ArrayList<>(Arrays.asList(1f, 5f, 10f, 20f, 50f, 100f, 200f, 500f, 1000f));

        for (float denomination : denominations) {
            System.out.print("Enter quantity for $" + denomination + ": ");
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                quantity = 0;
            }
            moneyManager.replenishMoney(denomination, quantity);
        }
    }

    /**
     * Sets the price of an item in a specified slot.
     *
     * @param slotNumber the slot number
     * @param newPrice   the new price to set
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void setItemPrice(int slotNumber, float newPrice) throws InterruptedException {
        this.slots.get(slotNumber).getItem().setPrice(newPrice);
        System.out.println("Success... New price set for " + this.slots.get(slotNumber).getItem().getName());
        Thread.sleep(1000);
    }

    /**
     * Sets the stock of an item in a specified slot.
     *
     * @param slotNumber the slot number
     * @param newStock   the new stock quantity to set
     */
    public void setItemStock(int slotNumber, int newStock) throws InterruptedException {
        if (this.slots.get(slotNumber).addQuantity(newStock)) {
            System.out.println("Success... Stocks added for " + this.slots.get(slotNumber).getItem().getName());
        }
    }

    /**
     * Allows the user to buy an item from a specified slot by inserting money.
     *
     * @param slotNumber the slot number
     */
    public void buyItem(int slotNumber, ArrayList<Float> collected) {
        moneyManager.printMoneyAvailability();
        FrmSlot slot = slots.get(slotNumber);
        collected.forEach(value -> {
            replenishChange(value, 1);
        });

        int initNum = slot.getItemQuantity();
        int finNum;
        slot.dispenseItem();
        finNum = slot.getItemQuantity();
        boolean isDispensed = false;
        if (initNum > finNum) {
            isDispensed = true;
        }

        System.out.println("Is item dispensed? " + isDispensed);
        slot.setItemSold_slot();
        slot.setSlotRevenue();
        moneyManager.printMoneyAvailability();
    }

    /**
     * Sets the photo path of an item in a specified slot.
     *
     * @param slotNum  the slot number
     * @param filePath the file path of the item photo
     */
    public void setItemPhotoPath(int slotNum, String filePath) {
        slots.get(slotNum).getItem().setItemPhotoPath(filePath);
    }

    /**
     * Returns the photo path of an item in a specified slot.
     *
     * @param slotNum the slot number
     * @return the file path of the item photo
     */
    public String getItemPhotoPath(int slotNum) {
        return slots.get(slotNum).getItem().getItemPhotoPath();
    }

    /**
     * Adds items to a specified slot.
     *
     * @param slotNumber the slot number
     * @param itemName   the name of the item
     * @param itemPrice  the price of the item
     * @param itemCal    the calorie count of the item
     * @param itemQuant  the initial quantity of the item
     */
    public void addItems(int slotNumber, String itemName, float itemPrice, float itemCal, int itemQuant) {
        Item item = new Item(itemName, itemPrice, itemCal);
        FrmSlot slot = new FrmSlot(item, itemQuant, slotCapacity);
        this.slots.add(slot);
    }

    /**
     * Returns the slot capacity of the vending machine.
     *
     * @return the slot capacity
     */
    public int getSlotCapacity() {
        return slotCapacity;
    }

    /**
     * Returns the number of slots in the vending machine.
     *
     * @return the number of slots
     */
    public int getNumSlots() {
        return this.numSlots;
    }

    /**
     * Returns the machine type of the vending machine.
     *
     * @return true if the machine is special, false otherwise
     */
    public boolean getMachineType() {
        return isSpecial;
    }

    /**
     * Returns the name of the vending machine.
     *
     * @return the name of the vending machine
     */
    public String getName() {
        return machineName;
    }

}
