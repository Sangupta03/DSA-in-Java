package Recursion;
import java.util.*;

class restoreIP {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans=new ArrayList<>();
        if(s.length()<4 || s.length()>12) return ans;

        solve(s,ans,0,"",0);
        return ans;
    }

    public void solve(String s,List<String> ans, int start,String path,int parts){
        if(parts==4){
            if(start==s.length()){
                ans.add(new String(path.substring(0,path.length()-1))); 
                //remove the last '.' at end
            }
            return;
        }

        //len of part could be 1, 2 or 3 so end<start+3
        for(int end=start;end<s.length() && end<start+3;end++){
            String part=s.substring(start,end+1);

            if(part.charAt(0)=='0' && part.length()>1) continue;
            //leading zero check

            if(Integer.parseInt(part)>255) continue;
            //digit value check

            solve(s,ans,end+1,path+part+".",parts+1); //next partition at end+1
        }
    }
}