package BinarySearch;

class searchinTwoD_two{
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix[0].length;
        int row=0;
        int col=m-1;

        while(row<matrix.length && col>=0){
            if(matrix[row][col]==target){
                return true;
            }else if(matrix[row][col]<target){
                row++; //increase value
            }else{
                col--;  //to decrease value
            }
        }
        return false;
    }
}