package barberProblem;

import java.util.*;
import java.util.concurrent.Semaphore;
//Barber Thread
public class Barber implements Runnable {
	Semaphore bchair;

	public Barber(Semaphore barberChair) {
		bchair = barberChair;
	}
	
	@Override
	public void run() {	
		try {
			//barber sleeps until woken by a customer
			synchronized(bchair) {
				if(bchair.hasQueuedThreads() == false) {
					bchair.acquire(1);
				}
			System.out.println("Barber is sleeping");
			wait(1);
			System.out.println("Barber is awake and has vacated his chair");
			bchair.release(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
