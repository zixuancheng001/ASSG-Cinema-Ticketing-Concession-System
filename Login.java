import java.io.*;

//class name
public class Login {
	
	//Data File
	private static final String file1 = "CustomerAccount.txt";
	private static final String file2 = "StaffAccount.txt";
	private String staffID;
    private String userName;
    private String password;
	
	//Contructor no arg
    public Login() {
    	staffID = "";
        userName = "";
        password = "";
    }
    
    //Contructor with parameters
    public Login(String staffID, String userName, String password) {
    	this.staffID = staffID;
        this.userName = userName;
        this.password = password;
    }
    
    //set methods
    public void setStaffID(String staffID){
    	this.staffID = staffID;
    }
    
    public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	//get methods
	public String getStaffID(){
		return staffID;
	}
	
	public String getUserName(){
		return userName;
	}
	  
	public String getPassword(){
		return password;
	}
	
	// Customer Login
	public boolean CustomerLogin(){
		try {
	        	
			File f1 = new File(file1);
				
			// Check File is have or not
	        if (!f1.exists()) {
	            System.out.println("No account found. Please sign up first.");
	            return false;
	        }
				
	        BufferedReader br = new BufferedReader(new FileReader(f1));
	        String line;
				
			//Read fine line by line
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(",");
					
	            if (data[0].equals(userName) && data[1].equals(password)) {
	                br.close();
	                return true;
	            }
	        }
	        br.close();
	    }catch (IOException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    return false;
	}
	
	//Staff login
	public boolean StaffLogin(){
		try {
	        	File f2 = new File(file2);
	
	            if (!f2.exists()) {
	                System.out.println("No account found. Please sign up first.");
	                return false;
	            }
	
	            BufferedReader br = new BufferedReader(new FileReader(f2));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                
	                if (data.length >= 2 && data[0].equals(staffID) && data[1].equals(password)) {
				        br.close();
				        return true;
				    }
				}
	            br.close();
	     }catch (IOException e) {
	        System.out.println("Error: " + e.getMessage());
	     }
	     return false;
	}
}
