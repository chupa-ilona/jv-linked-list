package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(value);
        if (index == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrev(newNode);
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            add(value);
            return;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            Node<T> prevNode = current.getPrev();

            prevNode.setNext(newNode);
            newNode.setPrev(prevNode);

            newNode.setNext(current);
            current.setPrev(newNode);
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T value : list) {
            add(value);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (T) current.getValue();
    }

    @Override
    public T set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        T result = (T) current.getValue();
        current.setValue(value);
        return result;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        T value = (T) current.getValue();
        unlinkNode(current);
        size--;
        return value;
    }

    @Override
    public boolean remove(T target) {
        Node<T> current = head;

        while (current != null) {
            if (java.util.Objects.equals(current.getValue(), target)) {
                unlinkNode(current);
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    private void unlinkNode(Node<T> node) {
        Node<T> prevNode = node.getPrev();
        Node<T> nextNode = node.getNext();

        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.setNext(nextNode);
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.setPrev(prevNode);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
