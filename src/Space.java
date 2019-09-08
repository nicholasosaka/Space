import javax.swing.*;

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
}
