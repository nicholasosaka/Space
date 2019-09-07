import javax.swing.*;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Panel {
	private JFrame frame;

	Panel(String title, int width, int length){
		this.frame = new JFrame(title);
		this.frame.setSize(width, length);
		this.frame.pack();
		this.frame.setVisible(true);
	}
}
