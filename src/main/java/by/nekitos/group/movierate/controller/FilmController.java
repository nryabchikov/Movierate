package by.nekitos.group.movierate.controller;

import by.nekitos.group.movierate.model.Film;
import by.nekitos.group.movierate.service.FilmService;
import by.nekitos.group.movierate.storage.InMemoryFilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final InMemoryFilmStorage inMemoryFilmStorage;
    private final FilmService filmService;

    @Autowired
    public FilmController(InMemoryFilmStorage inMemoryFilmStorage, FilmService filmService) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
        this.filmService = filmService;
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

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public Collection<Film> getMostPopularFilms(@RequestParam(required = false) Integer count) {
        if (count != null) {
            return filmService.getMostPopularFilms(count);
        } else {
            return filmService.getMostPopularFilms();
        }
    }
}
