package ru.yandex.practicum.filmorate.storage.film;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryBaseStorage;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@Getter
public class InMemoryFilmStorage extends InMemoryBaseStorage<Film> implements FilmStorage {
    private final static LocalDate START_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    @Override
    public Film create(Film film) {
        log.info("Creating film {}", film);
        return super.create(film);
    }

    @Override
    public Film update(Film film) {
        log.info("Updating film {}", film);
        return super.update(film);
    }

    @Override
    public List<Film> getAll() {
        return super.getAll();
    }

//    @Override
//    public Film validate(Film data) {
//        if (data.getReleaseDate().isBefore(START_RELEASE_DATE)) {
//            log.warn("Release date film is before 28.12.1895");
//            throw new FilmValidationException("Film release date is invalid");
//        }
//        return data;
//    }

    @Override
    public Film get(Long id) {
        return super.get(id);
    }
}
