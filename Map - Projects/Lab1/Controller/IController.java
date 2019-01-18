package Controller;
import Repository.*;
import Model.*;

public interface IController {

    void addC(GeometricObjects obj)throws RepositoryExceptions;
    void removeC(String name) throws RepositoryExceptions;
    GeometricObjects[] filterC(double volume) throws RepositoryExceptions;

}
