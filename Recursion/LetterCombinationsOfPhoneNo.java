package Recursion;
import java.util.*;
class LetterCombinationsOfPhoneNo {
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return new ArrayList<>();
        HashMap<Character,String> hp=new HashMap<>();
        
        hp.put('2',"abc");
        hp.put('3',"def");
        hp.put('4',"ghi");
        hp.put('5',"jkl");
        hp.put('6',"mno");
        hp.put('7',"pqrs");
        hp.put('8',"tuv");
        hp.put('9',"wxyz");

        StringBuilder temp=new StringBuilder();
        List<String> ans=new ArrayList<>();
        solve(0,temp,ans,digits,hp);
        return ans;
    }

    public void solve(int idx,StringBuilder sb,List<String> ans,String digits,HashMap<Character,String> hp){

        if(idx>=digits.length()){
            ans.add(sb.toString());
            return;
        }

        char c=digits.charAt(idx);
        String ch=hp.get(c);

        for(int i=0;i<ch.length();i++){
            sb.append(ch.charAt(i));
            solve(idx+1,sb,ans,digits,hp);//be careful take idx, that is next digit
            sb.deleteCharAt(sb.length()-1);
        }
    }
}