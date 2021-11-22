package com.egg.library;


import com.egg.library.domain.UserVO;
import com.egg.library.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;


import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/verifyusername",configurator = SpringConfigurator.class)
public class ServerWebSocket {


    private Session session;
    public static Set<ServerWebSocket> listeners =new CopyOnWriteArraySet<ServerWebSocket>();

    @Autowired
    private UserService userService;


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        listeners.add(this);
    }

    @OnMessage
    public void handleMessage(String username) {

        try {
            for (ServerWebSocket listener : listeners ){
                Optional<UserVO> user =  userService.findByUsername(username);
                if(user.isPresent()){
                    listener.sendMessage("The username already exists");
                }else{
                    listener.sendMessage("Username ok");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    @OnClose
    public void onClose(Session session) {
        listeners.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //Error
    }

    public static void broadcast(String message) {
        for (ServerWebSocket listener : listeners) {
            listener.sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {

        }
    }
}
