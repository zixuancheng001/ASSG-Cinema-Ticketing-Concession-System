import java.util.Scanner;

public class CinemaSystem{
	
	static int status;
	
	public static class Input {
        public static final Scanner scanner = new Scanner(System.in);
    }

    public static void main(String[] args){
    	
    	System.out.println("+======================================================================================================+");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                         WELCOME TO HW CINEMA                                         |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("|                                                                                                      |");
    	System.out.println("+======================================================================================================+");
    	System.out.println();
    	System.out.println("Click Enter To Access System...");
    	Input.scanner.nextLine();
    	
    
    	do {
		    System.out.println("\n+========================================================================+");
		    System.out.println("|                  CINEMA TICKETING & CONCESSION SYSTEM                  |");
		    System.out.println("+========================================================================+");
		    System.out.println("|                                                                        |");
		    System.out.println("|                              Who Are You?                              |");
		    System.out.println("|                                                                        |");
		    System.out.println("|                             [1] Customer                               |");
		    System.out.println("|                             [2] Staff                                  |");
		    System.out.println("|                                                                        |");
		    System.out.println("+========================================================================+");
		    System.out.print("Enter Option > ");
			    	
	    	while (true){
			    String input = Input.scanner.nextLine();
			
			    if (input.matches("\\d+")){
			        status = Integer.parseInt(input);
			        break;
			    }else{
			        System.out.print("\nInvalid input! Please enter numbers only: ");
			    }	
			}
		    if (status == 1){
		    	System.out.println("\n+=========================================================================+");
		    	System.out.println("+                             CUSTOMER ACCOUNT                            +");
		    	System.out.println("+=========================================================================+");
		    	if (Start()){ 
			    	Customer c = new Customer("", "", "", "", "");
			        c.custMenu();
				}
		    }else if(status == 2){
		    	System.out.println("\n+=========================================================================+");
		    	System.out.println("+                              STAFF ACCOUNT                              +");
		    	System.out.println("+=========================================================================+");
		    	if (Start()){
		    	Staff s = new Staff();
			    s.staffMenu();
		    	}
		    }else{
		    	System.out.println("\nInvalid Choice, Please Enter 1 OR 2!!!");
		    }
    	}while (true);	
    }
    
    public static boolean Start(){
    	
    	int choice;
    	
    	while(true){
	    	System.out.println("\n* Do You Have An Account? -> [Click 1] Login Now");
	    	System.out.println("\n* Don't Have An Account? -> [Click 2] Sign up Now ");
	    	System.out.println();
	    	System.out.print("\nEnter Your Choice [1&2]: ");
	    	
	    	while (true){
			    String input = Input.scanner.nextLine();
			
			    if (input.matches("\\d+")){
			        choice = Integer.parseInt(input);
			        break;
			    }else{
			        System.out.print("\nInvalid input T^T ! Please Enter 1 OR 2 only: ");
			    }
			}			
	    	if (choice == 1){
	    		System.out.println("\n================================================");
			    System.out.println("               Login Your Account               ");
			    System.out.println("================================================");
			    if (login_()) {
			        return true;
			    }
				while (true){
				    System.out.println("\n+--------------------------------------+");
				    System.out.println("|  [1]      Try Again                  |");
				    System.out.println("|  [2]      Sign Up                    |");
				    System.out.println("+--------------------------------------+");
				    System.out.print("\nEnter Your Choice: ");
				
				    int option;
					while (true){
						String input = Input.scanner.nextLine();	
						if (input.matches("\\d+")) {
							option = Integer.parseInt(input);
							break;
						}else{
							System.out.print("\nInvalid input! Please enter numbers only: ");
						}
					}
				    if (option == 1){
					    if (login_()){
					    	return true;
					    }
				    }else if (option == 2){
				    	if(status == 1){
							return customer_signUp_();
						}else if (status == 2){
							return staff_signUp_();
						}
					}else{
				    	System.out.println("\nInvalid choice!!!Please Enter Again!");
				    }
				}		
	    	}else if(choice == 2){
	    		System.out.println("\n+=========================================================================+");
	    		System.out.println("                           Sign Up Your Account                            ");
	    		System.out.println("+=========================================================================+");
		    	if(status == 1){
					return customer_signUp_();
				}else if (status == 2){
					return staff_signUp_();
				}
				return false;	
		    }else{
		    	System.out.println("\nInvalid choice....Please Enter 1 OR 2 Only!");
		    }
		}
	}
	
    public static boolean login_(){
    	
    	Login l1 = new Login();
    	
    	if (status == 1){
	    	System.out.print("\nEnter Your User Name > ");
	    	l1.setUserName (Input.scanner.nextLine());
	    	System.out.print("Enter Your Password > ");
	    	l1.setPassword (Input.scanner.nextLine());
	    	
	    	if (l1.CustomerLogin()){
	    		System.out.println("\n+========================================================================+");
		        System.out.println("|                           LOGIN SUCCESSFUL!                            |");
		        System.out.println("+========================================================================+");
		        System.out.println("|                         WELCOME TO HW CINEMA!!!                        |");
		        System.out.println("+========================================================================+");
		        return true; 
		    }else{
		        System.out.println("\nInvalid username or password!!!");
		        return false; 
		    }
    	}else if(status == 2){
	    	System.out.print("\nEnter Your Staff ID: ");
	    	l1.setStaffID (Input.scanner.nextLine());
	    	System.out.print("Enter Your Password: ");
	    	l1.setPassword (Input.scanner.nextLine());
	    	
		    if (l1.StaffLogin()) {
		    	System.out.println("\n+========================================================================+");
		        System.out.println("|                           LOGIN SUCCESSFUL!                            |");
		        System.out.println("+========================================================================+");
		        System.out.println("|                         WELCOME TO HW CINEMA!!!                        |");
		        System.out.println("+========================================================================+");
		        return true; 
		    } else {
		        System.out.println("\nInvalid username or password!!!");
		        return false; 
		    }
    	}return false;
	}
    
    public static boolean customer_signUp_(){
    	
    	Customer s = new Customer();
    	Signup.customerSignUp s1 = new Signup().new customerSignUp();
    	
    	while (true){
	        System.out.print("\nCreate Your User Name: ");
	        s.setUserName(Input.scanner.nextLine());
	
	        if (s1.checkCustomerAccount(s.getUserName())){
	            System.out.println("\nUsername already exists! Try again.\n");
	        }else{
	            break;
	        }
	    }
	    
    	System.out.print("Set Your Password: ");
    	s.setPassword (Input.scanner.nextLine());
    	System.out.print("Enter Your Phone Number: ");
    	s.setPhoneNum (Input.scanner.nextLine());
    	System.out.print("Enter Your Name: ");
    	s.setName (Input.scanner.nextLine());
    	System.out.print("Enter Your Email: ");
    	s.setEmail (Input.scanner.nextLine());

    	s1.saveCustomerAccount(s);
    	System.out.println("\n================================================");
	    System.out.println("+      Registration successful! Login Now!     +");
	    System.out.println("================================================");
	    return login_();
    }
    
    public static boolean staff_signUp_(){
    	
    	Staff s = new Staff();
    	Signup.staffSignUp s2 = new Signup().new staffSignUp();
    	
    	while (true) {
	        System.out.print("\nCreate Staff ID: ");
	        s.setStaffID(Input.scanner.nextLine());
	
	        if (s2.checkStaffAccount(s.getStaffID())) {
	            System.out.println("\nThis Staff ID already exists! Try again.");
	        }else{
	            break;
	        }
	    }
	    System.out.print("Enter Your Name: ");
    	s.setName (Input.scanner.nextLine());
    	System.out.print("Enter Your Phone Number: ");
    	s.setPhoneNum (Input.scanner.nextLine());

    	s2.saveStaffAccount(s);
	    System.out.println("\n+========================================================================+");
		System.out.println("+                  Registration successful! Login Now!                   +");
		System.out.println("+========================================================================+");
	    return login_();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //don't delete

}