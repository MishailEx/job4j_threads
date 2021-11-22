package ru.job4j.concurrent;

import java.util.List;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        List<String> symbol = List.of("|", "\\", "/");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (String str : symbol) {
                    System.out.print("\r load: " + "Loading ..." + str);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }


}
