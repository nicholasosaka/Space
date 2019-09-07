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

	void addShape(Shape shape){
		if(shape != null) {
			this.shapes.add(shape);
		}
	}

	void removeShape(Shape shape){
		if(shape != null) {
			this.shapes.remove(shape);
		}
	}

	void removeAllShapes(){
		this.shapes.clear();
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
}
