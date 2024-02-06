package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.AbstractStorage;

import java.util.List;

public interface UserStorage extends AbstractStorage<User> {
    User create(User user);

    User update(User user);

    List<User> getAll();

    User get(Long id);
}
