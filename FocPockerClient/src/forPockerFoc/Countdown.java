package forPockerFoc;
 
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;


public class Countdown {
	private int seconds;
	private JTextField timerIcon;
	Timer timer;
	private final Font redOne = new Font("serif", Font.BOLD, 14); 
	
	
	public Countdown(int time) {
		this.seconds = time;
		timer = new Timer();
		timerIcon = new JTextField();
		timerIcon.setEditable(false);
		timerIcon.setColumns(8);
		timerIcon.setText("time befor fold" + seconds);
	
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (seconds > 0) {
					seconds--;
					timerIcon.setText("Time:     " + seconds);
					if(seconds < 32){
						timerIcon.setFont(redOne);
						timerIcon.setForeground(Color.RED);
					}
				}			
				if (seconds == 0) {
					timerIcon.setText("Time is over!");
					
					//TODO next Player
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}


	public JTextField getTimerIcon() {
		return timerIcon;
	}
	
	
}
