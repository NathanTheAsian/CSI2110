import java.util.Arrays;

/** Prints Example 1 in the TestGridNetwork format. Example: java TestExample1 2 2. */
public final class TestExample1 {
    // Fixed input from Example 1. Each road is {u,v,time}.
    private static final int[][] ROADS = {
        {0,1,9}, {1,2,1}, {2,3,1}, {4,5,1}, {5,6,9}, {6,7,1},
        {8,9,1}, {9,10,1}, {10,11,1}, {0,4,1}, {1,5,1}, {2,6,1},
        {3,7,1}, {4,8,1}, {5,9,1}, {6,10,1}, {7,11,1}
    };
    private static final int[][] QUERIES = {{0,11}, {0,8}, {10,11}};
    // Repairs are {row,column} coordinates. Stage 2 builds on stage 1.
    private static final int[][][] REPAIRS = {
        {},
        {{0,0}, {0,1}, {1,0}, {2,0}, {2,2}, {2,3}},
        {{0,2}, {1,2}, {1,1}, {2,3}} // vertex 11 is already open
    };

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2 || !args[0].matches("[123]")
                || (args.length == 2 && !args[1].matches("[012]")))
            throw new IllegalArgumentException("Usage: java TestExample1 <part 1-3> [stage 0-2]");
        int part = Integer.parseInt(args[0]);
        int stage = args.length == 2 ? Integer.parseInt(args[1]) : 2;

        GridNetwork network = new GridNetwork(3, 4, ROADS, new ListPartition(12));
        GridNetwork.Status status = null;
        for (int s = 0; s <= stage; s++)
            status = network.repairStage(REPAIRS[s], QUERIES);

        System.out.println("Stage " + stage);
        if (part == 1) printConnectivity(status);
        else if (part == 2) printRoutes(network);
        else printCriticalRoads(network, status);
    }

    private static void printConnectivity(GridNetwork.Status status) {
        System.out.println("Connectivity: opened=" + status.opened
                + " count=" + status.components.length
                + " components=" + pairs(status.components));
        System.out.print("  ");
        for (int i = 0; i < QUERIES.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(pair(QUERIES[i]) + "=" + (status.connected[i] ? 1 : 0));
        }
        System.out.println();
    }

    private static void printRoutes(GridNetwork network) {
        System.out.println("Fastest routes:");
        for (int[] query : QUERIES) {
            DijkstraPaths.Route route = DijkstraPaths.shortestRoute(network, query[0], query[1]);
            System.out.println("  " + pair(query) + ": " + (route.time < 0 ? "-1"
                    : "time=" + route.time + " route="
                    + Arrays.toString(route.vertices).replace(" ", "")));
        }
    }

    private static void printCriticalRoads(GridNetwork network, GridNetwork.Status status) {
        System.out.println("Critical roads:");
        for (int i = 0; i < QUERIES.length; i++) {
            int[] query = QUERIES[i];
            int[][] roads = RoadAnalysis.criticalRoads(network, query[0], query[1]);
            System.out.println("  " + pair(query) + ": "
                    + (status.connected[i] ? orderedRoads(roads) : "-1"));
        }
    }

    // Row order is unrestricted. Sort a copy for consistent output.
    private static String orderedRoads(int[][] roads) {
        int[][] sorted = roads.clone();
        Arrays.sort(sorted, (a, b) -> a[0] == b[0]
                ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        return pairs(sorted);
    }

    private static String pair(int[] pair) {
        return "(" + pair[0] + "," + pair[1] + ")";
    }

    private static String pairs(int[][] values) {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) text.append(',');
            text.append(pair(values[i]));
        }
        return text.append(']').toString();
    }
}
