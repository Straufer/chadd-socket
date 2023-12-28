package com.paung.service;

import com.paung.service.entity.Message;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class ChatService {

    private final Sinks.Many<Message> messages = Sinks.many().multicast().directBestEffort();
    private final Flux<Message> messageFlux = messages.asFlux();
    private final AuthenticationContext authenticationContext;

    public ChatService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public Flux<Message> join(){
        return this.messageFlux;
    }

    void add(String msg){
        var username = this.authenticationContext.getPrincipalName().orElse("Anonymous");
        this.messages.tryEmitNext(new Message(username, msg, Instant.now()));
    }

}

