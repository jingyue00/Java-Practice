package edu.nyu.cs9053.homework7;

import java.util.concurrent.atomic.AtomicReference;

public class Wallet<T> {

    private final AtomicReference<T[]> array;
    private ArrayCreator<T> arrayCreator;
    private int size;
    private static final int RESIZE_FACTOR = 2;

    public Wallet(ArrayCreator<T> arrayCreator, int size) {
        this.arrayCreator = arrayCreator;
        this.array = new AtomicReference<>();
        this.array.set(arrayCreator.create(size));
        this.size = 0;
    }

    public boolean add(T value) {
        if (value == null || contains(value)) {
            return false;
        }
        if (size >= array.get().length) {
            resize();
        }
        array.get()[size++] = value;
        return true;
    }

    public boolean contains(T value) {
        if (value == null) {
            return false;
        }
        for (T element : array.get()) {
            if (element != null && element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }
        if (contains(value)) {
            int i = 0;
            while (i < getSize() && !array.get()[i].equals(value)) {
                i++;
            }
            while (i < getSize() - 1) {
                array.get()[i] = array.get()[i + 1];
                i++;
            }
            size--;
            return true;
        }
        return false;
    }

    public T get(int index) {
        if (index >= size) {
            throw new RuntimeException("Invalid index");
        }
        return array.get()[index];
    }

    public int getSize() {
        return this.size;
    }

    private void resize() {
        T[] newWalletArray = arrayCreator.create(array.get().length * RESIZE_FACTOR);
        java.lang.System.arraycopy(this.array.get(), 0, newWalletArray, 0, this.array.get().length);
        this.array.set(newWalletArray);
    }
}