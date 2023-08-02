package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the main maintenance panel for the application.
 * It provides the user with options to maintain either Regular or Special Vending Machines,
 * or return to the main menu. The selection leads to corresponding maintenance panels.
 */
public class Maintain_Main {

    /**
     * Constructor for the Maintain_Main panel.
     * Initializes the GUI components and provides buttons for the user to choose between
     * maintaining Regular Vending Machines, Special Vending Machines, or returning to the main menu.
     * The frame is centered on the screen.
     */
    public Maintain_Main() {
        JFrame frame = new JFrame("Choose an Option");
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose an option", JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        JButton maintainRegularButton = new JButton("Maintain Regular Vending Machine");
        maintainRegularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MaintainVM_Regular();
            }
        });
        buttonPanel.add(maintainRegularButton);

        JButton maintainSpecialButton = new JButton("Maintain Special Vending Machine");
        maintainSpecialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MaintainVM_Special();
            }
        });
        buttonPanel.add(maintainSpecialButton);

        JButton returnMenuButton = new JButton("Return to Menu");
        returnMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });
        buttonPanel.add(returnMenuButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        centerFrameOnScreen(frame);
    }

    /**
     * Centers the given JFrame on the user's screen.
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
}
