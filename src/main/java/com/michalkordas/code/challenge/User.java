package com.michalkordas.code.challenge;

class User {
    private static final int MAX_MESSAGE_LENGTH = 140;

    private final Wall wall = new Wall();
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

    public Wall wall() {
        return wall;
    }
}
