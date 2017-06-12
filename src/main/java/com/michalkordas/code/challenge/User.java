package com.michalkordas.code.challenge;

import java.util.ArrayList;
import java.util.List;

class User {
    private static final int MAX_MESSAGE_LENGTH = 140;

    private final Wall wall = new Wall();
    private final List<User> following = new ArrayList<>();
    private final String name;

    User(String name) {
        this.name = name;
    }

    String name() {
        return name;
    }

    void postMessage(String message) {
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException(
                "You cannot post message over 140 chars"
            );
        }
        wall.add(message);
    }

    Wall wall() {
        return wall;
    }

    void follow(User user) {
        following.add(user);
    }

    Timeline timeline() {
        return new Timeline(following);
    }
}
