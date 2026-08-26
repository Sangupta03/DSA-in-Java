package Sliding_window;
class shortLexicobeautifulString {
    public String shortestBeautifulSubstring(String s, int k) {
        int n=s.length();
        int left=0;
        int cnt=0;
        String ans="";
        int minLen=Integer.MAX_VALUE;
        for(int right=0;right<n;right++){
            if(s.charAt(right)=='1'){
                cnt++;
            }
            while(cnt==k){
                String curr=s.substring(left,right+1);
                if((right-left+1)<minLen || (minLen==(right-left+1) && curr.compareTo(ans)<0)){
                    minLen=right-left+1;
                    ans=curr;
                }
                if(s.charAt(left)=='0'){
                    left++;
                }else{
                    cnt--;
                    left++;
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) return "";
        return ans;
    }
}