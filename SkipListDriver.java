/** @author Aditya Viswanatham
 * 	Project 2: Skip List Implementation.
 * 	CS 3345.004
 * 
 */

package arv160730;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

//Driver program for skip list implementation.

public class SkipListDriver {
    public static void main(String[] args) throws FileNotFoundException {
	/*Scanner sc;
	if (args.length > 0) {
	    File file = new File(args[0]);
	    sc = new Scanner(file);
	} else {
	    sc = new Scanner(System.in);
	}
	String operation = "";
	long operand = 0;
	long result = 0;
	Long returnValue = null;
	SkipList<Long> skipList = new SkipList<>();
	// Initialize the timer
	Timer timer = new Timer();

	while (!((operation = sc.next()).equals("End"))) {
	    returnValue = null;
	    switch (operation) {
	    case "Add":
		operand = sc.nextLong();
		if(skipList.add(operand)) {
		    returnValue = 1L;
		}
		break;
	    case "Ceiling":
		operand = sc.nextLong();
		returnValue = skipList.ceiling(operand);
		break;
	    case "First":
		returnValue = skipList.first();
		break;
	    case "Get":
		int intOperand = sc.nextInt();
		returnValue = skipList.get(intOperand);
		break;
	    case "Last":
		returnValue = skipList.last();
		break;
	    case "Floor":
		operand = sc.nextLong();
		returnValue = skipList.floor(operand);
		break;
	    case "Remove":
		operand = sc.nextLong();
		if (skipList.remove(operand) != null) {
		    returnValue = 1L;
		}
		break;
	    case "Contains":
		operand = sc.nextLong();
		if (skipList.contains(operand)) {
		    returnValue = 1L;
		}
		break;
		}
	    if (returnValue != null) {
		result += returnValue;
	    }
	}

	// End Time
	timer.end();

	System.out.println(result);
	System.out.println(timer);
    }*/
    	
    /** Timer class for roughly calculating running time of programs
     *  @author Balaji Raghavachari
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

   /* public static class Timer {
	    long startTime, endTime, elapsedTime, memAvailable, memUsed;
	
	    public Timer() {
	   		startTime = System.currentTimeMillis();
	   	}
	
		public void start() {
		    startTime = System.currentTimeMillis();
		}
	
		public Timer end() {
		    endTime = System.currentTimeMillis();
		    elapsedTime = endTime-startTime;
		    memAvailable = Runtime.getRuntime().totalMemory();
		    memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		    return this;
		}
	
		public String toString() {
		    return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
		}
    	
    }*/
    	SkipList<Integer> my_list = new SkipList<>();
    	my_list.add(1);
    	my_list.add(2);
    	my_list.add(4);
    	my_list.add(2);
    	System.out.println(my_list.size());
    }

}
