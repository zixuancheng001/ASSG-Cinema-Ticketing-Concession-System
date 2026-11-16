import java.io.*;
import java.util.*;

public class Order {

    private String    orderID;
    private double    totalAmount;
    private String    orderDate;
    private String    status;
    private OrderItem orderItem;
    private static final String FILE_NAME = "orders.txt";

    public Order() {
        this.orderID     = generateOrderID();
        this.totalAmount = 0.0;
        this.orderDate   = new Date().toString();
        this.status      = "";
    }
	
	// Get
    public String getOrderID(){
    	return orderID;
    }
    
    public double getTotalAmount(){
    	return totalAmount;
    }
    
    public String getOrderDate(){
    	return orderDate;
    }
    
    public String getStatus(){
    	return status;
    }

    
    private String generateOrderID() {
        int num = (int)(Math.random() * 90000) + 10000;
        return "OR" + num;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
	
	//calculate total ticket and concession
    public void calculateTotal(Concession concession) {
        double total = 0.0;
        total += orderItem.getSeats().length * 16.0;

        // Concession
        if (concession != null){
	        Item[] items = concession.getCartItems();
	        int[] qtys = concession.getCartQty();
	
	        for (int i = 0; i < concession.getCartCount(); i++) {
	            total += items[i].getPrice() * qtys[i];
	        }
	    }
        this.totalAmount = total;
    }
    
    // Confirm Order
    public void checkOut() {
        this.status = "CheckOut";
        saveOrderToFile();
        System.out.println("\n+========================================================================+");
        System.out.println("|                             Order Summary                              |");
        System.out.println("+========================================================================+");
        System.out.println("  Order ID     : " + orderID);
        System.out.printf ("  Total Amount : RM %.2f%n", totalAmount);
        System.out.println("  Date         : " + orderDate);
        System.out.println("  Status       : " + status);
        System.out.println("==========================================================================");
        System.out.println();
        System.out.println("Click Enter To Checkout...");
        CinemaSystem.Input.scanner.nextLine();
    }

    // Cancel Order
    public void cancelOrder() {
        this.status = "Cancelled";
        System.out.println("\n+========================================================================+");
        System.out.println("|                            ORDER CANCELLED!                            |");
        System.out.println("+========================================================================+");
        System.out.println("  Order ID     : " + orderID);
        System.out.printf ("  Total Amount : RM %.2f%n", totalAmount);
        System.out.println("  Date         : " + orderDate);
        System.out.println("  Status       : " + status);
        System.out.println("==========================================================================");
        System.out.println();
        System.out.println("Click Enter Back To Menu...");
        CinemaSystem.Input.scanner.nextLine();
    }

    public int selectStatus() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("   Would you like to continue your order?    ");
        System.out.println("+------------------------------------------+");
        System.out.println("|  [1]    CheckOut                          |");
        System.out.println("|  [2]    Cancel Order                      |");
        System.out.println("+------------------------------------------+");

        int choice;
        while (true) {
            String input = CinemaSystem.Input.scanner.nextLine();
            if (input.matches("\\d+")) {
                choice = Integer.parseInt(input);
                break;
            } else {
                System.out.print("\nInvalid input! Please enter numbers only: ");
            }
        }
        return choice;
    }

    public void saveOrderToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(orderID + "," + totalAmount + "," + orderDate + "," + status);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public static void loadOrders() {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) {
                System.out.println("No orders found.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                found = true;
                String[] data = line.split(",");
                System.out.println("\n==========================================================================");
                System.out.println("  Order ID     : " + data[0]);
                System.out.printf ("  Total Amount : RM %.2f%n", Double.parseDouble(data[1]));
                System.out.println("  Date         : " + data[2]);
                System.out.println("  Status       : " + data[3]);
                System.out.println("==========================================================================");
            }
            br.close();
            if (!found) System.out.println("No orders found.");
        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
    }
}