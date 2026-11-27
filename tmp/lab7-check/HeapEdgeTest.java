import java.util.Random;

public class HeapEdgeTest {
    private static void check(boolean condition) {
        if (!condition) throw new AssertionError();
    }

    public static void main(String[] args) {
        HeapPriorityQueue<Integer, String> empty = new HeapPriorityQueue<>(0);
        check(empty.isEmpty() && empty.size() == 0);
        check(empty.min() == null && empty.removeMin() == null);
        try { empty.insert(1, "x"); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
        try { new HeapPriorityQueue<Integer, String>(-1); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }

        HeapPriorityQueue<String, String> single = new HeapPriorityQueue<>(1);
        Entry<String, String> entry = single.insert("a", null);
        check(single.min() == entry && single.size() == 1);
        try { single.insert("b", "full"); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
        check(single.removeMin() == entry && single.isEmpty());
        check(single.removeMin() == null);
        single.insert("b", "reuse");
        check(single.removeMin().getValue().equals("reuse"));

        HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<>();
        try { heap.insert(null, 0); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
        check(heap.isEmpty());
        java.util.PriorityQueue<Integer> reference = new java.util.PriorityQueue<>();
        Random random = new Random(2110);
        for (int i = 0; i < 20000; i++) {
            if (reference.isEmpty() || (reference.size() < 100 && random.nextBoolean())) {
                int key = random.nextInt(101) - 50;
                Entry<Integer, Integer> added = heap.insert(key, key);
                check(added.getKey().equals(key) && added.getValue().equals(key));
                reference.add(key);
            } else {
                Entry<Integer, Integer> removed = heap.removeMin();
                check(removed.getKey().equals(reference.remove()));
                check(removed.getValue().equals(removed.getKey()));
            }
            check(heap.size() == reference.size());
            check(heap.isEmpty() == reference.isEmpty());
            check(reference.isEmpty() ? heap.min() == null : heap.min().getKey().equals(reference.peek()));
        }
        while (!reference.isEmpty()) check(heap.removeMin().getKey().equals(reference.remove()));
        check(heap.isEmpty() && heap.removeMin() == null);
        System.out.println("Edge cases and 20,000 randomized operations passed.");
    }
}
