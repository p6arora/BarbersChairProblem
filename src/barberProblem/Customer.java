package barberProblem;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
	Random random = new Random();
	Semaphore bchair;
	Semaphore chairs;
	String name;


	public Customer(Semaphore barberChair, Semaphore chairs, String name) {
		bchair = barberChair;
		this.chairs = chairs;
		this.name = name;
	}


	@Override
	public void run() {
		try {
			//customer waits 
			Thread.sleep(random.nextInt(1000));
			
			//checks the status of the customer seats
			System.out.println("Customer " + name + " is trying to acquire a seating chair...");
			boolean chairsAvailable = chairs.tryAcquire(1000, TimeUnit.SECONDS);
			
			//TODO: Deadlock over 2 semaphores
			//TODO: Notify() isn't waking up the barber thread 
			
			//if seats free, grabs a chair 
			if(chairsAvailable) {
				System.out.println("Customer " + name + " almost has got a chair");
				chairs.acquire(1);
				System.out.println("Customer " + name + " has acquired a seating chair...");
				System.out.println(name + ": Number of Threads waiting to get a chair: " + chairs.getQueueLength());
				System.out.println(name + ": Number of customer seats in shop : " + chairs.availablePermits());
			}
			//If seat not free, leaves
			else {
				System.out.println("Customer " + name + " could not find a seat. Has Left");
				System.out.println(name + ": Number of Active Threads: " + Thread.activeCount());
				Thread.currentThread().stop();
			}
			
				//checks the status of the barber (if he is sleeping or working) 
				System.out.println(name + ": Trying to wake the Barber");
				bchair.notify();
				//tries to get into barber's chair
				//if barber busy, waits
				if(bchair.tryAcquire(1000, TimeUnit.SECONDS)) {
					System.out.println("Trying to get into Barber chair");
					chairs.release(1);
					bchair.acquire(1);
					System.out.println("Customer " + name +" has gotten into Barber's chair");
					Thread.sleep(1000);
					System.out.println("Customer " + name + "  is done. Customer has left Barber's chair");
					bchair.release(1);
				}
				System.out.println("Haircut finished. Customer " + name + " has left");
				Thread.currentThread().stop();;
				
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
