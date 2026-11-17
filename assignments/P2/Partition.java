/** Partition interface from Programming Assignment 1. */
public interface Partition {
    int find(int x);
    boolean union(int x, int y);
    int size(int x);
    int count();
    long elementVisits();
}
