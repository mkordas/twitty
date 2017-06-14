package com.michalkordas.twitty.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int MAX_MESSAGE_LENGTH = 140;

    private final Wall wall = new Wall();
    private final List<User> following = new ArrayList<>();
    private final String name;

    public User(String name) {
        this.name = name;
    }

    String name() {
        return name;
    }

    public void postMessage(String message) {
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException(
                "You cannot post message over 140 chars"
            );
        }
        wall.add(message);
    }

    public Wall wall() {
        return wall;
    }

    public void follow(User user) {
        following.add(user);
    }

    public Timeline timeline() {
        return new Timeline(following);
    }
}
