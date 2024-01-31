package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.Film;
import by.nekitos.group.movierate.storage.InMemoryFilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final InMemoryFilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmController(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        return inMemoryFilmStorage.create(film);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return inMemoryFilmStorage.update(film);
    }

    @GetMapping
    public Collection<Film> output() {
        return inMemoryFilmStorage.output();
    }
}
