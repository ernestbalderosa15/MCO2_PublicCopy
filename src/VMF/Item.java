package VMF;

/**
 * The VendingMachineFactory.Item class represents an item with a name, price, and calories.
 */
public class Item {

    /**
     * The name of the item.
     */
    private final String name;

    /**
     * The price of the item.
     */
    private float price;

    /**
     * The calories of the item.
     */
    private final float calories;

    /**
     * The file path of the item's photo.
     */
    private String itemPhotoPath;

    /**
     * A flag indicating whether the item is sold separately or not.
     * Default value is false.
     */
    private boolean isSoldSep = false;

    /**
     * Constructs a VendingMachineFactory.Item object with the specified name, price, and calories.
     *
     * @param n the name of the item
     * @param p the price of the item
     * @param c the calories of the item
     */
    protected Item(String n, float p, float c) {
        name = n;
        price = p;
        calories = c;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the calories of the item.
     *
     * @return the calories of the item
     */
    public float getCalories() {
        return calories;
    }

    /**
     * Sets the price of the item to the specified value.
     *
     * @param p the new price of the item
     */
    public void setPrice(float p) {
        price = p;
    }

    /**
     * Checks if the item is empty (i.e., name is empty).
     *
     * @return true if the item is empty, false otherwise
     */
    public boolean isEmpty() {
        return name.isEmpty();
    }

    /**
     * Sets the flag indicating whether the item is sold separately or not.
     *
     * @param TorF the flag value (true if sold separately, false otherwise)
     */
    public void setSoldSep(boolean TorF) {
        isSoldSep = TorF;
    }

    /**
     * Returns the flag indicating whether the item is sold separately or not.
     *
     * @return true if the item is sold separately, false otherwise
     */
    public boolean getSoldSep() {
        return isSoldSep;
    }

    /**
     * Sets the file path of the item's photo.
     *
     * @param photoPath the file path of the item's photo
     */
    public void setItemPhotoPath(String photoPath) {
        itemPhotoPath = photoPath;
    }

    /**
     * Returns the file path of the item's photo.
     *
     * @return the file path of the item's photo
     */
    public String getItemPhotoPath() {
        return itemPhotoPath;
    }
}
