package org.example;

import org.example.service.Service;
import org.example.ui.Console;

public class Main {
    public static void main(String[] args) {
        final var service = new Service();
        final var console = new Console(service);
        console.mainLoop();
    }
}