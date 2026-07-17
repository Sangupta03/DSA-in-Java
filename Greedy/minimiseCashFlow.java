package Greedy;
import java.util.*;

class minimiseCashFlow {
    public int[][] minCashFlow(int[][] transaction) {
        
        int n=transaction.length;
        int[] balance=new int[n];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                balance[i]+=transaction[j][i];
                balance[i]-=transaction[i][j];
            }
        }
        // code here
        PriorityQueue<int[]> cred=new PriorityQueue<>((a,b)->b[0]-a[0]);
        PriorityQueue<int[]> dept=new PriorityQueue<>((a,b)->b[0]-a[0]);
        
        for(int i=0;i<n;i++){
            if(balance[i]>0){
                cred.offer(new int[]{balance[i],i});
            }else if(balance[i]<0){
                dept.offer(new int[]{-balance[i],i});
            }
        }
        
        int[][] ans = new int[n][n];
        
        while(!cred.isEmpty() && !dept.isEmpty()){
            int[] d=dept.poll();
            int[] c=cred.poll();
            
            int amount=Math.min(d[0],c[0]);
            
            d[0]-=amount;
            c[0]-=amount;
            
            ans[d[1]][c[1]]=amount;
            
            if(d[0]>0) dept.offer(d);
            if(c[0]>0) cred.offer(c);
        }
        return ans;
    }
}
