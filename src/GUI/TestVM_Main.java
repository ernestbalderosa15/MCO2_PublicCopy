package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TestVM_Main class represents a GUI panel for choosing an option to test a vending machine.
 */
public class TestVM_Main {

    /**
     * Constructs a new TestVM_Main GUI panel.
     */
    public TestVM_Main() {
        JFrame frame = new JFrame("Choose an Option");
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose an option", JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        JButton testRegularButton = new JButton("Test Regular Vending Machine");
        testRegularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TestVM_Regular();
            }
        });
        buttonPanel.add(testRegularButton);

        JButton testSpecialButton = new JButton("Test Special Vending Machine");
        testSpecialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TestVM_Special();
            }
        });
        buttonPanel.add(testSpecialButton);

        JButton returnMenuButton = new JButton("Return to Menu");
        returnMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to return to menu
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
}
