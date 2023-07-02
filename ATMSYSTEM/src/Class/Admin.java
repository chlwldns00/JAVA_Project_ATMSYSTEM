package Class;


import java.util.*;


public class Admin {
	private ATMSystem atm;
	private String AdminId = "root";
	private String AdminPassword = "root";
	
	public void setAtm(ATMSystem atm) {
	    	this.atm = atm;
	}
	
	
	public void FillMoney() {
		atm.getMoney().put("1만원", 1000);
	    atm.getMoney().put("5만원", 200);
	}
	
	public String getAdminId() {
		return AdminId;
	}
	
	public String getAdminPassword() {
		return AdminPassword;
	}
	
	
}