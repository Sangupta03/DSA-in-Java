class NinjaFriends3D {
    public int maxChocolates(int[][] g) {
        int m=g.length;
        int n=g[0].length; //col

        //3D dp;
        int[][][] dp=new int[m][n][n];

        for(int j1=0;j1<n;j1++){
            for(int j2=0;j2<n;j2++){
                dp[m-1][j1][j2]=(j1==j2)?g[m-1][j1]:g[m-1][j1]+g[m-1][j2];
            }
        }
        for(int i=m-2;i>=0;i--){
            for(int j1=0;j1<n;j1++){
                for(int j2=0;j2<n;j2++){
                    int maxi=(int) -1e9;
                    int value=(j1==j2) ?g[i][j1]:g[i][j1]+g[i][j2];
                    for(int d1=-1;d1<=1;d1++){
                        for(int d2=-1;d2<=1;d2++){
                            int ans=value;
                            if(j1+d1>=0 && j1+d1<n && j2+d2>=0 && j2+d2<n){
                                ans+=dp[i+1][j1+d1][j2+d2];
                            }else{
                                ans+=(int) -1e9;
                            }
                            maxi=Math.max(ans,maxi);
                        }
                    }
                    dp[i][j1][j2]=maxi;
                }
            }
        }
        return dp[0][0][n-1];
    }
}