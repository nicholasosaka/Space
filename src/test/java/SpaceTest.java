import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceTest {
	Space space;

	@Before
	public void initialize(){
		space = new Space("Unit Testing", 500, 500);
	}

	@Test
	public void addShapeShouldAddShape() {
		Shape s = new Shape(1,100,100,100,100);
		space.addShape(s);
		assertEquals(s, space.getShapes().get(0));

		space.addShape(1,100,100,100,100);
		assertEquals(2, space.getShapes().size());
	}

	@Test
	public void addCircle() {
		Shape s = new Shape(4,500,250,250,100);
		space.addShape(s);
		assertEquals(4, space.getShapes().get(0).getID());
	}

	@Test
	public void addTriangle() {
		Shape s = new Shape(0,500,250,250,100);
		space.addShape(s);
		assertEquals(0, space.getShapes().get(0).getID());
	}

	@Test
	public void addRectangle() {
		Shape s = new Shape(2,500,250,250,100);
		space.addShape(s);
		assertEquals(2, space.getShapes().get(0).getID());
	}

	@Test
	public void multipleShapesShouldPopulateProperly() {
		for(int i = 0; i < 5; i++) {
			space.addShape(i % 4, 100, 100, 100, 100);
		}

		assertEquals(5, space.getShapes().size());
	}

	@Test
	public void pointCheckForTriangle(){
		space.addShape(0,250,250,100,100);

		assertTrue(space.isPointInSpace(250, 250));

		assertFalse(space.isPointInSpace(100, 100));
	}

	@Test
	public void pointCheckForCircle(){
		space.addShape(250, 250, 100);

		assertTrue(space.isPointInSpace(250, 250));

		assertFalse(space.isPointInSpace(100, 100));
	}

	@Test
	public void pointCheckForRectangle(){
		space.addShape(2,250, 250, 100,100);

		assertTrue(space.isPointInSpace(250, 250));

		assertFalse(space.isPointInSpace(100, 100));
	}

	@Test
	public void pointInSpaceTrue(){
		Shape p = new Shape(3, 250, 250, 5);
		space.addShape(250, 250, 100);

		assertTrue(space.isPointInSpace(250, 250));

		assertTrue(space.isPointInSpace(p));
	}

	@Test
	public void pointInSpaceFalse(){
		Shape p = new Shape(3,5, 5, 5);
		space.addShape(250, 250, 50);

		assertFalse(space.isPointInSpace(455, 455));

		assertFalse(space.isPointInSpace(p));
	}

	@Test
	public void checkSpaceContents(){
		Shape s = new Shape(3, 250, 250, 100);
		Shape q = new Shape(3, 250, 250, 100);
		space.addShape(s);

		assertTrue(space.isShapeInSpace(s));
		assertTrue(space.isShapeInSpace(q));
	}
}