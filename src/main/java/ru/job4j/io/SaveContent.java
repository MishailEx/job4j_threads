package ru.job4j.io;

import java.io.*;

public class SaveContent {
    private File file;
    private final String content;

    public SaveContent(String content) {
        this.content = content;
    }

    public synchronized void save() throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }

    public void setFile(File file) {
        this.file = file;
    }
}
