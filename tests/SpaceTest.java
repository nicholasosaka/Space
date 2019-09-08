import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceTest {

	@Test
	void addShapeShouldAddShape() {
		Space space = new Space("addShapeShouldAddShape", 500, 500);
		Shape s = new Shape(1,100,100,100,100);
		space.addShape(s);
		assertEquals(1, space.getShapes().size());
		assertEquals(s, space.getShapes().get(0));
	}
}