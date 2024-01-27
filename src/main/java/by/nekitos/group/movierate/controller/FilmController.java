package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.Film;
import by.nekitos.group.movierate.storage.FilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmStorage filmStorage;

    @Autowired
    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        return filmStorage.create(film);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmStorage.update(film);
    }

    @GetMapping
    public Collection<Film> output() {
        return filmStorage.output();
    }
}
