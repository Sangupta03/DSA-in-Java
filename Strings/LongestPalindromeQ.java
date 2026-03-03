package Strings;

class  LongestPalindromeQ {
    public String longestPalindrome(String s) {
        if(s==null || s.isEmpty()) return "";
        int maxLen=1;
        int start=0;

        for(int i=0;i<s.length();i++){
            int oddLen=expandAroundCenter(s,i,i);
            int evenLen=expandAroundCenter(s,i,i+1);

            int currLen=Math.max(oddLen,evenLen);
            if(currLen>maxLen){
                maxLen=currLen;
                start=i-(currLen-1)/2;
            }
        }
        return s.substring(start,start+maxLen);  
    }

    public int expandAroundCenter(String s,int left,int right){
    
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1; //gives length of palindromee
    }
}
