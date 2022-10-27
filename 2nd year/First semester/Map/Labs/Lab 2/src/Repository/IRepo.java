package Repository;
import Model.Vehicle;

public interface IRepo {
    public void add(Vehicle v) throws Exception;
    public void remove(Vehicle v) throws Exception;
    public Vehicle[] filer(int price);
    public Vehicle[] getVehicles();
    public int getCurrentIndex();
    public void setCurrentIndex(int index);
}
