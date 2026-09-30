class maxNestingDepthOf2Valid {
    //question asks to divide seq into 2 grps A(0) or B(0) , such that the max depth of each grp is min;
    //so if max depth of whole seq=d;
    //max depth of A and B must be less than or equal to d/2;
    public int[] maxDepthAfterSplit(String seq) {
        
        int[] ans=new int[seq.length()];
        int n=seq.length();
        int depth=0;
        for(int i=0;i<n;i++){
            char ch=seq.charAt(i);

            if(ch=='('){
                depth++;
                ans[i]=depth%2; //even depth to group A;  val=0
            }else{
                ans[i]=depth%2; //odd depth to group B; val=1
                depth--;
            }
        }
        return ans;
    }
}