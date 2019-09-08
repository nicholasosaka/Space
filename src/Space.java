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

	public void addShape(Shape shape){
		if(shape != null){
			this.panel.addShape(shape);
		}
	}

	public void addShape(int id, int locX, int locY, int width, int height){
		Shape s = new Shape(id, this.panel.getHeight(), this.panel.getWidth(), locX, locY, width, height);
		this.panel.addShape(s);
	}

	public void addShape(int id, int locX, int locY, int radius){
		Shape s = new Shape(id, this.panel.getHeight(), this.panel.getWidth(), locX, locY, radius);
		this.panel.addShape(s);
	}

	public boolean isPointInShapes(int x, int y){
		for(Shape s : this.panel.getShapes()){
			if(s.isPointInShape(x,y)){
				return true;
			}
		}
		return false;
	}

	public void exit() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

}
