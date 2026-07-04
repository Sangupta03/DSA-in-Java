package Sliding_window;
import java.util.*;
class subWithKdiff {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atmostK(nums,k)-atmostK(nums,k-1);
    }

    public int atmostK(int[] nums,int k){
        int cnt=0;
        int left=0;
        int n=nums.length;
        HashMap<Integer,Integer> hp=new HashMap<>();

        for(int right=0;right<n;right++){
            hp.put(nums[right],hp.getOrDefault(nums[right],0)+1);

            while(hp.size()>k){
                if(hp.containsKey(nums[left])){
                    hp.put(nums[left],hp.get(nums[left])-1);
                    if(hp.get(nums[left])==0) hp.remove(nums[left]);
                }
                left++;
            }
            cnt+=(right-left+1);
        }
        return cnt;
    }
}