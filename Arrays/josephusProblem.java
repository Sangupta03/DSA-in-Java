import java.util.*;

//brute force is to simulate a queue
class josephusProblem {
    public int findTheWinner(int n, int k) {
        ArrayList<Integer> ans=new ArrayList<>();

        for(int i=1;i<=n;i++){
            ans.add(i);
        }

        int idx=0;
        while(ans.size()>1){
            //for 0 based indexing hence k-1 for idx to remove
            idx=(idx+k-1)%ans.size();
            ans.remove(idx);
        }

        return ans.get(0);
        
    }
}

//Optimal approach

class Solution {
    public int findTheWinner(int n, int k) {
        
        int ans=0;
        int i=1;
        while(i<=n){
            ans=(ans+k)%i;  //modulus i to wrap around circle
            i++;  //start next counting or next round
        }

        return ans+1; //player left
    }
}