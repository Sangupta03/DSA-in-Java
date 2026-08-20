import java.util.*;
class distributeEleIntoArr {
    public int[] resultArray(int[] nums) {
        int n=nums.length;
        ArrayList<Integer> n1=new ArrayList<>();
        ArrayList<Integer> n2=new ArrayList<>();

        n1.add(nums[0]);
        n2.add(nums[1]);
        int l1=nums[0];
        int l2=nums[1];
        for(int i=2;i<n;i++){
            if(l1>l2){
                n1.add(nums[i]);
                l1=nums[i];
            }else{
                n2.add(nums[i]);
                l2=nums[i];
            }
        }
        int idx=0;
        int[] ans=new int[n];
        for(int x:n1){
            ans[idx++]=x;
        }
        for(int y:n2){
            ans[idx++]=y;
        }
        return ans;
    }
}