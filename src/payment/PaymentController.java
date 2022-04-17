package payment;

import java.text.SimpleDateFormat;

import java.util.Scanner;

import roomsvc.*;
import systemTime.SystemTime;
import guest.*;
import hrps.HRPS;

import java.util.Date;
import java.util.Calendar;

/**
* Payment helper class  
* @author Greg Lim
* @version 1.0
* @since 2022-04-17
*/
public final class PaymentController {
	Scanner sc = HRPS.sc;
	OrderData orderData = HRPS.orderData;
	OrderModel orderModel;
	SystemTime sysTime = HRPS.time;
	
	public static final double WEEKEND_RATE_MUL = 1;
	public static final double WEEKDAY_RATE_MUL = 0.9;
	
	public static final int INVOICE_WIDTH = 40;
	
	public static final String THIN_LB = "-".repeat(INVOICE_WIDTH)+("\n"); 
	public static final String THICC_LB = "=".repeat(INVOICE_WIDTH)+("\n");
	
	public static final double TAX = 0.07; // 0.07
	
	private double percentDiscount;
	private double fixDiscount;
	private double priceAfterDiscount;
	private double priceAfterTax;
	
	/**
	 * constructor method for payment controller
	 * @param orderModel orderModel
	 */
	public PaymentController(OrderModel orderModel) {
		this.orderModel = orderModel;
		percentDiscount = 0;
		fixDiscount = 0;
		priceAfterDiscount = 0;
		priceAfterTax = 0;
	}
	
	/**
	 * method to make payment
	 * @param p
	 */
	public void makePayment(Payment p) {
		System.out.println("Paying for room: "+ p.getRoomId());
		double priceNoDiscount = p.calPrice();
		System.out.println("Total before discount: " + String.format("%.2f",priceNoDiscount));
		getDiscounts(p);
		priceAfterDiscount = priceNoDiscount*(100-percentDiscount)/100 - fixDiscount;
		System.out.println("Price after discount: " + String.format("%.2f", priceAfterDiscount));
		priceAfterTax = priceAfterDiscount *(1+TAX);
		System.out.println("Price after tax: " + String.format("%.2f", priceAfterTax));
		
		System.out.println("Payment by? \n\t1. Cash \n\t2. Card");
		int choice=0;
		choice = sc.nextInt();
		sc.nextLine();
		while(choice < 1 || choice > 2) {
			System.out.println("Invalid Choice");
			choice = sc.nextInt();
			sc.nextLine();
		}
		
		switch(choice) {
			case 1:
				cashHandler();
				break;
			case 2:
				cardHandler(p);
				break;
		}
	}
	
	/**
	 * method to get discount inputs and update discounts
	 * @param p
	 */
	private void getDiscounts(Payment p) {
		System.out.println("Percent discount (0 to skip): ");
		percentDiscount = sc.nextDouble();
		System.out.println("Fix discount (0 to skip): ");
		fixDiscount = sc.nextDouble();
		if(p.getPriceNoDiscount()*(100-percentDiscount)/100 - fixDiscount <0) {
			System.out.println("Total less than 0. Retrying...");
			getDiscounts(p);
		}
	}

	/**
	 * UI for card payments
	 * @param p
	 */
	private void cardHandler(Payment p) {
		CreditCard creditCard = p.getCreditCard();
		CreditCardPayment ccp = new CreditCardPayment();
		ccp.setReciever("Our hotel");
		System.out.println("Card Number: " + creditCard.getCardNumber());
		ccp.setCardNumber(creditCard.getCardNumber());
		System.out.println("CSC/CVV: " + creditCard.getCVV());
		ccp.setCSC(creditCard.getCVV());
		System.out.println("Expiry: " + creditCard.getExpiryMM()+"/"+creditCard.getExpiryYYYY());
		ccp.setExpMonth(creditCard.getExpiryMM());
		ccp.setExpYear(creditCard.getExpiryYYYY());
		boolean isPaid = ccp.sendPayment();
		
		if(isPaid) {
			System.out.println("Payment successful. Thank You");
		} else {
			System.out.println("Transection Faild. Pls try again");
		}
	}

	/**
	 * UI for payment by cash
	 */
	private void cashHandler() {	
		System.out.println("Please pay " + String.format("%.2f",priceAfterTax) + "\nPress enter to simulate payment...");
		sc.nextLine();
		System.out.println("Thank You");
	}
	
	/**
	 * print bill invoice
	 * @param p payment object
	 */
	public void printBillInvoice(Payment p) {
		Double rate = p.getRate();
		String invoice = "";
		invoice+=THICC_LB;
		invoice+=("INVOICE\n");
		invoice+=THIN_LB;
		invoice+=("Paid on: "+SystemTime.parseDate(sysTime.getDate())); //adsf
		invoice+=("\nStart Date:"+dateToStr(p.getStartDate()));
		invoice+=("\nEnd Date: "+dateToStr(p.getEndDate()));
		invoice+=("\nNumber weekdays: "+ Integer.toString(p.getNumWeekdays()));
		invoice+=("\nWeekday rate of "+(String.format("%.2f: ", rate*WEEKDAY_RATE_MUL))+String.format(" %.2f",rate*WEEKDAY_RATE_MUL*p.getNumWeekdays()));
		invoice+=("\nNumber weekends: "+ Integer.toString(p.getNumWeekends()));
		invoice+=("\nWeekend rate of "+(String.format("%.2f: ", rate*WEEKEND_RATE_MUL)) +String.format(" %.2f",rate*WEEKEND_RATE_MUL*p.getNumWeekends()));
		invoice+=("\nBefore Discount: "+String.format("%.2f", p.getPriceNoDiscount()));
		invoice+=("\nFix Discount: "+String.format("%.2f", fixDiscount));
		invoice+=("\nPercent Discount: "+String.format("%.2f", percentDiscount));
		invoice+=("\nAfter Discount: "+ String.format("%.2f", priceAfterDiscount));
		invoice+=("\nTax: "+String.format("%.2f", TAX*priceAfterDiscount));
		invoice+="\n";
		invoice+=(THIN_LB);
		invoice+=("Total Payable:"+String.format("%.2f\n", priceAfterTax));
		invoice+=THICC_LB;
		invoice+="\n";
		System.out.print(invoice);
		
		orderModel.printOrderItem(p.getRoomId());
	}
	
	/**
	 * print a summary of the payment
	 * does not include discounts and room services
	 * @param p
	 */
	public void printSummary(Payment p) {
		String summary = "";
		summary+=(THICC_LB);
		summary+=("BILL SUMMARY\n");
		summary+=THIN_LB;
		summary+=("Start Date:"+dateToStr(p.getStartDate()));
		summary+=("\nEnd Date:"+ dateToStr(p.getEndDate()));
		summary+=("\nNumber weekdays: "+ Integer.toString(p.getNumWeekdays()));
		summary+=("\nNumber weekends: "+ Integer.toString(p.getNumWeekends()));
		summary+=("\n"+THIN_LB);
		summary+=("Net Total:"+String.format("%.2f", p.getPriceNoDiscount()));
		summary+=("\n"+THICC_LB);
		System.out.println(summary);
		
	}
	
	/**
	 * date to string converter
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		return format;
	}
	
	/**
	 * get number of weekdays between 2 dates
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getWeekdays(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int weekDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++weekDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return weekDays;
	}
	
	/**
	 * calculate total number of days between 2 days
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDays(Date startDate, Date endDate) {
	    int daysdiff = 0;
	    long diff = endDate.getTime() - startDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
	    daysdiff = (int) diffDays;
	    return daysdiff;
	}
	
}
	