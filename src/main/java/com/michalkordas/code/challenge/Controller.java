package com.michalkordas.code.challenge;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Controller {

    private final Map<String, User> users = new HashMap<>();

    @RequestMapping("/{user}/wall")
    public Wall wall(@PathVariable("user") String user) {
        return retrieveUser(user).wall();
    }

    @RequestMapping(value = "/{user}/message", method = POST)
    public void message(@PathVariable("user") String user, @RequestBody String contents) {
        retrieveUser(user).postMessage(contents);
    }

    private User retrieveUser(String user) {
        return users.computeIfAbsent(user, User::new);
    }

}
