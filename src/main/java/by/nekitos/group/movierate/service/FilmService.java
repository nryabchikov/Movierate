package by.nekitos.group.movierate.service;

import by.nekitos.group.movierate.model.Film;
import by.nekitos.group.movierate.storage.InMemoryFilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {
    private int START_INDEX = 0;
    private int DEFAULT_SIZE = 10;
    private final InMemoryFilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    public void addLike(int filmId, int userId) {
        getFilmById(filmId).getLikes().add(userId);
    }

    public void deleteLike(int filmId, int userId) {
        getFilmById(filmId).getLikes().remove(userId);
    }

    public Collection<Integer> outputLikes(int filmId) {
        return getFilmById(filmId).getLikes();
    }


    public List<Film> getMostPopularFilms() {
        inMemoryFilmStorage.output().sort(Comparator.comparing(Film::getLikesAmount).reversed());
        return DEFAULT_SIZE > inMemoryFilmStorage.output().size() ?
                inMemoryFilmStorage.output().subList(START_INDEX, inMemoryFilmStorage.output().size()) :
                inMemoryFilmStorage.output().subList(START_INDEX, DEFAULT_SIZE);
    }

    public List<Film> getMostPopularFilms(int count) {
        inMemoryFilmStorage.output().sort(Comparator.comparing(Film::getLikesAmount).reversed());
        return count > inMemoryFilmStorage.output().size() ?
                inMemoryFilmStorage.output().subList(START_INDEX, inMemoryFilmStorage.output().size()) :
                inMemoryFilmStorage.output().subList(START_INDEX, count);
    }
    public Film getFilmById(int id) {
        return inMemoryFilmStorage.output().stream().filter(user -> (user.getId() == id)).findFirst().get();
    }
}
