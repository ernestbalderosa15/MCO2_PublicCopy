package GUI;

import VMF.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ReplenishMachineGUI class represents a GUI panel for replenishing a Vending Machine.
 */
public class ReplenishMachineGUI implements ActionListener {
    private JFrame frame = new JFrame("Replenish Machine");
    private JLabel slotSelectLabel;
    private JButton goBackBtn;
    private JComboBox selectedSlotCombobox;
    private JLabel toAddLabel;
    private JSpinner toAddSpinner;
    private JButton saveButton;
    private JButton checkSlotDetails; // New button
    private VendingMachine selectedMachine;

    /**
     * Constructs a new ReplenishMachineGUI panel for the specified Vending Machine.
     *
     * @param selectedMachine the Vending Machine to replenish
     */
    public ReplenishMachineGUI(VendingMachine selectedMachine) {
        this.selectedMachine = selectedMachine;

        // Construct preComponents
        Integer[] slotNumbers = new Integer[selectedMachine.getNumSlots()];
        for (int i = 0; i < selectedMachine.getNumSlots(); i++) {
            slotNumbers[i] = i + 1;
        }
        selectedSlotCombobox = new JComboBox<>(slotNumbers); // Initialization for selectedSlot

        // Construct components
        checkSlotDetails = new JButton("Check Slot Details");
        slotSelectLabel = new JLabel("Select Slot");
        goBackBtn = new JButton("Go Back");
        toAddLabel = new JLabel("Quantity to Add");
        toAddSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)); // Initializing JSpinner
        saveButton = new JButton("Save");

        // Create JPanel and set its layout
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(262, 215));
        panel.setLayout(null);

        // Add components
        panel.add(slotSelectLabel);
        panel.add(goBackBtn);
        panel.add(selectedSlotCombobox);
        panel.add(toAddLabel);
        panel.add(toAddSpinner); // Adding JSpinner to the panel
        panel.add(saveButton);
        panel.add(checkSlotDetails);

        goBackBtn.addActionListener(this);
        saveButton.addActionListener(this);
        checkSlotDetails.addActionListener(this);

        // Set component bounds (only needed by Absolute Positioning)
        slotSelectLabel.setBounds(30, 25, 100, 25);
        goBackBtn.setBounds(30, 165, 200, 30);
        selectedSlotCombobox.setBounds(130, 25, 100, 25);
        toAddLabel.setBounds(30, 70, 100, 25);
        toAddSpinner.setBounds(130, 70, 100, 25);
        saveButton.setBounds(30, 125, 200, 30);
        checkSlotDetails.setBounds(30, 100, 200, 30);
        saveButton.setBounds(30, 135, 200, 30);

        frame.setPreferredSize(new Dimension(262, 245));
        frame.add(panel); // Adding panel to the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        centerFrameOnScreen(frame);
    }

    /**
     * Centers the JFrame on the screen.
     *
     * @param frame the JFrame to center
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
        if (e.getSource() == checkSlotDetails) {
            StringBuilder messageToShow = new StringBuilder("Quantity in slot ");
            int slotNumber = (int) selectedSlotCombobox.getSelectedItem() - 1;
            int quantity = selectedMachine.getSlot(slotNumber).getItemQuantity();
            messageToShow.append((slotNumber + 1) + ": " + quantity + "\n");
            messageToShow.append("Item name: " + selectedMachine.getSlot(slotNumber).getItem().getName());
            messageToShow.append("\nMax Capacity: " + selectedMachine.getSlotCapacity());
            JOptionPane.showMessageDialog(frame, messageToShow);
        } else if (e.getSource() == saveButton) {
            int selectedSlot = (int) selectedSlotCombobox.getSelectedItem() - 1;
            System.out.println("SELECTED ITEM: " + selectedSlot);
            int stockToAdd = (int) toAddSpinner.getValue();
            if (stockToAdd == 0) {
                JOptionPane.showMessageDialog(frame, "Nothing to add.");
            } else if (selectedMachine.getSlot(selectedSlot).addQuantity(stockToAdd)) {
                selectedMachine.printSlots();
                JOptionPane.showMessageDialog(frame, "Successfully added!");
            } else {
                JOptionPane.showMessageDialog(frame, "Cannot exceed slot capacity!");
            }
        } else if (e.getSource() == goBackBtn) {
            frame.dispose();
            MaintainVM_Regular maintainVM = new MaintainVM_Regular();
        }
    }
}
