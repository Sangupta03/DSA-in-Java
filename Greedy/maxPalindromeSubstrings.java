package Greedy;

class maxPalindromeSubstrings {
    public int maxPalindromes(String s, int k) {
        int cnt=0;
        int idx=0;

        //to get the max no. of valid palindromes, we need to find the shortest length valid palindrome always
        while(idx+k<=s.length()){
            if(isPalindrome(s,idx,idx+k-1)){ //odd len palindrome
                cnt++;
                idx=idx+k; //update idx to start a new substring
            }else if(idx+k<s.length() && isPalindrome(s,idx,idx+k)){ //even len palindrome
                cnt++;
                idx=idx+k+1;
            }else{
                idx++;
            }
        }
        return cnt;
        
    }
    public boolean isPalindrome(String s,int low,int high){
        while(low<high){
            if(s.charAt(low)!=s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}