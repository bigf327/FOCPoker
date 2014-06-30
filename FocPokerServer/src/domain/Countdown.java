package domain;

import java.util.Timer;
import java.util.TimerTask;


public class Countdown {
	public static int seconds = 60;
	
	public Countdown() {
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println(seconds);
				if (seconds > 0) {
					seconds--;
				}
				if (seconds == 0) {
					System.exit(0);
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}
	public static void main(String[] args) {
		new Countdown();
	}
}
