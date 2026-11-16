
//brute force
class subArrWithOddSum {
    public int numOfSubarrays(int[] arr) {
  
        int MOD=100_000_007;
        int cnt=0;

        for(int i=0;i<arr.length;i++){
            cnt+=solve(arr,i,0);
        }
        return cnt%MOD;
    }

    public int solve(int[] arr,int idx,int sum){

        if(idx==arr.length){
            return 0;
        }

        sum+=arr[idx];

        int cnt=0;

        if(sum%2==1){
            cnt++;
        }

        cnt+=solve(arr,idx+1,sum);
        //branching starts from idx;

       return cnt;      
    }
}

//Optimal approach

class Solution {
    //currSum=prefixSum[i]-prefixSum[i-1]

    //odd-even=odd;
    //even-odd=odd;

    public int numOfSubarrays(int[] arr) {
        int MOD=1_000_000_007;

        long ans=0;
        int oddCnt=0;
        int evenCnt=1; //cnt of even prefixSum
        long sum=0;

        for(int i=0;i<arr.length;i++){
            sum+=arr[i];

            if(sum%2==1){
                //need even to get odd sum
                // Current prefix is odd.
                // Need previous even prefix.
                ans+=evenCnt;
                oddCnt++; //odd prefixSum 
            }else{

                // Current prefix is even.
                // Need previous odd prefix.
                //need odd to get odd sum since sum is even
                //odd-even=odd
                ans+=oddCnt;
                evenCnt++;// even prefixSum
            }
        }
        return (int)(ans%MOD);
    }
}