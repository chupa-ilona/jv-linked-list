package core.basesyntax;

public class Node <T> {
    private T value;
    private Node <T> next;
    private Node <T> prev;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
