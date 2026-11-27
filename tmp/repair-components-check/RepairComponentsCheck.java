import java.util.Arrays;

public final class RepairComponentsCheck {
    private static int checks;

    private static GridNetwork.Status check(GridNetwork network, int[][] repairs,
                                             int[][] expected) {
        GridNetwork.Status status = network.repairStage(repairs, new int[0][2]);
        if (!Arrays.deepEquals(status.components, expected)) {
            throw new AssertionError("Expected " + Arrays.deepToString(expected)
                    + " but got " + Arrays.deepToString(status.components));
        }
        checks++;
        return status;
    }

    public static void main(String[] args) {
        GridNetwork single = new GridNetwork(1, 1, new int[0][3], new ListPartition(1));
        check(single, new int[0][2], new int[0][2]);
        check(single, new int[][]{{0, 0}}, new int[][]{{0, 1}});
        single.printComponents();
        check(single, new int[][]{{0, 0}, {0, 0}}, new int[][]{{0, 1}});

        GridNetwork line = new GridNetwork(1, 5,
                new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 1}, {3, 4, 1}},
                new ListPartition(5));
        GridNetwork.Status beforeMerge = check(line, new int[][]{{0, 4}, {0, 0}},
                new int[][]{{0, 1}, {4, 1}});
        check(line, new int[][]{{0, 3}}, new int[][]{{0, 1}, {3, 2}});
        check(line, new int[][]{{0, 2}}, new int[][]{{0, 1}, {2, 3}});
        if (line.partition.find(2) == 2) {
            throw new AssertionError("Test requires a representative above the minimum label");
        }
        check(line, new int[][]{{0, 1}}, new int[][]{{0, 5}});
        check(line, new int[0][2], new int[][]{{0, 5}});
        if (!Arrays.deepEquals(beforeMerge.components, new int[][]{{0, 1}, {4, 1}})) {
            throw new AssertionError("Previous stage components were mutated");
        }

        int[][] roads = {{0,1,9}, {1,2,1}, {2,3,1}, {4,5,1}, {5,6,9}, {6,7,1},
                {8,9,1}, {9,10,1}, {10,11,1}, {0,4,1}, {1,5,1}, {2,6,1},
                {3,7,1}, {4,8,1}, {5,9,1}, {6,10,1}, {7,11,1}};
        GridNetwork sample = new GridNetwork(3, 4, roads, new ListPartition(12));
        check(sample, new int[0][2], new int[0][2]);
        check(sample, new int[][]{{0,0}, {0,1}, {1,0}, {2,0}, {2,2}, {2,3}},
                new int[][]{{0,4}, {10,2}});
        check(sample, new int[][]{{0,2}, {1,2}, {1,1}, {2,3}}, new int[][]{{0,9}});
        System.out.println("PASS: " + checks + " component checks, stable snapshots, non-minimum representative");
    }
}
