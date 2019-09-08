import javax.swing.*;
import java.awt.event.WindowEvent;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Space {
	private JFrame frame;
	private Panel panel;

	Space(String title, int width, int length){
		this.frame = new JFrame(title);
		this.frame.setSize(width, length);
		this.panel = new Panel(this.frame);
		this.frame.add(this.panel);
		this.frame.repaint();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	public void exit() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

}
