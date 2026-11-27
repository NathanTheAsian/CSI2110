import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Rectangular road network with cumulative intersection repairs. */
public final class GridNetwork {
    public final int n;
    public final int m;
    public final int vertexCount;
    public final Partition partition;
    public final boolean[] open;
    // Slots 0..count-1 hold sorted neighbors (blocked or open); slot 4 holds count.
    public final int[][] neighbors;
    // travelTime[v][i] matches neighbors[v][i]; slot 4 holds the same count.
    public final int[][] travelTime;

    /** Result returned by repairStage; component rows are {label,size}. */
    public static final class Status {
        public final int opened;
        public final int[][] components;
        public final boolean[] connected;

        public Status(int opened, int[][] components, boolean[] connected) {
            this.opened = opened;
            this.components = components;
            this.connected = connected;
        }
    }

    private GridNetwork.Status currentStatus;

    /**
     * Each row of roads is {u,v,time}
     * The partition starts with n*m singleton clusters. All vertices start blocked.
     */
    public GridNetwork(int n, int m, int[][] roads, Partition partition) {
        this.n = n;
        this.m = m;
        this.vertexCount = n * m;
        this.travelTime = new int[n * m][5];
        this.partition = partition;
        this.open = new boolean[n * m];
        this.neighbors = new int[n * m][5];
        this.currentStatus = new Status(0, new int[0][2], new boolean[0]);

        // Every road is usable in both directions once its endpoints are open.
        for (int[] road : roads) {
            addNeighbor(road[0], road[1], road[2]);
            addNeighbor(road[1], road[0], road[2]);
        }
    }

    public void printAll() {
        for (int i = 0; i < neighbors.length; i++) {
            System.out.print("Node " + i + ": ");
            for (int j = 0; j < neighbors[i][4]; j++) {

                //travel time from node i to node j 
                System.out.print("(" + travelTime[i][j] + ",");
                System.out.print(neighbors[i][j] + ") ");
            }
            System.out.println("| " + neighbors[i][4]);

        }
    }

    public void printPartition(Partition partition) {
        int n = this.vertexCount; // Use vertexCount instead of totalSize()

        // Group elements by cluster
        java.util.Map<Integer, java.util.List<Integer>> clusters = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            int clusterId = partition.find(i);
            clusters.computeIfAbsent(clusterId, k -> new java.util.ArrayList<>()).add(i);
        }

        // Print clusters grouped in curly braces
        boolean first = true;
        for (int i = 0; i < n; i++) {
            int clusterId = partition.find(i);
            if (clusterId == i) { // Only print when we encounter the cluster root
                if (!first)
                    System.out.print(", ");
                System.out.print("{");
                java.util.List<Integer> elements = clusters.get(i);
                for (int j = 0; j < elements.size(); j++) {
                    if (j > 0)
                        System.out.print(", ");
                    System.out.print(elements.get(j));
                }
                System.out.print("}");
                first = false;
            }
        }
        System.out.println();
    }

    public void printComponents() {
        int[][] comp = currentStatus.components;
        for (int i = 0; i < comp.length; i++) {
            System.out.print("Component " + i + ": ");
            for (int j = 0; j < 2; j++) {
                System.out.print(comp[i][j] + " ");
            }

            System.out.println();

        }

    }

    public void printNeighbors(int Node) {
        for (int i = 0; i < neighbors[Node][4]; i++) {
            System.out.print(neighbors[Node][i] + " ");
        }
    }

    /** Insert a neighbor and its weight together, preserving vertex-ID order. */
    private void addNeighbor(int vertex, int neighbor, int time) {
        int count = neighbors[vertex][4];
        int index = count;

        // Only occupied slots are examined, so vertex 0 is not an empty marker.
        while (index > 0 && neighbors[vertex][index - 1] > neighbor) {
            neighbors[vertex][index] = neighbors[vertex][index - 1];
            travelTime[vertex][index] = travelTime[vertex][index - 1];
            index--;
        }

        neighbors[vertex][index] = neighbor;
        travelTime[vertex][index] = time;
        neighbors[vertex][4] = count + 1;
        travelTime[vertex][4] = count + 1;
    }

    /**
     * Apply this stage's {row,col} repairs cumulatively, then summarize the
     * network.
     * Queries contain {source,target} vertex identifiers.
     * id(r,c) = r * m + c
     */
    public Status repairStage(int[][] repairs, int[][] queries) {


        int opened = currentStatus.opened;

        for (int i = 0; i < repairs.length; i++) {
            int row = repairs[i][0]; // r
            int col = repairs[i][1]; // c
            int vertexID = (row * m + col);

            if (open[vertexID]) {
                continue;
            }
            open[vertexID] = true;
            opened++;

            for (int j = 0; j < neighbors[vertexID][4]; j++) {

                if (open[neighbors[vertexID][j]]
                        && (partition.find(neighbors[vertexID][j]) != partition.find(vertexID))) { // if these two neighbors open and in different clusters -> union them
                    partition.union(vertexID, neighbors[vertexID][j]);


                }

            }
        }
        int[][] newComponents = new int[vertexCount][2];
        
    
        Set<Integer> owners = new HashSet<>();
        int componentCount = 0;

        // Scanning IDs in order makes the first vertex each component's
        // smallest label, even when its partition representative is different.
        for (int i = 0; i < vertexCount; i++) {
            if (open[i] && owners.add(partition.find(i))) {
                newComponents[componentCount][0] = i;
                newComponents[componentCount][1] = partition.size(i);
                componentCount++;
            }
        }
        newComponents = Arrays.copyOf(newComponents, componentCount);


        boolean[] connected = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int source = queries[i][0];
            int target = queries[i][1];
            connected[i] = open[source] && open[target]
                    && partition.find(source) == partition.find(target);
        }

        GridNetwork.Status newStatus = new Status(opened, newComponents, connected);
        currentStatus = newStatus;
        return currentStatus;
    }
}
