public class LinkedQueue<E> {
    private Node<E> front;   // first item in queue
    private Node<E> rear;    // last item in queue
    private int size;        // number of items

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E removed = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedQueue<String> q = new LinkedQueue<>();

        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");

        System.out.println(q.dequeue()); // A
        System.out.println(q.dequeue()); // B
        System.out.println(q.isEmpty()); // false
        System.out.println(q.dequeue()); // C
        System.out.println(q.isEmpty()); // true
    }
}
