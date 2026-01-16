package BinarySearch;

class searchinTwoD {
    public boolean searchMatrix(int[][] matrix, int target) {
        int low=0;
        int m=matrix.length;
        int n=matrix[0].length; //col len
        int high=m*n-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            int row=mid/n;
            int col=mid%n;
            if(matrix[row][col]<target){
                low=mid+1;
            }else if(matrix[row][col]>target){
                high=mid-1;
            }else if(matrix[row][col]==target){
                return true;
            }
        }
        return false;
    }
}