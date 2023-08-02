package GUI;
import VMF.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The addItemSpecialGUI class provides a GUI for adding special items to the FriedRiceVM vending machine.
 * The user can select the item type from a dropdown and enter details such as the name, price, calories, and availability.
 */
public class addItemSpecialGUI {
    JPanel panel = new JPanel();
    private FriedRiceVM selectedMachine;
    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField caloriesField;
    private JTextField availabilityField;
    private JTextField machineNameField;
    private JTextField slotCapacityField;
    private JComboBox<String> itemTypeComboBox;

    /**
     * Constructs the GUI for adding special items to the selected FriedRiceVM machine.
     *
     * @param selectedMachine The FriedRiceVM to which items will be added
     */
    addItemSpecialGUI(FriedRiceVM selectedMachine){
        this.selectedMachine = selectedMachine;
        createAndShowGUI();
    }

    /**
     * Creates and displays the main GUI.
     */

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Vending Machine Configuration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createMainPanel());
        centerFrameOnScreen(frame);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the main panel with input fields and buttons.
     *
     * @return the main panel
     */
    private JPanel createMainPanel() {
        panel.setLayout(new GridLayout(8, 2));

        panel.add(new JLabel("Item Type:"));
        String[] itemTypes = {"Rice", "Meat", "Egg", "Condiments"};
        itemTypeComboBox = new JComboBox<>(itemTypes);
        panel.add(itemTypeComboBox);

        panel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        panel.add(itemNameField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Calories:"));
        caloriesField = new JTextField();
        panel.add(caloriesField);

        panel.add(new JLabel("Availability in Slot:"));
        availabilityField = new JTextField();
        panel.add(availabilityField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new CreateMachineAction());

        panel.add(saveButton);
        selectedMachine.printSlots();

        return panel;
    }

    /**
     * Private class to handle the action of creating a new item in the machine.
     */
    private class CreateMachineAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemType = (String) itemTypeComboBox.getSelectedItem();
            String itemName = itemNameField.getText();
            float price = Float.parseFloat(priceField.getText());
            float calories = Float.parseFloat(caloriesField.getText());
            int availability = Integer.parseInt(availabilityField.getText());

            switch (itemType) {
                case "Rice":
                    if(selectedMachine.getSlotTypeOccupied("Rice") != 2){
                        for(int i = 0; i < selectedMachine.getSlotTypeOccupied("Rice"); i++){
                            selectedMachine.addItems(0,itemName,price,calories,availability);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Rice slots already full.");
                    }
                    break;
                case "Meat":
                    if(selectedMachine.getSlotTypeOccupied("Meat") != 3){
                        for(int i = 0; i < selectedMachine.getSlotTypeOccupied("Meat"); i++){
                            selectedMachine.addItems(0,itemName,price,calories,availability);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Meat slots already full.");
                    }
                    break;
                case "Egg":
                    if(selectedMachine.getSlotTypeOccupied("Egg") != 3){
                        for(int i = 0; i < selectedMachine.getSlotTypeOccupied("Egg"); i++){
                            selectedMachine.addItems(0,itemName,price,calories,availability);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Egg slots already full.");
                    }
                    break;
                case "Condiments":
                    if(selectedMachine.getSlotTypeOccupied("Condiments") != 5){
                        for(int i = 0; i < selectedMachine.getSlotTypeOccupied("Condiments"); i++){
                            selectedMachine.addItems(0,itemName,price,calories,availability);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Condiments slots already full.");
                    }
                    break;
            }
        }
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

}
