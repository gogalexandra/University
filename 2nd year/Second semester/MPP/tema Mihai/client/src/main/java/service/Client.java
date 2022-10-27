package service;

import common.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Message sendAndReceive(Message request) {
        try (Socket socket = new Socket(host, port);
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {

//            System.out.printf("Sending request: %s", request);

            request.writeTo(os);

            Message response = new Message();
            response.readFrom(is);

//            System.out.printf("Received response: %s", response);

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
