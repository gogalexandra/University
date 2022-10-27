import java.util.Scanner;

public class First {
    static{
        String s = "1";
        String s2 = "4";
        String s3 = "4";
        String[] strings = {s, s3, s2};
        main(strings);
    }

    public static void main(String[] args) {
        int sum = 0;
        int l = args.length;
        for (String s : args) {
            sum += Integer.parseInt(s);
        }
        double average = sum/ l;
        System.out.printf("%f is the average of the given numbers%n", average);
    }

}

