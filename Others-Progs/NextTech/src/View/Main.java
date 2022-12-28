package View;

import Controller.Controller;
import Model.Activity;
import Repo.Repo;

import java.util.ArrayList;
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
            System.out.println("1.Add activity");
            System.out.println("2.Start activity");
            System.out.println("3.Stop activity");
            System.out.println("4.Show past activities +/- filter");
            System.out.println("5.Compute points for done activities");
            System.out.println("6.Show all");
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
                        System.out.println("Give name of activity:  ");
                        String givenActivityName = scanner.next();
                            ctrl.addActivity(givenActivityName);
                        break;
                    case 2:
                        System.out.println("Give name of activity:  ");
                        String givenActivityName1 = scanner.next();

                        System.out.println("Give start time:  ");
                        int givenStartTime = scanner.nextInt();
                        ctrl.startActivity(givenActivityName1, givenStartTime);
                        break;
                    case 3:
                        System.out.println("Give name of activity:  ");
                        String givenActivityName3 = scanner.next();

                        System.out.println("Give end time:  ");
                        int givenEndTime = scanner.nextInt();
                        ctrl.stopActivity(givenActivityName3, givenEndTime);
                        break;
                    case 4:
                        System.out.println("Give time to see past activities:  ");
                        int givenEndTime1 = scanner.nextInt();

                        System.out.println("Do you wanna apply filter(Yes/No):  ");
                        String applyFilter = scanner.next();
                        if (applyFilter.equals("no")){
                            ArrayList<Activity> noFilter = ctrl.findPastActivities(givenEndTime1, "", "");
                            System.out.println(noFilter.toString());
                        }
                        else{
                            System.out.println("Apply filter on type or duration:  ");
                            String filter = scanner.next();

                            System.out.println("Give type/duration:  ");
                            String info = scanner.next();

                            ArrayList<Activity> withFilter = ctrl.findPastActivities(givenEndTime1, filter,info);
                            System.out.println(withFilter.toString());
                        }

                    case 5:
                        System.out.println("Give array of points:  ");
                        String points = scanner.next();
                        ArrayList<Integer> p = new ArrayList<Integer>(Arrays.asList(Integer.valueOf(String.valueOf(points.split(" ")))));
                        System.out.println(ctrl.computeTotalPoints(p));
                    case 6:
                        System.out.println(ctrl.getRepo().toString());


                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
