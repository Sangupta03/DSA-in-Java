class minChangesBeautifulString {
    public int minChanges(String s) {
        int oneCnt=0;

        int zeroCnt=0;
        int cnt=0;

        for(int i=0;i<s.length();i++){
            int d=s.charAt(i)-'0';

            if(d==0) zeroCnt++;
            else oneCnt++;

            if(i%2==1){
                if(zeroCnt%2==1 || oneCnt%2==1){
                    cnt++;
                    zeroCnt=0;
                    oneCnt=0;
                }
            }
        }
        return cnt;
    }
}

//approach 2 just check adjacent pairwise characters no need to cnt
class Solution {
    public int minChanges(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                cnt++;
            }
        }
        return cnt;
    }
}