package GUI;
import VMF.FrmSlot;
import VMF.Slot;
import VMF.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * BuyItemGUI is a user interface for purchasing items from a vending machine.
 * Users can select items from available slots, insert money, and purchase the items.
 * Money can be inserted in various denominations, and the change is calculated if necessary.
 */
public class BuyItemGUI extends JFrame implements ActionListener {
    private VendingMachine selectedMachine;
    private ArrayList<JButton> itemButtons;
    private float totalAmount;
    private int selectedSlotNumber;
    private JLabel selectedItemLabel;
    private ArrayList<Float> collectedMoney;
    private JLabel totalAmountLabel;
    JPanel transactionPanel = new JPanel();

    /**
     * Constructor for BuyItemGUI.
     *
     * @param selectedMachine The VendingMachine object from which items will be purchased.
     */
    public BuyItemGUI(VendingMachine selectedMachine) {
        this.selectedMachine = selectedMachine;

        itemButtons = new ArrayList<>();
        totalAmount = 0;
        selectedSlotNumber = -1;
        collectedMoney = new ArrayList<>();
        setupGUI();
        setupMoneyPanel();

        selectedItemLabel = new JLabel("");
        transactionPanel.add(selectedItemLabel); // Add the "Selected Item" label to the transaction panel

        // Add vertical space
        transactionPanel.add(Box.createVerticalStrut(10)); // Create a 10-pixel vertical space

        totalAmountLabel = new JLabel("Total Amount: $" + totalAmount);
        transactionPanel.add(totalAmountLabel);


        centerFrameOnScreen(this);
        setTitle("Buy Items");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    /**
     * Set up the money panel with buttons representing different denominations.
     */
    private void setupMoneyPanel() {
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new GridLayout(1, 0, 5, 5)); // One row for money buttons

        ArrayList<Float> denominations = new ArrayList<>(Arrays.asList(1f, 5f, 10f, 20f, 50f, 100f, 200f, 500f, 1000f));
        for (float denomination : denominations) {
            JButton moneyButton = new JButton("$" + denomination);
            moneyButton.setActionCommand(Float.toString(denomination));
            moneyButton.addActionListener(this);
            moneyPanel.add(moneyButton);
        }

        add(moneyPanel, BorderLayout.SOUTH);
    }

    /**
     * Set up the graphical user interface for selecting and purchasing items.
     */
    private void setupGUI() {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Maximum 3 items per row, 10px spacing between components

        ArrayList<FrmSlot> slots = selectedMachine.getAllSlots();
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            String itemName = slot.getItem().getName();
            int itemQuantity = slot.getItemQuantity();
            float itemPrice = slot.getItem().getPrice();
            String itemPhotoPath = selectedMachine.getItemPhotoPath(i); // Get the photo file path

            // Load the ImageIcon and set a default size for the photo
            ImageIcon itemIcon = new ImageIcon(itemPhotoPath);
            int iconWidth = 100; // Set the desired width of the photo
            int iconHeight = 100; // Set the desired height of the photo
            itemIcon.setImage(itemIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));

            JButton itemButton = new JButton(itemName + " ($" + itemPrice + ") - " + itemQuantity + " available", itemIcon);
            if(itemQuantity == 0){
                itemButton.setEnabled(false);
            }
            itemButton.setActionCommand(Integer.toString(i)); // Set the action command to the slot number for identification
            System.out.println();
            itemButton.addActionListener(this);
            itemButtons.add(itemButton);
            itemPanel.add(itemButton);
        }

        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS)); // One component per row
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JButton cancelButton = new JButton("Cancel Transaction");
        cancelButton.addActionListener(this);
        transactionPanel.add(cancelButton);

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        transactionPanel.add(buyButton);

        // Add the transactionPanel to a new JPanel that uses BorderLayout
        // This will ensure that the transactionPanel is positioned correctly on the left side
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(transactionPanel, BorderLayout.NORTH);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(itemPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);   // Transaction panel is now on the left (WEST) side
    }

    // ... (Other private helper methods) ...
    private void updateSelectedItemLabel(int slotNumber) {
        Slot slot = selectedMachine.getSlot(slotNumber);
        String itemName = slot.getItem().getName();
        float itemPrice = slot.getItem().getPrice();
        selectedItemLabel.setText("Selected Item: " + itemName + " ($" + itemPrice + ")");
    }

    private void updateTransactionSummary() {
        totalAmountLabel.setText("Total Amount: $" + totalAmount); // Access totalAmountLabel directly
    }

    private void collectMoney(float denomination) {
        collectedMoney.add(denomination);
        totalAmount += denomination;
        updateTransactionSummary();
    }

    private void refundMoney() {
        // If there's no money collected, return immediately
        if (collectedMoney.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No money to refund.");
            return;
        }

        // Sort the collected money in descending order
        Collections.sort(collectedMoney, Collections.reverseOrder());

        StringBuilder refundBreakdown = new StringBuilder("Transaction Cancelled. Refund breakdown:\n");
        float sumVal = 0;
        for(float money : collectedMoney){
            sumVal += money;
            refundBreakdown.append("$").append(money).append("\n");
        }

        // Append the total refund to the refundBreakdown
        refundBreakdown.append("Total money refunded: $").append(sumVal);

        JOptionPane.showMessageDialog(this, refundBreakdown.toString());
        collectedMoney.clear();
        totalAmount = 0;
        updateTransactionSummary();
    }


    private void updateItemButton(int slotNumber, int newQuantity) {
        Slot selectedSlot = selectedMachine.getSlot(slotNumber);
        JButton itemButton = itemButtons.get(slotNumber);
        String itemName = selectedSlot.getItem().getName();
        float itemPrice = selectedSlot.getItem().getPrice();
        ImageIcon itemIcon = (ImageIcon) itemButton.getIcon();
        int iconWidth = 100; // Set the desired width of the photo
        int iconHeight = 100; // Set the desired height of the photo
        itemIcon.setImage(itemIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));

        // Update the JButton text with the new item quantity
        itemButton.setText(itemName + " ($" + itemPrice + ") - " + newQuantity + " available");
        itemButton.setIcon(itemIcon);

        if(newQuantity == 0){
            itemButton.setEnabled(false);
        }

        revalidate();
        repaint();
    }

    private boolean completeTransaction() {
        boolean isCompleted = false;
        Slot selectedSlot = selectedMachine.getSlot(selectedSlotNumber);
        float slotPrice = selectedSlot.getItem().getPrice();

        if (totalAmount < slotPrice) {
            // Cancel the transaction and refund the money
            JOptionPane.showMessageDialog(this, "Insert more money.");
        } else if (totalAmount > slotPrice) {
            // Check if change can be given
            float change = totalAmount - slotPrice;
            boolean canGiveChange = selectedMachine.canGiveChange(change);

            if (canGiveChange) {
                selectedMachine.buyItem(selectedSlotNumber, collectedMoney);
                ArrayList<Float> changeDenominations = selectedMachine.returnMoney(change);
                Collections.sort(changeDenominations, Collections.reverseOrder());
                // Create a message to display the change denominations
                StringBuilder changeMessage = new StringBuilder("Change returned: $" + change + "\n");
                changeMessage.append("Total Calories: " + selectedSlot.getItem().getCalories());

                JOptionPane.showMessageDialog(this, changeMessage.toString());

                int updatedItemQuantity = selectedMachine.getSlot(selectedSlotNumber).getItemQuantity();
                updateItemButton(selectedSlotNumber, updatedItemQuantity);
                collectedMoney.clear();
                totalAmount = 0;
                updateTransactionSummary();
            }
            else {
                // Cancel the transaction and refund the money
                JOptionPane.showMessageDialog(this, "Sorry, Can't give change.");
                refundMoney();
                this.dispose();
                TestVM_Regular testVM = new TestVM_Regular();
                // Update the transaction summary to show the denominations entered
                // collectedMoney contains all the denominations entered
            }
        } else if (totalAmount == slotPrice) {
            // Complete the transaction, give the item
            selectedMachine.buyItem(selectedSlotNumber, collectedMoney);
            StringBuilder messageToShow = new StringBuilder("Success! Item purchased.\n");
            messageToShow.append("Total calories: " +selectedSlot.getItem().getCalories());
            // Show the success message
            JOptionPane.showMessageDialog(this, messageToShow.toString());

            // Update the item quantity in the GUI
            int updatedItemQuantity = selectedSlot.getItemQuantity();
            updateItemButton(selectedSlotNumber, updatedItemQuantity);
            collectedMoney.clear();
            totalAmount = 0;
            isCompleted = true;
            // Update the transaction summary to show the item bought
        }

        // Reset selectedSlotNumber and clear the collectedMoney
        return isCompleted;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            System.out.println(command);
            if (sourceButton.getText().startsWith("$") && selectedSlotNumber != -1) {
                float denomination = Float.parseFloat(command);
                collectMoney(denomination);
            }

            if (sourceButton.getText().equals("Cancel Transaction")) {
                refundMoney();
                this.dispose();
                TestVM_Regular testVM = new TestVM_Regular();
            } else if (sourceButton.getText().equals("Buy")) {
                if (selectedSlotNumber != -1) {
                    if(completeTransaction()){
                        updateTransactionSummary();
                    }
                } else {
                    // Show a message to select an item before clicking the Buy button
                    JOptionPane.showMessageDialog(this, "Please select an item before clicking Buy.");
                }
            } else if (selectedMachine.getNumSlots() > Float.parseFloat(command) && !sourceButton.getText().startsWith("$")) {
                try {
                    float slotNumber = Float.parseFloat(command);
                    selectedSlotNumber = (int) slotNumber; // Convert float to int if needed
                    updateSelectedItemLabel(selectedSlotNumber); // Update the "Selected Item" label with the price
                    updateTransactionSummaryWithSelectedItem(selectedSlotNumber); // Update the transaction summary with the selected item details in real-time
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }

            if(selectedMachine.getSlot(selectedSlotNumber).getItemQuantity() == 0){
                selectedSlotNumber = -1;
            }
        }
    }


    private void updateTransactionSummaryWithSelectedItem(int slotNumber) {
        Slot selectedSlot = selectedMachine.getSlot(slotNumber);
        String itemName = selectedSlot.getItem().getName();
        float itemPrice = selectedSlot.getItem().getPrice();

        // Remove the old "Selected Item" label
        JPanel transactionPanel = (JPanel) getContentPane().getComponent(1);
        transactionPanel.remove(selectedItemLabel);

        revalidate();
        repaint();
    }

    /**
     * Centers the frame on the screen.
     *
     * @param frame The JFrame object to be centered.
     */
    private void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        int centerX = (screenWidth - frameWidth) / 2;
        int centerY = (screenHeight - frameHeight) / 2;

        frame.setLocation(centerX, centerY);
    }
}
