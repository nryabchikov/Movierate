package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.User;
import by.nekitos.group.movierate.storage.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserController(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
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
}
