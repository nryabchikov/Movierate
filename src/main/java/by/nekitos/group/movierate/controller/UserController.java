package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.User;
import by.nekitos.group.movierate.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserStorage userStorage;

    @Autowired
    public UserController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userStorage.create(user);
    }

    @GetMapping
    public Collection<User> output() {
        return userStorage.output();
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userStorage.update(user);
    }
}
