import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/** PROVIDED: reads, validates, and reports one complete repair stage at a time. */
public final class TestGridNetwork {
    private TestGridNetwork() { }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(System.out);
        try {
            run(input, output);
        } catch (IllegalArgumentException ex) {
            output.flush();
            System.err.println("Input error: " + ex.getMessage());
            System.exit(1);
        } catch (UnsupportedOperationException ex) {
            output.flush();
            System.err.println("Starter task: " + ex.getMessage());
            System.exit(2);
        }
    }

    private static void run(BufferedReader input, PrintWriter output) throws IOException {
        int[] dimensions = integers(requiredLine(input, "dimensions"), 2);
        int n = dimensions[0], m = dimensions[1];
        if (n < 1 || m < 1 || (long) n * m > 400)
            throw new IllegalArgumentException("Use n,m >= 1 and n*m <= 400");
        int vertices = n * m;
        int roadCount = n * (m - 1) + (n - 1) * m;
        int[][] roads = new int[roadCount][];
        for (int i = 0; i < roadCount; i++)
            roads[i] = integers(requiredLine(input, "road record"), 3);
        validateRoads(n, m, roads);

        int q = integers(requiredLine(input, "query count"), 1)[0];
        if (q < 1 || q > 20) throw new IllegalArgumentException("q must be in 1..20");
        int[][] queries = new int[q][];
        for (int j = 0; j < q; j++) {
            queries[j] = integers(requiredLine(input, "query pair"), 2);
            for (int v : queries[j]) {
                if (v < 0 || v >= vertices)
                    throw new IllegalArgumentException("Query endpoint outside the grid");
            }
        }

        // Change only this construction if you use your own P1 implementation.
        Partition partition = new ListPartition(vertices);
        GridNetwork network = new GridNetwork(n, m, roads, partition);
        long stage = 0;
        GridNetwork.Status status = network.repairStage(new int[0][2], queries);
        printReport(output, stage, network, partition, queries, status);

        String line;
        while ((line = nextLine(input)) != null) {
            String[] words = line.split("\\s+");
            if (words.length == 1 && words[0].equals("END")) return;
            if (words.length != 2 || !words[0].equals("STAGE"))
                throw new IllegalArgumentException("Expected STAGE b or END");
            int b = integer(words[1]);
            if (b < 1) throw new IllegalArgumentException("A repair stage needs b >= 1");
            int[][] repairs = new int[b][];
            for (int i = 0; i < b; i++) {
                repairs[i] = integers(requiredLine(input, "complete repair stage"), 2);
                int r = repairs[i][0], c = repairs[i][1];
                if (r < 0 || r >= n || c < 0 || c >= m)
                    throw new IllegalArgumentException("Repair coordinate outside the grid");
            }
            // Apply repairs only after every coordinate in this stage is valid.
            status = network.repairStage(repairs, queries);
            printReport(output, ++stage, network, partition, queries, status);
        }
    }

    private static void validateRoads(int n, int m, int[][] roads) {
        int vertices = n * m;
        boolean[][] seen = new boolean[vertices][2]; // right and down roads
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            if (u < 0 || u >= vertices || v < 0 || v >= vertices || u >= v)
                throw new IllegalArgumentException("Road needs in-range identifiers with u < v");
            if (time < 1 || time > 1_000_000_000)
                throw new IllegalArgumentException("Road time must be in 1..1000000000");
            int direction;
            if (v == u + 1 && u / m == v / m) direction = 0;
            else if (v == u + m) direction = 1;
            else throw new IllegalArgumentException("Road endpoints are not grid neighbors");
            if (seen[u][direction]) throw new IllegalArgumentException("Duplicate road");
            seen[u][direction] = true;
        }
    }

    private static String nextLine(BufferedReader input) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return null;
    }

    private static String requiredLine(BufferedReader input, String context) throws IOException {
        String line = nextLine(input);
        if (line == null) throw new IllegalArgumentException("Expected " + context + " before EOF");
        return line;
    }

    private static int integer(String word) {
        try { return Integer.parseInt(word); }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid integer: " + word);
        }
    }

    private static int[] integers(String line, int count) {
        String[] words = line.split("\\s+");
        if (words.length != count)
            throw new IllegalArgumentException("Expected " + count + " integers on a line");
        int[] values = new int[count];
        for (int i = 0; i < count; i++) values[i] = integer(words[i]);
        return values;
    }

    private static void printReport(PrintWriter out, long stage, GridNetwork network,
                                    Partition partition, int[][] queries,
                                    GridNetwork.Status status) {
        checkStatus(network, partition, queries, status);
        if (stage > 0) out.println();
        out.println("Stage " + stage);
        out.println("Connectivity: opened=" + status.opened
                + " count=" + status.components.length
                + " components=" + pairs(status.components));
        out.print("  ");
        for (int j = 0; j < queries.length; j++) {
            if (j > 0) out.print(" ");
            out.print(pair(queries[j]) + "=" + (status.connected[j] ? 1 : 0));
        }
        out.println();

        out.println("Fastest routes:");
        for (int j = 0; j < queries.length; j++) {
            int[] query = queries[j];
            DijkstraPaths.Route route = DijkstraPaths.shortestRoute(network, query[0], query[1]);
            checkRoute(network, query, status.connected[j], route);
            out.println("  " + pair(query) + ": " + (route.time < 0 ? "-1"
                    : "time=" + route.time + " route=" + vertices(route.vertices)));
        }

        out.println("Critical roads:");
        for (int j = 0; j < queries.length; j++) {
            int[] query = queries[j];
            int[][] critical = RoadAnalysis.criticalRoads(network, query[0], query[1]);
            require(critical != null, "Null critical-road result");
            require(status.connected[j] || critical.length == 0,
                    "Critical roads returned for an unreachable pair");
            for (int[] road : critical)
                require(road != null && road.length == 2 && road[0] < road[1]
                        && roadTime(network, road[0], road[1]) > 0, "Invalid critical road");
            Arrays.sort(critical, (a, b) -> {
                int first = Integer.compare(a[0], b[0]);
                return first != 0 ? first : Integer.compare(a[1], b[1]);
            });
            for (int k = 1; k < critical.length; k++)
                require(!Arrays.equals(critical[k - 1], critical[k]), "Duplicate critical road");
            out.println("  " + pair(query) + ": "
                    + (status.connected[j] ? pairs(critical) : "-1"));
        }
        out.flush();
    }

    private static void checkStatus(GridNetwork network, Partition partition,
                                    int[][] queries, GridNetwork.Status status) {
        require(status != null && status.components != null && status.connected != null,
                "Null Status result or field");
        int opened = 0, sum = 0, previousLabel = -1;
        for (boolean state : network.open) if (state) opened++;
        require(opened == status.opened, "Incorrect open count");
        require(status.components.length == partition.count()
                - (network.vertexCount - opened), "Incorrect component count");
        for (int[] component : status.components) {
            require(component != null && component.length == 2
                    && component[0] > previousLabel && component[0] < network.vertexCount
                    && network.open[component[0]] && component[1] > 0,
                    "Invalid component row");
            previousLabel = component[0];
            sum += component[1];
        }
        require(sum == opened, "Component sizes do not sum to opened");
        require(status.connected.length == queries.length, "Incorrect query-result count");
        for (int j = 0; j < queries.length; j++) {
            int s = queries[j][0], t = queries[j][1];
            boolean connected = network.open[s] && network.open[t]
                    && partition.find(s) == partition.find(t);
            require(status.connected[j] == connected, "Incorrect Partition connectivity");
        }
    }

    private static void checkRoute(GridNetwork network, int[] query, boolean connected,
                                   DijkstraPaths.Route route) {
        require(route != null && route.vertices != null, "Null route result");
        require((route.time >= 0) == connected, "Dijkstra and Partition disagree");
        if (!connected) {
            require(route.time == -1 && route.vertices.length == 0, "Invalid unreachable result");
            return;
        }
        int[] path = route.vertices;
        require(path.length > 0 && path[0] == query[0]
                && path[path.length - 1] == query[1], "Invalid route endpoints");
        boolean[] seen = new boolean[network.vertexCount];
        long sum = 0;
        for (int i = 0; i < path.length; i++) {
            int v = path[i];
            require(v >= 0 && v < network.vertexCount && network.open[v] && !seen[v],
                    "Invalid, blocked, or repeated route vertex");
            seen[v] = true;
            if (i > 0) {
                int time = roadTime(network, path[i - 1], v);
                require(time > 0, "Route uses a non-usable road");
                sum += time;
            }
        }
        require(sum == route.time, "Incorrect route travel time");
    }

    private static int roadTime(GridNetwork network, int u, int v) {
        if (u < 0 || v < 0 || u >= network.vertexCount || v >= network.vertexCount
                || !network.open[u] || !network.open[v]) return -1;
        for (int i = 0; i < network.neighbors[u].length; i++)
            if (network.neighbors[u][i] == v) return network.travelTime[u][i];
        return -1;
    }

    private static String pair(int[] pair) { return "(" + pair[0] + "," + pair[1] + ")"; }

    private static String pairs(int[][] values) {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) text.append(',');
            text.append(pair(values[i]));
        }
        return text.append(']').toString();
    }

    private static String vertices(int[] values) {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) text.append(',');
            text.append(values[i]);
        }
        return text.append(']').toString();
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new IllegalStateException(message);
    }
}
