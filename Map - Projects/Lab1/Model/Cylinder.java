package Model;
import java.lang.Math;


public class Cylinder implements GeometricObjects {

    private String name;
    private double height,radius;

    public Cylinder(String name,double height,double radius){
        this.name = name;
        this.height = height;
        this.radius = radius;
    }

    public double getVolume(){
        return Math.PI * Math.pow(this.radius,2) * this.height;
    }

    public String getName(){
        return this.name;
    }

    public void setHeight(double h){
        this.height = h;
    }

    public void setRadius(double r){
        this.radius = r;
    }

    public String toString(){
        return "The " + this.name + "volume is: " + this.getVolume();
    }
}
