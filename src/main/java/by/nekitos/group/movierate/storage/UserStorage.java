package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.model.User;

import java.util.Collection;

public interface UserStorage {
    User create(User user);
    User update(User user);
    Collection<User> output();
}
