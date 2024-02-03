package by.nekitos.group.movierate.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private int id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
    private Set<Integer> friends = new HashSet<>();
}

/*
JSON

{
    "id": 1,
    "email": "nik241104@gmail.com",
    "login": "petrt",
    "name": "fiknes",
    "birthday": "2004-11-24"
}

 */
