import java.util.*;

public class Ewallet extends Payment{
	
	//Data Field
	private String ewalletType;
	private String phoneNo;
	private double paidAmount;
	private String status; //successfully or unsuccessfully
	
	//construtors
	//no args
	public Ewallet(){
		super("",0,0.0,"");
		ewalletType = "";
		phoneNo = "";
		paidAmount = 0.0;
		status = ""; 
	}
	
	//with parameter
	public Ewallet(String referenceNo,int totalQty,double amount,String paymentDate,String ewalletType,String phoneNo,double paidAmount,String status){
		super(referenceNo,totalQty,amount,paymentDate);
		this.ewalletType = ewalletType;
		this.phoneNo = phoneNo;
		this.paidAmount = paidAmount;
		this.status = status;
	}
	
	//function/methods
	public void setewalletType(String ewalletType){
		this.ewalletType = ewalletType;
	}
	
	public void setphoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	
	public void setpaidAmount(double paidAmount){
		this.paidAmount = paidAmount;
	}
	
	public void setstatus(String status){
		this.status = status;
	}

	//get
	public String ewalletType(){
		return ewalletType;
	}
	
	public String getphoneNo(){
		return phoneNo;
	}
	
	public double getpaidAmount(){
		return paidAmount;
	}
	
	public String getstatus(){
		return status;
	}
	
	@Override
	public double calculatePayment(){
		if(paidAmount >= getamount()){
			status = "Successfully";
		}else{
			status = "Unsuccessfully (Insufficient Balance)";
		}
		
		return paidAmount - getamount(); // balance (usually 0 for e-wallet)
	}
	
	//E-wallet Number
	public String enterPhoneNo(){
		Scanner sc = new Scanner(System.in);
		
		boolean isValid = false;
				
		while(!isValid){
			System.out.print("\nEnter E-Wallet Phone Number (eg.0123456789):");
			String Num = sc.nextLine();
					
			if (Num.startsWith("01")&&(Num.length() >=10&&Num.length()<=11)&&Num.matches("\\d+")){
				isValid =true;
			}
			else{
				System.out.println("\nError:Invalid Phone Number.Please Try Again!");
			}
		}
		
		return "\nSending Request to"+phoneNo+"..."+"\nPaid Via E-wallet.Transaction Successful!";
			
	}
	
	//toString
	public String toString(){
		return super.toString()+"\n\nPayment Process:"+
			                    "\n----------------"+
			                    enterPhoneNo()+
			                    "\nPaid Amount:RM"+paidAmount+
			                    "\nBalance: RM"+calculatePayment()+
						        "\nPayment Process Status:"+
						        status;
	}
	
}