package payment;

/**
 * This class is solely to act as a credit card system
 * This is to simulate payment
 * @author Greg Lim
 * @since 2022-04-12
 */
public class CreditCardPayment {
	private String name; // default is null
	private String cardNumber;
	private String CSC;
	private String reciever;
	private String MM;
	private String YYYY;
	
	
	public CreditCardPayment(){}
	
	public void setName(String name){this.name = name;}
	public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
	public void setCSC(String CSC){this.CSC = CSC;}
	public void setReciever(String reciever){this.reciever = reciever;}
	public void setExpMonth(String month){this.MM = month;}
	public void setExpYear(String year){this.YYYY = year;}
	
	// this is for sumulation only
	// no validation done as assume to corrent
	public Boolean sendPayment() {
		return true; // assumes values are valid creditcard details
		
	}
	

}
