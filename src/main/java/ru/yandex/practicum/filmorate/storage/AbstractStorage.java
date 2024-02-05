package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.BaseUnit;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface AbstractStorage<T extends BaseUnit> {
    T create(T data);
    T update(T data);
    List<T> getAll();
    public abstract T validate(T data);
    T get(Long id);
}
