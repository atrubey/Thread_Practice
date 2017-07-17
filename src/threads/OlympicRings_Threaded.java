package threads;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	public static void main(String[] args) {
	// Make A Program that uses Threads and robots to draw the Olympic rings. One robot should draw one ring simultaneously with the other 4 robots.

	Robot r1 = new Robot(800, 300);
	Robot r2 = new Robot(700, 300);
	Robot r3 = new Robot(900, 300);
	Robot r4 = new Robot(850, 400);
	Robot r5 = new Robot(750, 400);

	r1.setSpeed(10);
	r2.setSpeed(10);
	r3.setSpeed(10);
	r4.setSpeed(10);
	r5.setSpeed(10);
	
	r1.penDown();
	r2.penDown();
	r3.penDown();
	r4.penDown();
	r5.penDown();
	
	Thread t1 = new Thread(()->drawCircle(r1));
	t1.start();
	Thread t2 = new Thread(()->drawCircle(r2));
	t2.start();
	Thread t3 = new Thread(()->drawCircle(r3));
	t3.start();
	Thread t4 = new Thread(()->drawCircle(r4));
	t4.start();
	Thread t5 = new Thread(()->drawCircle(r5));
	t5.start();
	
	try {
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	r1.penUp();
	r2.penUp();
	r3.penUp();
	r4.penUp();
	r5.penUp();
	
	r1.moveTo(0, 0);
	r2.moveTo(0, 0);
	r3.moveTo(0, 0);
	r4.moveTo(0, 0);
	r5.moveTo(0, 0);

	}

	public static void drawCircle(Robot r) {
		for (int i = 0; i<360; i++){
			r.move(1);
			r.turn(1);
		}
	}
	

	
}

