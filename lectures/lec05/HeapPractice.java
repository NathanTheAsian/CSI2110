public class HeapPractice {

    public abstract static class MaxHeap {
        public abstract void insert(int value);

        public abstract int peekMax();

        public abstract int extractMax();

        public abstract int size();

        public abstract boolean isEmpty();

        public abstract void buildHeap(int[] values);

        protected abstract void siftUp(int index);

        protected abstract void siftDown(int index);

        protected abstract int parent(int index);

        protected abstract int leftChild(int index);
            r

        protected abstract int rightChild(int index);

        protected abstract void swap(int firstIndex, int secondIndex);
    }

    public abstract static class MinHeap {
        public abstract void insert(int value);

        public abstract int peekMin();

        public abstract int extractMin();

        public abstract int size();

        public abstract boolean isEmpty();

        public abstract void buildHeap(int[] values);

        protected abstract void siftUp(int index);

        protected abstract void siftDown(int index);

        protected abstract int parent(int index);

        protected abstract int leftChild(int index);

        protected abstract int rightChild(int index);

        protected abstract void swap(int firstIndex, int secondIndex);
    }
}
