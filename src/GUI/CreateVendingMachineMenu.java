package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represents the menu for creating a vending machine.
 * It provides the user with options to create either a regular or a special vending machine.
 */
public class CreateVendingMachineMenu implements ActionListener {
    JFrame frame = new JFrame("Create Vending Machine");
    private JLabel createVendoMenu = new JLabel("      Regular? or Special?");
    private JButton regularMachineBtn = new JButton("Regular Machine");
    private JButton SpecialMachineBtn = new JButton("Special Machine");
    private JButton mainMenuBtn = new JButton("Back to Main Menu");

    /**
     * Constructor for the CreateVendingMachineMenu panel.
     * Initializes the GUI components and provides buttons for the user to choose between
     * creating a regular or special vending machine. The frame is centered on the screen.
     */
    CreateVendingMachineMenu() {
        frame.setLayout(null); // Set layout manager to null

        createVendoMenu.setBounds(65, 20, 155, 30);
        regularMachineBtn.setBounds(65, 65, 145, 30);
        SpecialMachineBtn.setBounds(65, 110, 145, 30);
        mainMenuBtn.setBounds(65, 155, 145, 30);

        regularMachineBtn.addActionListener(this);
        SpecialMachineBtn.addActionListener(this);
        mainMenuBtn.addActionListener(this);

        frame.add(createVendoMenu);
        frame.add(regularMachineBtn);
        frame.add(SpecialMachineBtn);
        frame.add(mainMenuBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        centerFrameOnScreen(frame);
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
        if (e.getSource() == mainMenuBtn) {
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
        }

        if (e.getSource() == regularMachineBtn) {
            frame.dispose();
            RegularVM_Part1 regularVM_part1 = new RegularVM_Part1();
        }

        if (e.getSource() == SpecialMachineBtn) {
            frame.dispose();
            SpecialVM_Part1 specialVM_part1 = new SpecialVM_Part1();
        }
    }
}
