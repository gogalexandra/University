package service;

import java.util.concurrent.ExecutorService;

public abstract class BaseService {
    protected ExecutorService executorService;
    protected Client client;

    public BaseService(ExecutorService executorService, Client client) {
        this.executorService = executorService;
        this.client = client;
    }
}
