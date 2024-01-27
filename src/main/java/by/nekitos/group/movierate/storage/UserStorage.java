package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.exception.ValidationException;
import by.nekitos.group.movierate.model.Film;
import by.nekitos.group.movierate.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserStorage {

    private final static Logger log = LoggerFactory.getLogger(UserStorage.class);
    private final String MSG_ERROR_LOGIN = "Login should be filled in and doesn't contain spaces.";
    private final String MSG_ERROR_EMAIL = "Email should be filled in and contains @.";
    private final String MSG_ERROR_DATE = "Birthday date no later than now(" + LocalDate.now() + ")";
    private final LocalDate localDate = LocalDate.now();
    private List<User> listOfUsers = new ArrayList<>();

    public User create(User user) {
        validateData(user);
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

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            log.warn(MSG_ERROR_EMAIL);
            throw new ValidationException(MSG_ERROR_EMAIL);
        }
    }
    private void validateLogin(String login) {
        if (login == null || login.contains(" ")) {
            log.warn(MSG_ERROR_LOGIN);
            throw new ValidationException(MSG_ERROR_LOGIN);
        }
    }

    private void validateName(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }

    private void validateDate(LocalDate date) {
        if (date.isAfter(localDate)) {
            log.warn(MSG_ERROR_DATE);
            throw new ValidationException(MSG_ERROR_DATE);
        }
    }

    private void validateData(User user) {
        validateEmail(user.getEmail());
        validateLogin(user.getLogin());
        validateName(user);
        validateDate(user.getBirthday());
    }
}
