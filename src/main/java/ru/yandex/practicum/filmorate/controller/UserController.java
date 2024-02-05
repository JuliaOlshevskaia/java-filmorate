package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController{
    @Autowired
    UserService userService;

    @PostMapping
    public User create(@Valid @RequestBody User user) {
//        user.setFriends(null);
        log.info("Creating user {}", user);
        user = validate(user);
        return userService.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.info("Updating user {}", user);
        user = validate(user);
        return userService.update(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Long id,
                        @PathVariable Long friendId) {
        Long iii = id;
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User deleteFriend(@PathVariable Long id,
                           @PathVariable Long friendId) {
        return userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@PathVariable Long id) {
        Long idUser = id;
        return userService.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Long id,
                                       @PathVariable Long otherId) {
        return userService.getCommonFriends(id, otherId);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    public User validate(User user) {
        if (user.getName()==null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return user;
    }

}
