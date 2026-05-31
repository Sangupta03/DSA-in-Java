package StacksANDqueues;
import java.util.*;
class celebrityQ {
    public int celebrity(int matrix[][]) {
        // code here
        int n=matrix.length;
        Stack<Integer> stk=new Stack<>();
        for(int i=0;i<n;i++){
            stk.push(i);
        }
        
        while(stk.size()>1){
            int a=stk.pop();
            int b=stk.pop();
            
            if(matrix[a][b]==1){
                stk.push(b);
            }else{
                stk.push(a);
            }
        }
        //check
        int top=stk.pop();
        
        for(int i=0;i<n;i++){
            if(i==top) continue;
            
            if(matrix[i][top]==0 || matrix[top][i]==1){
                return -1;
            }
        }
        return top;
    }
}