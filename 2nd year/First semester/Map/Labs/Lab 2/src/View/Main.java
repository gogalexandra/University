//3. Intr-un service se afla in reparatie mai multe
//        masini, camioane si motociclete. Sa se afiseze
//        toate autovehiculele ce au costul de reparatie
//        mai mare de 1000Ron.

package View;

import Controller.Controller;
import Model.Car;
import Model.MotorBike;
import Model.Truck;
import Model.Vehicle;
import Repository.Repo;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Repo repo = new Repo();
        Controller controller = new Controller(repo);
        menu(controller);

    }

    public static void menu(Controller ctrl){
        boolean done = true;
        while (done){
            System.out.println("0.Exit");
            System.out.println("1.Add");
            System.out.println("2.Remove");
            System.out.println("3.Filter");
            System.out.println("4.Show all");
            System.out.print("cmd: ");
            Scanner scanner = new Scanner(System.in);
            try{
            int cmd = scanner.nextInt();
            switch (cmd){
                case 0:
                    done = false;
                    System.out.println("Bye-bye!");
                    break;
                case 1:
                    System.out.println("Add a car, truck or motorbike? ");
                    String type = scanner.next();
                    System.out.println("Give cost of repair:  ");
                    int givenPrice = scanner.nextInt();
                    if(Objects.equals(type, "car")){
                        Vehicle c = new Car(givenPrice);
                        ctrl.add(c);
                    }
                    else if(Objects.equals(type, "truck")){
                        Vehicle t = new Truck(givenPrice);
                        ctrl.add(t);
                    }
                    else if(Objects.equals(type, "motorbike")){
                        Vehicle m = new MotorBike(givenPrice);
                        ctrl.add(m);
                    }
                    break;
                case 2:
                    System.out.println("Remove a car, truck or motorbike? ");
                    String type2 = scanner.next();
                    System.out.println("Give cost of repair:  ");
                    int givenPrice2 = scanner.nextInt();
                    if(Objects.equals(type2, "car")){
                        Vehicle c = new Car(givenPrice2);
                        ctrl.remove(c);
                    }
                    else if(Objects.equals(type2, "truck")){
                        Vehicle t = new Truck(givenPrice2);
                        ctrl.remove(t);
                    }
                    else if(Objects.equals(type2, "motorbike")){
                        Vehicle m = new MotorBike(givenPrice2);
                        ctrl.remove(m);
                    }
                    break;
                case 3:
                    Vehicle[] filtered = ctrl.filter(1000);
                    int dim = filtered.length - 1;
                    for (int i = 0; i < dim; i++) {
                        System.out.println(filtered[i].toString());
                    }
                    break;
                case 4:
                    int all = ctrl.getNrElems();
                    for (int i = 0; i < all; i++) {
                        System.out.println(ctrl.getAll()[i].toString());
                    }
                    break;

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            }
        }
    }
}
