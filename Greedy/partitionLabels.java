import java.util.*;
class partitionLabels {
    public List<Integer> partitionLabelsQ(String s) {
        
        int[] last=new int[26];
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            last[ch-'a']=i;
        }
        List<Integer> ans=new ArrayList<>();
        int end=0;
        int start=0;
        
        for(int i=0;i<s.length();i++){
            end=Math.max(end,last[s.charAt(i)-'a']);
            if(i==end){
                int len=(end-start+1);
                ans.add(len);
                start=i+1;
            }
        }
        return ans;
    }
}