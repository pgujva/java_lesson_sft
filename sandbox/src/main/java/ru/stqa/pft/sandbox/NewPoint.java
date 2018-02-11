package ru.stqa.pft.sandbox;

public class NewPoint {
  public static void main(String[] args) {

    NewPoint p1 = new NewPoint(-3.0, -5.0);
    NewPoint p2 = new NewPoint(-1.0, -5.0);


    System.out.println("Расстояние между двумя точками = " + p1.distance(p2));
    System.out.println("Расстояние между двумя точками = " + p2.distance(p1));
  }

  public double x;
  public double y;

  public NewPoint(double x, double y) {

    this.x = x;
    this.y = y;
  }

  public double distance(NewPoint p) {
    return Math.sqrt(Math.abs(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2)));
  }
}