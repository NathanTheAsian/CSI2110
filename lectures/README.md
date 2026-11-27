# CSI 2110 lectures

## Directory guide

| Folder | Contents |
| --- | --- |
| [slides](slides) | Original lecture decks, numbered 00–05 |
| [notes](notes) | Study notes: LaTeX sources and available PDFs |
| [review](review) | Original arrays and linked-list review, with LaTeX source |
| [adt-practice](adt-practice) | Topic-organized Java ADT exercises and instructions |
| [practice/02-stacks-queues-deques](practice/02-stacks-queues-deques) | Earlier lecture 2 exercises, tests, and their existing `tmp` work |
| [examples/queues](examples/queues) | Queue examples from the former `lecture5` folder, including its reference PDFs |
| [archive](archive) | Extra lecture 1 deck, unused shortcut, and original empty folder |

## Lecture index

| Lecture | Slides | Notes PDF | Notes source |
| --- | --- | --- | --- |
| 00 · Introduction | [Slides](<slides/lec00-introduction.pdf>) | [PDF](notes/lec00-introduction-notes.pdf) | [LaTeX](notes/lec00-introduction-notes.tex) |
| 01 · Algorithm analysis | [Slides](<slides/lec01-analysis of algorithms.pdf>) | [PDF](notes/lec01-analysis-of-algorithms-notes.pdf) | [LaTeX](notes/lec01-analysis-of-algorithms-notes.tex) |
| 02 · Stacks, queues, deques | [Slides](<slides/lec02-stacks, queues, deques.pdf>) | [PDF](notes/lec02-stacks-queues-deques-notes.pdf) | [LaTeX](notes/lec02-stacks-queues-deques-notes.tex) |
| 03 · Lists | [Slides](<slides/lec03-list.pdf>) | [PDF](notes/lec03-lists-notes.pdf) | [LaTeX](notes/lec03-lists-notes.tex) |
| 04 · Trees and binary trees | [Slides](<slides/lec04-tree and binary tree.pdf>) | [PDF](notes/lec04-trees-binary-trees-notes.pdf) | [LaTeX](notes/lec04-trees-binary-trees-notes.tex) |
| 05 · Priority queues and heaps | [Slides](<slides/lec05-priority and heap.pdf>) | [PDF](notes/lec05-priority-queues-heaps-notes.pdf) | [LaTeX](notes/lec05-priority-queues-heaps-notes.tex) |

Review: [original PDF](review/Review.pdf), [study notes PDF](notes/review-arrays-linked-lists-notes.pdf), and [study notes source](notes/review-arrays-linked-lists-notes.tex).

The alternate [tree notes](notes/lec04-trees-and-binary-trees-notes.tex) and
[heap notes](notes/lec05-priority-queues-and-heaps-notes.tex) are also preserved.
See [the notes guide](notes/README.md) for compiling and editorial conventions.

Compile each Java exercise collection separately: several collections use the same
class names in the default package. Follow the README in the collection you are using.
The earlier lecture 2 `tmp` folder retains generated classes, extracted text, and a
practice generator alongside its original inputs so relative paths still work.

## Previous locations

| Previous path | Current path |
| --- | --- |
| `lec*.pdf` | `slides/lec*.pdf` |
| `0/` | `review/` |
| `1/` | `archive/duplicate-slides/lecture-01/` |
| `2/` | `practice/02-stacks-queues-deques/` |
| `lecture5/` | `examples/queues/` |
| `5/` | `archive/empty-folders/5/` |
| `New shortcut.lnk` | `archive/shortcuts/New shortcut.lnk` |

The empty `2/` directory is held open by another process; its contents are now in
`practice/02-stacks-queues-deques/`. It can be removed once that process releases it.

All original files were retained. The shortcut is archived unchanged and may still
refer to its original target. A SHA-256 inventory (baselined after resuming the interrupted move) is in
[archive/reorganization-manifest.json](archive/reorganization-manifest.json).
