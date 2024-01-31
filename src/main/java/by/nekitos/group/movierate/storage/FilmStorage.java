package by.nekitos.group.movierate.storage;

import by.nekitos.group.movierate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Film create(Film film);
    Film update(Film film);
    Collection<Film> output();
}
