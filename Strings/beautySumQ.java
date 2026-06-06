class beautySumQ {
    public int beautySum(String s) {
        int total=0;
        int n=s.length();

        for(int i=0;i<n;i++){
            int[] freq=new int[26];
            
            for(int j=i;j<n;j++){
                int min=Integer.MAX_VALUE;
                int max=0;
                freq[s.charAt(j)-'a']++;
                for(int f:freq){
                    if(f>0){
                       max=Math.max(f,max);
                       min=Math.min(f,min); 
                    }
                }
                total+=(max-min);
            }
        }
        return total;
    }
}