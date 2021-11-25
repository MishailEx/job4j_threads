package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddBase() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        assertTrue(cache.add(base));
    }

    @Test
    public void whenAddBaseButIdExists() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base base2 = new Base(1, 1);
        cache.add(base);
        assertFalse(cache.add(base2));
    }

    @Test
    public void whenRemoveIdExisting() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertTrue(cache.add(base));
    }

    @Test
    public void whenUpdateIdWithSameVersionThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        assertTrue(cache.update(base));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateIdWithDifferVersionThenException() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setName("ddd");
        cache.update(base);
        cache.update(base);
    }

    @Test
    public void whenUpdateIdNoExistThenFalse() {
        Cache cache = new Cache();
        assertFalse(cache.update(new Base(3, 8)));
    }

}