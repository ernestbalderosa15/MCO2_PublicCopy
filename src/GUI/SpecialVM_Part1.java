package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents a graphical user interface (GUI) panel for maintaining a Special Vending Machine (FriedRiceVM).
 * Users can select a vending machine and choose from various maintenance options such as initializing items,
 * choosing photos, replenishing items, replenishing money, collecting money, viewing machine statistics, and disposing
 * of the machine. Each maintenance option opens a new GUI panel specific to that option for further actions.
 */
public class SpecialVM_Part1 extends JPanel implements ActionListener {
    private JFrame frame = new JFrame("RegularVM");
    private JLabel machineNameLabel = new JLabel("Machine Name");
    private JLabel slotCapacityLabel = new JLabel("Slot Capacity ");
    private JSpinner slotCapacitySpinner =  new JSpinner(new SpinnerNumberModel(8, 8, 20, 1));
    private JTextField machineNameTextField = new JTextField(5);
    private JButton createMachineBtn = new JButton("Create Machine");
    private JButton mainMenuBtn = new JButton("Return to Menu");
    private JLabel warningMsg = new JLabel();

    /**
     * Constructor for the MaintainVM_Special panel.
     * Initializes the GUI components and displays a list of Special Vending Machines available for maintenance.
     * Users can select a machine and choose from various maintenance options using the comboboxes and buttons provided.
     * The frame is set to be non-resizable and centered on the screen.
     */
    SpecialVM_Part1() {
        setLayout(null);

        // Add action listeners
        createMachineBtn.addActionListener(this);
        mainMenuBtn.addActionListener(this);

        // Add components to the panel
        add(machineNameLabel);
        add(machineNameTextField);
        add(warningMsg);
        add(createMachineBtn);
        add(mainMenuBtn);
        add(slotCapacityLabel);
        add(slotCapacitySpinner);

        // Set component bounds (only needed by Absolute Positioning)
        machineNameLabel.setBounds(30, 20, 100, 25);
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
        if (e.getSource() == mainMenuBtn) {
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
        } else if (e.getSource() == createMachineBtn) {
            String machineName = machineNameTextField.getText();
            int slotCapValue = (int) slotCapacitySpinner.getValue();

            if(machineName.isEmpty()){
                warningMsg.setForeground(Color.red);
                warningMsg.setText("Name must not be empty.");
            }

            if(!machineName.isEmpty() && slotCapValue > 0){
                Main.newFactory.createSpecialVM(machineName,slotCapValue);
                warningMsg.setText("Success!");
                warningMsg.setForeground(Color.GREEN);
            }

            System.out.println("machine name: " + machineName);
            System.out.println("slotCap: " + slotCapValue);
        }
    }
}
