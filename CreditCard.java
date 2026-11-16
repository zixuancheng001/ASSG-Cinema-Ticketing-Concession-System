import java.util.*;

public class CreditCard extends Payment{
	
	//Data Field
	private String creditNo;
	private double paidAmount;
	private String status; //successfully or unsuccessfully
	
	//construtors
	//no args
	public CreditCard(){
		super("",0,0.0,"");
		creditNo = "";
		paidAmount = 0.0;
		status = ""; 
	}
	
	//with parameter
	public CreditCard(String referenceNo,int totalQty,double amount,String paymentDate,String creditNo,double paidAmount,String status){
		super(referenceNo,totalQty,amount,paymentDate);
		this.creditNo = creditNo;
		this.paidAmount = paidAmount;
		this.status = status;
	}
	
	//function/methods
	public void setcreditNo(String creditNo){
		this.creditNo = creditNo;
	}
	
	public void setpaidAmount(double paidAmount){
		this.paidAmount = paidAmount;
	}
	
	public void setstatus(String status){
		this.status = status;
	}

	//get
	public String getcreditNo(){
		return creditNo;
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
			status = "Unsuccessfully (Transaction Failed)";
		}
		
		return paidAmount - getamount();
	}
	
	//CreditCard Number
	public String enterCreditCardNo(){
		Scanner sc = new Scanner(System.in);
		
		boolean isValid =false;
				
		while(!isValid){
			System.out.println("\nEnter 16-digit Credit Number:");
			String Num = sc.nextLine();
			if (Num.length()==16&& Num.matches("\\d+")){
				isValid =true;
			}
			else{
				System.out.println("\nError:Invalid format.Please enter 16 digit number!");
			}
		}
			return "\nCredit Number:"+creditNo+"\nVerifying card"+creditNo+"..."+"\nPaid via Credit Card.Transaction Successful!";
			
	}
	
	//toString
	public String toString(){
		return super.toString()+"\n\nPayment Process:"+
			                    "\n----------------"+
			                    enterCreditCardNo()+
			                    "\nPaid Amount:RM"+paidAmount+
			                    "\nBalance: RM"+calculatePayment()+
						        "\nPayment Process Status:"+
						        status;
	}
	
}