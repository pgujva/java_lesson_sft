package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 5;
    p1.y = 6;

    Point p2 = new Point();
    p2.x = 2;
    p2.y = 1;
    System.out.println("Расстояние между двумя точками = " + distance(p1, p2));

  }

  public double x;
  public double y;


  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * 2 + (p1.y - p2.y) * 2);
  }
}