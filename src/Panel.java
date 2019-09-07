import javax.swing.*;
import java.util.ArrayList;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Panel extends JPanel {
	private JFrame frame;
	private ArrayList<Shape> shapes;

	public Panel(JFrame frame) {
		this.frame = frame;
		this.shapes = new ArrayList<>();
	}
}
