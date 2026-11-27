# CSI 2110 - Lab 8

Full name: Nathan Ng
Student number: 300494064

## Compile and run

From this directory, with a Java JDK installed:

```text
javac -d build net/datastructures/*.java Verify.java
java -cp build Verify
```

An optional argument selects the number of sizes to test, starting at 2 keys
and doubling each time. For example, `java -cp build Verify 5` tests sizes
2, 4, 8, 16, and 32. The default runs eight sizes, through 256 keys.
Each size uses max(10, 4*N) independent trials.

## Implementation

- Added public `TreeMap.height()`, inherited by `AVLTreeMap`. It calls the
  underlying binary tree's recursive height method. External sentinel leaves
  have height 0, so an empty map has height 0 and a one-key map has height 1.
- Updated `Verify.height` to accept a `TreeMap<K,V>` and return its height.
- Completed `randomTrees`: every trial creates fresh BST and AVL maps, skips
  duplicate random keys, and inserts the same keys in the same order into both.
  Values are the keys themselves (never null), so `get(key) == null` identifies
  a key not yet inserted. Both heights are recorded once per trial.
- Corrected the last output column to use the natural logarithm of the number
  of keys N, rather than the number of trials.

## Results and analysis

The first min/max/avg group is the regular BST; the second is the AVL tree.
`results.txt` contains one complete run. Random results will vary between runs.

In that run, at N=256 the BST had heights 13 to 24, averaging 16.861. The AVL
tree had heights 9 to 10, averaging 9.676. The AVL trees were shorter on average
and had a smaller height range because rotations maintain the balance
condition after insertion.

A BST can have height N when keys arrive in sorted order. Random insertion
typically gives logarithmic expected height, but does not guarantee balance.
An AVL tree guarantees O(log N) height. Thus both sets of random-trial averages
grow roughly logarithmically, while the AVL tree provides a tighter worst-case
bound. The log(N) column illustrates growth rate, not an exact height prediction.

The height traversal visits all internal and external nodes, so it takes O(N)
time and O(h) recursion space for height h.

## Validation

All provided Java sources compiled successfully. Checks passed for empty and
single-key maps, sorted insertion (7 keys: BST height 7, AVL height 3), a balanced
BST, duplicate replacement, removal back to empty, and trial statistics for
0, 1, and 2 keys. The default eight-size experiment also completed successfully.
