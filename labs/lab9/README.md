# CSI2110 Lab 9: Graphs and Depth-First Search

Full name: [required before submission]
Student number: [required before submission]

## Compile and run

From this directory:

```text
javac SimpleGraph.java net/datastructures/*.java
java SimpleGraph graph.txt
java SimpleGraph graph2.txt
java SimpleGraph graph-custom.txt
```

Enter a vertex name at the prompt (for example, A). Vertex names are case-sensitive.
The program first prints the graph, then the DFS traversal. An unknown start
vertex produces an explanatory error.

## Implementation and analysis

The reader creates one vertex per distinct name and one undirected edge per
distinct pair; reversed duplicates are ignored. DFS marks the starting vertex
before exploring neighbors to avoid revisiting vertices in cycles. It uses
outgoingEdges(v) and opposite(v, edge) to recursively visit neighbors.
startVisit prints the vertex on discovery; finishVisit prints --- followed by
the vertex after all its neighbors have been explored. Each search resets the
visited table and visits only the component reachable from its start.

For a reachable component with V vertices and E edges, DFS takes expected
O(V + E) time using hash-table lookups and O(V) auxiliary space for the visited
table and recursion stack. Looking up the initial vertex by name takes
O(V_total) time. Neighbor iteration order is unspecified, so multiple traversal
orders can be correct.

## Verification

- graph.txt: 10 vertices and 11 edges; searches from A and J visit all 10.
- graph2.txt: 12 vertices and 14 edges; searches from A and X visit all 12.
- graph-custom.txt: a triangle with a branch (A through E), a disconnected
  pair (X, Y), and a self-loop (Z). The reversed Y X edge tests duplicate
  handling. There are 8 vertices and 7 edges.
- Custom starts A and E reach exactly A, B, C, D, E; X and Y reach exactly
  X, Y; Z reaches only Z.
- Checks verify exactly one start and finish per reachable vertex, properly
  nested finish events, and discovery along an edge from the active vertex.
- Repeated searches on one graph reset the visited state. Unknown start
  vertices are rejected.

## Submission

Fill in the identity fields above before submitting. The ZIP contains this
README, all Java source files (with their original names), and the three graph
input files. Compiled files are not needed.
