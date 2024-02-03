package by.nekitos.group.movierate.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;
    private Set<Integer> likes = new HashSet<>();

    public int getLikesAmount() {
        return likes.size();
    }
}

/*
    JSON
{
    "id": 3,
    "name": "nik",
    "description": "petrt",
    "releaseDate": "2000-01-23",
    "duration": 5
}
*/
