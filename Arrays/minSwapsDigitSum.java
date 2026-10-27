import java.util.*;
class minSwapsDigitSum {
    public int minSwaps(int[] nums) {
        int n=nums.length;

        Integer[] sorted=new Integer[n];

        for(int i=0;i<n;i++){
            sorted[i]=nums[i];
        }
        Arrays.sort(sorted,new compareDigitSum());

        HashMap<Integer,Integer> valToIdx=new HashMap<>();

        for(int i=0;i<n;i++){
            valToIdx.put(nums[i],i);
        }

        int cnt=0;

        for(int i=0;i<n;i++){
            int expectedVal=sorted[i];
            int currVal=nums[i];

            if(expectedVal==currVal) continue;

            int idx=valToIdx.get(expectedVal);

            nums[i]=expectedVal;
            nums[idx]=currVal;

            valToIdx.put(expectedVal,i);
            valToIdx.put(currVal,idx);
            cnt++;
        }
        return cnt;
    }

    static class compareDigitSum implements Comparator<Integer>{
        @Override
        public int compare(Integer a,Integer b){
            int digitSum1=findSum(a);
            int digitSum2=findSum(b);

            if(digitSum1<digitSum2){
                return -1;
            }else if(digitSum1>digitSum2){
                return 1;
            }else{
                return Integer.compare(a,b);
            }
        }
    }
    public static int findSum(int num){
        int sum=0;

        while(num>0){
            int d=num%10;
            sum+=d;
            num=num/10;
        }
        return sum;
    }
}
