package hvl.no.exercisehvl;

import hvl.no.exercisehvl.helpers.Poll;
import hvl.no.exercisehvl.helpers.States;
import hvl.no.exercisehvl.helpers.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
@Component
public class PollManager {
    private final HashMap<String, User> users;
    private final HashMap<User, ArrayList<Poll>> polls;

    public PollManager() {
        users = new HashMap<>();
        polls = new HashMap<>();
    }

    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        users.put(user.getUsername(), user);
        polls.putIfAbsent(user, new ArrayList<>());
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/users")
    public Set<String> getUsers() {
        return users.keySet();
    }

    /**
     * Get all public polls and the polls of the logged-in user
     * @param user logged-in user
     */
    @GetMapping("/polls")
    public Set<Poll> getPolls(@RequestBody(required = false) User user) {
        Set<Poll> retPolls = new HashSet<>();
        if (user != null && polls.containsKey(user)) {
            for (Poll poll : polls.get(user)) {
                if (poll.getAvailability() == States.Private) retPolls.add(poll);
            }
        }

        for (ArrayList<Poll> polls1 : polls.values()) {
            for (Poll poll : polls1) {
                if (poll.getAvailability() == States.Public) {
                    retPolls.add(poll);
                }
            }
        }
        return retPolls;
    }

    /**
     * should create a new poll based on the UUID of the user not the username
     */
    @PostMapping("/polls")
    public ResponseEntity<String> createPoll(@RequestParam String username, @RequestBody Poll poll) {
        polls.get(users.get(username)).add(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body("Poll created");
    }

    /**
     * Delete a poll based on the UUID of the user not the username
     */
    @PostMapping("/polls/delete")
    public void deletePoll(@RequestParam String username, @RequestBody Poll poll) {
        polls.get(users.get(username)).remove(poll);
    }
}
