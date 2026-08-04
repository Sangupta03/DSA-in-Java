import java.util.*;
class stoneGame4 {
    public boolean winnerSquareGame(int n) {
        // dp[k] = 1 if the player whose turn it is (with k stones left) WINS, 0 if they LOSE
        // -1 means "not computed yet"
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp) == 1 ? true : false;
    }

    public int solve(int n, int[] dp) {
        // Base case: no stones left on your turn -> you have no legal move -> you lose
        if (n == 0) return 0; // 0 = false (lose), 1 = true (win)

        if (dp[n] != -1) return dp[n];

        // Try every possible move: remove s*s stones
        for (int s = 1; s * s <= n; s++) {
            int val = s * s;

            // After removing 'val' stones, it's the opponent's turn with (n - val) stones
            // 'game' tells us whether the OPPONENT wins from that resulting state
            int game = solve(n - val, dp);

            // If the opponent LOSES from (n - val), that means WE win by making this move
            // We only need ONE such winning move to guarantee a win -> stop immediately
            if (game == 0) {
                return dp[n] = 1; // return: current player wins
            }
        }

        // Tried every possible square move, and none of them left the opponent in a losing state
        // -> no matter what we do, opponent wins -> we lose
        return dp[n] = 0;
    }
}
// tabulation

class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp=new boolean[n+1];
        dp[0] = false;
        for(int i=1;i<=n;i++){
            dp[i]=false;
            for(int s=1;s*s<=i;s++){
                int val=s*s;
                boolean game=dp[i-val];
                if(game==false){
                    dp[i]=true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
  }