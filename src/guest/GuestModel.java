package guest;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.InvalidInputException;
import hrps.InputValidator;


/**
* guest model class that contains guest
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class GuestModel {
    Scanner sc = new Scanner(System.in);
    private GuestData guests;
    
    /**
     * guest model constructor
     * @param data
     */
    public GuestModel(GuestData data){
        guests = data;
    }
    
    /**
     * method to create a guest 
     * @return id of guest
     * @throws InvalidInputException
     */
    public Integer createGuest() throws InvalidInputException {
    		int id;
            String firstName;
            String lastName;
            String country;
            String nationality;
            String contact;
            Gender gender;
            Identity identity;
            String address;

            System.out.println("Enter new guest's id: ");
            // Verify id nvr use before
            id = InputValidator.validateGuestId(sc.nextInt());
            sc.nextLine();
            if(guests.exist(id)){
                System.out.println("ID already used!");
                return null;
            }

            // Get first & last Name
            System.out.println("Enter guest's first Name: ");
            firstName = InputValidator.validateString("first name",sc.nextLine());
            System.out.println("Enter guest's last Name: ");
            lastName = InputValidator.validateString("last name",sc.nextLine());


            // Get country
            System.out.println("Enter guest's country: ");
            country = InputValidator.validateString("Country",sc.nextLine());
            // Get nationality
            System.out.println("Enter guest's nationality: ");
            nationality = InputValidator.validateString("Nationality",sc.nextLine());
            // Get contact
            System.out.println("Enter guest's contact: ");
            contact = InputValidator.validateNumber("Contact", sc.nextLine());

            // Get gender
            System.out.println("Select guest's Gender: (1): Male, (2): Female (3): Others");
            int selection = InputValidator.validateIntRange(1, 3, "Gender option", sc.nextInt());
            switch(selection){
                case 1:{
                    gender = Gender.MALE;
                    break;
                }
                case 2:{
                    gender = Gender.FEMALE;
                    break;
                }
                default:
                    gender = Gender.OTHERS;
            }

            // Get gender
            System.out.println("Select guest's Identity: (1): License (2): Passport (3): Others");
            selection = InputValidator.validateIntRange(1, 3, "Gender option", sc.nextInt());
            sc.nextLine();
            switch(selection){
                case 1:{
                    identity = Identity.LICENSE;
                    break;
                }
                case 2:{
                    identity = Identity.PASSPORT;
                    break;
                }
                default:
                    identity = Identity.OTHERS;
            }

            
            String cardNumber, CVV, mm, yyyy;
            // Get CC, MAKE sure ccNumber is 16 digits long & contain only digits
            System.out.println("Enter 16-Digit Credit Card Number:");
            cardNumber = InputValidator.validateCCNumber(sc.nextLine());
            // Get CVV, MAKE sure CVV is 3 digits long & contain only digits
            System.out.println("Enter 3-Digit CVV:");
            CVV = InputValidator.validateCVV(sc.nextLine());
            // Get Credit card expiry MM and YYYY, contain only digit
            System.out.println("Enter year of expiry in YYYY:");
            yyyy = InputValidator.validateYear(sc.nextLine());
           
            System.out.println("Enter month of expiry in MM:");
            mm = InputValidator.validateMonth(sc.nextLine());
            
            System.out.println("Enter the address: ");
            address = InputValidator.validateAddress(sc.nextLine());

            guests.createGuest(id, firstName, lastName, country, nationality, contact, gender, identity, address, new CreditCard(cardNumber, CVV, mm, yyyy));
            return id;
        
    }

    /**
     * method to update guest details
     * @throws InvalidInputException
     * @throws InputMismatchException
     */
    public void updateGuestDetails() throws InvalidInputException, InputMismatchException{
        // Check if id is valid
    	System.out.println("Enter ID of guest to update");
    	int id = InputValidator.validateGuestId(sc.nextInt());
        if(!guests.exist(id)){
            System.out.println("This guest does not exist!");
            return;
        }
        // Start
        System.out.println("Select detail to update: (1) Name (2) Country (3) Nationality (4) Contact (5) Gender (6) Identity (7) Address (8) Credit Card Details");
        int c = InputValidator.validateIntRange(1, 8, "Update option", sc.nextInt());
        sc.nextLine();
        switch(c){
            case 1:{
                // Take in new first and last name
                String newFirst, newLast;
                System.out.println("Enter the new First Name: ");
                newFirst = InputValidator.validateString("first name",sc.nextLine());
                System.out.println("Enter the new Last Name: ");
                newLast = InputValidator.validateString("last name",sc.nextLine());
                // Update
                guests.updateName(id, newFirst, newLast);
                System.out.println("Name is updated to " + guests.getName(id));
                break;
            }
            case 2:{
                String newCountry;
                sc.nextLine();
                System.out.println("Enter the new Country: ");
                newCountry = InputValidator.validateString("country",sc.nextLine());
                guests.updateCountry(id, newCountry);
                System.out.println("Country is updated to " + guests.getCountry(id));
                break;
            }
            case 3:{
                String newNationality;
                sc.nextLine();
                System.out.println("Enter the new Nationality: ");
                newNationality = InputValidator.validateString("nationality",sc.nextLine());
                guests.updateNationality(id, newNationality);
                System.out.println("Nationality is updated to " + guests.getNationality(id));
                break;
            }
            case 4:{
                String newContact;
                sc.nextLine();
                System.out.println("Enter the new Contact Number: ");
                newContact = InputValidator.validateNumber("contact",sc.nextLine());
                guests.updateContact(id, newContact);
                System.out.println("Contact is updated to " + guests.getContact(id));
                break;
            }
            case 5:{
                int selection;
                System.out.println("Select new Gender: (1): Male, (2): Female (3): Others");
                selection = InputValidator.validateIntRange(1,3,"Gender option",sc.nextInt());
                sc.nextLine();
                switch(selection){
                    case 1:{
                        guests.updateGender(id, "MALE");
                        break;
                    }
                    case 2:{
                        guests.updateGender(id, "FEMALE");
                        break;
                    }
                    default:
                        guests.updateGender(id, "OTHERS");
                }
                System.out.println("Gender is updated to " + guests.getGender(id));
                break;
            }
            case 6:{
                int selection;
                System.out.println("Select updated Identity: (1): License (2): Passport (3): Others");
                selection = InputValidator.validateIntRange(1, 3, "update identity option", sc.nextInt());
                sc.nextLine();
                switch(selection){
                    case 1:{
                        guests.updateIdentity(id, "LICENSE");
                        break;
                    }
                    case 2:{
                        guests.updateIdentity(id, "PASSPORT");
                        break;
                    }
                    default:
                        guests.updateIdentity(id, "OTHERS");
                }
                System.out.println("Identity is updated to " + guests.getIdentity(id));
                break;
            }
            case 7:{
                String newAddress;
                //sc.nextLine();
                System.out.println("Enter the new Address ");
                newAddress = InputValidator.validateAddress(sc.nextLine());
                guests.updateAddress(id, newAddress);
                System.out.println("Address is updated to " + guests.getAddress(id));
                break;
            }
            case 8:{
                String cardNumber, CVV, mm, yyyy;
                // Get CC, MAKE sure ccNumber is 16 digits long & contain only digits
                System.out.println("Enter 16-Digit Credit Card Number:");
                cardNumber = InputValidator.validateCCNumber(sc.nextLine());
                // Get CVV, MAKE sure CVV is 3 digits long & contain only digits
                System.out.println("Enter 3-Digit CVV:");
                CVV = InputValidator.validateCVV(sc.nextLine());
                // Get Credit card expiry MM and YYYY, contain only digit
                System.out.println("Enter year of expiry in YYYY:");
                yyyy = InputValidator.validateYear(sc.nextLine());
                System.out.println("Enter month of expiry in MM:");
                mm = InputValidator.validateMonth(sc.nextLine());
                guests.updateCC(id,cardNumber, CVV, mm, yyyy);
                System.out.println("16-Digit Credit Card Number is updated to " + guests.getCardNumber(id));
                System.out.println("3-Digit CVV Number is updated to " + guests.getCardCVV(id));
                System.out.println("Year of expiry is updated to " + guests.getCardExpiryYYYY(id));
                System.out.println("Month of expiry is updated to " + guests.getCardExpiryMM(id));
            }
        }   
    }

    /**
     * method to print guest detail
     * @param id
     */
    public void printGuest(int id){
        // Returns all detail of the guest (if exist)
        if(!guests.exist(id)){
            System.out.println("This guest does not exist!");
            return;
        }

        GuestView.printGuest(guests, id);
 
    }

    /**
     * print all geust, names and details
     */
    public void getAllGuest(){
        System.out.println("TOTAL GUEST COUNT: " + guests.guestCount());
        HashMap<Integer, Guest> data = guests.getGuestHashMap();
        for(int i : data.keySet()){
            System.out.printf(i + ": " + data.get(i).getFirstName() + " " + data.get(i).getLastName() );
            System.out.println();
        }
        
    }
    
 
    /**
     * Search all guest for guest for given ID
     * @param id
     * @return
     */
    public Guest search(String id) {
        Integer requiredId = Integer.parseInt(id);
        return guests.readOne(requiredId);
    }
}
