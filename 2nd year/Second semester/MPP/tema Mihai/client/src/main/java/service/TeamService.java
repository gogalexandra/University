package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Message;
import domain.Team;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
public class TeamService extends BaseService {


    public TeamService(ExecutorService executorService, Client client) {
        super(executorService, client);
    }

    public Future<List<Team>> getAll() {
        return executorService.submit(() -> {
            Message response = this.client.sendAndReceive(new Message("get-team", ""));
            if (response.getHeader().equals(Message.OK)) {
                Type listType = new TypeToken<ArrayList<Team>>() {}.getType();
                return new Gson().fromJson(response.getBody(), listType);
            }
            return null;
        });
    }

    public Future<Boolean>
    add(String name, List<Integer> drivers) {
        return executorService.submit(() -> {
            Team entity = new Team(name, drivers);
            Message request = new Message("add-team", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> update(String name, List<Integer> drivers) {
        return executorService.submit(() -> {
            Team entity = new Team(name, drivers);
            Message request = new Message("update-team", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> delete(int id) {
        return executorService.submit(() -> {
            Message request = new Message("delete-team", String.valueOf(id));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }
}
