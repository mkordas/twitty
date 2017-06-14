package com.michalkordas.code.challenge.domain;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Timeline {
    private final List<User> followedUsers;

    Timeline(List<User> followedUsers) {
        this.followedUsers = ImmutableList.copyOf(followedUsers);
    }

    public List<String> getMessages() {
        return followedUsers
            .stream()
            .flatMap(user -> user.wall().getMessages().stream())
            .sorted(comparing(Message::creationTimestamp).reversed())
            .map(Message::getContents)
            .collect(Collectors.toList());
    }
}
