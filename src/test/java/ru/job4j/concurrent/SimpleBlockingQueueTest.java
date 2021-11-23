package ru.job4j.concurrent;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        List<Integer> num = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue(10);
        Thread producer = new Thread(() -> IntStream.range(0, 3).forEach(queue::offer));
        Thread consumer = new Thread(() -> num.add(queue.poll()));
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
        Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(queue::offer));
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                num.add(queue.poll());
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(num, is(List.of(0, 1, 2, 3, 4)));
    }
}