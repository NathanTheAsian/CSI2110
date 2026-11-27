
/** Dijkstra algorithm implemented using adaptable priority queue. */
public final class DijkstraPaths {
    private DijkstraPaths() { }

    /** time is -1 and vertices is empty when the pair is unreachable. */
    public static final class Route {
        public final long time;
        public final int[] vertices;

        public Route(long time, int[] vertices) {
            this.time = time;
            this.vertices = vertices;
        }
    }

    /**
     * Return a minimum-time route, including both endpoints.
     * Use long distances and Long.MAX_VALUE for infinity
     */
    public static Route shortestRoute(GridNetwork network, int source, int target) {
        // TODO 3: Implement Dijkstra's algorithm with the provided adaptable queue.
        throw new UnsupportedOperationException("Complete DijkstraPaths.shortestRoute");
    }
}
