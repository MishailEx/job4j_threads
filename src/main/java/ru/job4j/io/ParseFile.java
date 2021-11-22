package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) throws IOException {
        this.file = copy(file);
    }

    private File copy(File file) throws IOException {
        File copyFile = File.createTempFile("data", null);
        Files.copy(file.toPath(), copyFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        return copyFile;
    }


    private synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
        return output.toString();
    }

    public String getContent() {
        return content(em -> true);
    }

    public String getContentWithoutUnicode() {
        return content(em -> em < 0x80);
    }
}