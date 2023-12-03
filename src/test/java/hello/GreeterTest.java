package hello;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;

public class GreeterTest {
    private Greeter greeter = new Greeter();// instance initializer???

    @Test
    public void testGetHello() {
        assertThat(greeter.getHello(), containsString("Hello!"));
    }

    @Test
    public void testGetHello2() {
        assertEquals("Hello!", greeter.getHello());
    }

    @Test
    public void testTwoPlusTwoEqualsFour() {
        Greeter greeter2 = new Greeter();
        assertEquals(4, greeter2.add(2, 2));
        assertNotEquals(0, greeter2.add(2, 2));
        assertTrue(4 == greeter2.add(2,2));
    }

}
