package com.michalkordas.code.challenge;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

class Wall {
    private final List<String> messages = new ArrayList<>();

    void add(String message) {
        messages.add(message);
    }

    List<String> messages() {
        return Lists.reverse(messages);
    }
}
