import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

	SpaceGame spaceGame;
	Shape a,b;
	@Before
	public void setUp(){
		spaceGame = new SpaceGame("Unit testing", 500, 500);
		a = new Shape(100,100,100);
		b = new Shape(400,400,100);
		spaceGame.addShape(a);
		spaceGame.addShape(b);
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