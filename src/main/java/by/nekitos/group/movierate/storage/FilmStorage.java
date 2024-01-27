package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.exception.ValidationException;
import by.nekitos.group.movierate.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;


@Component
public class FilmStorage {

    private final static Logger log = LoggerFactory.getLogger(FilmStorage.class);
    private final String MSG_ERROR_NAME = "Name should be filled in.";
    private final LocalDate localDate = LocalDate.of(1985, 12, 28);

    private List<Film> listOfFilms = new ArrayList<>();

    public Film create(Film film) {
        //if (validateName(film.getName())) {
            listOfFilms.add(film);
        //}
        return film;
    }

    public Film update(Film film) {
        listOfFilms.add(film);
        return film;
    }

    public Collection<Film> output() {
        return listOfFilms;
    }



    public boolean checkData(Film film) {
        final int MAX_SIZE = 200;
        final int MIN_DURATION = 0;
        if (film.getName() == null) {
            return false;
        }

        if (film.getDescription().length() > MAX_SIZE) {
            return false;
        }

        if (film.getDuration() < MIN_DURATION) {
            return false;
        }

        if (film.getReleaseDate().isBefore(localDate)) {
            return false;
        }
        return true;
    }
}
