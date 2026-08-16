//Reduce every stone to value % 3 and count how many are 0, 1, 2; 0 only affects turn parity, while 1 and 2 are opposing moves. If count0 is even, Alice needs both 1 and 2; if count0 is odd, Alice wins only when |count1 - count2| > 2

class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt=new int[3];

        //we are just concerned about remainders;
        //2+1=0  or 1+2=0 so div by 3;
        //2+0 or 1+0 is good not div by 3;
        //if 0 not available then need atleast 2 or 1 for each, 2+2 or 1+1 not div by 3
        for(int x:stones){
            cnt[x%3]++;
        }

        if(cnt[0]%2==0){
            return cnt[1]>0 && cnt[2]>0;  //winning condition for alice when cnt0 is even
        }

        return Math.abs(cnt[2]-cnt[1])>2; //winning condition for alice when cnt0 is odd;
        //in all other cases bob wins
    }
}