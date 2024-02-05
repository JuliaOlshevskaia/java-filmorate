package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    FilmStorage filmStorage;
    public Film addLike(Long idFilm, Long idUser) {
        Film film = filmStorage.get(idFilm);
        film.addLike(idUser);
        filmStorage.update(film);
        return filmStorage.get(idFilm);
    }

    public Film deleteLike(Long idFilm, Long idUser) {
        Film film = filmStorage.get(idFilm);
        if (film.getLikes()==null || !(film.getLikes().contains(idUser))) {
            throw new DataNotFoundException(String.format("Data %s not found", idUser));
        }
        film.deleteLike(idUser);
        filmStorage.update(film);
        return filmStorage.get(idFilm);
    }

    public List<Film> getPopularFilms(Long count) {
        long minLikes = 0;
        if (count == null) {
            count = 10L;
        }
        List<Film> popularFilms = new ArrayList<>();
        List<Film> filmsAll =  filmStorage.getAll();
        for (Film film: filmsAll) {
            if (popularFilms.size()<count) {
                popularFilms.add(film);
                if (minLikes==0 && !(film.getLikes()==null) ) {
                    minLikes=film.getLikes().size();
                }
            } else {
                if (film.getLikes().size()>minLikes) {
                    minLikes = film.getLikes().size();
                    for (Film popularFilm: popularFilms) {
                        if (popularFilm.getLikes()==null || popularFilm.getLikes().size()<minLikes) {
                            popularFilms.remove(popularFilm);
                            break;
                        }
                    }
                    popularFilms.add(film);
                }
            }
        }
        return popularFilms;
    }

    public Film create(Film film) {
        return filmStorage.create(film);
    }

    public Film update(Film film) {
        return filmStorage.update(film);
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }

    public Film get(Long id) {
        return filmStorage.get(id);
    }

}
