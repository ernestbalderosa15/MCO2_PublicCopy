package GUI;

import VMF.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The addItemGUI class provides a GUI to add items to the selected VendingMachine.
 * It allows users to specify details such as slot number, item name, price, calories, and quantity.
 */
public class addItemGUI extends JPanel implements ActionListener {
    private VendingMachine selectedMachine;
    private JFrame frame = new JFrame();

    /**
     * Constructs the GUI for adding items to the selected vending machine.
     *
     * @param selectedMachine The VendingMachine to which items will be added
     */
    private JLabel slotNumberLabel = new JLabel("Slot Number:");
    private JComboBox<Integer> slotNumberComboBox;
    private JLabel itemNameLabel = new JLabel("Item Name:");
    private JTextField itemNameField = new JTextField();
    private JLabel itemPriceLabel = new JLabel("Item Price:");
    private JSpinner itemPriceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.01));
    private JLabel itemCaloriesLabel = new JLabel("Item Calories:");
    private JSpinner itemCaloriesSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10000.0, 0.1));
    private JLabel itemQuantityLabel = new JLabel("Item Quantity:");
    private JSpinner itemQuantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
    private JButton addItemBtn = new JButton("Add Item");
    private JButton goBackBtn = new JButton("Go Back");

    /**
     * Constructs the GUI for adding items to the selected vending machine.
     *
     * @param selectedMachine The VendingMachine to which items will be added
     */
    addItemGUI(VendingMachine selectedMachine) {
        this.selectedMachine = selectedMachine;
        setLayout(null);

        // Create a combo box for slot selection
        Integer[] slotNumbers = new Integer[selectedMachine.getNumSlots()];
        for (int i = 0; i < selectedMachine.getNumSlots(); i++) {
            slotNumbers[i] = i + 1;
        }
        slotNumberComboBox = new JComboBox<>(slotNumbers);

        for (int nums : selectedMachine.initializedSlots) {
            slotNumberComboBox.removeItem(nums);
        }

        if (slotNumberComboBox.getItemCount() == 0) {
            addItemBtn.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No more slots left to initialize.");
        }

        // Add action listeners
        addItemBtn.addActionListener(this);
        goBackBtn.addActionListener(this);

        // Add components to the panel
        add(slotNumberLabel);
        add(slotNumberComboBox);
        add(itemNameLabel);
        add(itemNameField);
        add(itemPriceLabel);
        add(itemPriceSpinner);
        add(itemCaloriesLabel);
        add(itemCaloriesSpinner);
        add(itemQuantityLabel);
        add(itemQuantitySpinner);
        add(addItemBtn);
        add(goBackBtn);

        // Set component bounds (only needed by Absolute Positioning)
        slotNumberLabel.setBounds(30, 20, 100, 25);
        slotNumberComboBox.setBounds(150, 20, 150, 25);
        itemNameLabel.setBounds(30, 60, 100, 25);
        itemNameField.setBounds(150, 60, 150, 25);
        itemPriceLabel.setBounds(30, 100, 100, 25);
        itemPriceSpinner.setBounds(150, 100, 150, 25);
        itemCaloriesLabel.setBounds(30, 140, 100, 25);
        itemCaloriesSpinner.setBounds(150, 140, 150, 25);
        itemQuantityLabel.setBounds(30, 180, 100, 25);
        itemQuantitySpinner.setBounds(150, 180, 150, 25);
        addItemBtn.setBounds(30, 220, 120, 30);
        goBackBtn.setBounds(160, 220, 120, 30);

        // Frame settings
        frame.add(this);
        frame.setPreferredSize(new Dimension(350, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerFrameOnScreen(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Centers the frame on the screen.
     *
     * @param frame The JFrame to be centered
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackBtn) {
            // Handle the action for the "Return to Menu" button
            frame.dispose();
            MaintainVM_Regular maintainVM = new MaintainVM_Regular();
        } else if (e.getSource() == addItemBtn) {
            // Handle the action for the "Add Item" button
            int slotNumber = (int) slotNumberComboBox.getSelectedItem();
            String itemName = itemNameField.getText();
            float itemPrice = ((Number) itemPriceSpinner.getValue()).floatValue();
            float itemCalories = ((Number) itemCaloriesSpinner.getValue()).floatValue();
            int itemQuantity = (int) itemQuantitySpinner.getValue();

            if (itemName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item name is empty");
            } else if (itemPrice != (int) itemPrice) {
                JOptionPane.showMessageDialog(this, "Price is not supported!");
            } else if (itemQuantity > selectedMachine.getSlotCapacity()) {
                JOptionPane.showMessageDialog(this, "Quantity cannot exceed slot capacity!");
            } else {
                selectedMachine.addItems(slotNumber - 1, itemName, itemPrice, itemCalories, itemQuantity);
                // Show a message or perform any other relevant actions to indicate successful addition of the item.
                JOptionPane.showMessageDialog(this, "Item added successfully!");
                selectedMachine.printSlots();
                selectedMachine.initializedSlots.add(slotNumber);
                slotNumberComboBox.removeItem(slotNumber);
                if (slotNumberComboBox.getItemCount() == 0) {
                    addItemBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No more slots left to initialize.");
                }
            }

            // Clear the text fields
            itemNameField.setText("");
            itemPriceSpinner.setValue(0.0);
            itemCaloriesSpinner.setValue(0.0);
            itemQuantitySpinner.setValue(0);
        }
    }
}
