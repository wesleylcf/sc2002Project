package reservation;
import java.util.Collection;
import java.util.HashMap;

import exception.NoReservationFoundException;

/**
* Reservation Data class which holds some Data Structure which supports(create/read/readAll/update/delete)
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class ReservationData {
	/**
	 * hash map of reservations
	 */
    private HashMap<String, Reservation> reservations;
    
    /**
     * Constructor of reservations data
     * Populate reservation date from reservationDM
     */
    public ReservationData() {
    	try {
    		reservations = ReservationDB.getInstance().populate();
    	} catch(Exception e) {
    		System.out.printf("error populating reservation data\n");
    	}
    	
    }

    /**
     * Constructor for reservations
     * @param reservations Initialize data passed from DB
     */
    public ReservationData(HashMap<String,Reservation> reservations) {
        this.reservations = reservations;
    }
    /**
    * Creates a reservation and adds to the data structure of reservations
    * @param rsv the Reservation to create
    */
    public void create(Reservation rsv) {
        reservations.put(rsv.getId(), rsv);
    }

    /**
    * Reads a reservation and returns it
    * @param rsv_id the Id of the Reservation to create
    * @return reservation associated with the Id passed in
    */
    public Reservation read(String rsv_id) throws NoReservationFoundException {
        if(reservations.containsKey(rsv_id)) return reservations.get(rsv_id);
        throw new NoReservationFoundException();
    }

    /**
    * Reads all reservations and returns it
    * @return HashMap of reservations
    */
    public Collection<Reservation> getCollection(){
        return reservations.values();
    }
    
    /**
     * reads all reservations
     * @return a HashMap of all reservations
     */
    public HashMap<String,Reservation> readAll() {
    	return this.reservations;
    }

    /**
    * Updates a reservation
    * @param id the Id of the Reservation to update
    * @param rsv the Reservation to update
    */
    public void update(String id, Reservation rsv) {
        reservations.replace(id, rsv);

    }

    /**
    * Deletes a reservation
    * @param rsv_id the Id of the Reservation to delete
    */
    public void delete(String rsv_id) {
        reservations.remove(rsv_id);
    }

 }
