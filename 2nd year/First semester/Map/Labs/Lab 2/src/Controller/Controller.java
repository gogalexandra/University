package Controller;

import Model.Vehicle;
import Repository.Repo;

public class Controller {
    private Repo repo;

    public Controller(Repo repo){
//        super();
        this.repo = repo;
    }

    public void add(Vehicle v) throws Exception {
        repo.add(v);
    }

    public void remove(Vehicle v) throws Exception{
        repo.remove(v);
    }

    public Vehicle[] filter(int price){
        return repo.filer(price);
    }

    public Repo getRepo(){
        return repo;
    }

    public Vehicle[] getAll(){ return repo.getVehicles(); }

    public int getNrElems() {return repo.getCurrentIndex(); }


}
