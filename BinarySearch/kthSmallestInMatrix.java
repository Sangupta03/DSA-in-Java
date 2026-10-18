package BinarySearch;

class kthSmallestInMatrix {
    public int kthSmallest(int[][] mat, int k) {
        int n=mat.length;
        int low=mat[0][0];
        
        int high=mat[n-1][n-1];
        
        while(low<=high){
            int mid=low+(high-low)/2;
            
            if(findEle(mat,k,mid)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return low;
        
    }
    
    public boolean findEle(int[][] mat,int k,int target){
        int cnt=0;
        int n=mat.length;
        
        int row=n-1;
        int col=0;
        
        while(col<n && row>=0){
            if(mat[row][col]<=target){
                cnt+=(row+1);  //all elements above this in the column are also<=target
                col++;  //check for larger no. < target
            }else{
                row--;
            }
        }
        return cnt<k;
    }
}
