# Tree BFS / Level-Order Mastery — Striver Style

> The whole pattern is **one template**. Every one of the 11 problems is that template plus a small
> tweak: "what do I track as I sweep each level?" Learn Part 0 cold and the rest are variations.

---

## Setup

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}
```

Trees are written level by level (root, then its children, left to right).

---

# PART 0 — THE ONE TEMPLATE (memorize this shape)

BFS visits the tree **level by level**, left to right, using a **queue** (FIFO). The single line that
turns plain BFS into *level-order* is **`int size = q.size();`** — it freezes how many nodes are on the
current level so you can process exactly one level per outer loop.

```java
Queue<TreeNode> q = new LinkedList<>();
if (root != null) q.offer(root);

while (!q.isEmpty()) {
    int size = q.size();                 // ← THE magic line: nodes on THIS level
    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();

        // ---- per-node work goes here ----
        // (i == 0        → first node of level)
        // (i == size-1   → last node of level)

        if (node.left  != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
    // ---- end-of-level work goes here (average, add level list, etc.) ----
}
```

- **Time:** O(n) — every node enqueued/dequeued once.
- **Space:** O(w) — w = max width of the tree (the queue holds at most one level).

### The 3 things that change between problems

Everything below is the template above with a different answer to one of these:

1. **What do I collect per node?** (value, first/last of level, or nothing)
2. **What do I track alongside each node?** (a column/horizontal distance, an index, a parent, a depth)
3. **What do I do at end-of-level?** (append the level, compute an average, flip a direction)

### DFS or BFS? (the decision)

Reach for **BFS** the moment the problem cares about **levels** or **horizontal position seen from the top**:
"level by level", "each level", "side view", "top/bottom view", "vertical/column", "width", "cousins /
same depth". DFS can do some of these, but BFS makes *level* and *top-down horizontal order* fall out for free.

---

# THE 11 PROBLEMS

## 1. Binary Tree Level Order Traversal  (the base template)

> Return values grouped by level: `[[level0], [level1], ...]`.

**Brute force to narrate:** "I could DFS and tag each value with its depth, then bucket by depth into
a list of lists — works, O(n), but I'm reconstructing 'level' artificially. BFS gives level grouping
directly, which is cleaner." (Both are O(n); BFS is the natural fit.)

```java
List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            level.add(node.val);
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        res.add(level);
    }
    return res;
}
```

**Test case:**
```
        3
       / \
      9   20
         /  \
        15   7
```
→ `[[3], [9, 20], [15, 7]]`.

> This is the parent of all 11. If you can write it blind, you're 70% done with the whole set.

---

## 2. Binary Tree Zigzag Level Order Traversal

> Same as #1, but odd levels go **right→left**. Alternate direction each level.

**Brute force:** "Do a normal level order, then reverse every other level list afterward — trivially
correct, one extra pass. Slightly cleaner: build each level with a deque so I never reverse."

**Optimal (deque, no reversing):**
```java
List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    boolean leftToRight = true;
    while (!q.isEmpty()) {
        int size = q.size();
        LinkedList<Integer> level = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            if (leftToRight) level.addLast(node.val);   // normal
            else             level.addFirst(node.val);  // reversed, for free
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        res.add(level);
        leftToRight = !leftToRight;                      // flip for next level
    }
    return res;
}
```

**Test case:**
```
        3
       / \
      9   20
         /  \
        15   7
```
→ `[[3], [20, 9], [15, 7]]`. (Level 1 reversed.)

**Key line:** `level.addFirst(...)` when going right-to-left — the queue still fills left→right, you
just insert into the *result list* from the front. Do NOT reverse the queue itself.

---

## 3. Average of Levels in Binary Tree

> Return the average value of each level.

```java
List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        long sum = 0;                                   // long → avoid int overflow on big values
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            sum += node.val;
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        res.add((double) sum / size);                   // end-of-level work
    }
    return res;
}
```

**Test case:**
```
        3
       / \
      9   20
         /  \
        15   7
```
→ `[3.0, 14.5, 11.0]`.

**Watch:** use `long sum` — the classic interview trap is `Integer.MAX_VALUE`-sized node values overflowing.

---

## 4. Cousins in Binary Tree

> Two values `x`, `y` are **cousins** if they're at the **same depth** but have **different parents**.

**Brute force:** "Find depth and parent of each with a DFS, then compare: cousins iff equal depth and
unequal parent. Perfectly fine. BFS lets me answer in one sweep with an early exit."

**Optimal (BFS, one pass):**
```java
boolean isCousins(TreeNode root, int x, int y) {
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        boolean foundX = false, foundY = false;
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            if (node.val == x) foundX = true;
            if (node.val == y) foundY = true;
            // if x and y are the two children of THIS node → siblings, not cousins
            if (node.left != null && node.right != null) {
                int l = node.left.val, r = node.right.val;
                if ((l == x && r == y) || (l == y && r == x)) return false;
            }
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        if (foundX && foundY) return true;   // same level AND not siblings (ruled out above)
        if (foundX || foundY) return false;  // only one on this level → different depths
    }
    return false;
}
```

**Test case:**
```
        1
       / \
      2   3
       \   \
        4   5      x = 4, y = 5
```
4 and 5 are both on level 2, parents 2 and 3 (different) → **true**.
For x=4, y=... a sibling case would return false via the sibling check.

**The two checks that make it correct:** (1) sibling check inside a node kills the "same parent" case;
(2) `foundX ^ foundY` on a level kills the "different depth" case.

---

## 5. Left Side View

> The nodes visible from the **left** — i.e. the **first** node of every level.

```java
List<Integer> leftSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            if (i == 0) res.add(node.val);              // first node of the level
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }
    return res;
}
```

**Test case:**
```
        1
       / \
      2   3
       \
        5
```
→ `[1, 2, 5]`.

---

## 6. Binary Tree Right Side View

> Same idea, mirrored — the **last** node of every level.

```java
List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            if (i == size - 1) res.add(node.val);       // last node of the level
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }
    return res;
}
```

**Test case:**
```
        1
       / \
      2   3
       \
        5
```
→ `[1, 3, 5]`. (Level 2 has only node 5, so it's both first and last → shows in both views.)

> Left view = `i == 0`, Right view = `i == size-1`. Literally one line apart. Great to say in interviews.

---

## 7. Populating Next Right Pointers in Each Node

> Each node has a `next` pointer; wire each node's `next` to the node immediately to its right on the
> same level (`null` if it's the last on the level).

```java
class Node { int val; Node left, right, next; Node(int v){ val = v; } }
```

**Brute force / general BFS (works for ANY tree, O(n) space):** "BFS level by level; keep a `prev`
pointer and link `prev.next = node` as I go." State this first — it's clean and always correct.
```java
Node connect(Node root) {
    if (root == null) return null;
    Queue<Node> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        Node prev = null;
        for (int i = 0; i < size; i++) {
            Node node = q.poll();
            if (prev != null) prev.next = node;         // link previous → current
            prev = node;
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }
    return root;
}
```

**Optimal O(1) space (perfect binary tree — use the `next` links you already built):**
```java
Node connectOptimal(Node root) {
    Node leftmost = root;
    while (leftmost != null && leftmost.left != null) { // walk down the left spine
        Node head = leftmost;
        while (head != null) {                          // traverse current level via next pointers
            head.left.next = head.right;                        // link within a parent
            if (head.next != null)
                head.right.next = head.next.left;               // link across parents
            head = head.next;
        }
        leftmost = leftmost.left;                        // drop to next level
    }
    return root;
}
```

**Test case:**
```
        1                       1 → null
       / \                     / \
      2   3        →          2 → 3 → null
     / \ / \                 / \ / \
    4  5 6  7               4→5→6→7 → null
```

**Interview line:** "BFS is O(n) space; but since the tree already gives me `next` on the level above,
I can traverse each level *using those links* and get O(1) extra space."

---

## 8. Vertical Order Traversal

> Group nodes by **column** (horizontal distance from root: left = −1, right = +1). Output columns
> left→right; within a column, top→bottom, left→right.

**Key idea:** track a **column index** alongside each node. Use a `TreeMap<column, list>` so columns
come out sorted automatically. BFS guarantees top-to-bottom, left-to-right order within a column.

```java
List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    TreeMap<Integer, List<Integer>> cols = new TreeMap<>();      // column → values (sorted by column)
    Queue<TreeNode> nodes = new LinkedList<>();
    Queue<Integer>  cidx  = new LinkedList<>();
    nodes.offer(root); cidx.offer(0);
    while (!nodes.isEmpty()) {
        TreeNode node = nodes.poll();
        int c = cidx.poll();
        cols.computeIfAbsent(c, k -> new ArrayList<>()).add(node.val);
        if (node.left  != null) { nodes.offer(node.left);  cidx.offer(c - 1); }
        if (node.right != null) { nodes.offer(node.right); cidx.offer(c + 1); }
    }
    res.addAll(cols.values());                                    // TreeMap iterates in column order
    return res;
}
```

**Test case:**
```
        3
       / \
      9   20        columns:  9 → -1,  3 → 0,  15,20 → ... 
         /  \       (3:col0, 9:col-1, 20:col+1, 15:col0, 7:col+2)
        15   7
```
→ `[[9], [3, 15], [20], [7]]`.

**Variant to mention (LeetCode 987):** if within the *same row and column* ties must be broken by
**value**, collect `(col, row, val)` triples, then sort by `(col, row, val)`. Same column idea, extra sort.

---

## 9. Top View of a Binary Tree

> Looking down from directly above: the **first** (topmost) node seen at each column.

**Key idea:** column index + `TreeMap`, but **only record the first** node per column (`putIfAbsent`).
Because BFS processes top levels first, the first node placed in a column is the topmost.

```java
List<Integer> topView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    TreeMap<Integer, Integer> map = new TreeMap<>();      // column → value
    Queue<TreeNode> nodes = new LinkedList<>();
    Queue<Integer>  cidx  = new LinkedList<>();
    nodes.offer(root); cidx.offer(0);
    while (!nodes.isEmpty()) {
        TreeNode node = nodes.poll();
        int c = cidx.poll();
        map.putIfAbsent(c, node.val);                     // KEEP the first seen (topmost)
        if (node.left  != null) { nodes.offer(node.left);  cidx.offer(c - 1); }
        if (node.right != null) { nodes.offer(node.right); cidx.offer(c + 1); }
    }
    res.addAll(map.values());
    return res;
}
```

**Test case:**
```
         1
        / \
       2   3
        \   \
         4   5      columns: 2→-1, 1→0, 3→+1, 4→0(hidden), 5→+2
```
→ `[2, 1, 3, 5]`. (4 is at column 0 but 1 got there first, so 4 is hidden.)

> **Must use BFS, not DFS, for Top/Bottom view.** DFS could reach a lower node in a column before a
> higher one via a different branch and record the wrong value. BFS's top-down order guarantees correctness.

---

## 10. Bottom View of Binary Tree

> Looking up from directly below: the **last** node seen at each column.

**Key idea:** identical to Top View, but **always overwrite** (`put`) so the last node per column wins.

```java
List<Integer> bottomView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Queue<TreeNode> nodes = new LinkedList<>();
    Queue<Integer>  cidx  = new LinkedList<>();
    nodes.offer(root); cidx.offer(0);
    while (!nodes.isEmpty()) {
        TreeNode node = nodes.poll();
        int c = cidx.poll();
        map.put(c, node.val);                             // OVERWRITE → last seen (bottom-most) wins
        if (node.left  != null) { nodes.offer(node.left);  cidx.offer(c - 1); }
        if (node.right != null) { nodes.offer(node.right); cidx.offer(c + 1); }
    }
    res.addAll(map.values());
    return res;
}
```

**Test case:**
```
         1
        / \
       2   3
        \   \
         4   5
```
→ `[2, 4, 3, 5]`. (At column 0, node 4 overwrites node 1.)

> Top View vs Bottom View differ by **one word**: `putIfAbsent` (keep first) vs `put` (keep last). Beautiful.

---

## 11. Maximum Width of Binary Tree

> Width of a level = distance between its leftmost and rightmost non-null nodes, **counting the null
> gaps in between**. Return the max width over all levels.

**Key idea:** give each node a **heap-style index** — root = 0, left child = `2*i`, right = `2*i+1`.
Width of a level = `rightmostIndex − leftmostIndex + 1`. **Normalize indices per level** (subtract the
level's first index) to prevent integer overflow on deep trees.

```java
int widthOfBinaryTree(TreeNode root) {
    if (root == null) return 0;
    int maxWidth = 0;
    Queue<TreeNode> nodes = new LinkedList<>();
    Queue<Integer>  idxs  = new LinkedList<>();
    nodes.offer(root); idxs.offer(0);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        int first = 0, last = 0;
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            int idx = idxs.poll();
            if (i == 0)        first = idx;             // leftmost index on this level
            if (i == size - 1) last  = idx;             // rightmost index on this level
            // normalize children by (idx - first) so numbers stay small → no overflow
            if (node.left  != null) { nodes.offer(node.left);  idxs.offer(2 * (idx - first)); }
            if (node.right != null) { nodes.offer(node.right); idxs.offer(2 * (idx - first) + 1); }
        }
        maxWidth = Math.max(maxWidth, last - first + 1);
    }
    return maxWidth;
}
```

**Test case:**
```
        1
       / \
      3   2
     /     \
    5       9        indices: level2 → 5 at idx0, 9 at idx3
```
→ width of level 2 = `3 - 0 + 1 = 4`. Answer = **4**.

**The two tricks:** (1) heap indexing `2i` / `2i+1` counts the invisible null gaps; (2) subtracting
`first` each level keeps indices from exploding past `Integer.MAX_VALUE` on skewed deep trees.

---

# CHEAT SHEET — the whole pattern on one screen

| # | Problem | Track alongside node | Per-node / per-level rule |
|---|---------|----------------------|---------------------------|
| 1 | Level Order | — | append each level list |
| 2 | Zigzag | — | flip flag; `addFirst` on reversed levels |
| 3 | Average | — | `sum/size` at end of level (use `long`) |
| 4 | Cousins | (implicit depth) | sibling check + "only one found on level" |
| 5 | Left View | — | take `i == 0` |
| 6 | Right View | — | take `i == size-1` |
| 7 | Next Right Ptr | `prev` pointer | `prev.next = node` (or O(1) via next links) |
| 8 | Vertical Order | **column** | `TreeMap<col, list>` |
| 9 | Top View | **column** | `TreeMap`, `putIfAbsent` (first) |
| 10 | Bottom View | **column** | `TreeMap`, `put` (last) |
| 11 | Max Width | **heap index** | `last - first + 1`, normalize per level |

**Three sub-families to memorize:**
- **Plain level sweep** (1,2,3,5,6): just the template + first/last/flip.
- **Column family** (8,9,10): template + a `column` counter + a `TreeMap`. Top vs Bottom = first vs last.
- **Index/position family** (11) and **relationship** (4,7): template + one extra piece of state per node.

---

# INTERVIEW PLAYBOOK

1. **Spot the trigger:** "level", "each level", "side/top/bottom view", "vertical/column", "width",
   "cousins/same depth" → BFS. Say it out loud: *"This is a level-order sweep."*
2. **State brute force:** for view/cousins problems the DFS-with-depth version is the honest brute
   force — mention it, then pivot to BFS as cleaner.
3. **Write the template first** (the Part 0 block), THEN plug in the one tweak.
4. **Name your extra state:** "I'll carry a `column` with each node" / "a heap `index`" / "a `prev`."
5. **Dry-run edge cases:** empty tree, single node, completely skewed (one child) — the last one breaks
   naive Max Width if you forget to normalize.