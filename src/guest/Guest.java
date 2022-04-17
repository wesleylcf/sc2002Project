package guest;

enum Gender { MALE, FEMALE, OTHERS }
enum Identity {LICENSE, PASSPORT, OTHERS}

/**
* guest class for attributes and methods for a guest
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class Guest {
	
	int id;
	// name of staff
	private String firstName;
	private String lastName;
	// country staff is from
	private String country;
	// nationality of staff
	private String nationality;
	// contact number of staff
	private String contact;

	private Gender gender;

	private Identity identity;

	private CreditCard cc;

	private String address;
	// Will have 

	/**
	 * constructor method for a guest
	 * @param id guest id
	 * @param firstName guest first name
	 * @param lastName guest last name
	 * @param country guest country
	 * @param nationality guest nationality
	 * @param contact guest contact number
	 * @param gender guest gender
	 * @param identity guest identity
	 * @param address guest home address
	 * @param cc guest credit card
	 */
	public Guest(int id, String firstName, String lastName, String country, String nationality, String contact, Gender gender, Identity identity, String address, CreditCard cc){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.nationality = nationality;
		this.contact = contact;
		this.gender = gender;
		this.identity = identity;
		this.address = address;
		this.cc = cc;
	}

	/**
	 * convert parse guest gender enum into a string
	 * @param gender
	 * @return
	 */
	public static String parseGenderType(Gender gender) {
		if(gender == Gender.MALE) return "MALE";
        else if(gender == Gender.FEMALE) return "FEMALE";
        else if(gender == Gender.OTHERS) return "OTHERS";
		return "Error";
	}

	/**
	 * Parse string and return gender enum
	 * @param genderString
	 * @return enum gender
	 */
	public static Gender parseGenderType(String genderString) {
		if(genderString.equals("MALE")) return Gender.MALE;
        else if(genderString.equals("FEMALE")) return Gender.FEMALE;
        else if(genderString.equals("OTHERS")) return Gender.OTHERS;
		return null;
	}

	/**
	 * convert identity enum into string
	 * @param identity
	 * @return
	 */
	public static String parseIdentityType(Identity identity) {
		if(identity == Identity.PASSPORT) return "PASSPORT";
        else if(identity == Identity.LICENSE) return "LICENSE";
        else if(identity == Identity.OTHERS) return "OTHERS";
		return null;
	}

	/**
	 * converts string to identity enum
	 * @param identityString
	 * @return
	 */
	public static Identity parseIdentityType(String identityString) {
		if(identityString.equals("PASSPORT")) return Identity.PASSPORT;
        else if(identityString.equals("LICENSE")) return Identity.LICENSE;
        else if(identityString.equals("OTHERS")) return Identity.OTHERS;
		return null;
	}

	/**
	 * setter method for first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * setter method for last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * setter method for name
	 * @param newName 
	 */
	public void setName(String newName) {
		this.firstName = lastName;
		this.lastName = newName;
	}

	/**
	 * setter method for guest country
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * setter method for guest nationality
	 * @param nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * setter method for guest contact
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * setter method for gender
	 * @param newGender
	 */
	public void setGender(Gender newGender) {
		this.gender = newGender;
	}

	/**
	 * setter method for guest identity
	 * @param identity
	 */
	public void setIdentity(Identity identity){
		this.identity = identity;
	}

	/**
	 * getter method for guest credit card number
	 * @return
	 */
	public String getCCNumber() {
		return cc.getCardNumber();
	}

	
	/**
	 * setter method for geust address
	 * @param address
	 */
	public void setAddress(String  address){
		this.address = address;
	}

	/**
	 * getter method for guest ID
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter method for guest first name
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * getter method of guest last name
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * getter method for guest country
	 * @return
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * getter method for guest nationality
	 * @return
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * getter method for guest contact number
	 * @return
	 */
	public String getContact() {
		return contact; 
	}

	/**
	 * getter method for guest gender enum
	 * @return
	 */
	public Gender getGender() {
		// maybe return enum instad of str
		return(gender);
	}

	/**
	 * getter method for guest Identity
	 * @return
	 */
	public Identity getIdentity(){
		return identity;
	}
	
	/**
	 * getter method for credit card object
	 * @return
	 */
	public CreditCard getCreditCard() {
		return cc;
	}
	
	/**
	 * getter for guest credit card number
	 * @return
	 */
	public String getCardNumber(){
		return cc.getCardNumber();
	}

	/**
	 * getter method for CVV
	 * @return
	 */
	public String getCVV(){
		return cc.getCVV();
	}

	/**
	 * getter method for credit car dexp
	 * @return
	 */
	public String getExpiryMM(){
		return cc.getExpiryMM();
	}

	/**
	 * getter mehtod for credit card exp
	 * @return
	 */
	public String getExpiryYYYY(){
		return cc.getExpiryYYYY();
	}

	/**
	 *  update credit card number
	 */
	public void updateCardNumber(String cardNumber){
		cc.updateCardNumber(cardNumber);
	}

	/**
	 * setter for cc month
	 * @param mm
	 */
	public void updateMM(String mm){
		cc.updateMM(mm);
	}

	/**
	 * setter method for year
	 * @param yy
	 */
	public void updateYYYY(String yy){
		cc.updateYYYY(yy);
	}

	/**
	 * setter method for cc cvv
	 * @param cvv
	 */
	public void updateCVV(String cvv){
		cc.updateCVV(cvv);
	}

	/**
	 * print guest full name
	 */
	public void print() {
		System.out.printf("Guest: %d, name: %s\n", id,firstName+" "+lastName);
	}
	

	/**
	 * get address
	 * @return
	 */
	public String getAddress(){
		return address;
	}

}