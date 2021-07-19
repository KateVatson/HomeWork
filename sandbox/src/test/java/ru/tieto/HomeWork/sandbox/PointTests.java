package ru.tieto.HomeWork.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  Point p1 = new Point(2.0, 9.0);
  Point p2 = new Point(5.0, -2);

  @Test
  public void TestHippo() {
    Assert.assertEquals(p2.distance(p1), 11.40175425099138);
  }


  @Test
  public void TestDistance() {
    Assert.assertEquals(p2.distance(p1), p1.distance(p2));
  }

}