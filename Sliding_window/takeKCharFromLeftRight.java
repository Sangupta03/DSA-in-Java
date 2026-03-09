package Sliding_window;

class takeKCharFromLeftRight {
    public int takeCharacters(String s, int k) {
        int[] total=new int[3];

        for(char c:s.toCharArray()){
            total[c-'a']++;
        }

        if(total[0]<k || total[1]<k || total[2]<k) return -1;

        int n=s.length();
        int[] freq=new int[3];

        int left=0;
        int maxLen=0;
        for(int right=0;right<n;right++){
            freq[s.charAt(right)-'a']++;

            while(freq[0]>total[0]-k || freq[1]>total[1]-k || freq[2]>total[2]-k){
                freq[s.charAt(left)-'a']--;
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
        }
        return n-maxLen;
    }
}