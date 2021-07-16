package ru.tieto.HomeWork.sandbox;
// Класс Hippo

public class PointClass {
  public static void main(String[] args){
    Point p1 = new Point(0.3, 4);
    Point p2 = new Point(-3, 7);
    System.out.println("Расстояние между двумя точками " + (p2.distance(p1)));

    Point p3 = new Point(-9, 4);
    Point p4 = new Point(17, -6);
    System.out.println("Расстояние между двумя точками " + (p4.distance(p3)));
  }

  }
