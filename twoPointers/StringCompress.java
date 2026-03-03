package twoPointers;
class stringCompress {
    public int compress(char[] chars) {
        int i=0;
        int idx=0;
    
        int n=chars.length;

        while(i<n){
            int cnt=0;
            char curr=chars[i];
            while(i<n && chars[i]==curr){
                cnt++;
                i++;
            }
            chars[idx]=curr;
            idx++;
            if(cnt>1){
                for(char c:String.valueOf(cnt).toCharArray()){
                    chars[idx]=c;
                    idx++;
                }
            }
        }
        return idx;
    }
}
