package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.BaseUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBaseStorage<T extends BaseUnit> implements AbstractStorage<T> {
    private final Map<Long, T> storage = new HashMap<>();
    private long generatedId;
    @Override
    public T create(T data) {
        data.setId(++generatedId);
        storage.put(data.getId(), data);
        return data;
    }

    @Override
    public T update(T data) {
        if (!storage.containsKey(data.getId())) {
            throw new DataNotFoundException(String.format("Data %s not found", data));
        }
        storage.put(data.getId(), data);
        return data;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(storage.values());
    }

    @Override
    public T get(Long id) {
        if (!(storage.containsKey(id))) {
            throw new DataNotFoundException("Данные с ид=" + id + " не найдены");
        }
        return storage.get(id);
    }
}
