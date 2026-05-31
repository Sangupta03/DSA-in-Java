package StacksANDqueues;

class maximalRectangleQ {
    public int maximalRectangle(char[][] matrix) {
        
        if(matrix.length==0 || matrix==null || matrix[0].length==0) return 0;

        int n=matrix.length;
        int m=matrix[0].length; //cols
        int[] height=new int[m];
        int maxArea=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='1'){
                    height[j]++;
                }else{
                    height[j]=0;
                }
            }
            maxArea=Math.max(maxArea,findLargest(height));
        }
        return maxArea;
    }

    public int findLargest(int[] height){

        if(height.length==0 || height==null) return 0;
        int n=height.length;
        int maxi=0;
        Stack<Integer> stk=new Stack<>();

        int nse=n;
        int pse=-1;
        for(int i=0;i<n;i++){
            
            while(!stk.isEmpty() && height[stk.peek()]>height[i]){
                int ele=stk.pop();
                nse=i;
                pse=stk.isEmpty()?-1:stk.peek();
                maxi=Math.max(maxi,(nse-pse-1)*height[ele]);
            }
            stk.push(i);
        }

        while(!stk.isEmpty()){
            int ele=stk.pop();
            nse=n;
            pse=stk.isEmpty()?-1:stk.peek();
            maxi=Math.max(maxi,(nse-pse-1)*height[ele]);
        }
        return maxi;
        
    }
}
