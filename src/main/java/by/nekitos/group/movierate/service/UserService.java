package by.nekitos.group.movierate.service;

import by.nekitos.group.movierate.model.User;
import by.nekitos.group.movierate.storage.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserService(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    public void addFriend(int userId, int friendId) {
        User user = getUserById(userId);
        user.getFriends().add(friendId);
        User friendUser = getUserById(friendId);
        friendUser.getFriends().add(userId);
    }

    public void deleteFriend(int userId, int friendId) {
        User user = getUserById(userId);
        user.getFriends().remove(friendId);
        User friendUser = getUserById(friendId);
        friendUser.getFriends().remove(userId);
    }

    public Collection<Integer> outputFriends(int userId) {
        return getUserById(userId).getFriends();
    }

    public User getUserById(int id) {
        return inMemoryUserStorage.output().stream().filter(userAnother -> userAnother.getId() == id).findFirst().get();
    }

    public List<Integer> outputCommonFriends(int userId, int friendId) {
        User user = getUserById(userId);
        User friendUser = getUserById(friendId);
        List<Integer> list = new ArrayList<>(user.getFriends());
        list.retainAll(friendUser.getFriends());
        return list;
    }
}
