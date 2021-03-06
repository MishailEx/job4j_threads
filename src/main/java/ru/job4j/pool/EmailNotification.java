package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s",
                    user.getUserName(), user.getEmail());
            String body = String.format("Add a new event to %s",
                    user.getUserName());
            send(subject, body, user.getEmail());
        });
    }

    public void send(String subject, String body, String email) {
    }

    public void close() throws InterruptedException {
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(100);
        }
    }
}
