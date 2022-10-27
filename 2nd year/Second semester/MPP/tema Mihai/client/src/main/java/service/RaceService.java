package service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Message;
import domain.Race;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
public class RaceService extends BaseService {
    public RaceService(ExecutorService executorService, Client client) {
        super(executorService, client);
    }

    public Future<List<Race>> getAll() {
        return executorService.submit(() -> {
            Message response = this.client.sendAndReceive(new Message("get-race", ""));
            if (response.getHeader().equals(Message.OK)) {
                Type listType = new TypeToken<ArrayList<Race>>() {}.getType();
                return new Gson().fromJson(response.getBody(), listType);
            }
            return null;
        });
    }

    public Future<Boolean> add(Integer id, String location, Date date) {
        return executorService.submit(() -> {
            Race entity = new Race(id, location, date);
            Message request = new Message("add-race", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> update(Integer id, String location, Date date) {
        return executorService.submit(() -> {
            Race entity = new Race(id, location, date);
            Message request = new Message("update-race", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> delete(int id) {
        return executorService.submit(() -> {
            Message request = new Message("delete-race", String.valueOf(id));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }
}
