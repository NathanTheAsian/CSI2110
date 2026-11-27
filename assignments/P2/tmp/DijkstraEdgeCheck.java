import java.util.Arrays;
public class DijkstraEdgeCheck {
    static void check(GridNetwork g, int s, int t, long expected, int... path) {
        DijkstraPaths.Route r = DijkstraPaths.shortestRoute(g, s, t);
        if (r.time != expected || !Arrays.equals(r.vertices, path))
            throw new AssertionError(s + " -> " + t + ": " + r.time + " " + Arrays.toString(r.vertices));
    }
    public static void main(String[] args) {
        GridNetwork g = new GridNetwork(1, 4, new int[][] {
            {0,1,1000000000}, {1,2,1000000000}, {2,3,1000000000}
        }, new ListPartition(4));
        check(g, 0, 0, -1);
        g.repairStage(new int[][] {{0,0},{0,3}}, new int[0][2]);
        check(g, 0, 0, 0, 0);
        check(g, 0, 3, -1);
        check(g, 0, 1, -1);
        g.repairStage(new int[][] {{0,1},{0,2}}, new int[0][2]);
        check(g, 0, 3, 3000000000L, 0,1,2,3);
        check(g, 3, 0, 3000000000L, 3,2,1,0);
        System.out.println("Passed blocked endpoints, self routes, disconnected vertices, cumulative repairs, reverse routes, and long distances.");
    }
}
