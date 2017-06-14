package com.michalkordas.code.challenge;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

class Timeline {
    private final List<User> followedUsers;

    Timeline(List<User> followedUsers) {
        this.followedUsers = ImmutableList.copyOf(followedUsers);
    }

    List<String> messages() {
        return followedUsers
            .stream()
            .flatMap(user -> user.wall().getMessages().stream())
            .sorted(comparing(Message::creationTimestamp).reversed())
            .map(Message::getContents)
            .collect(Collectors.toList());
    }
}
