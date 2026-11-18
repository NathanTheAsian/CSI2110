import java.util.Arrays;

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

    public GridNetwork.Status currentStatus;

    /**
     * Each row of roads is {u,v,time}
     * The partition starts with n*m singleton clusters. All vertices start blocked.
     */
    public GridNetwork(int n, int m, int[][] roads, Partition partition) {
        this.n = n;
        this.m = m;
        this.vertexCount = n*m;
        this.travelTime = new int[n*m][5];
        this.partition = partition;
        this.open = new boolean[n*m];
        this.neighbors = new int[n*m][5]; 

        
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
        System.out.println("Neighbors");
        
        //Finding neighbors and linking travel time



        for (int count = 0;count < roads.length; count++) {

            int centerNode = roads[count][0];
            int oppositeNode = roads[count][1];
           
            arrayInsert(neighbors[centerNode], roads[count][1], neighbors[centerNode][4],false);
            arrayInsert(neighbors[oppositeNode], roads[count][0], neighbors[oppositeNode][4],false);
            neighbors[centerNode][4]++;
            neighbors[oppositeNode][4]++; 
            
                       travelTime[centerNode][travelTime[centerNode][4]] = roads[count][2];
                       travelTime[oppositeNode][travelTime[oppositeNode][4]] = roads[count][2];
           
           
                       // arrayInsert(travelTime[centerNode],roads[count][2],travelTime[centerNode][4],true);
                       // arrayInsert(travelTime[oppositeNode],roads[count][2],travelTime[oppositeNode][4],true);
                       

            travelTime[centerNode][4]++;
            travelTime[oppositeNode][4]++;
            


        }

        // // Print neighbors after they're filled
        // printAll();
        
        
    }
    public void printAll() {
        for (int i = 0; i < neighbors.length; i++) {
            System.out.print("Node " + i + ": ");
            for (int j = 0; j < neighbors[i][4]; j++) {
                System.out.print("(" + travelTime[i][j] + ",");
                System.out.print(neighbors[i][j] + ") ");
            }
            System.out.println("| " + neighbors[i][4]);

        }
    }

    public void arrayInsert(int[] data, int value, int size, boolean duplicateCheck){

            for(int i = 0; i <= size ; i++){
                if(!duplicateCheck && data[i] == value){
                    return;
                }

                if(data[i] == 0){
                    data[i] = value;
                    return;
                }
                
                if(data[i] > value){
                    int temp = data[i];
                    data[i] = value;
                    value = temp;
                }
            }
        }


    /**
     * Apply this stage's {row,col} repairs cumulatively, then summarize the network.
     * Queries contain {source,target} vertex identifiers.
     * id(r,c) = r * m + c
     */
    public Status repairStage(int[][] repairs, int[][] queries) {
        int opened;
        
        for (int i = 0; i < repairs.length;i++) {    
            int row = repairs[i][0]; //r
            int col = repairs[i][1]; //c
            System.out.println(repairs[i].length + " Repairs");
            opened += repairs[i].length;
            
            System.out.println(" (" + row + "," + col + ") ");
            System.out.println("Node " + (row * m + col));
            
            //opens road via boolean[] open at index id(r,c) 
            open[(row*m+col)] = true;
            
            System.out.println(Arrays.toString(open));
            System.out.println(opened + " open intersections");
        }
        return new Status(opened, null, null);
    }
}
