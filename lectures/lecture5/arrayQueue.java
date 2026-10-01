public class arrayQueue<E> {
    private int size = 0;
    private int f = 0;
    private E[] array;

    @SuppressWarnings("unchecked")
    public arrayQueue(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    public void enqueue(E e) {
        if (size == array.length) {
            throw new IllegalStateException("Queue is full");
        }
        array[(f + size) % array.length] = e;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E removed = array[f];
        array[f] = null;
        f = (f + 1) % array.length;
        size--;
        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}