package com.michalkordas.code.challenge;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;

class Timeline {
    private final List<User> following;

    Timeline(List<User> following) {
        this.following = ImmutableList.copyOf(following);
    }

    List<String> messages() {
        return following
            .stream()
            .flatMap(user -> user.wall().messages().stream())
            .collect(Collectors.toList());
    }
}
