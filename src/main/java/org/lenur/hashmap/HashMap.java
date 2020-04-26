package org.lenur.hashmap;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int threshold;
    private int size;
    private Node<K, V>[] table;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        int index = findIndex(hash);
        Node<K, V> node = getNode(key, index, hash);

        if (node == null) {
            Node<K, V> newNode = new Node<>(hash, key, value, table[index]);
            table[index] = newNode;
            size++;
        } else {
            node.value = value;
        }

        if (size >= threshold) {
            resize();
        }
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = findIndex(hash);
        Node<K, V> node = getNode(key, index, hash);

        return (node == null) ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    private Node<K, V> getNode(K key, int index, int hash) {
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.hash == hash && Objects.equals(key, node.key)) {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    private void resize() {
        size = 0;
        int newCapacity = table.length << 1;
        threshold = (int)(newCapacity * DEFAULT_LOAD_FACTOR);
        Node<K, V>[] newTable = new Node[newCapacity];
        Node<K, V>[] oldTable = table;
        table = newTable;

        transfer(oldTable);
    }

    private void transfer(Node<K, V>[] oldTable) {
        for (Node<K, V> e : oldTable) {
            while (e != null) {
                put(e.getKey(), e.getValue());
                e = e.next;
            }
        }
    }

    private int findIndex(int hash) {
        return hash & (table.length - 1);
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }
    }
}
