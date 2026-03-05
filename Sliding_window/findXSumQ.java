package Sliding_window;
import java.util.*;
class findXSumQ {
    public int[] findXSum(int[] nums, int k, int x) {

        HashMap<Integer,Integer> hp=new HashMap<>();
        int n=nums.length;
        int[] res=new int[n-k+1];
        for(int i=0;i<k;i++){
            hp.put(nums[i],hp.getOrDefault(nums[i],0)+1);
        }

        res[0]=calculateXSum(hp,x);
        for(int i=k;i<n;i++){
            int out=nums[i-k];
            if(hp.get(out)==1){
                hp.remove(out);
            }else{
                hp.put(out,hp.get(out)-1);
            }

            int in=nums[i];
            hp.put(in,hp.getOrDefault(in,0)+1);
            res[i-k+1]=calculateXSum(hp,x);
        }
        return res;
    }

    public int calculateXSum(HashMap<Integer,Integer> map,int x){

        List<int[]> ls=new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            ls.add(new int[]{entry.getValue(),entry.getKey()}); //freq,number;
        }

        Collections.sort(ls,(a,b)->{
            if(a[0]!=b[0]) return b[0]-a[0];
            return b[1]-a[1];
        });
        int cnt=0;
        int sum=0;
        for(int[] p:ls){
            if(cnt>=x) break;
            sum+=p[0]*p[1];
            cnt++;
        }
        return sum;
    }
}
