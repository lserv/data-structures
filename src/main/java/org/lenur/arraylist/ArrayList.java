package org.lenur.arraylist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        elementData = new Object[capacity];
    }

    @Override
    public void add(T value) {
        this.ensureCapacity(1);

        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        this.ensureCapacity(1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        this.ensureCapacity(list.size());

        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.checkIndex(index);

        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        this.checkIndex(index);

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        this.checkIndex(index);

        T val = this.get(index);

        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;

        return val;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
                return this.remove(i);
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void ensureCapacity(int count) {
        if (size + count > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);

            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);

            this.elementData = newElementData;
        }
    }
}
