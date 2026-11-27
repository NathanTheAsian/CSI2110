
/** Rectangular road network with cumulative intersection repairs. */
public final class GridNetwork {
    public final int n;
    public final int m;
    public final int vertexCount;
    public final Partition partition;
    public final boolean[] open;
    // All grid neighbors (blocked or open), in strictly increasing vertex-ID order.
    public final int[][] neighbors;
    // travelTime[v][i] is the fixed time from v to neighbors[v][i].
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

    /**
     * Each row of roads is {u,v,time}
     * The partition starts with n*m singleton clusters. All vertices start blocked.
     */
    public GridNetwork(int n, int m, int[][] roads, Partition partition) {
        
        //initiate nodeGrid
        int vertexID = 0;
        int[][] nodeGrid = new int[n][m];
        for (int row = 0;row < n; row++) {
            for (int col = 0; col < m; col ++) {
                
                nodeGrid[row][col] = vertexID;
                System.out.print(vertexID + " ");

                vertexID++;
            }
            System.out.println(" ");
        }
        
        //Finding neighbors and linking travel time
        /*
        int[][] neighbors = {
            {0, left, right, top, bottom}, 
            ... 
            {(m*n), left, right, top, bottom}
        }; 

        
        */

        for (int row = 0;row < roads.length; row++) {
            System.out.print("v" + roads[row][0] + ", "); //root 
            System.out.print("u" + roads[row][1] + ", "); //neighbor to root
            System.out.println("time" + roads[row][2]); //travel time
        }
        System.out.println(" ");
    
        throw new UnsupportedOperationException("");
    }

    /**
     * Apply this stage's {row,col} repairs cumulatively, then summarize the network.
     * Queries contain {source,target} vertex identifiers.
     */
    public Status repairStage(int[][] repairs, int[][] queries) {
       
       // TODO 2: Implement the repair updates and connectivity report.
        throw new UnsupportedOperationException("Complete GridNetwork.repairStage");
    }
}
