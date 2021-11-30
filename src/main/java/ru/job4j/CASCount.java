package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int ref;
        int inc;
        do {
            ref = count.get();
            inc = ref + 1;
        }
        while (!count.compareAndSet(ref, inc));
    }

    public int get() {
        return count.get();
    }
}