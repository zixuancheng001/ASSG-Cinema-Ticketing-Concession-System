import java.util.*;

public class Cash extends Payment{
	
	//Data Field
	private double paidAmount;
	private double balanceAmount;
	private String status; //successfully or unsuccessfully
	
	//construtors
	//no args
	public Cash(){
		super("",0,0.0,"");
		paidAmount = 0.0;
		balanceAmount = 0.0;
		status = ""; 
	}
	
	//with parameter
	public Cash(String referenceNo,int totalQty,double amount,String paymentDate,double paidAmount,double balanceAmount,String status){
		super(referenceNo,totalQty,amount,paymentDate);
		this.paidAmount = paidAmount;
		this.balanceAmount = balanceAmount;
		this.status = status;
	}
	
	//function/methods
	public void setpaidAmount(double paidAmount){
		this.paidAmount = paidAmount;
	}
	
	public void setbalanceAmount(double balanceAmount){
		this.balanceAmount = balanceAmount;
	}
	
	public void setstatus(String status){
		this.status = status;
	}

	//get
	public double getpaidAmount(){
		return paidAmount;
	}
	
	public double getbalanceAmount(){
		return balanceAmount;
	}
	
	public String getstatus(){
		return status;
	}
	
	//Cash calculation
	@Override
	public double calculatePayment(){
		balanceAmount = paidAmount - getamount();
		
		if(balanceAmount >= 0){
			status = "Sucessfully";
		}else{
			status = "Unsucessfully";
		}
		
		return balanceAmount;
	}
	//toString
	public String toString(){
		return super.toString()+"\n\nPayment Process:"+
			                    "\n----------------"+
			                    "\nPaid Amount:"+paidAmount+
			                    "\nBalance Amount:"+calculatePayment()+
						        "\nPayment Process Status:"+status;
	}
	
}