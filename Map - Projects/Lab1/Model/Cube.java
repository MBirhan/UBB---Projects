package Model;
import java.lang.Math;

public class Cube implements GeometricObjects {

    private String name;
    private double length;

    public Cube(String name,double length){
        this.name = name;
        this.length = length;
    }

    public double getVolume(){
        return Math.pow(length,3);
    }

    public String getName(){
        return this.name;
    }

    public void setLength(double l){
        this.length = l;
    }

    public String toString(){
        return "The " + this.name + "volume is: " + this.getVolume();
    }

}
