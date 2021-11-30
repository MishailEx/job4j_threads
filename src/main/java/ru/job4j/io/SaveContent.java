package ru.job4j.io;

import java.io.*;

public final class SaveContent {
    private final File file;

    public SaveContent(File file) {
        this.file = file;
    }

    public synchronized void save(String content) throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
