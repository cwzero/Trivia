package trivia.gui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CountdownLabel extends JLabel {
	public CountdownLabel() {

	}
	
	public CountdownLabel(final int length) {
		this.setText(length + "");
		Thread countdownThread = new Thread() {
			public void run() {
				for (int a = length; a >= 0; a--) {
					CountdownLabel.this.setText(a + "");
					event(a);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		countdownThread.start();
	}
	
	public void event(int time) {
		
	}
}
