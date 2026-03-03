package Strings;

class palindromicSubstrings {
    public int countSubstrings(String s) {
        int count=0;

        for(int i=0;i<s.length();i++){
            int evenCnt=expandAroundCenter(s,i,i+1);
            int oddCnt=expandAroundCenter(s,i,i);

            count+=evenCnt+oddCnt;
        }
        return count;
    }

    public int expandAroundCenter(String s,int left,int right){

        int cnt=0;
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
            cnt++;
        }
        return cnt;
    }
}
