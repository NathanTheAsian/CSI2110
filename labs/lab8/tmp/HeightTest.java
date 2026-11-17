import net.datastructures.TreeMap;
import net.datastructures.AVLTreeMap;

public class HeightTest extends Verify {
  private static void check(boolean condition, String message) {
    if (!condition) throw new AssertionError(message);
  }

  public static void main(String[] args) {
    HeightTest test = new HeightTest();
    TreeMap<Integer, Integer> bst = new TreeMap<>();
    AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
    check(test.height(bst) == 0 && test.height(avl) == 0, "Empty heights");
    for (int i = 1; i <= 7; i++) {
      bst.put(i, i);
      avl.put(i, i);
      if (i == 1) check(bst.height() == 1 && avl.height() == 1, "Singleton heights");
    }
    check(bst.height() == 7 && avl.height() == 3, "Sorted insertion heights");
    bst.put(4, 99);
    avl.put(4, 99);
    check(bst.size() == 7 && avl.size() == 7, "Duplicate keys");
    check(bst.height() == 7 && avl.height() == 3, "Duplicate heights");
    for (int i = 1; i <= 7; i++) { bst.remove(i); avl.remove(i); }
    check(bst.height() == 0 && avl.height() == 0, "Empty after removal");
    for (int k : new int[] {4, 2, 6, 1, 3, 5, 7}) bst.put(k, k);
    check(bst.height() == 3, "Balanced BST");
    test.randomTrees(0, 5);
    check(test.d_totalHeightBST == 0 && test.d_totalHeightAVL == 0, "Empty trials");
    test.randomTrees(1, 5);
    check(test.d_totalHeightBST == 5 && test.d_totalHeightAVL == 5, "Singleton trials");
    test.randomTrees(2, 10);
    check(test.d_minBST == 2 && test.d_maxBST == 2 && test.d_totalHeightBST == 20,
          "BST counters");
    check(test.d_minAVL == 2 && test.d_maxAVL == 2 && test.d_totalHeightAVL == 20,
          "AVL counters");
    test.printStatistics();
    System.out.println("All height and statistics checks passed.");
  }
}
