package BinarySearch;
class medianInRowWiseSortMatrix {
    public int median(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;

        int low=mat[0][0];
        int high=mat[0][m-1];
        
        for(int i=0;i<n;i++){
            low=Math.min(mat[i][0],low);
            high=Math.max(mat[i][m-1],high);
        }
        
        int needed=(n*m)/2;
        
        while(low<=high){
            int mid=low+(high-low)/2;
            
            int cnt=0;
            for(int i=0;i<n;i++){
                cnt+=upperBound(mat[i],mid);
            }
            //shrink high when count meets or exceeds the required median position
            if(cnt<=needed){
                low=mid+1;
            }else{
                high=mid-1;
            }
            
        }
        return low;
        
    }
    
    public int upperBound(int[] matrix,int target){
        int low=0;
        int high=matrix.length-1;
        
        
        while(low<=high){
            int mid=low+(high-low)/2;
            
            if(matrix[mid]<=target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return low;  //index here also returns the cnt of numbers less than or equal to target
        //0 based indexing
    }
}