package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        int newVersion = model.getVersion() + 1;
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            if (b.getVersion() != model.getVersion()) {
                throw new OptimisticException("Version differ");
            }
            Base update = new Base(b.getId(), newVersion);
            update.setName(model.getName());
            return update;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}