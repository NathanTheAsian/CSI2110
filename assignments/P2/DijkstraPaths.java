import java.util.ArrayList;
import java.util.Arrays;

import net.datastructures.HeapAdaptablePriorityQueue;
import net.datastructures.Entry;

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
        if (!network.open[source] || !network.open[target]) {
            return new Route(-1, new int[0]);
        }
        if (source == target) {
            return new Route(0, new int[] {source});
        }

        long[] distance = new long[network.vertexCount];
        int[] previous = new int[network.vertexCount];
        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(previous, -1);
        distance[source] = 0;

        HeapAdaptablePriorityQueue<Long, Integer> pq = new HeapAdaptablePriorityQueue<>();
        ArrayList<Entry<Long, Integer>> entries = new ArrayList<>(network.vertexCount);
        for (int v = 0; v < network.vertexCount; v++) {
            entries.add(network.open[v] ? pq.insert(distance[v], v) : null);
        }

        while (!pq.isEmpty()) {
            Entry<Long, Integer> minimum = pq.removeMin();
            int u = minimum.getValue();
            entries.set(u, null); // Removed vertices have finalized distances.
            if (minimum.getKey() == Long.MAX_VALUE) {
                break; // All remaining vertices are unreachable from source.
            }
            if (u == target) {
                break;
            }

            for (int i = 0; i < network.neighbors[u][4]; i++) {
                int v = network.neighbors[u][i];
                if (entries.get(v) == null) {
                    continue; // Skip blocked or already finalized vertices.
                }
                long candidate = distance[u] + network.travelTime[u][i];
                if (candidate < distance[v]) {
                    distance[v] = candidate;
                    previous[v] = u;
                    pq.replaceKey(entries.get(v), candidate);
                }
            }
        }

        if (distance[target] == Long.MAX_VALUE) {
            return new Route(-1, new int[0]);
        }

        // Follow predecessors backward, then fill the route in source-to-target order.
        int length = 0;
        for (int v = target; v != -1; v = previous[v]) {
            length++;
        }
        int[] vertices = new int[length];
        int v = target;
        for (int i = length - 1; i >= 0; i--) {
            vertices[i] = v;
            v = previous[v];
        }
        return new Route(distance[target], vertices);

    }

   
}
