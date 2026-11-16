////class name
public class Person{
	
	//Data fields
    private String password;
    private String phoneNum;
    private String name;
    private String email;

    ////Contructor no arg
    public Person(){
    	password = "";
    	phoneNum = "";
    	name = "";
    	email = "";	
    }
	
	////Contructor with parameters
	public Person(String password, String phoneNum, String name, String email){
        this.password = password;
        this.phoneNum = phoneNum;
        this.name = name;
        this.email = email;
    }
    
    //set methods
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	//get methods
	public String getPassword(){
		return password;
	}
	
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
}
