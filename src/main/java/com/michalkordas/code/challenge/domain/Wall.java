package com.michalkordas.code.challenge.domain;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class Wall {
    private final List<Message> messages = new ArrayList<>();

    void add(String message) {
        messages.add(new Message(message));
    }

    public List<Message> getMessages() {
        return Lists.reverse(messages);
    }
}
