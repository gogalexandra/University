package client.config;

import client.service.ClientF1Service;
import client.tcp.TcpClient;
import client.ui.UI;
import common.F1Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class appConfig {
    @Bean
    ExecutorService executorService(){
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    TcpClient tcpClient() {
        return new TcpClient(executorService());
    }

    @Bean
    F1Service F1Service(){
        return new ClientF1Service(executorService(), tcpClient());
    }

    @Bean
    UI ui(){
        return new UI(F1Service());
    }

}
