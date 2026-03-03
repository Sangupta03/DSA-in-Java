package twoPointers;

class isSubsequenceQ {
    public boolean isSubsequence(String s, String t) {

        int slow=0;
        int fast=0;

        while(slow<s.length() && fast<t.length()){
            if(s.charAt(slow)==t.charAt(fast)){
                slow++;
            }
            fast++;
        }
        return (slow==s.length());
    }
}
