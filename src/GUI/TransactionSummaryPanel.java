package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import VMF.Slot;
import VMF.VendingMachine;

/**
 * The TransactionSummaryPanel class displays a summary of transactions for a selected vending machine.
 * It shows the previous and current inventory, total items sold, and total machine revenue.
 */
public class TransactionSummaryPanel extends JPanel {
    /**
     * Constructs a new TransactionSummaryPanel object for the given vending machine.
     *
     * @param selectedMachine the selected vending machine to display the transaction summary for
     */
    public TransactionSummaryPanel(VendingMachine selectedMachine) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(2, 1));

        // Previous Inventory Table
        DefaultTableModel previousModel = new DefaultTableModel(new String[]{"Slot Number","Slot Name", "Item Quantity"}, 0);
        JTable previousTable = new JTable(previousModel);
        for (int i = 0; i < selectedMachine.getNumSlots(); i++) {
            Slot selectedSlot = selectedMachine.getSlot(i);
            previousModel.addRow(new Object[]{i+1,selectedSlot.getItem().getName(), selectedSlot.getPreviousQuantity()});
        }
        JScrollPane previousScrollPane = new JScrollPane(previousTable);
        previousScrollPane.setBorder(BorderFactory.createTitledBorder("Previous Inventory"));
        tablePanel.add(previousScrollPane);

        // Current Inventory Table
        DefaultTableModel currentModel = new DefaultTableModel(new String[]{"Slot Number", "Slot Name", "Item Quantity", "Items Sold", "Sales/Slot"}, 0);
        JTable currentTable = new JTable(currentModel);
        for (int i = 0; i < selectedMachine.getNumSlots(); i++) {
            Slot slot = selectedMachine.getSlot(i);
            currentModel.addRow(new Object[]{i + 1, slot.getItem().getName(), slot.getItemQuantity(), slot.getItemSold_Slot(), slot.getSlotRevenue()});
        }
        JScrollPane currentScrollPane = new JScrollPane(currentTable);
        currentScrollPane.setBorder(BorderFactory.createTitledBorder("Current Inventory"));
        tablePanel.add(currentScrollPane);

        frame.add(tablePanel, BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new FlowLayout());

        JLabel totalItemsLabel = new JLabel("Total Items sold: " + selectedMachine.getTotalSoldItems());
        JLabel totalRevenueLabel = new JLabel("Total Machine revenue: " + selectedMachine.getTotalSales());

        summaryPanel.add(totalItemsLabel);
        summaryPanel.add(totalRevenueLabel);
        frame.add(summaryPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MaintainVM_Regular();
            }
        });
        summaryPanel.add(goBackButton);
        centerFrameOnScreen(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


