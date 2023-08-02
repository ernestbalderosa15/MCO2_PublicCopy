package GUI;

import VMF.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class represents the GUI for choosing photos for vending machine slots.
 * It allows the user to browse photos for each slot and save the selected paths to the VendingMachine object.
 */
public class ChoosePhotoGUI extends JFrame implements ActionListener {
    private VendingMachine vendingMachine;
    private JPanel slotPanel;
    private JScrollPane scrollPane;
    private JButton returnToMenuBtn;

    private String selectedImagePath;

    /**
     * Constructor for the ChoosePhotoGUI window.
     * Initializes the GUI components and populates the slot panel with slot components.
     *
     * @param vendingMachine The VendingMachine object for which the photos are being chosen.
     */
    ChoosePhotoGUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;

        // Create components
        slotPanel = new JPanel();
        slotPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Maximum 3 items per row, 10px spacing between components
        scrollPane = new JScrollPane(slotPanel);
        returnToMenuBtn = new JButton("Return to Menu");

        // Populate the slotPanel with slot components
        int numSlots = vendingMachine.getNumSlots();
        for (int i = 0; i < numSlots; i++) {
            slotPanel.add(createSlotComponent(i));
        }

        // Add action listener
        returnToMenuBtn.addActionListener(this);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(returnToMenuBtn, BorderLayout.SOUTH);

        setTitle("Choose Photos for Slots");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    /**
     * Creates a JPanel component representing a slot with buttons to browse and save photos.
     *
     * @param slotNumber The slot number for which the component is being created.
     * @return The JPanel component for the specified slot.
     */
    private JPanel createSlotComponent(int slotNumber) {
        JPanel slotComponent = new JPanel(new BorderLayout());
        JLabel slotLabel = new JLabel("Slot " + (slotNumber + 1), SwingConstants.CENTER);
        JButton browseBtn = new JButton("Browse Photo");
        JLabel selectedPhotoLabel = new JLabel("Selected Photo: " + selectedImagePath, SwingConstants.CENTER);
        JLabel photoPreviewLabel = new JLabel();
        JButton saveBtn = new JButton("Save");

        // Add action listener to the browse button
        browseBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedImagePath = selectedFile.getAbsolutePath();
                selectedPhotoLabel.setText("Selected Photo: " + selectedImagePath);
                updatePhotoPreview(photoPreviewLabel, selectedImagePath);
            } else {
                selectedPhotoLabel.setText("Selected Photo: " + selectedImagePath);
                updatePhotoPreview(photoPreviewLabel, selectedImagePath);
            }
        });

        // Add action listener to the save button
        saveBtn.addActionListener(e -> {
            // Update the VendingMachine object with the selected photo file path
            vendingMachine.setItemPhotoPath(slotNumber, selectedImagePath);
            System.out.println(vendingMachine.getSlot(slotNumber).getItem().getItemPhotoPath());
        });

        // Create a nested panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(browseBtn, BorderLayout.NORTH);
        buttonPanel.add(saveBtn, BorderLayout.SOUTH);
        buttonPanel.setPreferredSize(new Dimension(100, 50)); // Set a smaller size for the button panel

        // Add components to the slotComponent
        slotComponent.add(slotLabel, BorderLayout.NORTH);
        slotComponent.add(selectedPhotoLabel, BorderLayout.CENTER);
        slotComponent.add(photoPreviewLabel, BorderLayout.CENTER);
        slotComponent.add(buttonPanel, BorderLayout.SOUTH); // Add the nested panel to the SOUTH region of the slotComponent

        // Set a fixed size for each slot component
        slotComponent.setPreferredSize(new Dimension(200, 250));

        // Set a border to separate each slot
        slotComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return slotComponent;
    }

    /**
     * Updates the photo preview based on the provided image path.
     *
     * @param previewLabel The JLabel used to display the photo preview.
     * @param imagePath    The file path of the selected image.
     */
    private void updatePhotoPreview(JLabel previewLabel, String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(image);
            int maxWidth = 150;
            int maxHeight = 150;

            // Scale down the image to fit within the designated space
            if (image.getWidth() > maxWidth || image.getHeight() > maxHeight) {
                int newWidth = image.getWidth();
                int newHeight = image.getHeight();

                if (image.getWidth() > maxWidth) {
                    newWidth = maxWidth;
                    newHeight = (newWidth * image.getHeight()) / image.getWidth();
                }

                if (newHeight > maxHeight) {
                    newHeight = maxHeight;
                    newWidth = (newHeight * image.getWidth()) / image.getHeight();
                }

                icon = new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
            }

            previewLabel.setIcon(icon);
        } catch (IOException ex) {
            ImageIcon icon = new ImageIcon();
            previewLabel.setIcon(icon);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenuBtn) {
            dispose(); // Close the window and return to the main menu
            MaintainVM_Regular maintainVM = new MaintainVM_Regular(); // You may need to adjust this line to fit your program's structure
        }
    }
}
