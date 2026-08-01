package StacksANDqueues;
import java.util.*;

class createMaxNum {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxN=new int[k];
        
        for(int i=Math.max(0,k-nums2.length);i<=Math.min(k,nums1.length);i++){
            int[] ans1=findMax(nums1,i);    //find max subsequence formed from nums1
            int[] ans2=findMax(nums2,k-i);  //find max subsequence formed from nums2
            int[] res=merge(ans1,ans2);     //merge both sequence of digits
          
            if(greater(res,0,maxN,0)){
                maxN=res;   //store larger digit,
            }
        }
        return maxN;
    }

    public int[] findMax(int[] nums,int k){
        if(k==0) return new int[0];    //refer to Remove k digits leetcode
        //here we drop digits to find max number
        int drop=nums.length-k;     
        Deque<Integer> stk=new ArrayDeque<>();

        for(int i=0;i<nums.length;i++){
            while(!stk.isEmpty() && stk.peek()<nums[i] && drop>0){
                stk.pop();
                drop--;
            }
            stk.push(nums[i]);
        }

        while(!stk.isEmpty() && drop>0){
            stk.pop();
            drop--;
        }

        int n=stk.size();
        int[] arr=new int[n];
        for(int i=n-1;i>=0;i--){
            arr[i]=stk.pop();
        }
        return arr;

    }
    public int[] merge(int[] nums1,int[] nums2){
        int n=nums1.length;
        int m=nums2.length;
        int[] ans=new int[n+m];

        int i=0;
        int j=0;
        int idx=0;
        while(i<n || j<m){
            if(greater(nums1,i,nums2,j)){  //merge with suffix comparison
                ans[idx]=nums1[i];
                i++;
            }else{
                ans[idx]=nums2[j];
                j++;
            }
            idx++;
        }

        return ans;
    }
    public boolean greater(int[] a,int i,int[] b,int j){  

        int n=a.length;
        int m=b.length;

        while(i<n && j<m && a[i]==b[j]){  //suffix comparison, easily tells which digit formed is larger
            i++;
            j++;
        }

        if(i==n) return false;
        if(j==m) return true;

        return a[i]>b[j];
    }
}
