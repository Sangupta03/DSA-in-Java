import java.util.*;

class mergeInterval {
    public int[][] merge(int[][] intervals) {
        
        ArrayList<int[]> ans=new ArrayList<>();
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);

        for(int[] interval:intervals){

            if(ans.isEmpty() || ans.get(ans.size()-1)[1]<interval[0]){
                ans.add(interval);
            }else{
                int[] last=ans.get(ans.size()-1);
                last[1]=Math.max(interval[1],last[1]);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
