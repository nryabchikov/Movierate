package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserStorage {
    private List<User> listOfUsers = new ArrayList<>();

    public User create(User user) {
        listOfUsers.add(user);
        return user;
    }

    public User update(User user) {
        listOfUsers.add(user);
        return user;
    }

    public Collection<User> output() {
        return listOfUsers;
    }
}
