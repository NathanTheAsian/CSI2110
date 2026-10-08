# CSI 2110 lecture notes

One self-contained LaTeX document for each supplied lecture deck, based on Bo Sun's Fall 2026 slides. The notes consolidate repeated animation slides and include definitions, algorithms, worked traces, complexity explanations, and review questions with answers.

| Lecture | Notes | Source deck | PDF pages |
| --- | --- | --- | --- |
| 0 | [Introduction](lec00-introduction-notes.tex) | `lec00-introduction.pdf` | 44 |
| 1 | [Analysis of algorithms](lec01-analysis-of-algorithms-notes.tex) | `lec01-analysis of algorithms.pdf` | 95 |
| 2 | [Stacks, queues, and deques](lec02-stacks-queues-deques-notes.tex) | `lec02-stacks, queues, deques.pdf` | 53 |
| 3 | [Lists](lec03-lists-notes.tex) | `lec03-list.pdf` | 37 |
| 4 | [Trees and binary trees](lec04-trees-and-binary-trees-notes.tex) | `lec04-tree and binary tree.pdf` | 82 |
| 5 | [Priority queues and heaps](lec05-priority-queues-and-heaps-notes.tex) | `lec05-priority and heap.pdf` | 84 |

Source decks are in the parent `lectures` directory. References inside the notes use one-based PDF page numbers, which sometimes differ from printed slide numbers. Each document is independent: no external images, shared preamble, bibliography, or `input` files are required.

## Reading and compiling

Open any `.tex` file in Codex's built-in LaTeX editor to edit it and view the PDF preview. In a separately configured LaTeX installation, compile an individual file with `pdflatex` (or another compatible LaTeX engine).

The documents use standard packages: `geometry`, `amsmath`, `amssymb`, `booktabs`, `tabularx`, `listings`, `xcolor`, and `hyperref`.

## Editorial conventions

- Array indices start at zero unless a one-based alternative is explicitly given.
- Tree heights and depths count edges; a leaf has height zero.
- Logs are base two unless otherwise stated.
- Complexity claims distinguish worst-case, amortized, and output-space costs where relevant.
- Pseudocode includes boundary conditions and reference updates omitted in some slide sketches. Clear formula/indexing errors are corrected with explanations.
- Course logistics are a record of the supplied slides, not a live check of Brightspace.

## Validation

All six source files were submitted to the built-in LaTeX compiler. The compiler failed before processing the documents with `Unable to find standard directories for platform`. Compilation and rendered layout therefore remain unverified. Source structure and the worked algorithm traces were checked separately.
