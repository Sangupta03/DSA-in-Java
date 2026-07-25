# Tree DFS Mastery — Striver Style

> Goal: after this, you should look at *any* tree problem and, within 60 seconds, know
> (1) is it top-down or bottom-up, (2) do I need a global variable, (3) what do I return.
> Read **Part 0** three times. It is 80% of the battle.

---

## Setup: the node and how test cases are written

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}
```

Throughout, trees are written level by level. Example:

```
        1
       / \
      2   3
     / \
    4   5
```

means root=1, root.left=2, root.right=3, node2.left=4, node2.right=5.

---

# PART 0 — THE CORE MENTAL MODEL (the whole pattern in one idea)

Every binary-tree DFS problem is answered by **one sentence**:

> *"If a genie already gave me the correct answer for my LEFT subtree and my RIGHT
> subtree, how do I combine them to produce MY answer?"*

This is the **recursion leap of faith**. You do NOT trace the recursion in your head.
You trust the children are correct and only write the combine step.

### The two directions of information flow

This is the single most important distinction in tree DFS. Every problem is one of these:

| Direction | How info moves | Carried by | Mental cue |
|-----------|----------------|------------|------------|
| **Top-down** | root → leaves | **function parameters** | "I hand data DOWN to my children" (e.g. current path, current sum, depth so far) |
| **Bottom-up** | leaves → root | **return value** | "My children hand answers UP to me" (e.g. height, subtree sum, is-balanced) |

Most "clever" tree solutions are **bottom-up**, because computing something once per node and
returning it upward avoids recomputation.

### The universal skeleton

```java
ReturnType dfs(TreeNode node, /* top-down params */) {
    // 1. BASE CASE  — what is the answer for an empty tree / a leaf?
    if (node == null) return baseValue;

    // 2. ASK THE CHILDREN (leap of faith — trust they're correct)
    ReturnType left  = dfs(node.left,  ...);
    ReturnType right = dfs(node.right, ...);

    // 3. (optional) UPDATE A GLOBAL if the best answer can appear at ANY node
    global = best(global, combineThroughNode(left, right, node));

    // 4. COMBINE + RETURN what my PARENT needs from me
    return combineForParent(left, right, node);
}
```

### The 4 questions to ask for ANY tree problem (say these out loud in interviews)

1. **What's the base case?** (usually `node == null`, sometimes leaf)
2. **What do I need from each child?** (that decides the return type)
3. **Is the answer guaranteed to be at the root, or can it live at any node?**
   - At root → just return it. (Max Depth)
   - At any node → you need a **global variable**. (Diameter, Max Path Sum)
4. **Do I need to pass anything DOWN?** (path, running sum, target) → top-down parameter.

> 🔑 **The "return more than one thing" trick** (Tier 3): sometimes to answer the parent you must
> return TWO facts (e.g. "my height" AND "am I balanced"). You do this with a sentinel value,
> a small class, or a global. Half of "Medium/Hard" tree problems are just this.

---

# SUB-PATTERN MAP — which of the 18 are secretly the same

| Tier | Idea | Problems |
|------|------|----------|
| 1 | Pure traversal + trivial bottom-up | Inorder, Preorder, Postorder, Max Depth, Min Depth |
| 2 | Walk TWO trees in lockstep | Same Tree, Symmetric, Subtree of Another |
| 3 | Return height, but track a GLOBAL best-through-node | Diameter, Balanced, **Max Path Sum (Hard)** |
| 4 | Carry a running sum DOWN (top-down path) | Path Sum, Path Sum II, Path Sum III |
| 5 | Special structure / graph conversion | Nodes at Distance K, Boundary Traversal, Count Complete Nodes, Binary Tree Cameras |

Notice: **Diameter, Balanced, and the Hard "Max Path Sum" are the same problem.** Master Tier 3 and a Hard problem falls for free. That's the Striver payoff.

---

# TIER 1 — Foundations (traversal + trivial bottom-up)

## 1. Inorder Traversal  `[Left, Root, Right]`

**Brute force (interview narration):** "There is no real brute force for a traversal — the naive
approach is plain recursion. The *interesting* follow-up is doing it **iteratively** with a stack,
and the *hard* follow-up is **Morris traversal** in O(1) space. I'll mention I know all three."

**Recursive (know cold):**
```java
void inorder(TreeNode node, List<Integer> out) {
    if (node == null) return;
    inorder(node.left, out);   // Left
    out.add(node.val);         // Root
    inorder(node.right, out);  // Right
}
```

**Iterative (the version interviewers love):**
```java
List<Integer> inorderIterative(TreeNode root) {
    List<Integer> out = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
        while (curr != null) {        // go as far left as possible
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();           // deepest unvisited left
        out.add(curr.val);            // visit root
        curr = curr.right;            // move right
    }
    return out;
}
```
- **Time O(n), Space O(h)** (h = height; O(n) worst skewed tree).

**Test case:**
```
        1
         \
          2
         /
        3
```
`inorder → [1, 3, 2]`. (Left of 1 empty → 1; then right subtree rooted at 2, its left is 3 → 3; then 2.)

**Mnemonic:** In**O**rder → root in the middle. For a BST, inorder gives **sorted** output — remember this, it unlocks a dozen BST problems.

---

## 2. Preorder Traversal  `[Root, Left, Right]`

**Iterative (uses a stack, push right first):**
```java
List<Integer> preorder(TreeNode root) {
    List<Integer> out = new ArrayList<>();
    if (root == null) return out;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        out.add(node.val);                       // Root first
        if (node.right != null) stack.push(node.right); // push right BEFORE left
        if (node.left  != null) stack.push(node.left);  // so left pops first
    }
    return out;
}
```

**Test case** (same tree as #1): `preorder → [1, 2, 3]` (root 1, then its right subtree preorder: 2 then 2's left 3).

**Why it matters:** Preorder = "process node, then recurse." It's the natural order for **copying/serializing** a tree.

---

## 3. Postorder Traversal  `[Left, Right, Root]`

The postorder trick: do a **modified preorder** `Root, Right, Left`, then **reverse** it.
```java
List<Integer> postorder(TreeNode root) {
    LinkedList<Integer> out = new LinkedList<>();
    if (root == null) return out;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        out.addFirst(node.val);                  // insert at front == reverse
        if (node.left  != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);
    }
    return out;
}
```

**Test case** (same tree): `postorder → [3, 2, 1]`.

**Why it matters:** Postorder = "process children before yourself" = **bottom-up**. *Every* Tier 3 problem (Diameter, Balanced, Max Path Sum) is fundamentally postorder. When you need a child's answer before yours, you're doing postorder.

---

## 4. Maximum Depth of Binary Tree

> Max number of nodes on the longest root→leaf path.

**Brute force to say out loud:** "The naive idea is to compute, for every node, its depth from the
root, and take the max — but that either recomputes depth repeatedly or needs a top-down pass. The
clean solution is a single bottom-up postorder: a node's height = 1 + max(children heights)."

**Optimal (this is THE template — memorize its shape):**
```java
int maxDepth(TreeNode node) {
    if (node == null) return 0;                  // empty tree has depth 0
    int lh = maxDepth(node.left);
    int rh = maxDepth(node.right);
    return 1 + Math.max(lh, rh);                 // I'm 1 deeper than my tallest child
}
```
- **Time O(n), Space O(h).**

**Test case:**
```
      3
     / \
    9   20
       /  \
      15   7
```
Leaves 9,15,7 → depth 1. Node 20 → 1+max(1,1)=2. Node 3 → 1+max(1,2)=**3**.

> This 4-line function is the DNA of the whole tier. Diameter, Balanced, and Max Path Sum are all this function with **one extra line**.

---

## 5. Minimum Depth of Binary Tree

> Shortest root→**leaf** path. The **gotcha**: a node with only ONE child is not a leaf, so you cannot just take `min(left, right)` — that would return 0 through the missing side.

**Brute force:** "BFS level-order and return the level of the first leaf you hit — that's actually
optimal-ish because it can stop early. The recursive version must special-case one-child nodes."

**Optimal recursive (handle the one-child trap):**
```java
int minDepth(TreeNode node) {
    if (node == null) return 0;
    if (node.left == null)  return 1 + minDepth(node.right); // no left → go right
    if (node.right == null) return 1 + minDepth(node.left);  // no right → go left
    return 1 + Math.min(minDepth(node.left), minDepth(node.right));
}
```

**Test case:**
```
    1
   /
  2
```
Naive `1 + min(depth(2), depth(null))` = `1 + min(1, 0)` = 1 ❌ (wrong — node 1 isn't a leaf).
Correct answer = **2** (path 1→2). Our one-child guard returns `1 + minDepth(2) = 2` ✅.

**Lesson:** "min-depth to a *leaf*" ≠ "min-depth to null." Whenever a problem says **leaf**, ask: *is a one-child node handled?*

---

# TIER 2 — Walk two trees in lockstep

The move: recurse on **two nodes at once** — `(p, q)` — advancing both together.

## 6. Same Tree Check

**Brute force:** "Serialize both trees (say preorder with null markers) into strings and compare —
O(n) but uses extra space and is clumsy. Cleaner: recurse both in parallel."

**Optimal:**
```java
boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;         // both empty → equal
    if (p == null || q == null) return false;        // one empty → unequal
    if (p.val != q.val)         return false;        // values differ
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

**Test case:**
```
  p:  1        q:  1
     / \          / \
    2   3        2   4
```
Roots match, left subtrees (2==2) match, right subtrees 3 vs 4 → **false**.

---

## 7. Symmetric Tree

> Is the tree a **mirror** of itself? Key insight: compare `left.left` with `right.right`, and
> `left.right` with `right.left` (crossed).

**Brute force:** "Do an inorder traversal and check the value list is a palindrome — fails on trees
with duplicate values / different shapes, so it's buggy. Correct approach: mirror-compare recursion."

**Optimal:**
```java
boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return mirror(root.left, root.right);
}
boolean mirror(TreeNode a, TreeNode b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return false;
    return a.val == b.val
        && mirror(a.left,  b.right)   // outer pair
        && mirror(a.right, b.left);   // inner pair (crossed!)
}
```

**Test case (symmetric):**
```
        1
       / \
      2   2
     / \ / \
    3  4 4  3
```
`mirror(2L, 2R)`: vals equal; `mirror(3,3)` ✅ and `mirror(4,4)` ✅ → **true**.
Flip one 3 to a 5 and it becomes **false**. This "crossed comparison" is the whole trick.

---

## 8. Subtree of Another Tree

> Does `t` appear as a subtree of `s` (identical structure AND values, all the way down)?

**Brute force (this IS the expected first answer — say it):** "For **every** node of `s`, run the
Same-Tree check against `t`. That's O(n·m) — n nodes in s, each comparison up to m. Simple, correct,
and totally fine to state first."

```java
boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null) return t == null;              // empty s can only contain empty t
    if (isSameTree(s, t)) return true;            // does t match starting HERE?
    return isSubtree(s.left, t) || isSubtree(s.right, t); // else try children
}
// reuse isSameTree from problem #6
```
- **Time O(n·m).**

**Optimal (mention it exists):** serialize both with null markers into strings and check if
`serialize(t)` is a substring of `serialize(s)` (use KMP → O(n+m)). Great "I know the optimization" line.

**Test case:**
```
  s:    3            t:   4
       / \               / \
      4   5             1   2
     / \
    1   2
```
`isSameTree(s, t)`? 3≠4 no. Recurse left → node 4's subtree `4/(1,2)` equals `t` → **true**.

---

# TIER 3 — The most important tier: return height + track a GLOBAL

**The pattern:** the function *returns* one thing (usually height/gain), but along the way it
*updates a global* with "the best answer that passes THROUGH this node." Used when the answer can
live at any node, not just the root.

## 9. Diameter of Binary Tree

> Longest path (in **edges**) between any two nodes. The path may **not** pass through the root.

**Brute force (narrate this — it's the "aha"):** "For every node, compute
`leftHeight + rightHeight` — that's the longest path bending at that node. Take the max over all
nodes. But computing height at every node independently is O(n²)."

```java
// BRUTE O(n^2)
int diameterBrute(TreeNode root) {
    if (root == null) return 0;
    int through = height(root.left) + height(root.right);  // path bending here
    int best = Math.max(through, diameterBrute(root.left));
    return Math.max(best, diameterBrute(root.right));
}
int height(TreeNode n){ return n==null?0:1+Math.max(height(n.left),height(n.right)); }
```

**Optimal O(n) (compute height ONCE, update global while returning it):**
```java
int diameter = 0;
int diameterOfBinaryTree(TreeNode root) { height2(root); return diameter; }

int height2(TreeNode node) {
    if (node == null) return 0;
    int lh = height2(node.left);
    int rh = height2(node.right);
    diameter = Math.max(diameter, lh + rh);      // ← the ONE extra line vs Max Depth
    return 1 + Math.max(lh, rh);                  // return height to parent
}
```

**Test case:**
```
        1
       / \
      2   3
     / \
    4   5
```
At node 2: lh=1(node4), rh=1(node5) → `lh+rh=2`. At node 1: lh=2, rh=1 → `3`. Global max = **3** (path 4→2→5→... actually 4→2→1→3, 3 edges). Answer **3**.

> Notice: Max Depth returns `1+max(lh,rh)`. Diameter is the **same function** plus
> `global = max(global, lh+rh)`. That's Tier 3 in a nutshell.

---

## 10. Balanced Binary Tree

> Balanced = for **every** node, `|leftHeight − rightHeight| ≤ 1`.

**Brute force:** "At every node compute both subtree heights and check the condition — but height is
O(n), done at every node → O(n²)."
```java
// BRUTE O(n^2)
boolean isBalancedBrute(TreeNode root){
    if(root==null) return true;
    if(Math.abs(height(root.left)-height(root.right))>1) return false;
    return isBalancedBrute(root.left) && isBalancedBrute(root.right);
}
```

**Optimal O(n) — the sentinel trick (return −1 to mean "unbalanced"):**
```java
boolean isBalanced(TreeNode root) { return check(root) != -1; }

int check(TreeNode node) {
    if (node == null) return 0;
    int lh = check(node.left);
    if (lh == -1) return -1;                      // left already unbalanced → bubble up
    int rh = check(node.right);
    if (rh == -1) return -1;
    if (Math.abs(lh - rh) > 1) return -1;         // I'm unbalanced
    return 1 + Math.max(lh, rh);                  // else return my height
}
```

**Test case (unbalanced):**
```
        1
       /
      2
     /
    3
```
Node3 h=1. Node2: lh=1,rh=0 → ok, h=2. Node1: lh=2, rh=0 → |2−0|=2 >1 → return −1 → **false**.

**Lesson:** overloading a return value with a sentinel (`−1`) lets one function carry two facts (height + validity). Remember this — it recurs constantly.

---

## 11. Binary Tree Maximum Path Sum  ⭐ (Hard — but you already know it)

> A "path" = any sequence of connected nodes (parent↔child), need **not** touch root or leaf.
> Find the max sum. Values can be negative.

This is **Diameter with sums instead of counts, plus a "drop negatives" rule.**

**Brute force:** "For each node consider the best downward path on the left and on the right; the
best path *bending* at that node is `node.val + leftGain + rightGain`. Take the global max. Naively
recomputing gains is O(n²)."

**Optimal O(n):**
```java
int maxSum = Integer.MIN_VALUE;
int maxPathSum(TreeNode root) { gain(root); return maxSum; }

int gain(TreeNode node) {
    if (node == null) return 0;
    int left  = Math.max(gain(node.left),  0);   // if a child's gain is negative, DROP it (take 0)
    int right = Math.max(gain(node.right), 0);
    maxSum = Math.max(maxSum, node.val + left + right);  // best path BENDING here (the global)
    return node.val + Math.max(left, right);     // to parent: I can only extend ONE side upward
}
```

**Two insights that make this "Hard":**
1. **`Math.max(gain, 0)`** — a subtree that only hurts you is skipped (start fresh).
2. **You update the global with BOTH sides** (`left+right`, a bent path), but **return only ONE side**
   to the parent (a path can't fork upward through you).

**Test case:**
```
       -10
       /  \
      9    20
          /  \
         15   7
```
Node20: left=15,right=7 → global tries `20+15+7=42`; returns `20+max(15,7)=35`.
Node-10: left=max(9,0)=9, right=max(35,0)=35 → global tries `-10+9+35=34`; but 42 already stored.
Answer = **42**.

> If you can write Diameter, you can write this. That's the entire point of learning by *pattern*, not by problem.

---

# TIER 4 — Carry a running sum DOWN (top-down path problems)

## 12. Path Sum (root→leaf equals target?)

**Brute force:** "Enumerate every root→leaf path, sum each, compare to target — that's essentially
what the recursion does, just phrased with an explicit list. Passing a running remainder down is cleaner."

**Optimal (subtract as you descend):**
```java
boolean hasPathSum(TreeNode node, int target) {
    if (node == null) return false;
    if (node.left == null && node.right == null)     // LEAF
        return node.val == target;                    // did we land exactly?
    int rem = target - node.val;
    return hasPathSum(node.left, rem) || hasPathSum(node.right, rem);
}
```

**Test case:**
```
      5
     / \
    4   8
   /
  11
 /  \
7    2      target = 22
```
5→4→11→2 = 22 → **true**. (Descend: 22−5=17, 17−4=13, 13−11=2, at leaf 2 → 2==2 ✅.)

**Watch:** the base case is **leaf**, not null — again the "leaf vs null" theme from Min Depth.

---

## 13. Path Sum II (return ALL root→leaf paths equal to target)

Same descent, but **backtrack**: add node to path, recurse, **remove** node.

```java
List<List<Integer>> pathSum(TreeNode root, int target) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(root, target, new ArrayList<>(), res);
    return res;
}
void dfs(TreeNode node, int rem, List<Integer> path, List<List<Integer>> res) {
    if (node == null) return;
    path.add(node.val);                              // choose
    if (node.left == null && node.right == null && node.val == rem)
        res.add(new ArrayList<>(path));              // COPY the path (don't store the mutating ref!)
    else {
        dfs(node.left,  rem - node.val, path, res);
        dfs(node.right, rem - node.val, path, res);
    }
    path.remove(path.size() - 1);                    // un-choose (BACKTRACK)
}
```

**Test case:**
```
        5
       / \
      4   8
     /   / \
    11  13  4
   / \     / \
  7   2   5   1     target = 22
```
Answer: `[[5,4,11,2], [5,8,4,5]]`.

**Two classic bugs to avoid (say you know these):**
1. Forgetting `new ArrayList<>(path)` → all results point to the same emptied list.
2. Forgetting `path.remove(...)` → paths leak into siblings.

---

## 14. Path Sum III (paths sum to target; path goes DOWNWARD but need not start at root or end at leaf)

**Brute force (state it — it's the natural first answer):** "For every node, treat it as a start and
DFS downward counting paths that sum to target. Two nested recursions → O(n²) (O(n·h))."
```java
// BRUTE O(n^2)
int pathSum3Brute(TreeNode root, long target){
    if(root==null) return 0;
    return countFrom(root, target)
         + pathSum3Brute(root.left, target)
         + pathSum3Brute(root.right, target);
}
int countFrom(TreeNode n, long rem){
    if(n==null) return 0;
    int here = (n.val==rem)?1:0;                     // path can end here (not only at leaf)
    return here + countFrom(n.left, rem-n.val) + countFrom(n.right, rem-n.val);
}
```

**Optimal O(n) — prefix sums + HashMap (subarray-sum-equals-K, on a tree):**
```java
int count = 0;
Map<Long,Integer> prefix = new HashMap<>();

int pathSum(TreeNode root, int target) {
    prefix.put(0L, 1);                               // empty prefix
    dfs(root, 0L, target);
    return count;
}
void dfs(TreeNode node, long curr, int target) {
    if (node == null) return;
    curr += node.val;
    count += prefix.getOrDefault(curr - target, 0);  // how many earlier prefixes make a valid path ending here
    prefix.merge(curr, 1, Integer::sum);             // add my prefix
    dfs(node.left,  curr, target);
    dfs(node.right, curr, target);
    prefix.merge(curr, -1, Integer::sum);            // BACKTRACK: leave this root→node branch
}
```
> The `curr - target` lookup + backtracking undo is exactly the **"subarray sum equals K"** array
> trick, transplanted onto a root-to-node prefix. Recognizing this cross-over is elite pattern skill.
> Use `long` to dodge overflow.

**Test case:**
```
       10
      /  \
     5   -3
    / \    \
   3   2    11
  / \   \
 3  -2   1      target = 8
```
Paths summing to 8: `5→3`, `5→2→1`, `-3→11`. Answer = **3**.

---

# TIER 5 — Special structure / graph conversion

## 15. Print All Nodes at Distance K from a target node

> Trees only have downward pointers, but "distance K" can go **upward and sideways** too. Trick:
> turn the tree into an **undirected graph** (give every node a parent pointer), then BFS from target.

**Brute force / natural approach:** "Record each node's parent via one DFS, then BFS outward from the
target K levels, tracking visited so we don't bounce back. O(n) both passes."

```java
List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    mapParents(root, null, parent);                  // pass 1: build parent links

    Queue<TreeNode> q = new LinkedList<>();
    Set<TreeNode> seen = new HashSet<>();
    q.add(target); seen.add(target);
    int dist = 0;
    while (!q.isEmpty()) {
        if (dist == k) break;                        // current level IS distance k
        for (int sz = q.size(); sz > 0; sz--) {
            TreeNode node = q.poll();
            for (TreeNode nb : new TreeNode[]{node.left, node.right, parent.get(node)}) {
                if (nb != null && seen.add(nb)) q.add(nb);   // 3 neighbours: L, R, parent
            }
        }
        dist++;
    }
    List<Integer> res = new ArrayList<>();
    for (TreeNode node : q) res.add(node.val);
    return res;
}
void mapParents(TreeNode node, TreeNode par, Map<TreeNode,TreeNode> parent) {
    if (node == null) return;
    parent.put(node, par);
    mapParents(node.left, node, parent);
    mapParents(node.right, node, parent);
}
```

**Test case:**
```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4        target = 5, k = 2
```
Distance-2 from 5: `7, 4` (down) and `1` (up via 3). Answer = **[7, 4, 1]** (order may vary).

**Big lesson:** *"distance in any direction" in a tree → convert to graph + BFS.* This unlocks many follow-ups.

---

## 16. Boundary Traversal of a Binary Tree (anti-clockwise)

> Print the boundary: root → left boundary (top→down, no leaves) → all leaves (left→right) →
> right boundary (bottom→up, no leaves). The trap is **not double-counting** the root/leaves.

```java
List<Integer> boundary(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    if (!isLeaf(root)) res.add(root.val);            // root (unless it's the only node)

    // 1. left boundary (exclude leaves)
    TreeNode t = root.left;
    while (t != null) {
        if (!isLeaf(t)) res.add(t.val);
        t = (t.left != null) ? t.left : t.right;
    }
    // 2. all leaves, left to right
    addLeaves(root, res);
    // 3. right boundary (exclude leaves), collected bottom-up
    Deque<Integer> stack = new ArrayDeque<>();
    t = root.right;
    while (t != null) {
        if (!isLeaf(t)) stack.push(t.val);
        t = (t.right != null) ? t.right : t.left;
    }
    while (!stack.isEmpty()) res.add(stack.pop());
    return res;
}
boolean isLeaf(TreeNode n){ return n.left==null && n.right==null; }
void addLeaves(TreeNode n, List<Integer> res){
    if (n == null) return;
    if (isLeaf(n)) { res.add(n.val); return; }
    addLeaves(n.left, res);
    addLeaves(n.right, res);
}
```

**Test case:**
```
            1
          /   \
         2      3
        / \    / \
       4   5  6   7
          / \
         8   9
```
Root `1` → left boundary `2` → leaves `4,8,9,6,7` → right boundary bottom-up `3`.
Answer = **[1, 2, 4, 8, 9, 6, 7, 3]**.

**Why it's Medium:** no clever algorithm, but **3 careful sub-walks + leaf de-duplication**. Interviewers use it to test precision, not cleverness.

---

## 17. Count Complete Tree Nodes (better than O(n))

> In a **complete** tree, all levels full except possibly the last, filled left→right. Naive count
> is O(n) — but we can exploit completeness for **O(log²n)**.

**Brute force:** "Just DFS and count every node — O(n). Correct, and honestly acceptable. But since
it's *complete*, we can do better."

**Optimal O(log²n) — if left-spine height == right-spine height, the subtree is PERFECT → `2^h − 1`:**
```java
int countNodes(TreeNode root) {
    if (root == null) return 0;
    int lh = leftHeight(root), rh = rightHeight(root);
    if (lh == rh) return (1 << lh) - 1;              // perfect subtree: 2^h - 1, no recursion needed
    return 1 + countNodes(root.left) + countNodes(root.right);
}
int leftHeight(TreeNode n){ int h=0; while(n!=null){h++; n=n.left;} return h; }
int rightHeight(TreeNode n){ int h=0; while(n!=null){h++; n=n.right;} return h; }
```
Each of O(log n) levels does O(log n) height work → **O(log²n)**.

**Test case:**
```
        1
       / \
      2   3
     / \  /
    4  5 6
```
Root: lh=3 (1→2→4), rh=2 (1→3→6) → not perfect → `1 + countNodes(2) + countNodes(3)`.
Node2 perfect (lh=rh=2) → `2²−1=3`. Node3: lh=2,rh=1 → `1+count(6)+0 = 2`. Total `1+3+2=6`.

**Lesson:** when a problem hands you a **structural guarantee** ("complete", "perfect", "BST"), there's almost always a sub-linear trick hiding in it.

---

## 18. Binary Tree Cameras (Hard — greedy DFS with states)

> Place cameras on nodes; each camera covers itself, its parent, and its direct children. Minimize
> cameras so every node is covered. Greedy from the **bottom up**: put cameras as high as possible.

**Brute force:** "Tree DP: for each node, min cameras under 3 states (has-camera / covered-no-camera
/ not-covered) and combine children — correct but verbose. The greedy collapses it."

**The greedy insight:** leaves should NOT hold cameras (wasteful) — put the camera on the **parent of
a leaf**. So process bottom-up; whenever a child is *uncovered*, the parent must take a camera.

Each node returns one of 3 states:
- `0` = I am **NOT covered** (need my parent to cover me)
- `1` = I am **covered**, no camera on me
- `2` = I **have a camera**

```java
int cameras = 0;
int minCameraCover(TreeNode root) {
    if (dfs(root) == 0) cameras++;                   // root ended uncovered → cover it
    return cameras;
}
int dfs(TreeNode node) {
    if (node == null) return 1;                      // null = "covered" so leaves read as uncovered
    int l = dfs(node.left);
    int r = dfs(node.right);
    if (l == 0 || r == 0) { cameras++; return 2; }   // a child is uncovered → I MUST place a camera
    if (l == 2 || r == 2) return 1;                  // a child has a camera → I'm covered
    return 0;                                         // both children merely covered → I'm uncovered
}
```

**Test case:**
```
      0
     /
    0
   / \
  0   0
```
Leaves return 0 (uncovered). Their parent sees a 0 → places a camera (cameras=1), returns 2.
Root sees child state 2 → returns 1 (covered). Root not 0 → done. Answer = **1** camera.

**Why the order of the `if`s matters:** you must check "child uncovered" (`==0`) *before* "child has
camera" (`==2`), because covering an uncovered child is mandatory and takes priority. This ordering
is the whole trick.

---

# THE INTERVIEW PLAYBOOK (say these steps out loud)

1. **Clarify:** "Is a node with one child a leaf? Can values be negative? Is it a BST? Complete?"
   (Each answer flips the approach — see Min Depth, Max Path Sum, Count Complete Nodes.)
2. **State brute force first, always.** "The naive approach is X, which is O(...). Let me improve it."
   Interviewers grade *communication*; brute-first shows you reason instead of memorize.
3. **Classify the pattern:**
   - Need a child's result before mine? → **bottom-up / postorder** (return a value).
   - Carrying a running sum/path down? → **top-down** (parameter + backtrack).
   - Best answer can be at any node? → **global variable + return-something-else** (Tier 3).
   - Movement in all directions / distances? → **convert to graph + BFS** (Distance K).
   - Structural guarantee (complete/BST)? → look for a **sub-linear** trick.
4. **Write the 4-line skeleton**, fill base case → recurse → combine → return.
5. **Dry-run one small tree out loud.** Always test: empty tree, single node, skewed (one child), and a negative value if sums are involved.

# YOUR PRACTICE ORDER (spaced, not linear)

- **Day 1:** #1–5 (traversals + depths). Write iterative inorder from memory.
- **Day 2:** #6–8 (two-tree lockstep). Then re-derive #4 without looking.
- **Day 3:** #9–11 — the money tier. Write Diameter, then *derive* Max Path Sum from it yourself.
- **Day 4:** #12–14 (path sums + prefix-sum trick).
- **Day 5:** #15–18 (graph conversion, boundary, complete-count, cameras).
- **Day 6:** Blind re-solve #9, #11, #14, #18 — if these four flow, you've mastered the pattern.

> **The one thing to remember:** *"If the genie gave me the correct answers for my left and right
> subtrees, how do I combine them?"* Every single one of these 18 is that sentence, plus at most one
> decision: pass-down, global, or graph.