package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.exception.ValidationException;
import by.nekitos.group.movierate.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserStorageTest {

    private InMemoryUserStorage inMemoryUserStorage;
    private User user;

    @BeforeEach
    public void setUpFilmStorage() {
        inMemoryUserStorage = new InMemoryUserStorage();
    }

    @BeforeEach
    public void setUpUser() {
        user = new User();
    }

    @Test
    public void testCreateUser() {
        user.setEmail("nik241104@gmail.com");
        user.setLogin("Nekitos4");
        user.setName("Nikita");
        user.setBirthday(LocalDate.now());
        User createdUser = inMemoryUserStorage.create(user);
        assertEquals(user, createdUser);
        assertTrue(inMemoryUserStorage.output().contains(user));
    }

    @Test
    public void testCreateFilmWithInvalidEmail() {
        user.setLogin("Nekitos4");
        user.setName("Nikita");
        user.setBirthday(LocalDate.now());
        assertThrows(ValidationException.class, () -> inMemoryUserStorage.create(user));
        user.setEmail("nik241104gmail.com");
        assertThrows(ValidationException.class, () -> inMemoryUserStorage.create(user));
    }

    @Test
    public void testCreateFilmWithInvalidLogin() {
        user.setEmail("nik241104@gmail.com");
        user.setName("Nikita");
        user.setBirthday(LocalDate.now());
        assertThrows(ValidationException.class, () -> inMemoryUserStorage.create(user));
        user.setLogin("Nik kflsd");
        assertThrows(ValidationException.class, () -> inMemoryUserStorage.create(user));
    }

    @Test
    public void testCreateFilmWithInvalidName() {
        user.setEmail("nik241104@gmail.com");
        user.setLogin("Nekitos4");
        user.setBirthday(LocalDate.now());
        inMemoryUserStorage.validateData(user);
        assertEquals(user.getName(), user.getLogin());
    }

    @Test
    public void testCreateFilmWithInvalidDate() {
        user.setEmail("nik241104@gmail.com");
        user.setLogin("Nekitos4");
        user.setName("Nikita");
        user.setBirthday(LocalDate.of(2025, 12, 21));
        assertThrows(ValidationException.class, () -> inMemoryUserStorage.create(user));
    }
}