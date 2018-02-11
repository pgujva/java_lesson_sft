package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NewPointTests {
  @Test

  public void testDistance1() {
    NewPoint p1 = new NewPoint(10.0, 6.0);
    NewPoint p2 = new NewPoint(5.0, 1.0);
    Assert.assertEquals(p1.distance(p2), 7.0710678118654755);
  }

  @Test

  public void testDistance2() {
    NewPoint p1 = new NewPoint(10.0, 6.0);
    NewPoint p2 = new NewPoint(5.0, 1.0);
    Assert.assertEquals(p2.distance(p1), p1.distance(p2));
  }

  @Test

  public void testDistance3() {
    NewPoint p1 = new NewPoint(-3.0, -5.0);
    NewPoint p2 = new NewPoint(-1.0, -5.0);
    Assert.assertEquals(p2.distance(p1), 2.0);
  }
}
