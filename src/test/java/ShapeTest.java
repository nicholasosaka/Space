import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

	Space space;
	Shape a,b;
	@Before
	public void setUp(){
		space = new Space("Unit testing", 500, 500);
		a = new Shape(100,100,100);
		b = new Shape(400,400,100);
		space.addShape(a);
		space.addShape(b);
	}

	@Test
	public void intersectCircle() {
		Circle c1 = new Circle(250,250,100);
		Circle c2 = new Circle(250,250,50);

		assertFalse(a.intersect(b));
		assertTrue(c2.intersect(c1));
		assertTrue(c1.intersect(c2));
	}
}