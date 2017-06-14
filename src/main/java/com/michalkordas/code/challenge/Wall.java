package com.michalkordas.code.challenge;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

class Wall {
    private final List<Message> messages = new ArrayList<>();

    void add(String message) {
        messages.add(new Message(message));
    }

    public List<Message> getMessages() {
        return Lists.reverse(messages);
    }
}
