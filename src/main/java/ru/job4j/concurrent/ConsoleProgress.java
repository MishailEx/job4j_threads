package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String symbol;
                for (int i = 0; true; i++) {
                    switch (i) {
                        case (1):
                            symbol = "|";
                            break;
                        case (2):
                            symbol = "\\";
                            break;
                        default:
                            symbol = "/";
                            i = 0;
                            break;
                    }
                    System.out.print("\r load: " + "Loading ..." + symbol);
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
        Thread.sleep(1000);
        progress.interrupt();
    }


}
