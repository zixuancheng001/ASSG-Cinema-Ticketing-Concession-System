import java.util.Scanner;

public class Staff extends Person{
	
	//Data fields
	private String staffID;
	Schedule schedule = new Schedule();
    Concession concession = new Concession();
	Hall[] halls;
	
	//Contructor no arg
	public Staff(){
		super("","","","");
	    staffID = "";
	    
	    halls = new Hall[8];
	    for (int i = 0; i < halls.length; i++) {
	        halls[i] = new Hall(i + 1, "defaultMovieID");
	    }
	}
	
	//Contructor with arg
	public Staff(String password, String phoneNum, String name, String email,String staffID){
		super(password,phoneNum,name,email);
	    this.staffID = staffID;
	}
	
	//set
	public void setStaffID(String staffID){
		this.staffID = staffID;
	}
	
	//get
	public String getStaffID(){
		return staffID;
	}

    public void staffMenu(){
    	
        int choice=0;

        while (true){
            System.out.println("\n+------------------------------------------------------------------------+");
            System.out.println("|                               Staff Menu                               |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.println("|   1   |   Manage Movies                                                |");
            System.out.println("|   2   |   Manage Schedule                                              |");
            System.out.println("|   3   |   Manage Concession                                            |");
            System.out.println("|   4   |   Validate Ticket                                              |");
            System.out.println("|   5   |   Clear All Booking                                            |");
            System.out.println("|   6   |   Logout                                                       |");
            System.out.println("|  [0]  |   Exit                                                         |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");
            String input = CinemaSystem.Input.scanner.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Invalid choice! Please enter a number.");
                continue;
             }

            choice = Integer.parseInt(input);
			
			if (choice == 1){
				Movie.menu();
			}else if(choice == 2){
				Schedule.menu();
			}
			else if(choice == 3){
				concession.staff();
			}else if(choice == 4){
				while(true){
					System.out.println("\n================================");
					System.out.println("     Validate Ticket    ");
					System.out.println("================================");
			        System.out.print("Enter Ticket ID (0 to exit): ");
			        String id = CinemaSystem.Input.scanner.nextLine();
			
			        if(id.equals("0")){
			        	System.out.println("================================");
			            System.out.println("  Exiting ticket validation...  ");
			            System.out.println("================================");
			            break;
			        }
			        Ticket.validateTicket(id);
			    }
			}else if(choice == 5){
            	for(int i = 0; i < halls.length; i++){
                	halls[i] = new Hall(i + 1, "defaultMovieID");
                }
                System.out.println("================================");
                System.out.println("     All bookings cleared!      ");
                System.out.println("================================");
			}else if(choice == 6) {
				System.out.println("================================");
			    System.out.println("          Logging out...        ");
			    System.out.println("================================");
			    return;
	        }else if(choice == 0) {
	        	System.out.println("\n================================");
				System.out.println("        Exit staff menu         ");
				System.out.println("================================");
				System.exit(0);
			}else{
				System.out.println("Invalid choice! Please Enter Number 1-6 or 0 to Exit");
			}

     	}
    }
}