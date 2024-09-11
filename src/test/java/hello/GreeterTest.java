package hello;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;

public class GreeterTest {// new instance for each @Test
  private Greeter greeter = new Greeter();// instance initializer??? field initialization  by variable initializer

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
    // assertTrue(5 == greeter.add(2, 2));// fails test
    // assertNotEquals(5, greeter.add(2, 2));// won't be tested at all
  }

}
