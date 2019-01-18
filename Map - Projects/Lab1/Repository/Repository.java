package Repository;
import java.util.Arrays;
import Model.*;

public class Repository implements IRepository {

    private int size;
    private GeometricObjects[] repo;

    public Repository(int cap){
        size = 0;
        repo = new GeometricObjects[cap];
    }

    public void add(GeometricObjects objToAdd) throws RepositoryExceptions{
        if (repo.length == size) {
            throw new RepositoryExceptions("You cannot add another object since the list is full!");
        }
        try{
            repo[size++] = objToAdd;
        }
        catch(IndexOutOfBoundsException e){
            this.repo = Arrays.copyOf(this.repo, this.repo.length + 1);
            repo[size++] = objToAdd;
        }

    }

    public void remove(String name) throws RepositoryExceptions{

        int ok = 0;
        if (size == 0) {
            throw new RepositoryExceptions("List is empty!");
        }
        for (int i= 0;i < size;i++){
            if(repo[i].getName().equals(name)){
                ok = 1;
                for(int j =i;j<size;j++){
                    repo[i] = repo[i+1];
                }
                size--;
                i--;
            }
        }
        if(ok == 0){
            throw new RepositoryExceptions("There is no element with that name!");
        }
    }

    private void resize(GeometricObjects[] list){

    }

    @Override
    public GeometricObjects[] filterByVolume(double volume)throws RepositoryExceptions{
        int len = 0;
        for (int i = 0; i < size; i++){
            if (repo[i].getVolume() > volume) {
                len++;
            }
        }
        GeometricObjects[] auxRepo = new GeometricObjects[len];
        len = 0;

        for (int i = 0; i < size; i++){
            if(repo[i].getVolume() > volume){
                auxRepo[len++] = repo[i];
            }
        }
        if (auxRepo.length == 0)
            throw new RepositoryExceptions("There are no objects with that volume!");
        return auxRepo;
    }
}
