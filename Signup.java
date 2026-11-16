import java.io.*;
public class Signup{
	
	private static final String file1 = "StaffAccount.txt"; //file use to save customer account
	private static final String file2 = "CustomerAccount.txt"; //file use to save customer account
	
	class staffSignUp{
	//boolean is only return true or false
		 public boolean checkStaffAccount(String staffID) {
		        try {
		        	
		        	File f = new File(file1);
		            if (!f.exists()) return false;
		            
					//read the data form CustomerAccount.txt
		            BufferedReader br = new BufferedReader(new FileReader(f)); //read the data form CustomerAccount.txt
		            String line;
		
		            while ((line = br.readLine()) != null) {
		                String[] data = line.split(",");
		                //check the staffID is have or not
		                if (data.length > 0 && data[0].equals(staffID)) {
		                br.close();
		                    return true;
		                }
		            }
	
	            br.close();
	
	        } //Get and print error message
	        	catch (IOException e) {
	            System.out.println("Error"+ e.getMessage());
	        }
	
	        return false;
	    }
	    
	
	    public void saveStaffAccount(Staff s) {
	    	s.setPassword("Movie123");
	        try {
	
	            BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true));
	
	            bw.write(s.getStaffID() + "," + s.getPassword() + "," + s.getName() + "," + s.getPhoneNum());
	            bw.newLine();
	            bw.close();
	
	            System.out.println("Sign up successful!");
	        } catch (IOException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	
	}

	class customerSignUp{

	//boolean is only return true or false
	 public boolean checkCustomerAccount(String userName) {
	        try {
	        	
	        	File f = new File(file2);
	            if (!f.exists()) return false;
	            
				//read the data form CustomerAccount.txt
	            BufferedReader br = new BufferedReader(new FileReader(f)); //read the data form CustomerAccount.txt
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                //check the userName is have or not
	                if (data.length > 0 && data[0].equals(userName)) {
	                br.close();
	                    return true;
	                }
	            }
	
	            br.close();
	
	        } //Get and print error message
	        	catch (IOException e) {
	            System.out.println("Error"+ e.getMessage());
	        }
	
	        return false;
	    }
	
	    public void saveCustomerAccount(Customer c) {
	        try {
	
	            BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true));
	
	            bw.write(c.getUserName() + "," + c.getPassword() + "," + c.getPhoneNum() + "," + c.getName() + "," + c.getEmail());
	            bw.newLine();
	            bw.close();
	
	            System.out.println("Sign up successful!");
	        } catch (IOException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	
	}
}