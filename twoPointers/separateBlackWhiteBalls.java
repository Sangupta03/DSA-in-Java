package twoPointers;

class seperateBlackWhiteBalls {
    public long minimumSteps(String s) {
        char[] arr=s.toCharArray();
        int n=s.length();

        long steps=0;
        int cnt=0;

        for(int i=0;i<n;i++){
            if(arr[i]=='1') cnt++;
            else{
                steps+=cnt;
            }
        }
        return steps;
    }
}
