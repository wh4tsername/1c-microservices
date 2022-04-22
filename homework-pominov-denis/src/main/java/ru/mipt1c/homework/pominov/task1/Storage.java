package ru.mipt1c.homework.pominov.task1;

import ru.mipt1c.homework.task1.KeyValueStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Storage<K, V> implements KeyValueStorage<K, V> {
    private final DiskState<K, V> diskState;
    private final HashMap<K, V> inMemoryState;
    private boolean isClosed;

    public Storage(String path) {
        diskState = new DiskState<>(path);
        inMemoryState = diskState.load();
        isClosed = false;
    }

    @Override
    public V read(K key) {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        return inMemoryState.get(key);
    }

    @Override
    public boolean exists(K key) {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        return inMemoryState.containsKey(key);
    }

    @Override
    public void write(K key, V value) {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        inMemoryState.put(key, value);
    }

    @Override
    public void delete(K key) {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        inMemoryState.remove(key);
    }

    @Override
    public Iterator<K> readKeys() {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        return inMemoryState.keySet().iterator();
    }

    @Override
    public int size() {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        return inMemoryState.size();
    }

    @Override
    public void close() throws IOException {
        flush();

        isClosed = true;
    }

    @Override
    public void flush() {
        if (isClosed) {
            throw new RuntimeException("Trying to use closed storage");
        }

        try {
            diskState.store(inMemoryState);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
