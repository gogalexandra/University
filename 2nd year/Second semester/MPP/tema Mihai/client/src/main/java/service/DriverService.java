package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Message;
import domain.Driver;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
public class DriverService extends BaseService {

    public DriverService(ExecutorService executorService, Client client) {
        super(executorService, client);
    }


    public Future<List<Driver>> getAll() {
        return executorService.submit(() -> {
            Message response = this.client.sendAndReceive(new Message("get-driver", ""));
            if (response.getHeader().equals(Message.OK)) {
                Type listType = new TypeToken<ArrayList<Driver>>() {}.getType();
                return new Gson().fromJson(response.getBody(), listType);
            }
            return null;
        });
    }

    public Future<Boolean> add(Integer id, String name, Integer experience) {
        return executorService.submit(() -> {
            Driver entity = new Driver(id, name, experience);
            Message request = new Message("add-driver", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> update(Integer id, String name, Integer experience) {
        return executorService.submit(() -> {
            Driver entity = new Driver(id, name, experience);
            Message request = new Message("update-driver", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> delete(int id) {
        return executorService.submit(() -> {
            Message request = new Message("delete-driver", String.valueOf(id));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }
}
