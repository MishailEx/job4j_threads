package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), map.get(user.getId()), user);
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), map.get(user.getId()));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (map.get(fromId) != null && map.get(toId) != null) {
            if (map.get(fromId).getAmount() >= amount) {
                map.get(toId).setAmount(map.get(toId).getAmount() + amount);
                map.get(fromId).setAmount(map.get(fromId).getAmount() - amount);
                rsl = true;
            }
        }
        return rsl;
    }

    private synchronized boolean validate(Integer userId) {
        return map.containsKey(userId);
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        System.out.println(storage.transfer(1, 2, 50));
    }
}
