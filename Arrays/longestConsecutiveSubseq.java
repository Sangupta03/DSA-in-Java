import java.util.*;
class longestConsecutiveSubseq {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        int n=nums.length;

        HashSet<Integer> ht=new HashSet<>();
        for(int i=0;i<n;i++){
            ht.add(nums[i]);
        }
        int longest=1;
        for(int x:ht){
            if(!ht.contains(x-1)){
                int ele=x;
                int cnt=1;
                while(ht.contains(ele+1)){
                    ele++;
                    cnt++;
                }
                longest=Math.max(longest,cnt);
            }
        }
        return longest;
    }
}
