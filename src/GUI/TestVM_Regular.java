package GUI;

import VMF.VendingMachine;
import VMF.VendingMachineFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TestVM_Regular class represents a GUI panel for testing regular vending machines.
 */
public class TestVM_Regular extends JPanel implements ActionListener {
    private JFrame frame = new JFrame();
    private JLabel selectMachineLabel = new JLabel("Select a Vending Machine:");
    private JComboBox<String> machineComboBox;
    private JButton buyItemBtn = new JButton("Buy Item");
    private JButton returnToMenuBtn = new JButton("Return to Menu");

    /**
     * Constructs a new TestVM_Regular GUI panel.
     */
    public TestVM_Regular() {
        // Get the list of all vending machines from the factory
        VendingMachineFactory factory = Main.newFactory;
        String[] machineNames = new String[factory.getAllVendingMachines().size()];
        for (int i = 0; i < factory.getAllVendingMachines().size(); i++) {
            VendingMachine machine = factory.getAllVendingMachines().get(i);
            machineNames[i] = machine.getMachineName();
        }

        // Create the ComboBox to display all vending machine names
        machineComboBox = new JComboBox<>(machineNames);

        setLayout(null);

        // Add action listener
        buyItemBtn.addActionListener(this);
        returnToMenuBtn.addActionListener(this);

        // Add components to the panel
        add(selectMachineLabel);
        add(machineComboBox);
        add(buyItemBtn);
        add(returnToMenuBtn);

        // Set component bounds (only needed by Absolute Positioning)
        selectMachineLabel.setBounds(30, 20, 180, 25);
        machineComboBox.setBounds(30, 50, 170, 25);
        buyItemBtn.setBounds(30, 90, 170, 30);
        returnToMenuBtn.setBounds(30, 130, 170, 30);

        // Frame settings
        frame.add(this);
        frame.setPreferredSize(new Dimension(250, 250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerFrameOnScreen(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Helper method to center the JFrame on the screen.
     *
     * @param frame The JFrame to be centered.
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
        if (e.getSource() == buyItemBtn) {
            String selectedMachineName = (String) machineComboBox.getSelectedItem();

            VendingMachineFactory factory = Main.newFactory;
            VendingMachine selectedMachine = null;
            for (VendingMachine machine : factory.getAllVendingMachines()) {
                if (machine.getMachineName().equals(selectedMachineName)) {
                    selectedMachine = machine;
                    break;
                }
            }

            if(selectedMachine.initializedSlots.size() != selectedMachine.getNumSlots()){
                JOptionPane.showMessageDialog(this, selectedMachineName + " needs maintenance first.");
            } else {
                if (selectedMachine != null) {
                    frame.dispose();
                    new BuyItemGUI(selectedMachine);
                } else {
                    System.out.println("Error: Selected vending machine not found.");
                }
            }
        } else if (e.getSource() == returnToMenuBtn) {
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
        }
    }
}
