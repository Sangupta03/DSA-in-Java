class decodeWays {
    public int numDecodings(String s) {
        int n=s.length();

        int[] dp=new int[n];
        if (s.charAt(0) == '0') return 0; 
        dp[0]=1;
        for(int idx=1;idx<n;idx++){
            int ways=0;
            if(s.charAt(idx)!='0'){
                ways+=dp[idx-1];
            }
            
            int number=(s.charAt(idx-1)-'0')*10+s.charAt(idx)-'0';
            if(number>=10 && number<=26){
                if(idx==1){
                    ways+=1;  //just means entire string is processed, i,e, f(-1)
                }else{
                    ways+=dp[idx-2];
                }
            }
            dp[idx]=ways;
        }
        return dp[n-1];
    }
}