package BinarySearch;

class peakElementTwo {
    public int[] findPeakGrid(int[][] mat) {
        int m=mat[0].length; //col
        int low=0;
        int high=m-1;
        while(low<=high){
            int mid=(high-low)+low/2;
            int maxValueIdx=findMaxCol(mid,mat);
            int left=(mid-1>=0)?mat[maxValueIdx][mid-1]:-1;
            int right=(mid+1<m)?mat[maxValueIdx][mid+1]:-1;
            if(left<mat[maxValueIdx][mid] && right<mat[maxValueIdx][mid]){
                return new int[]{maxValueIdx,mid};
            }else if(left>mat[maxValueIdx][mid]){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return new int[]{-1,-1};
    }
    public int findMaxCol(int col,int[][] mat){
        int n=mat.length;
        int maxVal=-1;
        int maxIdx=-1;
        for(int i=0;i<n;i++){
            if(mat[i][col]>maxVal){
                maxVal=mat[i][col];
                maxIdx=i;
            }
        }
        return maxIdx;
    }
}