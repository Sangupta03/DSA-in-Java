package Recursion;
import java.util.*;
class removeInvalidParentheses {
    public List<String> removeInvalidParenthesesQ(String s) {
        HashSet<String> ans=new HashSet<>();
        int[] maxLen={-1};
        //USE MAXLEN AS GLOBAL VARIABLE
        solve(0,ans,s,0,maxLen,new StringBuilder());
        return new ArrayList<>(ans);
    }

    public void solve(int i,HashSet<String> hs, String s,int cnt,int[] maxLen, StringBuilder sb){

        if(cnt<0) return;    //NOTE IF CNT THAT IS BALANCE OF PARENTHESIS IS NEGATIVE AT ANY POINT RETURN AS THAT STRING WILL BE NOT BALANCED
        int len=sb.length();

        if(i==s.length()){
            if(cnt==0){
                if(maxLen[0]<sb.length()){
                    hs.clear();    //remov older less length value
                    maxLen[0]=sb.length();
                    hs.add(sb.toString());  //add maxlen string
                }

                if(maxLen[0]==sb.length()){
                    hs.add(sb.toString());   //more string of same maxLen added
                }
            }
            return;
        }

        if(s.charAt(i)!='(' && s.charAt(i)!=')'){  //anyways add normal alphabets
            sb.append(s.charAt(i));  //take always
            solve(i+1,hs,s,cnt,maxLen,sb);
            sb.setLength(len);  //backtrack
        }
        else{
            //TAKE 
            sb.append(s.charAt(i));
            solve(i+1,hs,s,cnt+(s.charAt(i)=='(' ? 1:-1),maxLen,sb);  //take
            //backtrack
            sb.setLength(len);

            //NOT TAKE
            solve(i+1,hs,s,cnt,maxLen,sb);
        }
    }
}
