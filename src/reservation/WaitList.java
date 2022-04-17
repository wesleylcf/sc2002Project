package reservation;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
WaitList class which holds failed reservations, which may be confirmed if that room type becomes available
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class WaitList {

	//HashMap of Key: roomType, Value: Queue of reservationID
	private HashMap<String, Queue<String>> waitList = new HashMap<String, Queue<String>>();
	
	public WaitList() {}
	
	/**
	 * Creates a waitlist(Queue) for a roomType
	 * @param roomType roomtype
	 */
	public void setWaitListQ(String roomType) {
		//Need to first check whether there is existing Queue of roomType
		//as Key that is already created
		if(waitList.get(roomType)==null)
			waitList.put(roomType, new PriorityQueue<>());
		//Else, no need to create a new queue
	}
	
	/**
	 * adds a booking to wait list
	 * @param roomType roomtype
	 * @param reservationID booking Id to add to waitlist
	 */
	public void add(String roomType, String reservationID) {
		setWaitListQ(roomType);
		waitList.get(roomType).add(reservationID);
	}
	
	/**
	 * Returns the first booking in waitlist for a roomtype
	 * @param roomType roomtype
	 */
	public String getFirst(String roomType) {
		try{
			Queue<String> q = waitList.get(roomType);
			if(q == null) return null;
			return  q.remove();			
		} catch(NoSuchElementException e){
			System.out.printf("%s\n", e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * print a list of guest on waiting list
	 */
	public void printWaitList() {
		for(String roomType : waitList.keySet()) {
			System.out.println("----------------");
			System.out.println(roomType+":");
			for(String rsv_id : waitList.get(roomType)) {
				System.out.printf("%s, ",rsv_id);
			}
			System.out.println("----------------");
			
		}
	}
	
}


