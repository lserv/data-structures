package org.lenur.linkedlist;

import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public LinkedList() {
    }

    @Override
    public boolean add(T value) {
        linkLast(value);
        return true;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            linkLast(value);
        } else {
            linkBefore(value, getNode(index));
        }
    }

    @Override
    public boolean addAll(java.util.List<T> list) {
        for (T e: list) {
            add(e);
        }

        return true;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        Node<T> node = getNode(index);

        return node.item;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);

        Node<T> node = getNode(index);
        T oldValue = node.item;
        node.item = value;

        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return unlink(getNode(index));
    }

    @Override
    public boolean remove(T t) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (Objects.equals(t, x.item)) {
                unlink(x);
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void linkLast(T value) {
        final Node<T> newNode = new Node<>(last, value, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;
        size++;
    }

    private void linkBefore(T value, Node<T> e) {
        final Node<T> p = e.prev;
        final Node<T> newNode = new Node<>(p, value, e);
        e.prev = newNode;
        if (p == null) {
            first = newNode;
        } else {
            p.next = newNode;
        }

        size++;
    }

    private T unlink(Node<T> e) {
        final T item = e.item;
        final Node<T> next = e.next;
        final Node<T> prev = e.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            e.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            e.next = null;
        }

        e.item = null;
        size--;

        return item;
    }

    private Node<T> getNode(int index) {
        Node<T> e;
        if (index < (size >> 1)) {
            e = first;
            for (int i = 0; i < index; i++) {
                e = e.next;
            }
        } else {
            e = last;
            for (int i = size - 1; i > index; i--) {
                e = e.prev;
            }
        }

        return e;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }
}
