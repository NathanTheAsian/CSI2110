/** Partition implemented using linked list. */
public final class ListPartition implements Partition {
    private static final class Node {
        Node next;
        Cluster owner;
    }

    private static final class Cluster {
        final int id;
        int size = 1;
        Node head;
        Node tail;
        Cluster(int id) { this.id = id; }
    }

    private final Node[] nodes;
    private int clusters;
    private long visits;

    public ListPartition(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative partition size");
        nodes = new Node[n];
        clusters = n;
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            Cluster cluster = new Cluster(i);
            node.owner = cluster;
            cluster.head = cluster.tail = node;
            nodes[i] = node;
        }
    }

    public int find(int x) { return nodes[x].owner.id; }
    public int size(int x) { return nodes[x].owner.size; }
    public int count() { return clusters; }
    public long elementVisits() { return visits; }

    public boolean union(int x, int y) {
        Cluster a = nodes[x].owner;
        Cluster b = nodes[y].owner;
        if (a == b) return false;
        if (a.size < b.size || (a.size == b.size && a.id > b.id)) {
            Cluster temp = a;
            a = b;
            b = temp;
        }
        for (Node node = b.head; node != null; node = node.next) {
            node.owner = a;
            visits++;
        }
        a.tail.next = b.head;
        a.tail = b.tail;
        a.size += b.size;
        b.head = b.tail = null;
        b.size = 0;
        clusters--;
        return true;
    }
}
