class rotateImage {
    public void rotate(int[][] matrix) {
        int n=matrix.length;

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }

        for(int row=0;row<n;row++){
            reverse(matrix,row);
        }
    }
    public void reverse(int[][] matrix,int row){
        int n=matrix.length;
        
        int i=0;
        int j=n-1;
        while(i<j){
            int temp=matrix[row][i];
            matrix[row][i]=matrix[row][j];
            matrix[row][j]=temp;
            i++;
            j--;
        }
    }
}