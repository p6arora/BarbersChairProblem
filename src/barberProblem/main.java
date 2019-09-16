package barberProblem;
/*	Solve the classic Barber Problem which emulates a barber shop. Barber contains 1 barber chair 
 * 	where he sleeps & 4 customer chairs where incoming customers come and wait till their turn to
 * 	go on the barber's chair. Barber will sleep until a customer arrives. It will then service that 
 * 	customer. If another customer arrives while the barber is servicing that customer, it will wait
 * 	in the chair. If all customer chairs are filled, it will leave
 * 
 */
import java.util.*;
import java.util.concurrent.Semaphore;

public class main {

	//barber chair
	public static Semaphore barberChair = new Semaphore(1);
	
	// shared resource for waiting customers 
	// 4 available chairs
	public static Semaphore chairs = new Semaphore(4);

	public static void main(String[] args) {
		

		try {
			

		// thread for the barber & customers
		Thread barber = new Thread(new Barber(barberChair));
		Thread C1 = new Thread(new Customer(barberChair, chairs, "A"));
		Thread C2 = new Thread(new Customer(barberChair, chairs, "B"));
		Thread C3 = new Thread(new Customer(barberChair, chairs, "C"));
		Thread C4 = new Thread(new Customer(barberChair, chairs, "D"));
		Thread C5 = new Thread(new Customer(barberChair, chairs, "E"));
		Thread C6 = new Thread(new Customer(barberChair, chairs, "F"));
		Thread C7 = new Thread(new Customer(barberChair, chairs, "G"));
		
		barber.setName("BarberThread");
		barber.start();
		C1.start();
		C2.start();
		C3.start();
		C4.start();
		C5.start();
		C5.start();
		C7.start();
		Thread.currentThread().destroy();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}

	}

}
