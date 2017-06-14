package com.michalkordas.twitty;

import com.michalkordas.twitty.domain.Timeline;
import com.michalkordas.twitty.domain.User;
import com.michalkordas.twitty.domain.Wall;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TwittyController {

    private final Map<String, User> users = new HashMap<>();

    @RequestMapping(value = "/{user}/message", method = POST)
    public void message(
        @PathVariable("user") String user,
        @RequestBody String contents
    ) {
        retrieveUser(user).postMessage(contents);
    }

    @RequestMapping(value = "/{user}/follow", method = POST)
    public void follow(
        @PathVariable("user") String user,
        @RequestBody String userToFollow
    ) {
        retrieveUser(user).follow(retrieveUser(userToFollow));
    }

    @RequestMapping(value = "/{user}/wall", method = GET)
    public Wall wall(@PathVariable("user") String user) {
        return retrieveUser(user).wall();
    }

    @RequestMapping(value = "/{user}/timeline", method = GET)
    public Timeline timeline(@PathVariable("user") String user) {
        return retrieveUser(user).timeline();
    }

    private User retrieveUser(String user) {
        return users.computeIfAbsent(user, User::new);
    }

}
