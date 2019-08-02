// Mark Stewart 2005 
// solution to Lab 4 - bathroom problem.
// See Lab4Instructions.txt for directions

//args[0] = delay time for output
//args[1] = minutes of flight
//args[2] = number of passengers
//args[3] = 2+x minutes spent in bathroom

import java.util.Scanner;

public class Bathroom
{
	final static int VERSION1=-999;
	static int sum=0,time=0,totalUsers=0;
	static int t1=0, t2=0; //timers for each bathroom visit
	static int maxStay, delay;
	static QueueDisplay q;

	public static void main(String[] args) {
		if (args.length<4) {
			System.out.println("Oops, your need to provide 4 arguements. GUI delay time, minutes of flight, number of passengers and maxStay in restroom");
			System.exit(0);
		}
		int cutoff= getVersion();
		
		maxStay = Integer.parseInt(args[3]);
		int numPassengers=Integer.parseInt(args[2]);
		int flightTime = Integer.parseInt(args[1]);
		delay = Integer.parseInt(args[0]);

		BathroomQueue line1 = new BathroomQueue(); //make the two lines for the two bathrooms
		BathroomQueue line2 = new BathroomQueue();
		int needToGo=0;
		double pToGo = .008; //the probability that each passenger will need to use the restroom per unit of time (minute)

		q = new QueueDisplay(); //this is a GUI to display the Q in realtime
		q.setVisible(true);

		while (time<flightTime || !(line1.isEmpty()&&line2.isEmpty())) {
			time++; //count time in minutes
			//add new passengers to queues. When flight lands - empty queues and add to times
			if (time<flightTime) {
				for (int i=0;i<(numPassengers-line1.length-line2.length);i++) //people in line can't get in line again
					if (Math.random()<pToGo) needToGo++;
				totalUsers += needToGo;
			}
			while (needToGo > 0) {
				int timeNeeded = (int)(maxStay*Math.random())+2;
				//note very little is different between the two versions.
				if (cutoff == VERSION1) { 	//choose based on shorter line
					if (line1.length > line2.length ) line2.enqueue(time, timeNeeded);
					else line1.enqueue(time, timeNeeded);
				}
				else { 				//slower people to line 2, faster people to line 1
					if (timeNeeded > cutoff ) line2.enqueue(time, timeNeeded);
					else line1.enqueue(time, timeNeeded);
				}
				needToGo--;
			}

			//remove people from restrooms and advance lines
			t1=advanceQueue(line1,t1); //modifies sum
			t2=advanceQueue(line2,t2);
			t1--;t2--;
			q.update(line1,line2);
			MarkSystem.wait(delay);
		}
		
		System.out.println("Total Users:"+totalUsers);
		System.out.println("Total wait time:"+sum);
		System.out.println("Average Wait:"+(double)sum/(double)totalUsers);
	}
	static int advanceQueue(BathroomQueue bq, int timer) {
		if (timer<=0 && !bq.isEmpty() ) 
		{
			BathroomQueue.Node visit = bq.dequeue();
			sum += time - visit.timeEntered; //wait time of person leaving bathroom added to total
			timer = visit.timeNeeded; 		//next person in line goes into bathroom	
		}
		return timer;
	}

	static int getVersion() {
		int cutOff=VERSION1;	
		System.out.println("Normal operation (N) or with Fast and Slow Line (F)?"); 
		Scanner keyboard= new Scanner(System.in); String answer = keyboard.nextLine();
		if (answer.equals("F")) {System.out.println("What is the length of bathroom visit that requires you visit the slow line?"); cutOff = keyboard.nextInt();}
		return cutOff;
	}	 

}
