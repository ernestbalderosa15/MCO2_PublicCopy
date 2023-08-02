package GUI;

import VMF.VendingMachine;
import VMF.VendingMachineFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MaintainVM_Regular class represents a panel for maintaining regular vending machines.
 * It provides the functionalities to initialize items, choose photos, replenish items,
 * replenish money, collect money, view machine statistics, and dispose of the machine.
 * Each maintenance option opens a new GUI panel specific to that option for further actions.
 */
public class MaintainVM_Regular extends JPanel implements ActionListener {
    private JFrame frame = new JFrame();
    private JLabel selectMachineLabel = new JLabel("Select a Vending Machine:");
    private JComboBox<String> machineComboBox;
    private JComboBox<String> maintenanceOptionsComboBox;
    private JButton maintainMachineBtn = new JButton("Maintain Machine");
    private JButton returnToMainMenuBtn = new JButton("Return to Main Menu");

    /**
     * Constructor for MaintainVM_Regular panel.
     * Initializes the GUI components and sets up the layout for maintaining a vending machine.
     */
    MaintainVM_Regular() {
        // Get the list of all vending machines from the factory
        VendingMachineFactory factory = Main.newFactory;
        String[] machineNames = new String[factory.getAllVendingMachines().size()];
        for (int i = 0; i < factory.getAllVendingMachines().size(); i++) {
            VendingMachine machine = factory.getAllVendingMachines().get(i);
            machineNames[i] = machine.getMachineName();
        }

        // Create the ComboBox to display all vending machine names
        machineComboBox = new JComboBox<>(machineNames);

        // Create the ComboBox to display maintenance options
        String[] maintenanceOptions = {
                "Initialize Items",
                "Choose Photos",
                "Replenish Items",
                "Replenish Money",
                "Collect Money",
                "Machine Statistics",
                "Dispose Machine"
        };
        maintenanceOptionsComboBox = new JComboBox<>(maintenanceOptions);

        setLayout(null);

        // Add action listeners
        maintainMachineBtn.addActionListener(this);
        returnToMainMenuBtn.addActionListener(this);

        // Add components to the panel
        add(selectMachineLabel);
        add(machineComboBox);
        add(maintenanceOptionsComboBox);
        add(maintainMachineBtn);
        add(returnToMainMenuBtn);

        // Set component bounds (only needed by Absolute Positioning)
        selectMachineLabel.setBounds(30, 20, 180, 25);
        machineComboBox.setBounds(30, 50, 170, 25);
        maintenanceOptionsComboBox.setBounds(30, 90, 170, 25);
        maintainMachineBtn.setBounds(30, 130, 170, 30);
        returnToMainMenuBtn.setBounds(30, 170, 170, 30);

        // Frame settings
        frame.add(this);
        frame.setPreferredSize(new Dimension(250, 260));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerFrameOnScreen(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Centers the frame on the screen.
     *
     * @param frame The JFrame to center.
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
        String selectedMachineName = (String) machineComboBox.getSelectedItem();

        // Find the selected vending machine from the factory
        VendingMachineFactory factory = Main.newFactory;
        VendingMachine selectedMachine = null;
        for (VendingMachine machine : factory.getAllVendingMachines()) {
            if (machine.getMachineName().equals(selectedMachineName)) {
                selectedMachine = machine;
                break;
            }
        }

        if (selectedMachine != null) {
            if (e.getSource() == maintainMachineBtn) {
                // Handle the action for the "Maintain Machine" button
                frame.dispose();
                String selectedOption = (String) maintenanceOptionsComboBox.getSelectedItem();
                if (selectedOption.equals("Initialize Items")) {
                    // Perform action for "Add Items" option
                    addItemGUI maintainMachine = new addItemGUI(selectedMachine);
                } else if(selectedOption.equals("Choose Photos")){
                    // Perform action for Add Items option
                    if(selectedMachine.initializedSlots.size() == selectedMachine.getNumSlots()){
                        ChoosePhotoGUI choosePhotoGUI = new ChoosePhotoGUI(selectedMachine);
                    } else {
                        JOptionPane.showMessageDialog(this, "Initialize all of " + selectedMachineName + "'s slots first.");
                        MaintainVM_Regular maintainVM = new MaintainVM_Regular();
                    }
                } else if (selectedOption.equals("Replenish Items")) {
                    // Perform action for "Replenish Items" option
                    if(selectedMachine.initializedSlots.size() == selectedMachine.getNumSlots()){
                        ReplenishMachineGUI replenishMachineGUI = new ReplenishMachineGUI(selectedMachine);
                    } else {
                        JOptionPane.showMessageDialog(this, "Initialize all of " + selectedMachineName + "'s slots first.");
                        MaintainVM_Regular maintainVM = new MaintainVM_Regular();
                    }
                } else if (selectedOption.equals("Replenish Money")) {
                    // Perform action for "Replenish Money" option
                    AddMoneyGUI addMoneyGUI = new AddMoneyGUI(selectedMachine);
                } else if (selectedOption.equals("Collect Money")) {
                    float totalSale = selectedMachine.getMoney().getTotalMoney();
                    if(selectedMachine.takeProfit()){
                        StringBuilder messageToShow = new StringBuilder("Success! " + totalSale + " has been collected.");
                        JOptionPane.showMessageDialog(this, messageToShow);
                    } else {
                        JOptionPane.showMessageDialog(this, "No more money to collect!");
                    }
                    new MaintainVM_Regular();
                } else if (selectedOption.equals("Machine Statistics")) {
                    if(selectedMachine.initializedSlots.size() == selectedMachine.getNumSlots()){
                        new TransactionSummaryPanel(selectedMachine);
                    } else {
                        JOptionPane.showMessageDialog(this, "Initialize all of " + selectedMachineName + "'s slots first.");
                        MaintainVM_Regular maintainVM = new MaintainVM_Regular();
                    }
                } else if (selectedOption.equals("Dispose Machine")) {
                    Main.newFactory.disposeVendingMachine(selectedMachine);
                    frame.dispose();
                    JOptionPane.showMessageDialog(this, selectedMachineName + " successfully disposed.");
                    MainMenu mainMenu = new MainMenu();
                }
            } else if (e.getSource() == returnToMainMenuBtn) {
                // Handle the action for the "Return to Main Menu" button
                frame.dispose();
                MainMenu mainMenu = new MainMenu();
            }
        } else {
            // Handle the case when the selected machine is not found (optional)
            System.out.println("Error: Selected vending machine not found.");
        }
    }
}
