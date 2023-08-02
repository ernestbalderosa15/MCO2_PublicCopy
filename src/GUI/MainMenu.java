package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the main menu for the Vending Machine Factory application.
 * It provides the user with options to create, test, or maintain vending machines.
 * The corresponding panels are displayed based on the user's choice.
 */
public class MainMenu extends JPanel implements ActionListener {
    JFrame frame = new JFrame("Vending Machine Factory");
    private JLabel welcomeLabel = new JLabel("Vending Machine Factory");
    private JButton createMachineBtn = new JButton("Create Machine");
    private JButton testMachineBtn = new JButton("Test Machine");
    private JButton maintainMachineBtn = new JButton("Maintain Machine");
    private JLabel warningMsg = new JLabel("");

    /**
     * Constructor for the MainMenu panel.
     * Initializes the GUI components and provides buttons for the user to choose between
     * creating, testing, or maintaining vending machines. The frame is centered on the screen.
     * If there are no vending machines available, appropriate warning messages and button
     * states are set.
     */
    MainMenu() {
        frame.setLayout(null);
        welcomeLabel.setBounds(65, 20, 155, 30);

        createMachineBtn.setBounds(65, 65, 145, 30);
        testMachineBtn.setBounds(65, 110, 145, 30);
        maintainMachineBtn.setBounds(65, 150, 145, 30);
        warningMsg.setBounds(65, 190, 200, 30);

        createMachineBtn.addActionListener(this);
        testMachineBtn.addActionListener(this);
        maintainMachineBtn.addActionListener(this);

        if (Main.newFactory.getAllVendingMachines().isEmpty() && Main.newFactory.getAllFriedRiceVMS().isEmpty()) {
            testMachineBtn.setEnabled(false);
            maintainMachineBtn.setEnabled(false);
            warningMsg.setForeground(Color.RED);
            warningMsg.setText("No Vending Machines yet.");
        }

        frame.add(welcomeLabel);
        frame.add(createMachineBtn);
        frame.add(testMachineBtn);
        frame.add(maintainMachineBtn);
        frame.add(warningMsg);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        centerFrameOnScreen(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Centers the given JFrame on the user's screen.
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
        if (e.getSource() == createMachineBtn) {
            frame.setVisible(false);
            new CreateVendingMachineMenu();
        }

        if (e.getSource() == maintainMachineBtn) {
            frame.dispose();
            new Maintain_Main();
        }

        if (e.getSource() == testMachineBtn) {
            frame.dispose();
            new TestVM_Main();
        }
    }
}
