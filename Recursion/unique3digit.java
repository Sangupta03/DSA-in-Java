package Recursion;
import java.util.*;

class unique3digit {
    HashSet<Integer> hs=new HashSet<>();
    public int totalNumbers(int[] digits) {
        boolean[] visited=new boolean[digits.length];
        solve(digits,0,0,visited);
        return hs.size();
    }
    public void solve(int[] digits,int currNum,int digitNo,boolean[] visited){
        if(digitNo==3){
            if(currNum%2==0){
                hs.add(currNum);
            }
            return;
        }
        for(int i=0;i<digits.length;i++){
            if(!visited[i]){
                if(digits[i]==0 && digitNo==0){
                    continue;
                }
                visited[i]=true;
                solve(digits,currNum*10+digits[i],digitNo+1,visited);
                visited[i]=false;
            }
        }
    }
}