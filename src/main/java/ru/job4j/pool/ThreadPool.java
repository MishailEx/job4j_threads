package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(2);

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread tmp = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        tasks.poll().run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            });
            tmp.start();
            threads.add(tmp);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        try {
            threadPool.work(() -> System.out.println("1"));
            threadPool.work(() -> System.out.println("2"));
            threadPool.work(() -> System.out.println("3"));
            threadPool.work(() -> System.out.println("4"));
            Thread.sleep(1000);
            threadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}