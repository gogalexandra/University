
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

import java.util.concurrent.ExecutionException;

public class ClientMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");
        Console console = context.getBean(Console.class);

        console.mainLoop();
    }
}
