package ru.mipt1c.homework.pominov.task1;

import ru.mipt1c.homework.task1.MalformedDataException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DiskState<K, V> {
    private final Path file;

    public DiskState(String path) {
        final Path directory = Paths.get(path);
        if (!Files.exists(directory)) {
            throw new RuntimeException("invalid directory");
        }

        file = Paths.get(path, "kv");
    }

    public HashMap<K, V> load() {
        if (!Files.exists(file)) {
            return new HashMap<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(file))) {
            return (HashMap<K, V>) input.readObject();
        } catch (IOException exception) {
            throw new MalformedDataException("corrupted file");
        } catch (ClassNotFoundException exception) {
            throw new MalformedDataException(exception);
        }
    }

    public void store(HashMap<K, V> inMemoryState) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(file));
        output.writeObject(inMemoryState);
    }
}
