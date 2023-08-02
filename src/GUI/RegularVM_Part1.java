package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RegularVM_Part1 class represents a GUI panel for creating a Regular Vending Machine.
 */
public class RegularVM_Part1 extends JPanel implements ActionListener {
    private JFrame frame = new JFrame("RegularVM");
    private JLabel machineNameLabel = new JLabel("Machine Name");
    private JLabel numSlotsLabel = new JLabel("Number of Slots (Max 9)");
    private JSpinner numSlotsSpinner =  new JSpinner(new SpinnerNumberModel(2, 2, 9, 1));
    private JLabel slotCapacityLabel = new JLabel("Slot Capacity ");
    private JSpinner slotCapacitySpinner =  new JSpinner(new SpinnerNumberModel(8, 8, 20, 1));
    private JTextField machineNameTextField = new JTextField(5);
    private JButton createMachineBtn = new JButton("Create Machine");
    private JButton mainMenuBtn = new JButton("Return to Menu");
    private JLabel warningMsg = new JLabel();

    /**
     * Constructs a new RegularVM_Part1 panel for creating a Regular Vending Machine.
     */
    public RegularVM_Part1() {
        setLayout(null);

        // Add action listeners
        createMachineBtn.addActionListener(this);
        mainMenuBtn.addActionListener(this);

        // Add components to the panel
        add(machineNameLabel);
        add(numSlotsLabel);
        add(numSlotsSpinner);
        add(machineNameTextField);
        add(warningMsg);
        add(createMachineBtn);
        add(mainMenuBtn);
        add(slotCapacityLabel);
        add(slotCapacitySpinner);

        // Set component bounds (only needed by Absolute Positioning)
        machineNameLabel.setBounds(30, 20, 100, 25);
        numSlotsLabel.setBounds(30, 95, 150, 25);
        numSlotsSpinner.setBounds(25, 120, 175, 25);
        machineNameTextField.setBounds(30, 50, 170, 25);
        slotCapacityLabel.setBounds(30,160,170,25);
        slotCapacitySpinner.setBounds(30,190,170,25);
        createMachineBtn.setBounds(30, 220, 175, 30);
        mainMenuBtn.setBounds(30, 260, 175, 30);
        warningMsg.setBounds(30,290,175,30);

        // Frame settings
        frame.add(this);
        frame.setPreferredSize(new Dimension(250, 360));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerFrameOnScreen(frame);
        frame.setVisible(true);
        frame.setResizable(false);
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
        if (e.getSource() == mainMenuBtn) {
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
        } else if (e.getSource() == createMachineBtn) {
            String machineName = machineNameTextField.getText();
            int numSlotsValue = (int) numSlotsSpinner.getValue();
            int slotCapValue = (int) slotCapacitySpinner.getValue();

            if(machineName.isEmpty()){
                warningMsg.setForeground(Color.red);
                warningMsg.setText("Name must not be empty.");
            }

            if(!machineName.isEmpty() && numSlotsValue > 0){
                warningMsg.setForeground(Color.GREEN);
                warningMsg.setText("Success!");
                Main.newFactory.createVendingMachine(machineName, numSlotsValue, slotCapValue);
                System.out.println("MACHINE: " + Main.newFactory.getAllVendingMachines().get(0).getName());
            }

            System.out.println("machine name: " + machineName);
            System.out.println("numslotval: " + numSlotsValue);
            System.out.println("slotCap: " + slotCapValue);

        }
    }
}
