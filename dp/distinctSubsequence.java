class distinctSubsequence {
    public int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();

        int[] prev=new int[m+1];

        for(int i=0;i<=n;i++) prev[0]=1;
        for(int j=1;j<=m;j++) prev[j]=0;

        for(int idx1=1;idx1<=n;idx1++){
            int[] temp=new int[m+1];
            temp[0]=1;
            for(int idx2=1;idx2<=m;idx2++){
                if(s.charAt(idx1-1)==t.charAt(idx2-1)){
                    temp[idx2]=prev[idx2-1]+prev[idx2];
                }else{
                    temp[idx2]=prev[idx2];
                }
            }
            prev=temp;
        }
        return prev[m]; 
    }
}