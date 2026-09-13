class imageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n=img1.length;
        int maxCnt=0;
        for(int i=-n+1;i<n;i++){  //rowOffset
            for(int j=-n+1;j<n;j++){  //colOffset
                int overlap=findOverlap(i,j,img1,img2);
                maxCnt=Math.max(maxCnt,overlap);
            }
        }
        return maxCnt;
    }

    public int findOverlap(int rowO,int colO,int[][] img1,int[][] img2){
        int n=img1.length;
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int nrow=i+rowO;
                int ncol=j+colO;
                if(nrow>=n || ncol>=n || nrow<0 || ncol<0) continue; //edge case
                if(img1[nrow][ncol]==1 && img2[i][j]==1){ //overlap found
                    cnt++;
                }
            }
        }
        return cnt;
    }
}