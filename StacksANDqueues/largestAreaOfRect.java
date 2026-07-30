package StacksANDqueues;
import java.util.*;
class largestAreaOfRect {
    public int largestRectangleArea(int[] heights) {
        int maxArea=0;
        int n=heights.length;
        Stack<Integer> stk=new Stack<>();
        int nse=n;
        int pse=-1;

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && heights[stk.peek()]>heights[i]){
                int ele=stk.pop();
                nse=i;
                pse=stk.isEmpty()?-1:stk.peek();
                int area=heights[ele]*(nse-pse-1);
                maxArea=Math.max(maxArea,area);
            }
            stk.push(i);
        }

        while(!stk.isEmpty()){
            int ele=stk.pop();
            nse=n;
            pse=stk.isEmpty()?-1:stk.peek();
            int area=heights[ele]*(nse-pse-1);
            maxArea=Math.max(maxArea,area);
        }
        return maxArea;
    } 
}