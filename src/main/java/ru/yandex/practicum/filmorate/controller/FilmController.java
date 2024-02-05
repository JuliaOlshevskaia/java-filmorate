package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
@AllArgsConstructor
public class FilmController{
    @Autowired
    FilmService filmService;
    private final static LocalDate START_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("Creating film {}", film);
        film = validate(film);
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Updating film {}", film);
        film = validate(film);
        return filmService.update(film);
    }

    @GetMapping
    public List<Film> getAll() {
        return filmService.getAll();
    }

    public Film validate(Film data) {
        if (data.getReleaseDate().isBefore(START_RELEASE_DATE)) {
            log.warn("Release date film is before 28.12.1895");
            throw new ValidationException("Film release date is invalid");
        }
        return data;
    }

    @GetMapping("/{id}")
    public Film get(@PathVariable Long id) {
        return filmService.get(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public Film addLike(@PathVariable Long id,
                        @PathVariable Long userId) {
        return filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Film deleteLike(@PathVariable Long id,
                        @PathVariable Long userId) {
        return filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam(required = false) Long count) {
        int iii = 1;
        return filmService.getPopularFilms(count);
    }
}
