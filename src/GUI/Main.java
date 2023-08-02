package GUI;

import VMF.VendingMachineFactory;

import javax.swing.*;


public class Main extends JPanel {
    public static VendingMachineFactory newFactory = new VendingMachineFactory();
    public static void main (String[] args) {
        MainMenu mainMenu = new MainMenu();
    }

}