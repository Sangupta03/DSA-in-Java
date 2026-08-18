# Partition DP (a.k.a. MCM Pattern / Interval DP) — Complete Guide

## 1. What is this pattern, really?

Every DP pattern is defined by **how the state moves**. In Partition DP, you are given a **range/interval `[i, j]`**, and you must **place a "cut point" `k` somewhere inside it** to break the range into two smaller sub-ranges: `[i, k]` and `[k+1, j]` (or `[i,k-1]` and `[k,j]`, depending on the problem).

You then **try every possible cut point `k`**, solve both halves recursively, combine their results with some **cost function**, and take the best (min/max/count) over all choices of `k`.

That's it. That's the entire pattern. Everything else is just "what is the cost function" and "what are the base cases."

> **Mental model:** "I have a range. I must break it into two pieces at some point. Which point gives me the best answer?" — if you can phrase the problem this way, it's Partition DP.

---

## 2. How to identify a Partition DP problem

Ask these questions when you read a problem. If you answer "yes" to most of these, it's Partition DP:

| Signal | Example |
|---|---|
| You're told to **place operators/brackets/cuts** in a sequence | Matrix Chain Multiplication, Boolean Parenthesization |
| The problem talks about **partitioning a string/array into parts** and optimizing something over the parts | Palindrome Partitioning II |
| **Order of operations/merging matters**, and different orders give different costs | MCM, Burst Balloons |
| You see the phrase **"minimum cost to combine/merge"** | Merge stones, MCM |
| The answer for a range `[i, j]` depends on **trying every split point `k` between `i` and `j`** | almost the definition |
| A **brute force would be to try every parenthesization / every way to insert cuts** (exponential, ~Catalan number growth) | any bracketing problem |

**Contrast with other patterns** (important for interview pattern-recognition):
- If you're choosing to **include/exclude single elements** → that's 0/1 Knapsack, not this.
- If you're comparing **two strings index by index** (i moves on one string, j on another) → that's the LCS/DP-on-2-strings pattern, not this.
- If the state moves **only forward on one array with a "take or skip" decision** → that's linear DP.
- The dead giveaway for Partition DP: **the recursion needs a THIRD variable `k` that ranges between your two boundary pointers `i` and `j`.**

---

## 3. The Master Template (Striver's 5-step approach)

This is the exact scaffold I want you to memorize. Every partition DP problem is this template with 3 blanks filled in.

### Step 1: Express in terms of `i` and `j` (the boundaries of the current range)

### Step 2: Explore all partition points `k` between `i` and `j`

### Step 3: Return the best (min/max) among all choices, combined with the cost of cutting at `k`

### Step 4: Convert to memoization (top-down)

### Step 5: Convert to tabulation (bottom-up) — **the tricky part**: since `f(i,j)` depends on smaller ranges, you must iterate by **increasing length of the range**, not by `i` or `j` alone.

---

## 4. The Mother Problem: Matrix Chain Multiplication (MCM)

**Problem:** Given dimensions of matrices in a chain, find the minimum number of scalar multiplications needed to multiply them all together, given that matrix multiplication is associative (order of multiplying pairs affects total cost, but not the final matrix).

**Why this is THE template problem:** every other partition DP problem is a reskin of this.

### 4.1 Recursive (brute force) — Step 1, 2, 3

```java
class Solution {
    // arr[] has n elements representing dimensions of n-1 matrices
    // Matrix i has dimensions arr[i-1] x arr[i]
    
    public int mcmRecursive(int[] arr, int i, int j) {
        // Base case: single matrix, no multiplication needed
        if (i == j) return 0;
        
        int minCost = Integer.MAX_VALUE;
        
        // Step 2: try every partition point k between i and j-1
        for (int k = i; k < j; k++) {
            int cost = arr[i - 1] * arr[k] * arr[j]        // cost of multiplying the two resulting matrices
                     + mcmRecursive(arr, i, k)               // cost of solving left partition
                     + mcmRecursive(arr, k + 1, j);          // cost of solving right partition
            
            minCost = Math.min(minCost, cost);              // Step 3: take the best
        }
        
        return minCost;
    }
    
    public int matrixMultiplication(int[] arr, int n) {
        return mcmRecursive(arr, 1, n - 1);
    }
}
```

**Read this slowly**: `i` and `j` represent a **range of matrix indices**, not array values. `k` is the point where you "cut" — meaning: multiply matrices `i..k` first, multiply matrices `k+1..j` first, then multiply those two results together. The cost of that final multiplication is `arr[i-1] * arr[k] * arr[j]` — this comes from matrix multiplication dimension rules.

### 4.2 Memoization (top-down) — Step 4

Just add a `dp[i][j]` cache. Nothing about the logic changes.

```java
class Solution {
    public int mcmMemo(int[] arr, int i, int j, int[][] dp) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = arr[i - 1] * arr[k] * arr[j]
                     + mcmMemo(arr, i, k, dp)
                     + mcmMemo(arr, k + 1, j, dp);
            minCost = Math.min(minCost, cost);
        }
        
        return dp[i][j] = minCost;
    }
    
    public int matrixMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return mcmMemo(arr, 1, n - 1, dp);
    }
}
```

### 4.3 Tabulation (bottom-up) — Step 5, the important shift in thinking

This is where beginners get stuck. In linear DP you loop `i` from 0 to n. Here, **you must loop by increasing interval length**, because `dp[i][j]` needs smaller ranges `dp[i][k]` and `dp[k+1][j]` to already be computed.

```java
class Solution {
    public int matrixMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];
        
        // i == j means single matrix -> cost 0, dp already initialized to 0, so skip
        
        // loop by increasing length of the chain
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = arr[i - 1] * arr[k] * arr[j]
                             + dp[i][k]
                             + dp[k + 1][j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }
        
        return dp[1][n - 1];
    }
}
```

**Why `i` goes from `n-1` down to `1`, and `j` from `i+1` upward:** you need `dp[i][k]` (with `k < j`, so shorter range, computed earlier) and `dp[k+1][j]` (with `k+1 > i`, so starts later — needs `i` to have already been processed for higher values). Running `i` in decreasing order and `j` in increasing order guarantees every sub-range you look up was already filled in. This exact loop shape is what you'll reuse in every tabulated interval DP.

**Complexity:** `O(n²)` states, `O(n)` transition per state → `O(n³)` time, `O(n²)` space.

---

## 5. The Pattern Recognition Checklist (use this on any new problem)

When you see a new problem, fill this out:

1. **What does the range `[i, j]` represent?** (indices in an array? a substring? a set of matrices?)
2. **What does the partition point `k` mean?** (a cut in the string? the last matrix in the left group? the last balloon burst?)
3. **What is the cost function when I combine the two halves at `k`?** (this is the ONLY problem-specific part)
4. **What is the base case?** (usually `i == j`, `i > j`, or a range of size ≤ 2)
5. **Am I minimizing or maximizing, or counting ways?**

Once you answer these 5 questions, you literally copy the MCM template and swap in your answers.

---

## 6. Now let's apply the template to variations

### 6.1 Palindrome Partitioning II (LeetCode 132)
**Problem:** Minimum cuts to partition a string so every part is a palindrome.

- Range `[i, j]`: substring `s[i..j]`
- Partition point `k`: where cuts happen (this one is slightly different — see below)
- Base case: if `s[i..j]` is already a palindrome → 0 cuts needed
- Cost: 1 (for making a cut) + recurse on the remainder

This is a **linear-partition hybrid**: instead of trying `k` between `i` and `j` and combining both halves' costs, you fix `i` and try every `k` as "the end of the first palindrome piece," then recurse only on the remainder. It's still "trying every partition point," just phrased slightly differently.

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        
        // Precompute palindrome table (classic interval DP itself!)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    isPalin[i][j] = (j - i <= 2) || isPalin[i + 1][j - 1];
                }
            }
        }
        
        int[] dp = new int[n + 1];   // dp[i] = min cuts needed for s[i..n-1]
        dp[n] = -1;                   // base case trick: -1 so first real cut counts as 0
        
        for (int i = n - 1; i >= 0; i--) {
            int minCuts = Integer.MAX_VALUE;
            for (int k = i; k < n; k++) {
                if (isPalin[i][k]) {
                    minCuts = Math.min(minCuts, 1 + dp[k + 1]);
                }
            }
            dp[i] = minCuts;
        }
        
        return dp[0];
    }
}
```

### 6.2 Burst Balloons (LeetCode 312)
**Problem:** Burst all balloons to maximize coins; bursting balloon `i` gives `nums[left]*nums[i]*nums[right]` where left/right are the *currently adjacent* balloons.

**Key insight (the hard part of this problem):** think about which balloon you burst **LAST** within a range, not first. This reframes it perfectly into "partition point k = the last balloon burst in range [i,j]", because after all its neighbors are gone, `i-1` and `j+1` are its guaranteed neighbors.

```java
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1; arr[n + 1] = 1;
        for (int i = 0; i < n; i++) arr[i + 1] = nums[i];
        
        int[][] dp = new int[n + 2][n + 2];
        
        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int maxCoins = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    // k is the LAST balloon burst in range [i, j]
                    int cost = arr[i - 1] * arr[k] * arr[j + 1]
                             + dp[i][k - 1]
                             + dp[k + 1][j];
                    maxCoins = Math.max(maxCoins, cost);
                }
                dp[i][j] = maxCoins;
            }
        }
        
        return dp[1][n];
    }
}
```

Notice: **exact same skeleton as MCM.** Range `[i,j]`, loop `k` from `i` to `j`, combine `dp[i][k-1] + dp[k+1][j] + cost`, take max instead of min. This is why I keep saying — once you know MCM, you know 80% of this pattern.

### 6.3 Boolean Parenthesization (counting variant)
**Problem:** Count ways to parenthesize a boolean expression (with AND/OR/XOR operators) so it evaluates to `True`.

This is the same template but you track **two DP tables**: `dpTrue[i][j]` and `dpFalse[i][j]` — because when you combine two halves, whether the result is True/False depends on both halves' True AND False counts (e.g., for OR: result is False only if BOTH halves are False; True in every other combination).

```java
class Solution {
    static final int MOD = 1_000_000_007;
    
    public int countWays(int n, String exp) {
        // characters at even indices are operands ('T'/'F'), odd indices are operators
        int len = (n + 1) / 2;  // number of operands
        long[][] dpTrue = new long[len][len];
        long[][] dpFalse = new long[len][len];
        
        for (int i = 0; i < len; i++) {
            char c = exp.charAt(2 * i);
            dpTrue[i][i] = (c == 'T') ? 1 : 0;
            dpFalse[i][i] = (c == 'F') ? 1 : 0;
        }
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                long countTrue = 0, countFalse = 0;
                for (int k = i; k < j; k++) {
                    char op = exp.charAt(2 * k + 1);
                    long lt = dpTrue[i][k],  lf = dpFalse[i][k];
                    long rt = dpTrue[k+1][j], rf = dpFalse[k+1][j];
                    long total = ((lt + lf) * (rt + rf)) % MOD;
                    
                    long t = 0;
                    if (op == '&') t = (lt * rt) % MOD;
                    else if (op == '|') t = (total - (lf * rf) % MOD + MOD) % MOD;
                    else t = (lt * rf + lf * rt) % MOD;  // XOR
                    
                    countTrue = (countTrue + t) % MOD;
                    countFalse = (countFalse + total - t + MOD) % MOD;
                }
                dpTrue[i][j] = countTrue;
                dpFalse[i][j] = countFalse;
            }
        }
        
        return (int) dpTrue[0][len - 1];
    }
}
```

Same loop shape. Same "increasing length" tabulation. Only the cost/combine function changed.

---

## 7. Side-by-side: the one thing that changes between problems

| Problem | Range `[i,j]` means | Partition `k` means | Combine cost |
|---|---|---|---|
| MCM | matrices `i..j` | last matrix in left group | `arr[i-1]*arr[k]*arr[j]` |
| Palindrome Partition | substring `s[i..j]` | end of first palindrome piece | `1 + dp[k+1]` |
| Burst Balloons | balloons `i..j` | **last** balloon burst | `arr[i-1]*arr[k]*arr[j+1]` |
| Boolean Parenthesization | subexpression `i..j` | operator position | depends on operator (`&`,`|`,`^`) |
| Egg Drop | floors `1..j`, `eggs = i` | floor where you drop egg | `1 + max(dp[eggs-1][k-1], dp[eggs][j-k])` |

The **shape of the code never changes.** Only this table's last column changes.

---

## 8. Practice roadmap (Striver-style progression)

Do them in this exact order — each builds one new twist on the base template:

1. **Matrix Chain Multiplication** (GFG/InterviewBit) — learn the base template cold, all 3 versions (recursive, memo, tabulation)
2. **Minimum Cost to Cut a Stick** (LeetCode 1547) — near-identical to MCM, just reframed
3. **Palindrome Partitioning II** (LeetCode 132) — learn the "fix i, vary k as endpoint" variant
4. **Burst Balloons** (LeetCode 312) — learn "think about what happens LAST" trick
5. **Boolean Parenthesization** (GFG) — learn the "two DP tables for True/False" trick
6. **Minimum Score Triangulation of Polygon** (LeetCode 1039) — pure MCM clone, confirms you've internalized it
7. **Merge Stones to Minimize Cost** (LeetCode 1000) — adds a constraint on `k` (must merge exactly `K` piles), teaches you to add constraints on top of the base template
8. **Optimal BST** (GFG) — same shape, cost function is expected search cost

By problem 6, you should be able to write the tabulated solution in under 10 minutes without looking anything up. That's when you've mastered the pattern.

---

## 9. Quick debugging checklist when your interval DP is wrong

- Are you iterating `i` from **high to low** and `j` from **low to high** (or by increasing length)? Get this backwards and you'll read uninitialized `dp` values.
- Off-by-one on `k`: does your combine step use `dp[i][k]` + `dp[k+1][j]`, or `dp[i][k-1]` + `dp[k][j]`? Both are valid — just be consistent with what "cutting at k" means for your problem.
- Base case: is it `i == j` (single element, cost 0) or `i > j` (empty range, cost 0)? Get this wrong and small ranges break.
- Integer overflow on multiplication-heavy cost functions (MCM, Burst Balloons) — use `long` if array values are large.

