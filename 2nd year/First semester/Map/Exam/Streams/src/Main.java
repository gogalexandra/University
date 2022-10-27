import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Ex1
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16);
        List<Integer> streamNr = numbers.stream().filter(p -> p % 4 == 0).collect(Collectors.toList());
        System.out.println(streamNr); // Display the string.

        //Ex2
        Integer streamNr2 = numbers.stream()
                .filter(p -> p % 4 == 0)
                .map(p -> p + 1)
                .reduce(0, (a, b) -> (a + b)% 2);
        System.out.println(streamNr2); // Display the string.

        //Ex3

        Predicate<Integer> con1 = i -> i % 3 == 0;
        Predicate<Integer> con2 = i -> i % 7 == 0;
        Integer stream3 = numbers.stream()
                .filter(con1.or(con2))
                .map(p -> p - 1)
                .reduce(0, (a, b) -> (a+b) % 5);
        System.out.println(stream3);

        //Ex4
        Predicate<Integer> con3 = p -> p % 5 == 0;
        Predicate<Integer> con4 = p -> p % 2 == 0;
        String stream4 = numbers.stream()
                .filter(con3.or(con4))
                .map(p -> "N" + p.toString() + "R")
                .reduce("",(a, b) -> a +b);
        System.out.println(stream4);

        //Ex5
        Predicate<Integer> con5 = p -> p % 3 == 0;
        Predicate<Integer> con6 = p -> p % 2 == 0;
        String stream5 = numbers.stream()
                .filter(con3.or(con4))
                .map(p -> p + 1)
                .map(p -> "A" + p + "B")
                .reduce("",(a, b) -> a + b);
        System.out.println(stream5);

        //Ex926

        String result = numbers.stream()
                .filter(p -> (p % 5 == 0 || p % 2 == 0))
                .map(p -> "N" + p.toString() + "R")
                .reduce("", (a, b) -> a+b);
        System.out.println(result);
    }
}
