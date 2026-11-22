
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
        /*
        Starting at vertex(source) check each neighbor and their traveltimes (refer to gridNetwork data)

        */
        
        
    }
}
