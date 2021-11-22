package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> map = new ConcurrentHashMap<>();

    public synchronized boolean add(User user) {
        boolean rsl = false;
        if (!validate(user.getId())) {
            map.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean update(User user) {
        boolean rsl = false;
        if (validate(user.getId())) {
            map.get(user.getId()).setAmount(user.getAmount());
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean delete(User user) {
        boolean rsl = false;
        if (validate(user.getId())) {
            map.remove(user.getId());
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (!validate(fromId) || !validate(toId)) {
            throw new IllegalArgumentException();
        }
        if (map.get(fromId).getAmount() >= amount) {
            map.get(toId).setAmount(map.get(toId).getAmount() + amount);
            rsl = true;
        } else {
            System.out.println("not enough money");
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
