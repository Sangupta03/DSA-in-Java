# Binary Search Tree (BST) Mastery — Striver Style

> Goal: after this, you should look at *any* BST problem and, within 60 seconds, know
> (1) do I **navigate by comparison** (O(h)) or **walk the sorted inorder stream** (O(n)),
> (2) do I need a global / running variable, (3) what template do I reach for.
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
        4
       / \
      2   7
     / \
    1   3
```

means root=4, root.left=2, root.right=7, node2.left=1, node2.right=3. (Note: this is a valid BST —
left < root < right everywhere.)

---

# PART 0 — THE CORE MENTAL MODEL (the whole pattern in one idea)

Everything in this pattern flows from **two facts**. Burn them in:

1. **Ordering:** for every node, `all left descendants < node < all right descendants`.
2. **Golden consequence:** the **inorder traversal of a BST is sorted ascending.** (Reverse inorder = descending.)

The one sentence that captures the whole pattern:

> *"A BST is a sorted array wearing a tree costume. When stuck, ask: what would I do if this were the
> sorted array `inorder(root)`?"*

### The two models — every problem is one of these

This is the single most important distinction in BST problems. Decide which one, and the template picks itself.

| Model | What you use | Cost | Mental cue |
|-------|--------------|------|------------|
| **A — Navigate by comparison** | the ordering: go left if smaller, right if larger | **O(h)** | "I don't visit the whole tree — I discard half at each step" (search, insert, delete, closest, successor) |
| **B — Sorted inorder stream** | the fact that inorder is sorted | **O(n)** | "This is really a sorted-array problem" (kth, validate, two-sum, recover, greater-tree, iterator, tree→DLL) |

> Most "clever" BST solutions are just Model B — do (or *pause*) an inorder walk and you're holding a
> sorted sequence. Most "efficient" ones are Model A — never touch the half you don't need.

### The reusable templates (these ARE the pattern)

**T1 — Search / navigate (Model A):**
```java
TreeNode search(TreeNode root, int target) {
    while (root != null && root.val != target)
        root = (target < root.val) ? root.left : root.right;
    return root;
}
```

**T2 — Insert (navigate to a null slot, attach):**
```java
TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left  = insert(root.left,  val);
    else                root.right = insert(root.right, val);
    return root;
}
```

**T3 — Inorder, iterative (Model B backbone — lets you stop early / step manually):**
```java
Deque<TreeNode> stack = new ArrayDeque<>();
TreeNode curr = root;
while (curr != null || !stack.isEmpty()) {
    while (curr != null) { stack.push(curr); curr = curr.left; } // dive left
    curr = stack.pop();     // next-smallest node (SORTED order)
    // visit curr.val
    curr = curr.right;
}
```

**T4 — Reverse inorder (sorted DESCENDING):** same as T3 but dive right first / recurse right→node→left.

### The 3 questions to ask for ANY BST problem (say these out loud in interviews)

1. **Is it actually a BST?** If yes, announce: *"I'll exploit left < root < right."* (Don't treat it as a plain binary tree.)
2. **Navigate (Model A) or sorted stream (Model B)?** "search/insert/delete/closest/successor" → A; "kth/validate/two-sum/recover/greater/iterator/sorted-list" → B.
3. **Do I need a running/global variable?** (running sum for Greater Tree, `prev` node for Validate/Recover, a counter for Kth.)

> 🔑 **The two recurring tricks:** (a) a **`prev` pointer** during inorder to compare each node with the
> previous one (Validate, Recover); (b) **stopping the inorder early** or pausing it (Kth, Iterator).
> Half of "Medium" BST problems are just these.

---

# SUB-PATTERN MAP — which of the 17 are secretly the same

| Tier | Idea | Problems |
|------|------|----------|
| 1 | Navigate by comparison (Model A, O(h)) | Search, Insert, Delete, Closest Value, Inorder Successor/Predecessor |
| 2 | Walk the sorted inorder stream (Model B) | Validate BST, Kth Smallest, BST Iterator, Two Sum IV |
| 3 | Inorder with a running memory (`prev` / running sum) | Convert BST to Greater Tree, Recover BST, Tree → Doubly Linked List |
| 4 | Build a BST from ordered input | Convert Sorted Array to BST, Construct BST from Preorder |
| 5 | Combine / detect BSTs | Merge 2 BST, Merge BSTs, Maximum Sum BST |

Notice: **Validate, Kth, Iterator, Recover, Greater Tree, and Tree→DLL are the same problem** — an
inorder walk where you do one small thing per node. Master Tier 2+3 and six problems fall at once.
That's the Striver payoff.

---

# TIER 1 — Navigate by comparison (Model A, O(h))

## 1. Search in a BST

**Brute force (interview narration):** "The naive approach treats it as any binary tree and DFS all n
nodes — O(n). But the ordering lets me discard half the tree at each step → O(h)."

**Optimal — T1 verbatim:**
```java
TreeNode searchBST(TreeNode root, int target) {
    while (root != null && root.val != target)
        root = (target < root.val) ? root.left : root.right;
    return root;   // null if absent
}
```
- **Time O(h), Space O(1).**

**Test case:** BST
```
    4
   / \
  2   7
 / \
1   3
```
`search(2)` → 2<4 go left → node 2 ✅. `search(5)` → 5>4 right to 7 → 5<7 left (null) → **null**.

---

## 2. Insert into a BST

**Brute force:** "Insert can go anywhere in a plain binary tree, but for a BST there's exactly one
correct leaf slot — walk to it comparing values."

**Optimal — T2 verbatim:**
```java
TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left  = insertIntoBST(root.left,  val);
    else                root.right = insertIntoBST(root.right, val);
    return root;
}
```

**Test case:** insert 5 into the tree above → 5>4 right to 7 → 5<7 left of 7 (empty) → attach.
Result: `7.left = 5`.

---

## 3. Delete Node in a BST  (the one people fear — 3 cases)

**Brute force:** "Collect all values, drop the key, rebuild a BST — O(n) and wasteful. The proper way
navigates to the node in O(h), then handles three structural cases."

**Optimal:**
```java
TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    if (key < root.val)      root.left  = deleteNode(root.left,  key);
    else if (key > root.val) root.right = deleteNode(root.right, key);
    else {                                        // found the node to delete
        if (root.left == null)  return root.right;   // case 1: 0 / 1 child (right)
        if (root.right == null) return root.left;    // case 2: 1 child (left)
        // case 3: two children → copy inorder successor (min of right subtree) up, then delete it
        TreeNode succ = root.right;
        while (succ.left != null) succ = succ.left;
        root.val = succ.val;
        root.right = deleteNode(root.right, succ.val);
    }
    return root;
}
```

**Test case:** delete 3 from
```
        5
       / \
      3   6
     / \   \
    2   4   7
```
Node 3 has two children → successor = min(right subtree of 3) = **4** → copy 4 up, delete old 4.
Result: the 3 becomes 4; its former right child (4) is removed.

**Lesson:** the two-children case is the whole difficulty — replace the value with the inorder
successor, then recursively delete that successor (which now has ≤ 1 child).

---

## 4. Closest Binary Search Tree Value

> Return the node value closest to a target `double`.

**Brute force:** "Inorder to a list, linear scan for the closest — O(n). But walking toward the target
narrows the gap monotonically → O(h)."

**Optimal:**
```java
int closestValue(TreeNode root, double target) {
    int closest = root.val;
    while (root != null) {
        if (Math.abs(root.val - target) < Math.abs(closest - target)) closest = root.val;
        root = (target < root.val) ? root.left : root.right;   // move toward target
    }
    return closest;
}
```

**Test case:** BST `{4,2,5,1,3}`, target = 3.7 → path 4 → 2 → 3. |4−3.7|=0.3 wins over |3−3.7|=0.7 → **4**.

---

## 5. Inorder Successor / Predecessor in BST

> Successor = next-larger value; predecessor = next-smaller. Do it in O(h) — no full inorder needed.

**Brute force:** "Full inorder to a sorted list, find the target, return the neighbor — O(n). But the
ordering lets me remember the best candidate as I walk down."

**Optimal (successor: remember the node each time you turn LEFT):**
```java
TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    TreeNode succ = null;
    while (root != null) {
        if (p.val < root.val) { succ = root; root = root.left; }  // candidate → go smaller
        else                    root = root.right;                // too small/equal → go larger
    }
    return succ;
}
TreeNode inorderPredecessor(TreeNode root, TreeNode p) {          // mirror image
    TreeNode pred = null;
    while (root != null) {
        if (p.val > root.val) { pred = root; root = root.right; }
        else                    root = root.left;
    }
    return pred;
}
```

**Test case:** BST `{20,10,30,5,15,25,35}` → successor of 15 = **20**; predecessor of 25 = **20**.

**Lesson:** "the last node where I turned left" is the successor. This "remember the turn" idea is
the O(h) shortcut that avoids a full traversal.

---

# TIER 2 — Walk the sorted inorder stream (Model B, O(n))

**The pattern:** run an inorder walk; because it yields sorted order, you either compare each node to
the **previous** one, **count** to the kth, or **pause** the walk. One small action per node.

## 6. Validate Binary Search Tree

> Is the whole tree a valid BST? (Strict: the *entire* left subtree < node < the *entire* right subtree.)

**Brute force (narrate this — it's the "aha"):** "For each node, verify all left-subtree values are
smaller and all right-subtree values larger — O(n²). Worse, the naive shortcut of only comparing a
node to its direct children is *wrong*."

**Optimal 1 — range bounds (pass allowed (min,max) DOWN):**
```java
boolean isValidBST(TreeNode root) { return valid(root, Long.MIN_VALUE, Long.MAX_VALUE); }
boolean valid(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;   // must stay strictly inside bounds
    return valid(node.left, min, node.val) && valid(node.right, node.val, max);
}
```

**Optimal 2 — inorder must be strictly increasing (Model B + `prev`):**
```java
long prev = Long.MIN_VALUE;
boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (!isValidBST(root.left)) return false;
    if (root.val <= prev) return false;   // sorted order violated
    prev = root.val;
    return isValidBST(root.right);
}
```

**Test case:**
```
      5
     / \
    1   4        the 3 is left-of-6 but must ALSO be > 5
       / \
      3   6
```
Naive child-check passes (3<4<6, 1<5<4?) but bounds/inorder catch that 4 < 5 sits in 5's right subtree → **false**. A valid `[2,1,3]` → **true**.

**Lesson:** use **`long`** bounds — node values can be `Integer.MIN/MAX_VALUE`, and `int` bounds overflow. Classic interview trap.

---

## 7. Kth Smallest Element in BST

**Brute force:** "Full inorder into a list, return index k−1 — O(n) time and space. But I can stop the
inorder the instant I reach the kth."

**Optimal (T3 with an early exit):**
```java
int kthSmallest(TreeNode root, int k) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
        while (curr != null) { stack.push(curr); curr = curr.left; }
        curr = stack.pop();
        if (--k == 0) return curr.val;    // early exit at the kth-smallest
        curr = curr.right;
    }
    return -1;
}
```
**Kth largest?** Same code with reverse inorder (dive right first).

**Test case:** BST `{3,1,4,null,2}` → sorted `1,2,3,4`. k=1 → **1**; k=3 → **3**.

---

## 8. Binary Search Tree Iterator

> `next()` returns the next-smallest value; `hasNext()`. O(h) memory, amortized O(1) per call.

**Brute force:** "Precompute the full inorder list in the constructor and index into it — O(n) space.
The elegant version is a *paused* inorder whose stack holds only the current left-spine → O(h) space."

**Optimal (T3, frozen mid-traversal):**
```java
class BSTIterator {
    private Deque<TreeNode> stack = new ArrayDeque<>();
    public BSTIterator(TreeNode root) { pushLeft(root); }
    private void pushLeft(TreeNode node) { while (node != null) { stack.push(node); node = node.left; } }
    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);          // seed the successor's left-spine
        return node.val;
    }
    public boolean hasNext() { return !stack.isEmpty(); }
}
```

**Test case:** on `{7,3,15,null,null,9,20}`: next→3, next→7, hasNext→true, next→9, next→15, next→20, hasNext→false.

**Lesson:** the iterative inorder stack (T3) *is* a pausable sorted generator. Recognizing that unifies Kth, Iterator, and the two-pointer version of Two Sum below.

---

## 9. Two Sum IV — Input is a BST

**Brute force:** "For each node, search the BST for `k − val` → O(n·h). Simple and correct."

**Cleaner — HashSet during any traversal (O(n) time/space):**
```java
Set<Integer> seen = new HashSet<>();
boolean findTarget(TreeNode root, int k) {
    if (root == null) return false;
    if (seen.contains(k - root.val)) return true;
    seen.add(root.val);
    return findTarget(root.left, k) || findTarget(root.right, k);
}
```
**BST-aware optimal (mention it):** an ascending BST iterator + a descending one, two-pointer converging — O(h) space, exploits the sorted order.

**Test case:** BST `{5,3,6,2,4,null,7}`, k=9 → **true** (2+7 / 4+5 / 3+6); k=28 → **false**.

---

# TIER 3 — Inorder with a running memory (`prev` / running sum)

**The pattern:** an inorder (or reverse-inorder) walk while carrying **one piece of state across
nodes** — the previous node, or a running total — and mutating as you go.

## 10. Convert BST to Greater Tree

> Replace each node's value with (its value + sum of all values greater than it).

**Brute force:** "For each node, sum every larger value — O(n²). Or inorder to a sorted list, compute
suffix sums, write back. The clean way is a reverse inorder with a running sum → O(n)."

**Optimal (T4 + running sum):**
```java
int sum = 0;
TreeNode convertBST(TreeNode root) {
    if (root == null) return null;
    convertBST(root.right);     // visit LARGER values first
    sum += root.val;
    root.val = sum;             // node becomes running total of everything ≥ it
    convertBST(root.left);
    return root;
}
```

**Test case:** BST `{2,1,3}` → node3: sum=3 → 3; node2: sum=5 → 5; node1: sum=6 → 6. Values `{1→6, 2→5, 3→3}`.

**Lesson:** "sum of everything greater" screams **reverse inorder** (descending) with an accumulator. Same skeleton as Kth-largest.

---

## 11. Recover BST

> Exactly two nodes were swapped. Fix the tree by swapping their values back (structure unchanged).

**Brute force:** "Inorder to a list, find the two positions that break sorted order, swap them, write
back — O(n) space. The in-place version tracks the offenders during the walk with a `prev` pointer."

**Optimal (inorder + `prev`, capture the 1–2 dips):**
```java
TreeNode first = null, second = null, prev = null;
void recoverTree(TreeNode root) {
    inorder(root);
    int t = first.val; first.val = second.val; second.val = t;   // swap back
}
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    if (prev != null && node.val < prev.val) {
        if (first == null) first = prev;   // 1st dip → the larger culprit is `prev`
        second = node;                     // last dip → the smaller culprit is current node
    }
    prev = node;
    inorder(node.right);
}
```

**Test case:** valid inorder `1,2,3,4,5`; swap 2 and 4 → `1,4,3,2,5`. Dips: 4>3 and 3>2 → first=4, second=2 → swap back → fixed.

**Lesson:** `first = prev` but `second = node` handles **both** the adjacent swap (one dip, both set at once) and the far-apart swap (two dips). Overloading a single walk to find two anomalies — same spirit as the sentinel trick in DFS.

---

## 12. Convert Binary Tree (BST) to Doubly Linked List

> In-place: `left` = prev, `right` = next; the DLL must be in sorted (inorder) order.

**Brute force:** "Inorder to a list, then stitch nodes with new pointers — O(n) space. In-place, thread
`prev ↔ curr` *during* the inorder walk."

**Optimal (inorder threading):**
```java
TreeNode prev = null, head = null;
TreeNode treeToDoublyList(TreeNode root) {
    if (root == null) return null;
    inorderLink(root);
    head.left = prev; prev.right = head;   // close the loop for a CIRCULAR DLL (LC 426)
    return head;
}
void inorderLink(TreeNode node) {
    if (node == null) return;
    inorderLink(node.left);
    if (prev == null) head = node;                 // first (smallest) = head
    else { prev.right = node; node.left = prev; }   // link prev ↔ node
    prev = node;
    inorderLink(node.right);
}
```

**Test case:** BST `{4,2,5,1,3}` → DLL `1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5` (circular back to 1). head = 1.

**Lesson:** identical skeleton to Recover BST — inorder walk carrying a `prev`. Only the per-node action differs (compare vs. link).

---

# TIER 4 — Build a BST from ordered input

## 13. Convert Sorted Array to BST

> Build a **height-balanced** BST from a sorted array.

**Brute force:** "Insert elements left-to-right with T2 → a degenerate O(n)-tall stick, O(n²) build. To
stay balanced, pick the MIDDLE as root so both halves are equal."

**Optimal (middle = root, recurse):**
```java
TreeNode sortedArrayToBST(int[] nums) { return build(nums, 0, nums.length - 1); }
TreeNode build(int[] nums, int lo, int hi) {
    if (lo > hi) return null;
    int mid = lo + (hi - lo) / 2;                // middle keeps it balanced
    TreeNode root = new TreeNode(nums[mid]);
    root.left  = build(nums, lo, mid - 1);
    root.right = build(nums, mid + 1, hi);
    return root;
}
```

**Test case:** `[-10,-3,0,5,9]` → root 0, left from `[-10,-3]`, right from `[5,9]`. Height ⌈log n⌉.

---

## 14. Construct BST from Preorder Traversal

> Preorder = root, then left, then right. Rebuild in O(n) using an **upper bound**.

**Brute force:** "Insert each preorder value one by one with T2 → O(n·h), O(n²) worst. A single scan
with a bound does it in O(n)."

**Optimal (recurse with an inherited upper bound):**
```java
int idx = 0;
TreeNode bstFromPreorder(int[] pre) { return build(pre, Integer.MAX_VALUE); }
TreeNode build(int[] pre, int bound) {
    if (idx == pre.length || pre[idx] > bound) return null;  // value belongs higher up
    TreeNode root = new TreeNode(pre[idx++]);
    root.left  = build(pre, root.val);   // left values must be < root
    root.right = build(pre, bound);      // right values must be < inherited bound
    return root;
}
```

**Test case:** `[8,5,1,7,10,12]` → root 8; left builds `5(1,7)`; right builds `10(_,12)`. O(n).

**Lesson:** "reconstruct a BST from one traversal" → lean on the ordering with a **bound**, not repeated insertion.

---

# TIER 5 — Combine / detect BSTs

## 15. Merge 2 BSTs

> Merge two BSTs into one **balanced** BST (all values, still sorted).

**Brute force:** "Insert every node of tree 2 into tree 1 with T2 → O(n·h) and possibly unbalanced.
Better: two inorders (each already sorted) → merge → rebuild balanced."

**Optimal (O(m+n)):**
```java
TreeNode mergeTwoBST(TreeNode r1, TreeNode r2) {
    List<Integer> a = new ArrayList<>(), b = new ArrayList<>();
    inorder(r1, a); inorder(r2, b);                    // both sorted
    List<Integer> merged = mergeSorted(a, b);          // two-pointer merge
    int[] arr = merged.stream().mapToInt(Integer::intValue).toArray();
    return build(arr, 0, arr.length - 1);              // reuse #13
}
List<Integer> mergeSorted(List<Integer> a, List<Integer> b) {
    List<Integer> out = new ArrayList<>(); int i = 0, j = 0;
    while (i < a.size() && j < b.size())
        out.add(a.get(i) <= b.get(j) ? a.get(i++) : b.get(j++));
    while (i < a.size()) out.add(a.get(i++));
    while (j < b.size()) out.add(b.get(j++));
    return out;
}
```
**O(1)-extra-space variant to mention:** merge on the fly using two BST iterators (two stacks).

**Test case:** BST1 `{1,2,4}`, BST2 `{3,5,6}` → merged sorted `[1,2,3,4,5,6]` → balanced BST.

---

## 16. Merge BSTs (k trees)

> Generalize #15 to `k` BSTs.

**Brute force:** "Concatenate all inorders and sort → O(N log N). Since each is already sorted, a
**k-way merge** with a min-heap gives O(N log k), then rebuild balanced."
```java
TreeNode mergeKBSTs(List<TreeNode> trees) {
    List<List<Integer>> lists = new ArrayList<>();
    for (TreeNode t : trees) { List<Integer> l = new ArrayList<>(); inorder(t, l); lists.add(l); }
    int[] all = kWayMerge(lists);                 // min-heap over k list heads (Merge k Sorted Lists)
    return build(all, 0, all.length - 1);         // reuse #13
}
```

**Note (LeetCode 1932 variant):** "Merge BSTs to Create Single BST" has a *special rule* — attach one
tree's root onto a leaf of another sharing its value, and the final tree must itself be a valid BST.
That's a distinct, harder problem: count values, find the overall root (value never appearing as a
leaf), stitch by matching root↔leaf values, then run #6 (Validate) to confirm.

**Test case (plain merge):** `{2}`, `{1,3}`, `{5,6}` → sorted `[1,2,3,5,6]` → balanced BST.

---

## 17. Maximum Sum BST in Binary Tree  ⭐ (the Tier-3-DFS trick, BST flavor)

> In an *arbitrary* binary tree, find the max sum of any subtree that is itself a valid BST.

**Brute force:** "For each node, validate its subtree and sum it if valid — O(n²). Fuse validation +
sum into one postorder that returns everything the parent needs → O(n)."

**Optimal (postorder returning `{isBST, min, max, sum}` + a global):**
```java
int maxSum = 0;                       // empty subtree contributes 0, so the answer is ≥ 0
int maxSumBST(TreeNode root) { dfs(root); return maxSum; }
// returns {isBST(1/0), subtreeMin, subtreeMax, subtreeSum}
int[] dfs(TreeNode node) {
    if (node == null) return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
    int[] l = dfs(node.left), r = dfs(node.right);
    if (l[0] == 1 && r[0] == 1 && node.val > l[2] && node.val < r[1]) {   // valid BST here
        int sum = node.val + l[3] + r[3];
        maxSum = Math.max(maxSum, sum);
        return new int[]{1, Math.min(node.val, l[1]), Math.max(node.val, r[2]), sum};
    }
    return new int[]{0, 0, 0, 0};     // not a BST → poison this subtree
}
```

**Why null returns `{1, +∞, −∞, 0}`:** it lets any leaf trivially pass `leftMax < val < rightMin`.

**Test case:**
```
        1
       / \
      4   3
     / \   \
    2   4   5
```
The top isn't a BST (4 sits left of 1). The `3 → 5` subtree is a valid BST summing **8** → answer **8**.

**Lesson:** this is exactly the DFS Tier-3 "return more than one thing" trick (like Balanced's sentinel,
but returning four facts). Recognizing that cross-over is the pattern fluency you're building.

---

# THE INTERVIEW PLAYBOOK (say these steps out loud)

1. **Confirm it's a BST**, and announce you'll exploit `left < root < right`. (Don't reduce it to a plain binary tree.)
2. **State brute force first, always.** Usually "treat it as a general binary tree, O(n) or O(n²)"; then improve using the BST property.
3. **Pick the model:**
   - search / insert / delete / closest / successor → **Model A**, navigate in O(h).
   - kth / validate / two-sum / recover / iterator / greater / sorted-list → **Model B**, inorder stream.
   - build / construct from sorted or preorder → recursive **build** (middle, or bound).
   - merge → inorder to sorted → merge → rebuild balanced.
   - max/valid BST *inside* a binary tree → **postorder returning `{isBST,min,max,sum}`**.
4. **Reach for the template** (T1–T4), adapt the one line that differs.
5. **Guard the traps:** `long` bounds in Validate; the two-children case in Delete; `prev`-pointer setup in Validate/Recover; k out of range; duplicate values.
6. **Dry-run:** single node, skewed tree (worst-case O(n) height), target absent, k too large.

# YOUR PRACTICE ORDER (spaced, not linear)

- **Day 1:** #1–3 (search, insert, delete). Write delete's three cases from memory.
- **Day 2:** #4–5 (closest, successor/predecessor). Then re-derive delete without looking.
- **Day 3:** #6–9 — the sorted-stream core. Write iterative inorder (T3) blind; reuse it for Kth and Iterator.
- **Day 4:** #10–12 (Greater Tree, Recover, Tree→DLL). Notice all three are "inorder + one carried variable."
- **Day 5:** #13–17 (build + combine + Max Sum BST).
- **Day 6:** Blind re-solve #3, #6, #11, #17 — if these four flow, you've mastered the pattern.

> **The one thing to remember:** *A BST is a sorted array wearing a tree costume.* Every one of these 17
> is either **navigate by comparison** (Model A, O(h)) or **walk the sorted inorder stream** (Model B, O(n)) —
> plus, at most, one carried variable: a `prev` node, a running sum, or a counter.