
public class Test {
    public static void main(String[] args) {

        int[][] ROADS = {
        {0,1,9}, {1,2,1}, {2,3,1}, {4,5,1}, {5,6,9}, {6,7,1},
        {8,9,1}, {9,10,1}, {10,11,1}, {0,4,1}, {1,5,1}, {2,6,1},
        {3,7,1}, {4,8,1}, {5,9,1}, {6,10,1}, {7,11,1}
        };

        
        System.out.println("nodeGrid:");

        Partition partition = new ListPartition(12);
        GridNetwork grid = new GridNetwork(3, 4, ROADS, partition);

        
        
    }
}