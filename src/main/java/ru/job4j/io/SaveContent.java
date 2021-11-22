package ru.job4j.io;

import java.io.*;

public class SaveContent {
    private final File file;
    private final String content;

    public SaveContent(File file, String content) {
        this.file = file;
        this.content = content;
    }

    public boolean save() throws IOException {
        boolean rsl = false;
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
                rsl = true;
            }
        }
        return rsl;
    }
}
