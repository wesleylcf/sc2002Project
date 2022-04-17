package guest;

/**
* class of a credit card
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class CreditCard {

	private String cardNumber; // MUST BE 16 digit
	private String expiryDateMM;
	private String expiryDateYYYY;
	private String CVV; // MUST BE 3 digit
	public CreditCard(String cardNumber, String CVV, String mm, String yyyy){
		this.cardNumber = cardNumber; this.CVV = CVV; this.expiryDateMM = mm; this.expiryDateYYYY = yyyy;
	}

	/**
	 * getter method for card number
	 * @return
	 */
	public String getCardNumber(){
		return cardNumber;
	}

	/**
	 * getter method for cvv
	 * @return
	 */
	public String getCVV(){
		return CVV;
	}

	/**
	 * getter method for exp date
	 * @return
	 */
	public String getExpiryMM(){
		return expiryDateMM;
	}

	/**
	 * getter for exp year
	 * @return
	 */
	public String getExpiryYYYY(){
		return expiryDateYYYY;
	}

	/**
	 * setter method for updating card number
	 * @param cardNumber
	 */
	public void updateCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	/**
	 * setter method for exp month
	 * @param mm
	 */
	public void updateMM(String mm){
		this.expiryDateMM = mm;
	}

	/**
	 * setter method for year
	 * @param yy
	 */
	public void updateYYYY(String yy){
		this.expiryDateYYYY = yy;
	}

	/**
	 * setter method for CVV number
	 * @param cvv
	 */
	public void updateCVV(String cvv){
		this.CVV = cvv;
	}


}