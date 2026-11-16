public class OrderItem {

    private String orderItemID;
    private Hall hall;
    private Concession concession;
    private String[] seats;

    public OrderItem(Hall hall, Concession concession, String[] seats) {
        this.orderItemID     = generateID();
        this.hall            = hall;
		this.concession  = concession;
		this.seats = seats;
    }

    public String[] getSeats(){
	    return seats;
	}
	
	public Hall getHall() {
	    return hall;
	}
	
	public Concession getConcession() {
	    return concession;
	}

    private String generateID() {
        int num = (int)(Math.random() * 90000) + 10000;
        return "OI" + num;
    }

    public void displayOrderItem(String orderID) {
        System.out.println("\n+========================================================================+");
        System.out.println("|                         ORDER ITEM DETAILS                            |");
        System.out.println("+========================================================================+");
        System.out.println("  Order Item ID : " + orderItemID);
        System.out.println("  Order ID      : " + orderID);

        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("                              MOVIE TICKETS                               ");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-20s %-12s %-10s %-8s %-8s%n","Movie", "Date", "Time", "Hall", "Seat");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < seats.length; i++) {
		    System.out.printf("%-20s %-12s %-10s %-8s %-8s%n",
		    hall.getMovieName(),
		    hall.getDate(),
		    hall.getTime(),
		    hall.getHallNo(),
		    seats[i]);
		}
        System.out.println("\n--------------------------------------------------------------------------");
		System.out.println("                              CONCESSION                                  ");
		System.out.println("--------------------------------------------------------------------------");
		
		if (concession == null || concession.getCartCount() == 0) {
		    System.out.println("  No concession ordered.");
		} else {
		    System.out.printf("%-15s %-10s %-12s %-10s%n", "Item", "Qty", "Unit Price", "Subtotal");
		    System.out.println("--------------------------------------------------------------------------");
		    Item[] items = concession.getCartItems();
		    int[]  qtys  = concession.getCartQty();
		    for (int i = 0; i < concession.getCartCount(); i++) {
		        Item item = items[i];
		        int  qty  = qtys[i];
		        System.out.printf("%-15s %-10d RM%-10.2f RM%-10.2f%n",
		            item.getName(), qty, item.getPrice(), item.getPrice() * qty);
		    }
		}

        System.out.println("\n==========================================================================");
        System.out.println("Click Enter To Continue...");
        CinemaSystem.Input.scanner.nextLine();
    }
}
