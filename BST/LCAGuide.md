# Lowest Common Ancestor (LCA) Mastery — Striver Style

> Goal: after this, you should look at *any* "common ancestor / meeting point / distance between two
> nodes" problem and, within 60 seconds, know (1) is it a plain binary tree, a BST, or a "deepest"
> variant, (2) does the answer come from the recursion's return value or a global, (3) which of the
> three templates to reach for. Read **Part 0** three times. It is 80% of the battle.
>
> **Trigger words that scream LCA:** "find common ancestor", "distance between two nodes", "lowest node
> covering both", "where two paths join."

---

## Setup: the node and how test cases are written

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}
```

Reference tree used across the doc:
```
              3
            /   \
           5     1
          / \   / \
         6   2 0   8
            / \
           7   4
```

---

# PART 0 — THE CORE MENTAL MODEL (the whole pattern in one idea)

**What is an LCA?** The lowest (deepest) node that has both `p` and `q` in its subtree. A node counts as
an ancestor of itself — so if `p` is an ancestor of `q`, then `p` is their LCA.

The one sentence that captures the pattern:

> *"Run a search for `p` and `q`. The LCA is the node where the two searches MEET — either they split
> (one found on the left, one on the right), or a node IS one of them with the other beneath it."*

### The engine (memorize this exact shape — it powers everything)

```java
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    // BASE: hit null, or found one of the targets → report it upward
    if (root == null || root == p || root == q) return root;

    TreeNode left  = lca(root.left,  p, q);   // did the LEFT subtree find p or q?
    TreeNode right = lca(root.right, p, q);   // did the RIGHT subtree find p or q?

    if (left != null && right != null) return root;  // they SPLIT here → this node IS the LCA
    return (left != null) ? left : right;            // both on one side (or neither) → bubble up
}
```

**How to read the return value:** each call answers *"did I find p or q (or their LCA) below me?"* If
**both** children return non-null, they each found one target → the current node is the meeting point.
Otherwise pass up whichever single side found something.

### The three flavors (this is the whole pattern)

| Flavor | What changes | Template |
|--------|--------------|----------|
| **Plain binary tree** | nothing — use the engine | postorder search, O(n) |
| **BST** | use the ordering to walk down | compare-and-move, O(h) |
| **"Deepest" variant** | return a *pair* `(depth, node)` instead of just a node | postorder returning two facts |

### The 3 questions to ask for ANY LCA problem (say these out loud in interviews)

1. **Is it a BST?** → don't use the O(n) engine; walk down by value comparison in O(h).
2. **Are both nodes guaranteed to exist?** → the plain engine silently assumes yes; if one may be
   absent it wrongly returns the other, so you must verify both were found.
3. **Do nodes have parent pointers?** → you can walk *upward*; use the two-pointer trick (O(1) space).

> 🔑 **The build-on-top trick:** once you can find the LCA, *distance between two nodes* and *kth
> ancestor* are just "find the meeting point, then do arithmetic." LCA is the workhorse, not the goal.

---

# SUB-PATTERN MAP — the 3 problems (+ their natural extensions)

| Tier | Idea | Problems |
|------|------|----------|
| 1 | The engine on a plain binary tree | **LCA of Binary Tree** |
| 2 | Exploit BST ordering (O(h)) | **LCA of a BST** |
| 3 | Return a pair `(depth, node)` — postorder | **LCA of Deepest Leaves** |
| + | Built on top of LCA | Distance Between Nodes, Kth Ancestor, LCA with parent pointers |

Notice: Tier 3 is the **DFS "return more than one thing" trick** (from your DFS doc's Tier 3) fused
with LCA logic. Master the engine and the pair-return idea, and all of these fall.

---

# TIER 1 — The engine (plain binary tree)

## 1. Lowest Common Ancestor of a Binary Tree

> Both `p` and `q` are guaranteed to exist. Return their LCA.

**Brute force (narrate this — it's the intuitive first answer):** "Find the root→p path and the root→q
path as two lists, then walk both from the front; the last node they share is the LCA. O(n) time, O(n)
space for the two paths. Clean — but I can fold it into one recursion with O(h) space."

```java
// BRUTE — path comparison
boolean findPath(TreeNode node, TreeNode target, List<TreeNode> path) {
    if (node == null) return false;
    path.add(node);
    if (node == target) return true;
    if (findPath(node.left, target, path) || findPath(node.right, target, path)) return true;
    path.remove(path.size() - 1);          // backtrack
    return false;
}
TreeNode lcaBrute(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pp = new ArrayList<>(), qp = new ArrayList<>();
    findPath(root, p, pp);
    findPath(root, q, qp);
    TreeNode lca = null;
    for (int i = 0; i < pp.size() && i < qp.size(); i++) {
        if (pp.get(i) == qp.get(i)) lca = pp.get(i);
        else break;                        // paths diverge here
    }
    return lca;
}
```

**Optimal — the engine (O(n) time, O(h) space):**
```java
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left  = lowestCommonAncestor(root.left,  p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) return root;
    return (left != null) ? left : right;
}
```

**Test cases** (reference tree):
- `lca(5, 1)` → **3** (they split at the root).
- `lca(5, 4)` → **5** (5 is an ancestor of 4; the base case `root == p` catches it).
- `lca(6, 4)` → **5**.
- `lca(7, 8)` → **3**.

**Lesson:** the clause `root == p || root == q` is what handles "one node is an ancestor of the other."
Drop it and `lca(5,4)` breaks. This one function is the DNA of the whole pattern.

---

# TIER 2 — Exploit BST ordering (O(h))

## 2. Lowest Common Ancestor of a Binary Search Tree

> Same task, but it's a BST — so the values tell you which way to go; no full search needed.

**Brute force:** "Use the Tier-1 engine — it's correct and O(n). But in a BST I can do better: the
ordering means I only ever walk one path down."

**Insight:** if both `p` and `q` are **smaller** than root, the LCA is on the left; if both **larger**,
on the right; the **first node that sits between them** (or equals one) is the split point = LCA.

**Optimal (O(h) time, O(1) space):**
```java
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
        if (p.val < root.val && q.val < root.val)      root = root.left;   // both smaller → go left
        else if (p.val > root.val && q.val > root.val) root = root.right;  // both larger → go right
        else return root;                                                  // split point → LCA
    }
    return null;
}
```

**Test case:**
```
        6
       / \
      2   8
     / \  / \
    0  4 7  9
      / \
     3   5
```
`lca(2, 8)` → 2<6 and 8>6 → they split → **6**.
`lca(0, 5)` → both < 6 → left to 2 → 0<2 and 5>2 → split → **2**.

**Lesson:** always ask "is it a BST?" first — the ordering turns an O(n) traversal into an O(h) walk.
This is the same "navigate by comparison" idea from your BST doc (Model A).

---

# TIER 3 — Return a pair `(depth, node)` — postorder

## 3. Lowest Common Ancestor of Deepest Leaves

> Return the LCA of **all the deepest leaves** of the tree.

**Brute force (narrate it):** "First BFS to find the maximum depth and collect every deepest leaf, then
run the Tier-1 engine repeatedly to fold them into a single LCA. Correct but two passes and clunky. One
postorder can compute both the depth and the LCA together."

**Insight:** postorder returning a pair `(deepestDepth, lcaOfThatDepth)`:
- if left and right subtrees are **equally deep**, the current node is the LCA of that depth;
- otherwise carry up the **deeper** side's answer.

**Optimal (O(n), single pass):**
```java
class Res { int depth; TreeNode node; Res(int d, TreeNode n){ depth = d; node = n; } }

TreeNode lcaDeepestLeaves(TreeNode root) { return solve(root).node; }

Res solve(TreeNode node) {
    if (node == null) return new Res(0, null);
    Res l = solve(node.left), r = solve(node.right);
    if (l.depth == r.depth) return new Res(l.depth + 1, node);   // balanced deepest → node is LCA
    return (l.depth > r.depth) ? new Res(l.depth + 1, l.node)    // deeper side wins, carry its LCA up
                               : new Res(r.depth + 1, r.node);
}
```

**Test case:**
```
        1
       / \
      2   3
     /   / \
    4   5   6
```
All of 4, 5, 6 are at the same deepest level. Node 2's subtree depth (2) < node 3's (2)? They're equal
under the root, so the root's children are equally deep → LCA = **1**. If only 5 and 6 were deepest,
node 3's side would be deeper → LCA = **3**.

**Lesson:** this is the DFS Tier-3 **"return more than one thing"** trick — instead of a sentinel
(`-1` for Balanced) or a global (Diameter), you return a small `(depth, node)` object. When one value
isn't enough to answer the parent, bundle two.

---

# EXTENSIONS — built on top of LCA (the pattern hint mentions "distance")

These aren't in the 3-problem set but use the exact same engine — great extra practice and common follow-ups.

## E1. Distance Between Two Nodes
**Formula (the whole trick):** `distance(p,q) = level(p) + level(q) − 2 × level(LCA(p,q))`.
Both nodes climb to the LCA; the shared prefix is counted twice, so subtract it.
```java
int findDistance(TreeNode root, int p, int q) {
    TreeNode lca = lcaByValue(root, p, q);          // engine, comparing vals
    return depthFrom(lca, p) + depthFrom(lca, q);   // edges from LCA down to each
}
int depthFrom(TreeNode node, int target) {          // -1 if not found
    if (node == null) return -1;
    if (node.val == target) return 0;
    int l = depthFrom(node.left, target);
    if (l != -1) return l + 1;
    int r = depthFrom(node.right, target);
    return (r != -1) ? r + 1 : -1;
}
```
**Test:** reference tree, `distance(7,4)` → LCA=2; depth 2→7 =1, 2→4 =1 → **2** (path 7→2→4).

## E2. Kth Ancestor of a Node
Single query: grab the root→node path (the brute `findPath` above), step back k → `path[size-1-k]`.
Many queries on a huge tree: **binary lifting** (`up[j] = up[j-1] ∘ up[j-1]`), O(log n) per query.

## E3. LCA with Parent Pointers (the elegant two-pointer trick)
If nodes have `.parent`, walk both up; when one hits the top, restart it from the other's start — they
meet at the LCA (same math as linked-list intersection), O(1) space.
```java
Node lca(Node p, Node q) {
    Node a = p, b = q;
    while (a != b) { a = (a == null) ? q : a.parent; b = (b == null) ? p : b.parent; }
    return a;
}
```

---

# THE INTERVIEW PLAYBOOK (say these steps out loud)

1. **Clarify:** "Is it a BST? Are both nodes guaranteed present? Do nodes have parent pointers? Single
   query or many?" — each answer switches the optimal approach.
2. **State brute force first, always.** Path-to-p + path-to-q, compare prefixes. Safe universal opener.
3. **Pick the flavor:**
   - plain binary tree → **the engine** (postorder, O(n)).
   - BST → **walk down by value comparison** (O(h)).
   - "deepest" / needs depth too → **return a pair `(depth, node)`**.
   - parent pointers → **two-pointer swap**.
   - "distance / kth ancestor" → **find LCA, then do arithmetic**.
4. **Write the engine**, then adapt the one thing that differs.
5. **Dry-run:** p is an ancestor of q (tests the `root == p` base case); p and q in different subtrees;
   for BST, both-smaller / both-larger / split.

# YOUR PRACTICE ORDER (spaced, not linear)

- **Day 1:** #1 (the engine). Write it blind, then re-derive why `root == p || root == q` is needed.
- **Day 2:** #2 (BST). Contrast: same problem, O(n) engine vs O(h) comparison walk.
- **Day 3:** #3 (Deepest Leaves) — the pair-return trick. Then E1 (Distance) using the engine.
- **Day 4:** E2 + E3 (Kth Ancestor, parent-pointer two-pointer).
- **Day 5:** Blind re-solve #1, #2, #3 and derive Distance from scratch — if these flow, you own the pattern.

> **The one thing to remember:** the LCA engine finds *where two searches meet*. Every problem here is
> that engine, plus at most one twist: **use the BST ordering** (O(h)), **return a `(depth, node)` pair**
> (deepest variant), or **do arithmetic at the meeting point** (distance, kth ancestor).