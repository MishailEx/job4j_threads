package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CASCountTest {

    @Test
    public void whenCountInTurn() {
        CASCount casCount = new CASCount();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casCount.increment();
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casCount.increment();
            }
        });
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(20, is(casCount.get()));
    }

}