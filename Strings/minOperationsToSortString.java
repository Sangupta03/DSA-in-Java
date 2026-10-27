class minOperationsToSortString {
    public int minOperations(String s) {

        boolean isSorted=true;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)<s.charAt(i-1)){
                isSorted=false;
                break;
            }
        }
        if(isSorted) return 0;
        if(s.length()==2) return -1;

        int maxChar=s.charAt(0);
        int minChar=s.charAt(0);

        for(int i=1;i<s.length();i++){
            if(s.charAt(i)<minChar){
                minChar=s.charAt(i);
            }
            if(s.charAt(i)>maxChar){
                maxChar=s.charAt(i);
            }
        }

        int n=s.length();
        if(s.charAt(0)==minChar || s.charAt(n-1)==maxChar){
            return 1;
        }

        int minMid=s.charAt(1);
        int maxMid=s.charAt(1);

        for(int i=2;i<n-1;i++){
            if(s.charAt(i)>maxMid){
                maxMid=s.charAt(i);
            }
            if(s.charAt(i)<minMid){
                minMid=s.charAt(i);
            }
        }

        if(minMid>s.charAt(n-1) && maxMid<s.charAt(0)){
            return 3;
        }
        return 2;
    }
}