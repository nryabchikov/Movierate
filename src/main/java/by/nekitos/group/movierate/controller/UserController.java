package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.User;
import by.nekitos.group.movierate.service.UserService;
import by.nekitos.group.movierate.storage.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final InMemoryUserStorage inMemoryUserStorage;
    private final UserService userService;

    @Autowired
    public UserController(InMemoryUserStorage inMemoryUserStorage, UserService userService) {
        this.inMemoryUserStorage = inMemoryUserStorage;
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return inMemoryUserStorage.create(user);
    }

    @GetMapping
    public Collection<User> output() {
        return inMemoryUserStorage.output();
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return inMemoryUserStorage.update(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public Collection<Integer> outputFriends(@PathVariable int id) {
        return userService.outputFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<Integer> outputCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return userService.outputCommonFriends(id, otherId);
    }
}
