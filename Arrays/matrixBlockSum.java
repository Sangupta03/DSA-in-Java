class matrixBlockSum {
    public int[][] matrixBlockSumQ(int[][] mat, int k) {
        int n=mat.length;
        int m=mat[0].length;

        int[][] prefix=new int[n+1][m+1]; //extra 0 row and 0 col, easier this way, find 2d prefix
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                prefix[i+1][j+1]=mat[i][j]+prefix[i+1][j]+prefix[i][j+1]-prefix[i][j];
                //prefix=leftrow+toprow-topleft overlap
            }
        }

        int[][] ans=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int top=Math.max(0,i-k);
                int left=Math.max(0,j-k);
                int bottom=Math.min(n-1,i+k);
                int right=Math.min(m-1,j+k);

                ans[i][j]=prefix[bottom+1][right+1]-prefix[bottom+1][left]-prefix[top][right+1]+prefix[top][left];
                //sum=big rectangle-top-left+overlapof topleft
            }
        }
        return ans;
    }
}