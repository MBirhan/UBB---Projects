package View;
import Model.*;
import Repository.*;
import Controller.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Repository repo = new Repository(100);
        Controller ctrl = new Controller();

        for( ; ; ){
            System.out.println("1.Add a cube!");
            System.out.println("2.Add a sphere!");
            System.out.println("3.Add a cylinder!");
            System.out.println("4.Remove an element!");
            System.out.println("5.Show all elements with the volume > then the given value!");
            System.out.print("Choose your destiny:");
            Scanner in = new Scanner(System.in);
            int cmd = in.nextInt();

            if(cmd == 0){
                break;
            }
            switch(cmd){
                case 1: {
                    System.out.print("Enter the name of the cubic object: ");
                    Scanner qw = new Scanner(System.in);
                    String name = qw.nextLine();
                    System.out.print("Enter the length of the cubic object: ");
                    Scanner we = new Scanner(System.in);
                    double length = we.nextDouble();
                    Cube cube = new Cube(name, length);
                    try {
                        ctrl.addC(cube);
                    } catch (RepositoryExceptions e) {
                        System.out.print(e + "\n");
                    }
                    break;
                }
                case 2:{
                    System.out.print("Enter the name of the sphere object: ");
                    Scanner qw = new Scanner(System.in);
                    String name = qw.nextLine();
                    System.out.print("Enter the radius of the sphere object: ");
                    Scanner we = new Scanner(System.in);
                    double radius = we.nextDouble();
                    Sphere sphere = new Sphere(name, radius);
                    try {
                        ctrl.addC(sphere);
                    } catch (RepositoryExceptions e) {
                        System.out.print(e + "\n");
                    }
                    break;
                }
                case 3:{
                    System.out.println("Enter the name of the cylinder object: ");
                    Scanner qw = new Scanner(System.in);
                    String name = qw.nextLine();
                    System.out.println("Enter the height of the cylinder object: ");
                    Scanner we = new Scanner(System.in);
                    double height = we.nextDouble();
                    System.out.println("Enter the radius of the cylinder object: ");
                    Scanner er = new Scanner(System.in);
                    double radius = er.nextDouble();
                    Cylinder cylinder = new Cylinder(name,height,radius);
                    try {
                        ctrl.addC(cylinder);
                    } catch (RepositoryExceptions e) {
                        System.out.print(e + "\n");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Enter the name of the object that you want removed!");
                    Scanner qw = new Scanner(System.in);
                    String name = qw.nextLine();
                    try {
                        ctrl.removeC(name);
                    } catch (RepositoryExceptions e) {
                        System.out.print(e + "\n");
                    }
                    break;

                }
                case 5:{
                    System.out.println("Enter the volume: ");
                    Scanner qw = new Scanner(System.in);
                    double volume = qw.nextDouble();
                    try {
                        GeometricObjects[] list = ctrl.filterC(volume);
                        for (int i = 0; i < list.length; i++)
                            System.out.println(list[i].toString());
                    }catch(RepositoryExceptions q){
                        System.out.print(q + "\n");
                    }
                    }
                    break;
                }
            }
        }
    }

