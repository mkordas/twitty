package com.michalkordas.code.challenge.domain;

class Message {

    private final String contents;
    private final long creationTime = System.nanoTime();

    Message(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    long creationTimestamp() {
        return creationTime;
    }
}
