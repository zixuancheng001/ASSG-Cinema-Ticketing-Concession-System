import java.util.Scanner;
public class Customer extends Person{
	
	//Data files
	private String userName;
	
	// Constructor no args
	public Customer(){
		super("","","","");
		userName = "";
	}
	
	// Constructor with args
	public Customer(String password, String phoneNum, String name, String email, String userName){
		super(password,phoneNum,name,email);
	    this.userName = userName;
	}	
		
	// set
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	// get
	public String getUserName(){
		return userName;
	}
 
	public void custMenu(){
		boolean running = true;
        while (running){
			System.out.println("\n+------------------------------------------------------------------------+");
			System.out.println("|                               Customer Menu                            |");
			System.out.println("+------------------------------------------------------------------------+");
			System.out.println("|   1   |   View Movies                                                  |");
			System.out.println("|   2   |   Logout                                                       |");
			System.out.println("|  [0]  |   Exit                                                         |");
			System.out.println("+------------------------------------------------------------------------+");
			System.out.print("\nEnter Your choice: ");
			int choice;
			
			// Check input
	        while (true){
	            String input = CinemaSystem.Input.scanner.nextLine();
	
	            if (input.matches("\\d+")){
	                choice = Integer.parseInt(input);
	                break;
	            }else{
	                System.out.print("\nInvalid input! Please enter numbers only: ");
	            }
	        }
			
			if (choice == 1){
				viewMovies();
	       	}else if(choice == 2){
			    System.out.println("+----------------------------- Logging out ------------------------------+");
			    return;
	        }else if(choice == 0){
	        	System.out.println("\n+==============================================+");
	            System.out.println("+                  GOODBYE!!                   +");
	            System.out.println("+==============================================+");
	            System.exit(0); //End program
	        }else{
		    	System.out.println("\nInvalid choice, Please Enter Number 1-4 OR 0 to Exit");
		    }
		}
	}
	
	// View Movies 
	public void viewMovies(){

	   Movie.loadMovies();
	   Movie.displayAllMovies();

       System.out.print("Enter Movie ID: ");
       String movieID = CinemaSystem.Input.scanner.nextLine();
       if (Movie.findMovie(movieID) == null) {
       	 System.out.println("Invalid Movie ID!");
       	 return;
       }

       Schedule.load();
       Schedule.displayByMovie(movieID);

       System.out.print("Enter Schedule ID: ");
       String scheduleID = CinemaSystem.Input.scanner.nextLine();

       Schedule s = null;

       for (int i = 0; i < 100; i++) {
          if (Schedule.getScheduleAt(i) != null && Schedule.getScheduleAt(i).getScheduleID().equalsIgnoreCase(scheduleID)) { 
          	s = Schedule.getScheduleAt(i);
             break;
            }
       }

       if(s == null){
          System.out.println("Invalid schedule!");
          return;
        }

       Hall hall = new Hall(s.getHallNo(), s.getMovieID());
       hall.setMovieInfo(
		    Movie.findMovie(movieID).getTitle(),
		    s.getDate(),
		    s.getTime(),
		    16.0
		);
	    // Choice Seat
	    hall.displaySeats();
	    int numSeats;
		while (true){
			System.out.println("--------------------------------------");
		    System.out.print("\nHow many seats do you want to book >");
		    String input = CinemaSystem.Input.scanner.nextLine();
		
		    if (input.matches("\\d+")){
		        numSeats = Integer.parseInt(input);
		
		        if (numSeats > 0){
		            break;
		        }else{
		            System.out.println("\nPlease enter at least 1 seat.");
		        }
		        
		    }else{
		        System.out.println("\nInvalid input! Numbers only.");
		    }
		}
	
	    boolean bookingSuccess = true;
	    String[] seats = new String[numSeats];
		
		//Check bookSeat is success or not
	    for (int i = 0; i < numSeats; i++){
	        System.out.print("\nChoice Your Seat " + (i+1) + ": ");
	        String seat = CinemaSystem.Input.scanner.nextLine();
	
	        boolean success = hall.bookSeat(seat);
	
	        if (!success){
	        	System.out.println("\n+---------------------------------------+");
	            System.out.println("\n    Booking failed. Rolling back...      ");
	            System.out.println("\n+---------------------------------------+");
				
				//cancle the seat
	            for (int j = 0; j < i; j++) {
	                hall.unbookSeat(seats[j]);
	            }
	            
	            bookingSuccess = false;
	            break;
	        }
	        seats[i] = seat;
	    }
		
	    if (!bookingSuccess){
	        System.out.println("\nPayment cancelled.");
	        return;
	    }
			
		System.out.println("\n+--------------------------------------------------------------------------+");
		System.out.println("+                  Would you like to order concessions?                    +");
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("+                     [1] Yes                [2] No                        +");
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.print("Enter Your Choice: ");
			
		String concessionInput = CinemaSystem.Input.scanner.nextLine();
		Order order = new Order();
		Payment payment = new Payment(order); 
		Concession concession = null;
		if (concessionInput.equals("1")){
		    concession = new Concession();
		    concession.customer(payment);
		}
		
		OrderItem orderItem = new OrderItem(hall, concession, seats);
		order.setOrderItem(orderItem);
		order.calculateTotal(concession);		
		orderItem.displayOrderItem(order.getOrderID());
		int status = order.selectStatus();
		if (status == 1){
		    order.checkOut();
		}else{
		    order.cancelOrder();
		    
		    for (String seat : seats){
		        hall.unbookSeat(seat);
		    }
		    return;
		}
		
		// Check Out	
		payment.processPayment();
		
		// Create ticket
	    for (int i = 0; i < numSeats; i++){
		    Ticket t = new Ticket(orderItem, seats[i]);
		    t.displayTicket();   // optional
			t.saveTicket();
		}
	}
}
			