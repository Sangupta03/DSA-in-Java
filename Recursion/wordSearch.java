package Recursion;

class wordSearch {

    public boolean exist(char[][] board, String word) {

        int n = board.length;
        int m = board[0].length;

        boolean[][] vis = new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                if(board[i][j]==word.charAt(0) &&
                   solve(0,i,j,board,vis,word))
                    return true;
            }
        }

        return false;
    }

    public boolean solve(int idx,int i,int j,
                         char[][] board,
                         boolean[][] vis,
                         String word){

        // matched entire word
        if(idx==word.length())
            return true;

        if(i<0 || j<0 || i>=board.length || j>=board[0].length
           || vis[i][j]
           || board[i][j]!=word.charAt(idx))
            return false;

        vis[i][j]=true;

        boolean found =
                solve(idx+1,i+1,j,board,vis,word) ||
                solve(idx+1,i-1,j,board,vis,word) ||
                solve(idx+1,i,j+1,board,vis,word) ||
                solve(idx+1,i,j-1,board,vis,word);

        vis[i][j]=false;

        return found;
    }
}