
package Strings;
class findLongPalindromeLen {
    public int longestPalindrome(String s) {
        int[] freq=new int[128];

        for(char c:s.toCharArray()){
            freq[c]++;
        }


        int length=0;
        for(int c:freq){
            length+=(c/2)*2;
            if(length%2==0 && c%2==1){
                length++;
            }
        }
        return length;
    }
}