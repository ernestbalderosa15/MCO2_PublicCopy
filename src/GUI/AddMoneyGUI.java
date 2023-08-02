package GUI;

import VMF.VendingMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The AddMoneyGUI class provides a graphical interface for replenishing money in the selected vending machine.
 * It includes several JSpinners for various currency denominations and two buttons to either save the changes
 * or go back to the previous interface.
 */
public class AddMoneyGUI extends JPanel implements ActionListener {
    JFrame frame = new JFrame("Add Money");
    private JLabel oneLabel, fiveLabel, tenLabel, twentyLabel, fiftyLabel, oneHunLabel, twoHunLabel, fiveHunLabel, oneThouLabel;
    private JSpinner oneSpinner, fiveSpinner, tenSpinner, twentySpinner, oneHunSpinner, fiftySpinner, twoHunSpinner, fiveHunSpinner, oneThouSpinner;
    private JButton saveButton, goBackBtn;
    private VendingMachine selectedMachine;

    /**
     * Constructs the AddMoneyGUI panel with various components to input money amounts.
     *
     * @param selectedMachine VendingMachine object to which money will be added
     */
    AddMoneyGUI(VendingMachine selectedMachine) {
        this.selectedMachine = selectedMachine;
        //construct components
        oneLabel = new JLabel("$1");
        fiveLabel = new JLabel("$5");
        tenLabel = new JLabel("$10");
        twentyLabel = new JLabel("$20");
        fiftyLabel = new JLabel("$50");
        oneHunLabel = new JLabel("$100");
        twoHunLabel = new JLabel("$200");
        fiveHunLabel = new JLabel("$500");
        oneThouLabel = new JLabel("$1000");

        oneSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        fiveSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        tenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        twentySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        oneHunSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        fiftySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        twoHunSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        fiveHunSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        oneThouSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        saveButton = new JButton("Save");
        goBackBtn = new JButton("Go Back");

        //adjust size and set layout
        setPreferredSize(new Dimension(262, 481));
        setLayout(null);

        //add components
        addComponents();

        saveButton.addActionListener(this);
        goBackBtn.addActionListener(this);

        //set component bounds (only needed by Absolute Positioning)
        setComponentBounds();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerFrameOnScreen(frame);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    private void addComponents() {
        add(oneLabel); add(fiveLabel); add(tenLabel); add(twentyLabel); add(fiftyLabel);
        add(oneHunLabel); add(twoHunLabel); add(fiveHunLabel); add(oneThouLabel);
        add(oneSpinner); add(fiveSpinner); add(tenSpinner); add(twentySpinner);
        add(oneHunSpinner); add(fiftySpinner); add(twoHunSpinner); add(fiveHunSpinner); add(oneThouSpinner);
        add(saveButton); add(goBackBtn);
    }

    private void setComponentBounds() {
        oneLabel.setBounds(30, 25, 100, 25);
        fiveLabel.setBounds(30, 60, 100, 25);
        tenLabel.setBounds(30, 95, 100, 25);
        twentyLabel.setBounds(30, 135, 100, 25);
        fiftyLabel.setBounds(30, 175, 100, 25);
        oneHunLabel.setBounds(30, 215, 100, 25);
        twoHunLabel.setBounds(30, 255, 100, 25);
        fiveHunLabel.setBounds(30, 295, 100, 25);
        oneThouLabel.setBounds(30, 335, 100, 25);
        oneSpinner.setBounds(130, 25, 100, 25);
        fiveSpinner.setBounds(130, 60, 100, 25);
        tenSpinner.setBounds(130, 95, 100, 25);
        twentySpinner.setBounds(130, 135, 100, 25);
        oneHunSpinner.setBounds(130, 215, 100, 25);
        fiftySpinner.setBounds(130, 175, 100, 25);
        twoHunSpinner.setBounds(130, 255, 100, 25);
        fiveHunSpinner.setBounds(130, 295, 100, 25);
        oneThouSpinner.setBounds(130, 335, 100, 25);
        saveButton.setBounds(30, 380, 200, 30);
        goBackBtn.setBounds(30, 425, 200, 30);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==saveButton){
            selectedMachine.replenishChange(1F, (int) oneSpinner.getValue());
            selectedMachine.replenishChange(5F, (int) fiveSpinner.getValue());
            selectedMachine.replenishChange(10F, (int) tenSpinner.getValue());
            selectedMachine.replenishChange(20F, (int) twentySpinner.getValue());
            selectedMachine.replenishChange(50F, (int) fiftySpinner.getValue());
            selectedMachine.replenishChange(100F, (int) oneHunSpinner.getValue());
            selectedMachine.replenishChange(200F, (int) twoHunSpinner.getValue());
            selectedMachine.replenishChange(500F, (int) fiveHunSpinner.getValue());
            selectedMachine.replenishChange(1000F, (int) oneThouSpinner.getValue());
            selectedMachine.printMoneyAvailability();
            JOptionPane.showMessageDialog(this, "Success! Money added.");
        } else if(e.getSource()==goBackBtn){
            frame.dispose();
            MaintainVM_Regular maintainVM = new MaintainVM_Regular();
        }
    }
}
