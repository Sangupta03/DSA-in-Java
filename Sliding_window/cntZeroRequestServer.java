package Sliding_window;
import java.util.*;
class cntZeroRequestServer {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        
        int llen=logs.length;
        int qLen=queries.length;
        int[] ans=new int[qLen];

        //sort logs by time
        Arrays.sort(logs,(a,b)->Integer.compare(a[1],b[1]));

        int[][] qSorted=new int[qLen][2];
        //stores the queries +their original index;

        for(int i=0;i<qLen;i++){
            qSorted[i][0]=queries[i];
            qSorted[i][1]=i;
        }

        Arrays.sort(qSorted,(a,b)->Integer.compare(a[0],b[0]));
        //sort queries by time also

        int activeServer=0;
        int left=0;
        int right=0;
        //sliding window pointers

        HashMap<Integer,Integer> hp=new HashMap<>();
        for(int[] q :qSorted){
            int l=q[0]-x; //l and r are the range considered now
            int r=q[0];
            int idx=q[1];

            while(right<llen && logs[right][1]<=r){
                if(!hp.containsKey(logs[right][0])){
                    activeServer++;
                }
                hp.put(logs[right][0],hp.getOrDefault(logs[right][0],0)+1);
                right++;
            }

            //decrease window from left
            while(left<llen && logs[left][1]<l){
                if(hp.containsKey(logs[left][0])){
                    hp.put(logs[left][0],hp.get(logs[left][0])-1);
                    if(hp.get(logs[left][0])==0){
                        hp.remove(logs[left][0]);
                        activeServer--;
                    }
                }
                left++;
            }
            ans[idx]=n-activeServer;
        }
        return ans;
    }
}
