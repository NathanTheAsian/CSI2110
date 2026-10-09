# CSI2110 lecture notes

One standalone LaTeX document per distinct PDF under `lectures/`, with a compiled PDF beside it. Notes cover definitions, algorithm pseudocode, implementation details, complexity, worked lecture examples, and study checks. References use PDF page numbers rather than printed slide numbers.

| Source in lectures/ | LaTeX notes | Readable PDF |
|---|---|---|
| [lec00-introduction.pdf](../lec00-introduction.pdf) | [lec00-introduction-notes.tex](lec00-introduction-notes.tex) | [PDF](lec00-introduction-notes.pdf) |
| [lec01-analysis of algorithms.pdf](../lec01-analysis%20of%20algorithms.pdf) | [lec01-analysis-of-algorithms-notes.tex](lec01-analysis-of-algorithms-notes.tex) | [PDF](lec01-analysis-of-algorithms-notes.pdf) |
| [lec02-stacks, queues, deques.pdf](../lec02-stacks,%20queues,%20deques.pdf) | [lec02-stacks-queues-deques-notes.tex](lec02-stacks-queues-deques-notes.tex) | [PDF](lec02-stacks-queues-deques-notes.pdf) |
| [lec03-list.pdf](../lec03-list.pdf) | [lec03-lists-notes.tex](lec03-lists-notes.tex) | [PDF](lec03-lists-notes.pdf) |
| [lec04-tree and binary tree.pdf](../lec04-tree%20and%20binary%20tree.pdf) | [lec04-trees-binary-trees-notes.tex](lec04-trees-binary-trees-notes.tex) | [PDF](lec04-trees-binary-trees-notes.pdf) |
| [lec05-priority and heap.pdf](../lec05-priority%20and%20heap.pdf) | [lec05-priority-queues-heaps-notes.tex](lec05-priority-queues-heaps-notes.tex) | [PDF](lec05-priority-queues-heaps-notes.pdf) |
| [0/Review.pdf](../0/Review.pdf) | [review-arrays-linked-lists-notes.tex](review-arrays-linked-lists-notes.tex) | [PDF](review-arrays-linked-lists-notes.pdf) |

## Duplicate sources

SHA-256 checks confirmed that `1/lec01-analysis of algorithms.pdf`, `lecture5/lec02-stacks, queues, deques.pdf`, and `lecture5/lec03-list.pdf` are byte-for-byte copies of their top-level counterparts. The same notes cover those copies. Existing sources and the original `0/Review.tex` are preserved.

## Editing and compiling

Each `.tex` file is self-contained; no shared preamble or external figure files are required. Open one in a LaTeX editor and compile with pdfLaTeX twice to update its table of contents.

```powershell
pdflatex -interaction=nonstopmode -halt-on-error lec04-trees-binary-trees-notes.tex
pdflatex -interaction=nonstopmode -halt-on-error lec04-trees-binary-trees-notes.tex
```

Explanations include clarified boundary cases and corrected notation where needed. Additional details are identified as clarifications or derived analysis. Course logistics in the introduction are recorded from the supplied slides, not independently verified current announcements.
