package trivia.gui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CountdownLabel extends JLabel {
	private Thread countdownThread;
	private boolean finished = false;
	
	public CountdownLabel() {

	}
	
	public CountdownLabel(final int length) {
		this.setText(length + "");
		countdownThread = new Thread() {
			@Override
			public void run() {
				for (int a = length; a >= 0; a--) {
					if (finished)
						break;
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
	
	public void stop() {
		finished = true;
	}
	
	public void event(int time) {
		
	}
}
