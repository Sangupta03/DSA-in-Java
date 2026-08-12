package Sliding_window;

class longestSubstringWithAtmostKChars {
    public int longestSubstring(String s, int k) {

        int maxLen=0;
        //Since the string contains lowercase English letters, there can be at most 26 distinct characters.
        //For each target, maintain a sliding window with exactly that many distinct characters.
        
        for(int target=1;target<=26;target++){
            int[] freq=new int[26];
            int good=0; //no.of chars with freq>=k
            int unique=0; //no. of distinct chars in window

            int left=0;
            for(int right=0;right<s.length();right++){
                char ch=s.charAt(right);
                freq[ch-'a']++;

                if(freq[ch-'a']==k){
                    good++;
                }
                if(freq[ch-'a']==1){
                    unique++;
                }
                while(unique>target){
                    char l=s.charAt(left);
                    freq[l-'a']--;
                    if(freq[l-'a']==k-1){  //be careful 
                        good--;
                    }
                    if(freq[l-'a']==0){
                        unique--;
                    }
                    left++;
                }
                if(unique==target && good==target){
                    maxLen=Math.max(maxLen,right-left+1);
                }
            }
            
        }

        return maxLen;
    }
}