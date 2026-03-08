package Sliding_window;

class longestRepeatChar {
    public int characterReplacement(String s, int k) {
        int[] freq=new int[26];
        int maxLen=0;
        int maxFreq=0;
        int left=0;

        for(int right=0;right<s.length();right++){
            freq[s.charAt(right)-'A']++;
            maxFreq=Math.max(maxFreq,freq[s.charAt(right)-'A']);

            int window=right-left+1;
            if(window-maxFreq>k){
                freq[s.charAt(left)-'A']--;
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
}