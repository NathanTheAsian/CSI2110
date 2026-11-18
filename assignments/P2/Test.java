
public class Test {
    public static void main(String[] args) {

        int[][] ROADS = {
        {0,1,9}, 
        {1,2,1}, 
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
        {{0,2}, {1,2}, {1,1}, {2,3}} // vertex 11 is already open
    };

        
        System.out.println("nodeGrid:");

        Partition partition = new ListPartition(12);
        GridNetwork grid = new GridNetwork(3, 4, ROADS, partition);
        GridNetwork network = new GridNetwork(3, 4, ROADS, new ListPartition(12));
        GridNetwork.Status status = null;
        int stage = args.length == 2 ? Integer.parseInt(args[1]) : 2;
        for (int s = 0; s <= stage; s++)    {
            status = network.repairStage(REPAIRS[s], QUERIES);
        }
        
        
    }
}