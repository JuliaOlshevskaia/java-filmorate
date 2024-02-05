package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.controller.BaseController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryBaseStorage;

import java.util.List;

@Slf4j
@Component
public class InMemoryUserStorage extends InMemoryBaseStorage<User> implements UserStorage {
    @Override
    public User create(User user) {
        log.info("Creating user {}", user);
        return super.create(user);
    }

    @Override
    public User update(User user) {
        log.info("Updating user {}", user);
        return super.update(user);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User validate(User user) {
        if (user.getName()==null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return user;
    }

    @Override
    public User get(Long id) {
        return super.get(id);
    }
}
