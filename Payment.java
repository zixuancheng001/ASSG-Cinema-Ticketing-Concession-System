import java.util.*;

public class Payment {
	
	//data field
    private String referenceNo;
    private String paymentMethod;
    private String paymentDate;
    private Order order;

    // Constructor
    public Payment(Order order) {
        this.order = order;
        this.paymentDate = new Date().toString();
    }
	
	//function
	//get
	public Order getOrder() {
		return order;
    }
    
    public String getPaymentMethod() {
    	return paymentMethod;
    }
    
    public String getReferenceNo() {
    	return referenceNo;
    }
    
    // Process Payment
    public void processPayment() {

        double amount = order.getTotalAmount();

        System.out.println("\n+==========================================================================+");
        System.out.println("|                                 Checkout                                 |");
        System.out.println("+==========================================================================+");
        System.out.printf("| Total Amount : RM %.2f%n", amount);
        System.out.println("+==========================================================================+");
        System.out.println("| Select Payment Method                                                    |");
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("| 1. Credit Card                                                           |");
        System.out.println("| 2. E-Wallet                                                              |");
        System.out.println("| 3. Cash                                                                  |");
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.print("Please Enter Your Choice : ");

        int choice;
        while (true) {
            String input = CinemaSystem.Input.scanner.nextLine();
            if (input.matches("\\d+")) {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) break;
            }
            System.out.print("Invalid input! Enter 1-3: ");
        }

        switch (choice) {

            case 1:
                processCreditCard();
                paymentMethod = "Credit Card";
                referenceNo = "CC" + System.currentTimeMillis();
                break;

            case 2:
                processEWallet();
                paymentMethod = "E-Wallet";
                referenceNo = "EW" + System.currentTimeMillis();
                break;

            case 3:
                processCash(amount);
                paymentMethod = "Cash";
                referenceNo = "CSH" + System.currentTimeMillis();
                break;
        }
        
        Receipt receipt = new Receipt(this);
		receipt.saveReceipt();
		receipt.printReceipt();
    }

    // Credit Card
    private void processCreditCard() {
        while (true) {
            System.out.print("Enter 16-digit Credit Card Number: ");
            String num = CinemaSystem.Input.scanner.nextLine();

            if (num.matches("\\d{16}")) {
                System.out.println("Processing payment...");
                break;
            } else {
                System.out.println("Invalid card number!");
            }
        }
    }

    // E-Wallet
    private void processEWallet() {
        while (true) {
            System.out.print("Enter phone number (01xxxxxxxxx): ");
            String num = CinemaSystem.Input.scanner.nextLine();

            if (num.matches("01\\d{8,9}")) {
                System.out.println("Sending payment request...");
                break;
            } else {
                System.out.println("Invalid phone number!");
            }
        }
    }

    // Cash
    private void processCash(double amount) {
        System.out.println("Please pay RM " + String.format("%.2f", amount) + " at the counter.");
    }
}