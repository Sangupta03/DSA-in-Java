import java.util.*;
class smallestRangeList {
    public int[] smallestRange(List<List<Integer>> nums) {
        
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);

        int max=Integer.MIN_VALUE;

        for(int i=0;i<nums.size();i++){
            int val=nums.get(i).get(0);
            pq.offer(new int[]{val,i,0});
            max=Math.max(max,val);
        }

        int start=0;
        int end=Integer.MAX_VALUE;

        while(true){
            int[] curr=pq.poll();
            int val=curr[0];
            int row=curr[1];
            int col=curr[2];

            if(max-val<end-start){
                start=val;
                end=max;
            }

            if(col+1==nums.get(row).size()){
                break;
            }

            int next=nums.get(row).get(col+1);

            pq.offer(new int[]{next,row,col+1});
            max=Math.max(next,max);
        }

        return new int[]{start, end};
    }
}
