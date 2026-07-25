package Trees;
import java.util.*;
class CBTtraversal {
    public ArrayList<ArrayList<Integer>> levelSort(int[] arr) {
        
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
       
        int levelSize=1;
        int idx=0;
        
        while(idx<arr.length){
            
            ArrayList<Integer> temp=new ArrayList<>();
            int cnt=0;
            while(cnt<levelSize && idx<arr.length){
                temp.add(arr[idx++]);
                cnt++;
            }
            Collections.sort(temp);
            ans.add(temp);
            
            levelSize*=2;
            
        }
        return ans;
        
    }
}