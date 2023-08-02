package VMF;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        String choice;
        VendingMachine vendingMachine = null;
        VendingMachineFactory vendingMachineFactory = new VendingMachineFactory();

        do {
            clearScreen();
            System.out.println("CURRENT NUMBER OF VENDING MACHINES: " + vendingMachineFactory.getAllVendingMachines().size());
            System.out.println("Vending Machine Factory Simulator");
            System.out.println("[A] Create a vending machine");
            System.out.println("[B] Test a vending machine");
            System.out.println("[X] Exit");

            System.out.print("Choice: ");
            choice = sc.nextLine().toLowerCase();


            switch (choice) {
                case "a" -> {
                    clearScreen();
                    String tempMachName;
                    System.out.println("[R]egular or [S]pecial? ");
                    System.out.print("Your choice: ");
                    String machChoice = sc.next().toLowerCase();
                    switch (machChoice) {
                        case "r":
                            System.out.println("Machine Name? ");
                            tempMachName = sc.next();
                            //vendingMachine = vendingMachineFactory.createVendingMachine(tempMachName);
                        case "s":
                            System.out.println("Machine Name? ");
                            tempMachName = sc.next().toLowerCase();
                            //vendingMachine = vendingMachineFactory.createSpecialVM(tempMachName);
                        default:
                            System.out.println("Incorrect input");
                            waitForEnter();
                            break;
                        }
                    }
                case "b" -> {
                    clearScreen();
                    if (vendingMachineFactory.getAllVendingMachines().size() > 0) {
                        System.out.println("Available Vending Machines:");
                        List<VendingMachine> vendingMachines = vendingMachineFactory.getAllVendingMachines();
                        for (int i = 0; i < vendingMachines.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + vendingMachines.get(i).getMachineName());
                        }
                        System.out.print("Choose a vending machine (1-" + vendingMachines.size() + "): ");
                        int machineChoice = Integer.parseInt(sc.nextLine());

                        VendingMachine selectedMachine = vendingMachines.get(machineChoice - 1);

                        clearScreen();
                        System.out.println("[T] Test the vending machine");
                        System.out.println("[M] Maintain the vending machine");
                        System.out.println("[S] Get statistics of the vending machine");
                        System.out.print("Choice: ");
                        String testOrMaintainChoice = sc.nextLine().toLowerCase();
                        clearScreen();

                        switch (testOrMaintainChoice) {
                            case "t" -> {
                                String testChoice;
                                do {
                                    clearScreen();
                                    System.out.println("What would you like to do? ");
                                    System.out.println("Print [S]lots");
                                    System.out.println("Print [M]oney available");
                                    System.out.println("      [B]uy from Machine.");
                                    System.out.println("Return to main menu [X]");
                                    System.out.print("Choice: ");
                                    testChoice = sc.nextLine().toLowerCase();
                                    switch (testChoice) {
                                        case "s":
                                            clearScreen();
                                            selectedMachine.printSlots();
                                            waitForEnter();
                                            break;
                                        case "m":
                                            clearScreen();
                                            selectedMachine.printMoneyAvailability();
                                            waitForEnter();
                                            break;
                                        case "b":
                                            clearScreen();
                                            int slotChoice;
                                            System.out.println("Choose from the items");
                                            selectedMachine.printSlots();
                                            System.out.print("Pick a slot: ");
                                            slotChoice = sc.nextInt() - 1;
                                            sc.nextLine();

                                            if(selectedMachine.getSlot(slotChoice).getItemQuantity() > 0){
                                                //selectedMachine.buyItem(slotChoice);

                                            } else {
                                                System.out.println("Not available. Try again.");
                                                waitForEnter();
                                                clearScreen();
                                            }
                                            waitForEnter();
                                            break;
                                        case "x":
                                            // Exit the loop and go back to the main menu
                                            break;
                                        default:
                                            System.out.println("Invalid choice");
                                            waitForEnter();
                                    }
                                } while (!testChoice.equals("x"));
                            }
                            case "m" -> {
                                clearScreen();
                                System.out.println("[1] Restock items");
                                System.out.println("[2] Set item price");
                                System.out.println("[3] Collect all money from vending machine");
                                System.out.println("[4] Add money to vending machine");
                                System.out.println("[D] Dispose the vending machine");
                                System.out.print("Choice: ");
                                String maintainChoice = sc.nextLine().toLowerCase();
                                clearScreen();
                                switch (maintainChoice) {
                                    case "1" -> {
                                        clearScreen();
                                        System.out.println("Set stock for which item? ");
                                        selectedMachine.printSlots();
                                        int slotReplenish;
                                        int ct = 0;
                                        do {
                                            if (ct > 0) {
                                                System.out.print("Please select the correct slot number.");
                                            }
                                            System.out.print("Pick a slot: ");
                                            slotReplenish = sc.nextInt() - 1;
                                            ct++;
                                        } while (slotReplenish >= selectedMachine.getAllSlots().size());
                                        System.out.print("Items to add: ");
                                        int setStock = sc.nextInt();
                                        selectedMachine.setItemStock(slotReplenish, setStock);
                                        waitForEnter();
                                    }
                                    case "2" -> {
                                        clearScreen();
                                        System.out.print("Set price for which item? ");
                                        selectedMachine.printSlots();
                                        int itemChoice;
                                        int ct1 = 0;
                                        do {
                                            if (ct1 > 0) {
                                                System.out.print("Please select the correct slot number.");
                                            }
                                            System.out.print("Pick a slot: ");
                                            itemChoice = sc.nextInt() - 1;
                                            ct1++;
                                        } while (itemChoice > selectedMachine.getAllSlots().size());
                                        System.out.print("Set price: ");
                                        float setPrice = sc.nextFloat();
                                        selectedMachine.setItemPrice(itemChoice, setPrice);
                                    }
                                    case "3" -> {clearScreen(); selectedMachine.takeProfit(); waitForEnter();}
                                    case "4" -> {clearScreen(); selectedMachine.replenishMachine(); waitForEnter();}
                                    case "d" -> {
                                        clearScreen();
                                        System.out.println("Are you sure you want to dispose of the vending machine? (Y/N)");
                                        String confirmDispose = sc.nextLine().toLowerCase();
                                        if (confirmDispose.equals("y")) {
                                            vendingMachineFactory.disposeVendingMachine(selectedMachine);
                                            System.out.println("Vending machine disposed successfully.");
                                        }
                                        waitForEnter();
                                    }
                                    default -> {System.out.println("INVALID CHOICE"); waitForEnter();}
                                }
                            }

                            case "s" -> {
                                clearScreen();
                                String statChoice;
                                System.out.println("[1] Total Items Sold");
                                System.out.println("[2] Total Sales");
                                System.out.println("[3] Starting Inventory vs. Previous Inventory");
                                System.out.println("[0] Exit");
                                System.out.print("Enter choice: ");
                                statChoice = sc.next();

                                switch (statChoice) {
                                    case "1" -> {
                                        clearScreen();
                                        System.out.println("Total Items sold for " + selectedMachine.getMachineName() + " is: " + selectedMachine.getTotalSoldItems());
                                        waitForEnter();
                                        break;
                                    }
                                    case "2" -> {
                                        clearScreen();
                                        System.out.println("Total sales for " + selectedMachine.getMachineName() + " is: " + selectedMachine.getTotalSales());
                                        waitForEnter();
                                        break;
                                    }
                                    case "3" -> {
                                        clearScreen();
                                        selectedMachine.displayInventories();
                                        waitForEnter();
                                        break;
                                    }
                                    case "0" -> {
                                        continue;
                                    }
                                    default -> {
                                        clearScreen();
                                        System.out.println("Invalid input. Try again.");
                                        waitForEnter();
                                    }
                                }

                                break;
                            }
                            default -> {
                                break;
                            }
                        }
                    } else {
                        System.out.println("No vending machine created yet. Please create a vending machine first.");
                        waitForEnter();
                    }
                }
                case "x" -> System.out.println("EXITING PROGRAM");
                default -> System.out.println("INVALID CHOICE");
            }
        } while (!choice.equals("x"));

        sc.close();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void waitForEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press Any key to continue...");
        sc.nextLine();
    }
}
