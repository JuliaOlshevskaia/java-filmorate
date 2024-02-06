package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;

@SpringBootTest
class FilmorateApplicationTests {
    private FilmController filmController;
    private UserController userController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController(new FilmService());
        userController = new UserController(new UserService());
    }

    @Test
    void filmValidate() {
        Film film = Film.builder()
                .name("Name")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 1, 1))
                .duration(100)
                .build();

        filmController.validate(film);
    }

    @Test
    void filmValidateNegativeReleaseDate() {
        Film film = Film.builder()
                .name("Name")
                .description("Description")
                .releaseDate(LocalDate.of(1800, 1, 1))
                .duration(100)
                .build();

        Assertions.assertThrows(ValidationException.class, () -> filmController.validate(film));
    }

    @Test
    void userValidate() {
        User user = User.builder()
                .email("aaa@mail.ru")
                .login("Login 1")
                .name("Name 1")
                .birthday(LocalDate.of(2000, 1, 1))
                .build();
        userController.validate(user);
    }

    @Test
    void userValidateNegativeName() {
        User user = User.builder()
                .email("aaa@mail.ru")
                .login("Login 1")
                .birthday(LocalDate.of(2000, 1, 1))
                .build();
        userController.validate(user);

        Assertions.assertEquals(user.getLogin(), user.getName(), "Пустое имя пользователя не заменилось на логин");
    }

}
