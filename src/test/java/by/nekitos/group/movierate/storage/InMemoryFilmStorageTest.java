package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.exception.ValidationException;
import by.nekitos.group.movierate.model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFilmStorageTest {

    private InMemoryFilmStorage inMemoryFilmStorage;
    private Film film;

    @BeforeEach
    public void setUpFilmStorage() {
        inMemoryFilmStorage = new InMemoryFilmStorage();
    }

    @BeforeEach
    public void setUpFilm() {
        film = new Film();
    }

    @Test
    public void testCreateFilm() {
        film.setName("Test Film");
        film.setDescription("This is a test film.");
        film.setReleaseDate(LocalDate.now());
        film.setDuration(120);
        Film createdFilm = inMemoryFilmStorage.create(film);
        assertEquals(film, createdFilm);
        assertTrue(inMemoryFilmStorage.output().contains(film));
    }

    @Test
    public void testCreateFilmWithInvalidName() {
        film.setName("");
        film.setDescription("This is a test film.");
        film.setReleaseDate(LocalDate.now());
        film.setDuration(120);
        assertThrows(ValidationException.class, () -> inMemoryFilmStorage.create(film));
    }

    @Test
    public void testCreateFilmWithInvalidDescription() {
        film.setName("Test Film");
        film.setReleaseDate(LocalDate.now());
        film.setDuration(120);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append(i);
        }
        film.setDescription(sb.toString());
        assertThrows(ValidationException.class, () -> inMemoryFilmStorage.create(film));
    }

    @Test
    public void testCreateFilmWithInvalidDate() {
        film.setName("Test Film");
        film.setDescription("This is a test film.");
        LocalDate localDate = LocalDate.of(1985, 12, 27);
        film.setReleaseDate(localDate);
        film.setDuration(120);

        assertThrows(ValidationException.class, () -> inMemoryFilmStorage.create(film));
    }

    @Test
    public void testCreateFilmWithInvalidDuration() {
        film.setName("Test Film");
        film.setDescription("This is a test film.");
        film.setReleaseDate(LocalDate.now());
        film.setDuration(-1);
        assertThrows(ValidationException.class, () -> inMemoryFilmStorage.create(film));
    }
}