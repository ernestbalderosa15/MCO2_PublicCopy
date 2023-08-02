package VMF;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The VendingMachineFactory.MoneyManager class is responsible for managing money denominations and quantities.
 * It allows replenishing money, calculating the total money, retrieving money quantities,
 * getting available denominations, printing money availability, and emptying out the money.
 */
public class MoneyManager {
    /**
     * The list of money denominations managed by the MoneyManager.
     */
    private final ArrayList<Float> moneyDenominations;

    /**
     * The list of corresponding quantities for each money denomination.
     */
    private ArrayList<Integer> moneyQuantities;

    /**
     * Constructs a new VendingMachineFactory.MoneyManager object.
     */
    MoneyManager() {
        moneyDenominations = new ArrayList<>();
        moneyQuantities = new ArrayList<>();
    }

    /**
     * Replenishes the money by adding the specified denomination and quantity.
     * If the denomination already exists, the quantity is updated, otherwise, a new denomination is added.
     *
     * @param denomination the denomination to replenish
     * @param quantity     the quantity to replenish
     */
    public void replenishMoney(float denomination, int quantity) {
        int index = moneyDenominations.indexOf(denomination);

        if (index != -1) {
            int currentQuantity = moneyQuantities.get(index);
            int newQuantity = currentQuantity + quantity;
            moneyQuantities.set(index, newQuantity);
        } else {
            moneyDenominations.add(denomination);
            moneyQuantities.add(quantity);
        }
    }

    /**
     * Calculates the total amount of money based on the denominations and quantities.
     *
     * @return the total money amount
     */
    public float getTotalMoney() {
        float totalMoney = 0;

        for (int i = 0; i < moneyDenominations.size(); i++) {
            float denomination = moneyDenominations.get(i);
            int quantity = moneyQuantities.get(i);
            totalMoney += denomination * quantity;
        }

        return totalMoney;
    }

    /**
     * Retrieves the quantity of money for the specified denomination.
     *
     * @param denomination the denomination to retrieve the quantity for
     * @return the quantity of money for the specified denomination
     */
    public int getMoneyQuantity(float denomination) {
        int index = moneyDenominations.indexOf(denomination);

        if (index != -1) {
            return moneyQuantities.get(index);
        }

        return 0;
    }

    /**
     * Retrieves a list of available money denominations.
     *
     * @return a list of available money denominations
     */
    public ArrayList<Float> getAvailableDenominations() {
        return new ArrayList<>(moneyDenominations);
    }

    /**
     * Prints the availability of money denominations and their quantities.
     */
    public void printMoneyAvailability() {
        System.out.println("Money Availability");
        System.out.println("------------------");

        for (int i = 0; i < moneyDenominations.size(); i++) {
            float denomination = moneyDenominations.get(i);
            int quantity = moneyQuantities.get(i);
            System.out.println("$" + denomination + " - " + quantity);
        }
    }

    /**
     * Empties out the money by setting all quantities to zero.
     */
    public void emptyOutMoney() {
        Collections.fill(moneyQuantities, 0);
    }
}
