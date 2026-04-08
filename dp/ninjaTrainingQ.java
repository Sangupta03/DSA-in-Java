class ninjaTrainingQ {
    public int ninjaTraining(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][4];

        // Base case: Day 0
        dp[0][0] = Math.max(matrix[0][1], matrix[0][2]);
        dp[0][1] = Math.max(matrix[0][0], matrix[0][2]);
        dp[0][2] = Math.max(matrix[0][0], matrix[0][1]);
        dp[0][3] = Math.max(matrix[0][0], Math.max(matrix[0][1], matrix[0][2]));
        
        // Fill the DP table
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) { // 'last' is the task forbidden TODAY
                dp[day][last] = 0;
                
                for (int task = 0; task <= 2; task++) { // 'task' is what we actually do TODAY
                    if (task != last) {
                        // Current day's points + Max points from prev day where 'task' was forbidden
                        int points = matrix[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], points);
                    }
                }
            }
        }
        
        return dp[n - 1][3]; // Return max points where no task is forbidden on the last day
    }
}