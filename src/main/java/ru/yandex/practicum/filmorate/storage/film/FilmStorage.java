package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;


public interface FilmStorage {
    Film create(Film film);
    Film update(Film film);
    List<Film> getAll();
    Film get(Long id);
}
