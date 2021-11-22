package ru.job4j.thread;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String urlDownload;
    private long bytesWrited;
    private long deltaTime;

    public Wget(String url, String urlDownload, int speed) {
        this.url = url;
        this.urlDownload = urlDownload;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(urlDownload)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long end = System.currentTimeMillis();
                long timeWrite = end - start;
                start = end;
                bytesWrited += bytesRead;
                deltaTime += timeWrite;
                if (bytesWrited >= speed) {
                    if (deltaTime < 1000) {
                        Thread.sleep(1000 - deltaTime);
                    }
                    bytesWrited = bytesWrited - speed;
                    deltaTime = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("incorrect number"
                    + " of arguments, you need 3 arguments");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        String urlDownload = args[1];
        int speed = Integer.parseInt(args[2]);
        Thread wget = new Thread(new Wget(url, urlDownload, speed));
        wget.start();
        wget.join();
    }
}