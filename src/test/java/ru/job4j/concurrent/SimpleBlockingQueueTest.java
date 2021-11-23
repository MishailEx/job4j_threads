package ru.job4j.concurrent;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        List<Integer> num = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue(10);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                num.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(num, is(List.of(0)));
    }

    @Test
    public void whenFullCapacity() throws InterruptedException {
        List<Integer> num = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue(2);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    num.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(num, is(List.of(0, 1, 2, 3, 4)));
    }
}