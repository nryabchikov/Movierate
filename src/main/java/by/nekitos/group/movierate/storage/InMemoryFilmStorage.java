package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.exception.ValidationException;
import by.nekitos.group.movierate.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;


@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final static Logger log = LoggerFactory.getLogger(InMemoryFilmStorage.class);
    private final String MSG_ERROR_NAME = "Name should be filled in.";
    private final String MSG_ERROR_DESCRIPTION = "Description should be shorter than 200 characters.";
    private final String MSG_ERROR_DATE = "Release date no earlier than December 28, 1895.";
    private final String MSG_ERROR_DURATION = "Film duration should be positive.";
    private final LocalDate localDate = LocalDate.of(1985, 12, 28);
    private final int MAX_LENGTH_DESCRIPTION = 200;
    private final int MIN_DURATION = 0;

    private List<Film> listOfFilms = new ArrayList<>();

    @Override
    public Film create(Film film) {
        validateData(film);
        listOfFilms.add(film);
        return film;
    }

    @Override
    public Film update(Film film) {
        listOfFilms.add(film);
        return film;
    }

    @Override
    public Collection<Film> output() {
        return listOfFilms;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            log.warn(MSG_ERROR_NAME);
            throw new ValidationException(MSG_ERROR_NAME);
        }
    }

    private void validateDescription(String description) {
        if (description.length() > MAX_LENGTH_DESCRIPTION) {
            log.warn(MSG_ERROR_NAME);
            throw new ValidationException(MSG_ERROR_DESCRIPTION);
        }
    }

    private void validateDate(LocalDate date) {
        if (date.isBefore(localDate)) {
            log.warn(MSG_ERROR_DATE);
            throw new ValidationException(MSG_ERROR_DATE);
        }
    }

    private void validateDuration(int duration) {
        if (duration < MIN_DURATION) {
            log.warn(MSG_ERROR_DURATION);
            throw new ValidationException(MSG_ERROR_DURATION);
        }
    }

    public void validateData(Film film) {
        validateName(film.getName());
        validateDescription(film.getDescription());
        validateDate(film.getReleaseDate());
        validateDuration(film.getDuration());
    }
}
