package com.michalkordas.code.challenge;

class Message {

    private final String contents;
    private final long creationTime = System.nanoTime();

    Message(String contents) {
        this.contents = contents;
    }

    String contents() {
        return contents;
    }

    long creationTimestamp() {
        return creationTime;
    }
}
