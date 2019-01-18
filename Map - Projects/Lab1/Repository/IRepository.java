package Repository;
import Model.*;

public interface IRepository {
    void add(GeometricObjects objToAdd) throws RepositoryExceptions;
    void remove(String name) throws RepositoryExceptions;

    GeometricObjects[] filterByVolume(double volume)throws RepositoryExceptions;

}
