package client;

import client.ui.UI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;

public class ClientApp {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("client.config");
        ExecutorService executorService1 = context.getBean(ExecutorService.class);
        UI ui = context.getBean(UI.class);
        ui.run();

        executorService1.shutdownNow();
    }
}
