package Controller;

import Model.GeometricObjects;
import Repository.*;
import Repository.RepositoryExceptions;

public class Controller {
    private IRepository repo;

    public Controller(){
        this.repo = new Repository(100);
    }

    public void addC(GeometricObjects obj) throws RepositoryExceptions{
            repo.add(obj);
    }

    public void removeC(String name) throws RepositoryExceptions{
        repo.remove(name);
    }

    public GeometricObjects[] filterC(double volume) throws RepositoryExceptions{
        return repo.filterByVolume(volume);
    }

}
