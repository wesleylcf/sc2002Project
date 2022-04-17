package payment;

import java.util.ArrayList;
import java.util.Date;

import guest.*;
import reservation.*;
import room.*;
import roomsvc.*;
import systemTime.*;
import hrps.*;

/**
 * 
 * @author Greg Lim
 * @version 1.0
 * @since 2022-04-17
 */
public class Payment {
	
	public static final double WEEKEND_RATE_MUL = 1;
	public static final double WEEKDAY_RATE_MUL = 0.9;
	
	private String roomID;
	private double priceNoDiscount=0;
	private Room room;
	private Guest guest;
	private CreditCard creditCard;
	
	private ArrayList<OrderItem> roomServices;
	private Date startDate;
	private Date endDate;
	private int numWeekdays;
	private int numWeekends;
	private int totalDays;

	private double rate=0;
	
	/**
	 * create a payment object to check
	 * @param booking booking object
	 * @param guestModel guest model
	 * @param roomModel room model
	 */
	public Payment(Booking booking, GuestModel guestModel, RoomModel roomModel) {
		roomID = booking.getRoomId();
		roomServices = HRPS.orderData.getOrderByRoomId(roomID);
		room = roomModel.getRoom(roomID);
		startDate = booking.getCheckIn();
		endDate = booking.getCheckOut();
		totalDays = SystemTime.getDays(startDate, endDate);
		numWeekdays = SystemTime.getWeekdays(startDate, endDate)+1;
		numWeekends = totalDays - numWeekdays;
		rate = room.getRoomPrice();
		guest = guestModel.search(booking.getGuestIDs().get(0));
		creditCard = guest.getCreditCard();
		priceNoDiscount = calPrice();
	}
	
	
	/**
	 * calculate the price before discount
	 * @return price before discount
	 */
	protected double calPrice() {
		double sum = 0;
		sum += numWeekdays * WEEKDAY_RATE_MUL * rate;
		sum += numWeekends * WEEKEND_RATE_MUL * rate;
		
		if(roomServices != null) {
			for(OrderItem item : roomServices) {
				sum += item.getPrice();
			}
		}
		return sum;
	}
	
	/**
	 * getter method for room id
	 * @return
	 */
	public String getRoomId() {
		return this.roomID;
	}
	
	/**
	 * getter method for credit card
	 */
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	
	/**
	 * getter method for price before discount
	 * @return
	 */
	public double getPriceNoDiscount() {
		return this.priceNoDiscount;
	}
	
	/**
	 * getter method for start date
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * getter method for end date
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * getter method for rate
	 * @return
	 */
	public double getRate() {
		return rate;
	}
	
	/**
	 * getter method for number of weekends
	 * @return
	 */
	public Integer getNumWeekends() {
		return numWeekends;
	}
	
	/**
	 * getter method for number of weekdays
	 * @return
	 */
	public Integer getNumWeekdays() {
		return numWeekdays;
	}
	
}
