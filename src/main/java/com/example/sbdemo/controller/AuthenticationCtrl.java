package com.example.sbdemo.controller;

import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.models.TokenSet;
import com.angelbroking.smartapi.models.User;
import com.example.sbdemo.model.Repo.WebhookResponseRepo;
import com.example.sbdemo.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.angelbroking.smartapi.*;

import java.time.LocalDateTime;

@RestController
public class AuthenticationCtrl {

    @Autowired
    WebhookResponseRepo webhookResponseRepo;
    @RequestMapping(path = "/echo",method = RequestMethod.GET,produces = "application/json")
    public String echoInputString(@RequestParam String inputString){
        return "Provided input string is "+inputString;
    }

    @RequestMapping(path = "/autheticate",method = RequestMethod.GET,produces = "application/json")
    public void autheticate(@RequestParam String clientId,String password,String totp){
// Initialize SamartAPI using Client code, Password, and TOTP.
        SmartConnect smartConnect = new SmartConnect();

        // Provide your API key here
        smartConnect.setApiKey("<key>");

        // Set session expiry callback.
        smartConnect.setSessionExpiryHook(new SessionExpiryHook() {
            @Override
            public void sessionExpired() {
                System.out.println("session expired");
            }
        });

        User user = smartConnect.generateSession(clientId, password, totp);
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        // token re-generate

        TokenSet tokenSet = smartConnect.renewAccessToken(user.getAccessToken(),
                user.getRefreshToken());
        smartConnect.setAccessToken(tokenSet.getAccessToken());

    }

    @RequestMapping(path = "/webhook",method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
    public void webhook(@RequestBody String json){

        WebhookResponse webhookResponse=new WebhookResponse();
        webhookResponse.setResponse(json);
        webhookResponse.setCreated(LocalDateTime.now());
        webhookResponseRepo.save(webhookResponse);
    }
}
