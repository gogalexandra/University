package service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Message;
import domain.Participation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.StreamSupport;

@Component
public class ParticipationService extends BaseService {


    public ParticipationService(ExecutorService executorService, Client client) {
        super(executorService, client);
    }

    public Future<List<Participation>> getAll() {
        return executorService.submit(() -> {
            Message response = this.client.sendAndReceive(new Message("get-participation", ""));
            if (response.getHeader().equals(Message.OK)) {
                Type listType = new TypeToken<ArrayList<Participation>>() {}.getType();
                return new Gson().fromJson(response.getBody(), listType);
            }
            return null;
        });
    }

    public Future<Boolean> add(Integer id, Integer team, Integer race) {
        return executorService.submit(() -> {
            Participation entity = new Participation(id, team, race);
            Message request = new Message("add-participation", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> update(Integer id, Integer team, Integer race) {
        return executorService.submit(() -> {
            Participation entity = new Participation(id, team, race);
            Message request = new Message("update-participation", new Gson().toJson(entity));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }

    public Future<Boolean> delete(int id) {
        return executorService.submit(() -> {
            Message request = new Message("delete-participation", String.valueOf(id));

            Message response = this.client.sendAndReceive(request);

            if (response.getHeader().equals(Message.OK)) {
                return new Gson().fromJson(response.getBody(), Boolean.class);
            }

            return false;
        });
    }
}

