package guest;

/**
* Guest view to display
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class GuestView {

	/**
	 * print guest main menu
	 */
	public static void printMainMenu() {
		System.out.println("(1) Create Guest");
		System.out.println("(2) Update Guest details");
		System.out.println("(3) Check Guest details");
		System.out.println("(4) Print All Guest");
		System.out.println("(5) QUIT ");
	}
	
	/**
	 * print details of a guest
	 * @param guests guest data object
	 * @param id guest id
	 */
	public static void printGuest(GuestData guests, int id) {
		System.out.println("===GUEST ID " + id + "===");
        System.out.println("Name:" + guests.fullName(id));
        System.out.println("Country:" + guests.getCountry(id));
        System.out.println("Nationality:" + guests.getNationality(id));
        System.out.println("Contact:" + guests.getContact(id));
        System.out.println("Gender:" + guests.getGender(id));
        System.out.println("Identity:" + guests.getIdentity(id));
        System.out.println("Address:" + guests.getAddress(id));
        System.out.println("Payment:");
        System.out.println("Card Number:" + guests.getCardNumber(id));
        System.out.println("Card CVV:" + guests.getCardCVV(id));
        System.out.println("Card Exiry (YYYY):" + guests.getCardExpiryYYYY(id));
        System.out.println("Card Expiry (MM):" + guests.getCardExpiryMM(id));
	}

}
