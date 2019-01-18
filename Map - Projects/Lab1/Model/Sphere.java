package Model;
import java.lang.Math;

public class Sphere implements GeometricObjects {

    private String name;
    private double radius;

    public Sphere(String name, double radius){
        this.name = name;
        this.radius = radius;
    }

    public String getName(){
        return this.name;
    }

    public double getVolume(){
        return ( 4.0 / 3.0 ) * Math.PI * Math.pow( this.radius, 3 );
    }

    public void setRadius(double r){
        this.radius = r;
    }

    public String toString(){
        return "The " + this.name + "volume is: " + this.getVolume();
    }
}
