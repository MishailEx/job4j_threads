package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int ref;
        int inc;
        do {
            ref =  count.get();
            inc = ref++;
        }
        while (!count.compareAndSet(ref, inc));
        throw new UnsupportedOperationException("Count is not impl.");
    }

    public int get() {
        int ref;
        int inc;
        do {
            ref =  count.get();
            inc = ref++;
        }
        while (!count.compareAndSet(ref, inc));
        return ref;
    }
}