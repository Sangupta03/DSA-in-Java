class searchInMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int n=matrix.length;
        int m=matrix[0].length;

        int low=0;
        int high=n*m-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            int row=mid/m;
            int col=mid%m;

            if(target<matrix[row][col]){
                high=mid-1;
            }else if(target>matrix[row][col]){
                low=mid+1;
            }else if(matrix[row][col]==target){
                return true;
            }
        }
        return false;
    }
}
