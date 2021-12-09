package com.egg.library.web.sockets;

import com.egg.library.domain.UserVO;
import com.egg.library.domain.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Optional;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        Optional<UserVO> user =  userService.findByUsername(""+jsonObject.get("user"));
        if(user.isPresent()){
            session.sendMessage(new TextMessage("The username already exists"));
        }else{
            session.sendMessage(new TextMessage("The username is avaible!"));
        }

    }

}
