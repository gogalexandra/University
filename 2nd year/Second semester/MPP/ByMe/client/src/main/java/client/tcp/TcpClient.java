package client.tcp;

import main.java.common.Message;
import common.F1Service;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


public class TcpClient {
    private ExecutorService executorService;

    public TcpClient() {
    }

    public TcpClient(ExecutorService executorService){
        this.executorService = executorService;
    }

    public Message sendAndReceive(Message request) throws Exception {
        try(var socket = new Socket(F1Service.HOST, F1Service.PORT);
            var is = socket.getInputStream();
            var os = socket.getOutputStream()){
            System.out.println("sending request: " + request);
            request.writeTo(os);
            System.out.println("request sent");

            Message response = new Message();
            response.readFrom(is);
            System.out.println("received response: " + response);

            return response;
            }catch (ConnectException ce){
                throw new Exception("Could not establish connection with the server");
        }catch (IOException e){
                e.printStackTrace();
                throw new Exception("exception in send and receive", e);
        }

    }
}
