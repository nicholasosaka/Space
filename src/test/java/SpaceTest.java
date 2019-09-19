import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpaceTest {

	@Test
	public void addShapeShouldAddShape() {
		Space space = new Space("addShapeShouldAddShape", 500, 500);

		Shape s = new Shape(1,100,100,100,100);
		space.addShape(s);
		assertEquals(s, space.getShapes().get(0));

		space.addShape(1,100,100,100,100);
		assertEquals(2, space.getShapes().size());
	}

	@Test
	public void addCircle() {
		Space space = new Space("addCircle", 500, 500);

		Shape s = new Shape(4,500,250,250,100);
		space.addShape(s);
		assertEquals(4, space.getShapes().get(0).getID());
	}

	@Test
	public void addTriangle() {
		Space space = new Space("addTriangle", 500, 500);

		Shape s = new Shape(0,500,250,250,100);
		space.addShape(s);
		assertEquals(0, space.getShapes().get(0).getID());
	}

	@Test
	public void addRectangle() {
		Space space = new Space("addCircle", 500, 500);

		Shape s = new Shape(2,500,250,250,100);
		space.addShape(s);
		assertEquals(2, space.getShapes().get(0).getID());
	}

	@Test
	public void multipleShapesShouldPopulateProperly() {
		Space space = new Space("multipleShapesShouldPopulateProperly", 500,500);

		for(int i = 0; i < 5; i++) {
			space.addShape(i % 4, 100, 100, 100, 100);
		}

		assertEquals(5, space.getShapes().size());

	}

	@Test
	public void pointCheckForTriangle(){
		Space space = new Space("pointCheckForTriangle", 500, 500);

		space.addShape(0,250,250,100,100);

		assertEquals(true, space.isPointInSpace(250,250));

		assertEquals(false, space.isPointInSpace(100,100));
	}

	@Test
	public void pointCheckForCircle(){
		Space space = new Space("pointCheckForCircle", 500, 500);

		space.addShape(250, 250, 100);

		assertEquals(true, space.isPointInSpace(250,250));

		assertEquals(false, space.isPointInSpace(100,100));
	}

	@Test
	public void pointCheckForRectangle(){
		Space space = new Space("pointCheckForRectangle", 500, 500);

		space.addShape(2,250, 250, 100,100);

		assertEquals(true, space.isPointInSpace(250,250));

		assertEquals(false, space.isPointInSpace(100,100));
	}

	@Test
	public void pointInSpaceTrue(){
		Space space = new Space("pointInSpaceTrue", 500, 500);
		Shape p = new Shape(3, 500, 500, 250, 250, 5);
		space.addShape(250, 250, 100);

		assertEquals(true, space.isPointInSpace(250,250));

		assertEquals(true, space.isPointInSpace(p));
	}

	@Test
	public void pointInSpaceFalse(){
		Space space = new Space("pointInSpaceFalse", 500, 500);
		Shape p = new Shape(3, 500, 500, 5, 5, 5);
		space.addShape(250, 250, 50);

		assertEquals(false, space.isPointInSpace(455,455));

		assertEquals(false, space.isPointInSpace(p));
	}
}