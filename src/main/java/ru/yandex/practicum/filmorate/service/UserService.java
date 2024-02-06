package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserStorage userStorage;

    public User addFriend(Long idUser, Long idFriend) {
        User user = userStorage.get(idUser);
        User userFriend = userStorage.get(idFriend);
        Set<Long> friends = user.getFriends();
        if (friends == null) {
            friends = new HashSet<>();
        }
        friends.add(idFriend);
        user.setFriends(friends);
        userStorage.update(user);

        Set<Long> friendFriends = userFriend.getFriends();
        if (friendFriends == null) {
            friendFriends = new HashSet<>();
        }
        friendFriends.add(idUser);
        userFriend.setFriends(friendFriends);
        userStorage.update(userFriend);
        return userStorage.get(idUser);
    }

    public User deleteFriend(Long idUser, Long idFriend) {
        User user = userStorage.get(idUser);
        Set<Long> friends = user.getFriends();
        friends.remove(idFriend);
        user.setFriends(friends);
        userStorage.update(user);

        User userFriend = userStorage.get(idFriend);
        Set<Long> friendFriends = userFriend.getFriends();
        friendFriends.remove(idUser);
        userFriend.setFriends(friendFriends);
        userStorage.update(userFriend);
        return userStorage.get(idUser);
    }

    public List<User> getAllFriends(Long idUser) {
        User user = userStorage.get(idUser);
        Set<Long> friends = user.getFriends();
        List<User> userFriends = new ArrayList<>();
        if (friends == null) {
            return userFriends;
        }
        for (Long id : friends) {
            userFriends.add(userStorage.get(id));
        }
        return userFriends;
    }

    public List<User> getCommonFriends(Long id, Long otherId) {
        User user = userStorage.get(id);
        User otherUser = userStorage.get(otherId);
        List<User> commonFriends = new ArrayList<>();

        Set<Long> idsCommonFriends = new HashSet<>();
        if (user.getFriends() == null || otherUser.getFriends() == null) {
            return commonFriends;
        }
        idsCommonFriends = new HashSet<>(user.getFriends());
        idsCommonFriends.retainAll(otherUser.getFriends());

        for (Long idFriend : idsCommonFriends) {
            commonFriends.add(userStorage.get(idFriend));
        }
        return commonFriends;
    }

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public List<User> getAll() {
        List<User> users = userStorage.getAll();
        return users;
    }

    public User get(Long id) {
        return userStorage.get(id);
    }

}
