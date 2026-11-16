import java.io.*;
public class Ticket {
	
	public static Ticket[] tickets = new Ticket[100];
	private static int count = 0;
    private String ticketID;
    private OrderItem orderItem;
    private String seat;
    private String movieName;
	private String date;
	private String showTime;
	private String hall;
	private double price;
    private static final String FILE_NAME = "tickets.txt";

    // Constructor
    
    public Ticket(OrderItem orderItem, String seat) {
	    this.ticketID = generateTicketID();
	    this.orderItem = orderItem;
	    this.seat = seat;
	
	    tickets[count++] = this;
	}

    public Ticket(String ticketID, String movieName, String date,
              String showTime, String seat, String hall, double price) {

    this.ticketID = ticketID;
    this.movieName = movieName;
    this.date = date;
    this.showTime = showTime;
    this.seat = seat;
    this.hall = hall;
    this.price = price;

    tickets[count++] = this;
}

    // ================= GETTERS =================
    public String getTicketID() {
        return ticketID;
    }

    public String getSeat() {
        return seat;
    }

    public String getMovieName() {
	    if (orderItem != null)
	        return orderItem.getHall().getMovieName();
	    return movieName;
	}
	
	public String getDate() {
	    if (orderItem != null)
	        return orderItem.getHall().getDate();
	    return date;
	}
	
	public String getShowTime() {
	    if (orderItem != null)
	        return orderItem.getHall().getTime();
	    return showTime;
	}
	
	public String getHall() {
	    if (orderItem != null)
	        return "Hall " + orderItem.getHall().getHallNo();
	    return hall;
	}

    public double getPrice() {
	    if (orderItem != null)
	        return orderItem.getHall().getPrice();
	    return price;
	}

    public OrderItem getOrderItem() {
        return orderItem;
    }

    // ================= ID =================
    private String generateTicketID() {
        int num = (int)(Math.random() * 90000) + 10000;
        return "T" + num;
    }

    // ================= DISPLAY =================
    public void displayTicket() {
        System.out.println("\n================= TICKET =================");
        System.out.println("Ticket ID : " + ticketID);
        System.out.println("Movie     : " + getMovieName());
        System.out.println("Date      : " + getDate());
        System.out.println("Time      : " + getShowTime());
        System.out.println("Hall      : " + getHall());
        System.out.println("Seat      : " + seat);
        System.out.printf("Price     : RM %.2f\n", getPrice());
        System.out.println("==========================================");
    }
    
    public void saveTicket() {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
	        bw.write(ticketID + "," + getMovieName() + "," + getDate() + "," +
         getShowTime() + "," + seat + "," + getHall() + "," + getPrice());
	        bw.newLine();
	    } catch (Exception e) {
	        System.out.println("Error saving ticket");
	    }
	}

	public static void loadFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            Ticket t = new Ticket(
                data[0],
                data[1],
                data[2],
                data[3],
                data[4],
                data[5],
                Double.parseDouble(data[6])
            );
        }

    } catch (Exception e) {
        System.out.println("No ticket data.");
    }
}
	
	public static void validateTicket(String id){
	    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
	        String line;
	
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(",");
	
	            if (data[0].equals(id)) {
	                System.out.println("\nValid Ticket");
	                System.out.println("Movie : " + data[1]);
	                System.out.println("Seat  : " + data[4]);
	                return;
	            }
	        }
	
	    } catch (Exception e) {
	        System.out.println("Error reading ticket file");
	    }
	
	    System.out.println("\nInvalid Ticket");
	}
}