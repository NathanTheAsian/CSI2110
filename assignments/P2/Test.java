import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        // Accept a stage alone, or the existing <part> <stage> invocation.
        if (args.length > 2 || (args.length > 0
                && !args[args.length - 1].matches("[012]"))) {
            System.err.println("Usage: java Test [stage 0-2] or java Test <part> <stage 0-2>");
            return;
        }
        int stage = args.length == 0 ? 2 : Integer.parseInt(args[args.length - 1]);

        int[][] ROADS = {
            {0,1,9},
            {1,2,2},
            {2,3,1},
            {4,5,1},
            {5,6,9},
            {6,7,1},
            {8,9,1},
            {9,10,1},
            {10,11,1},
            {0,4,1},
            {1,5,1},
            {2,6,1},
            {3,7,1},
            {4,8,1},
            {5,9,1},
            {6,10,1},
            {7,11,1}
        };

        int[][] QUERIES = {{0,11}, {0,8}, {10,11}};

        int[][][] REPAIRS = {
            {},
            {{0,0}, {0,1}, {1,0}, {2,0}, {2,2}, {2,3}},
            {{0,2}, {1,2}, {1,1}, {2,3}}
        };

        System.out.println("nodeGrid:");

        GridNetwork network = new GridNetwork(3, 4, ROADS, new ListPartition(12));
        GridNetwork.Status status = null;

        network.printAll();

        for (int s = 0; s <= stage; s++) {

            System.out.println("\n========== Stage: " + s + " ==========");

            status = network.repairStage(REPAIRS[s], QUERIES);

            // Repair statistics
            System.out.println("\n--- Repair Status ---");
            System.out.println("Opened: " + status.opened);
            System.out.println("Components: " + status.components.length);
            System.out.println("Open vertices: " + Arrays.toString(network.open));

            // Component statistics
            for (int[] component : status.components) {
                System.out.println(
                    "Label: " + component[0] +
                    " | Size: " + component[1]
                );
            }

            // Vertex and edge statistics
            System.out.println("\n--- Network Status ---");

            for (int i = 0; i < network.vertexCount; i++) {

                System.out.println("Vertex: " + i);
                System.out.println("Open: " + network.open[i]);
                int neighborCount = network.neighbors[i][4];
                System.out.println("Neighbors: " +
                    Arrays.toString(Arrays.copyOf(network.neighbors[i], neighborCount)));
                System.out.println("Weights: " +
                    Arrays.toString(Arrays.copyOf(network.travelTime[i], neighborCount)));

                System.out.println();
            }

            // Dijkstra testing
            System.out.println("\n--- Dijkstra Status ---");

            for (int i = 0; i < QUERIES.length; i++) {

                int source = QUERIES[i][0];
                int target = QUERIES[i][1];

                System.out.println("Source: " + source);
                System.out.println("Target: " + target);
                System.out.println("Connected: " + status.connected[i]);
                try {
                    DijkstraPaths.Route route =
                        DijkstraPaths.shortestRoute(network, source, target);
                    System.out.println("Minimum time: " + route.time);
                    System.out.println("Path: " + Arrays.toString(route.vertices));
                } catch (RuntimeException ex) {
                    System.out.println("Route FAILED: " + ex);
                }
                System.out.println();
            }

            System.out.println("========== End Stage: " + s + " ==========");
        }
    }
}
