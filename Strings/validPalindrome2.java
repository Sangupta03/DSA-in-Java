class validPalindrome2 {
    public boolean validPalindrome(String s) {
        int left=0;
        int right=s.length()-1;

        while(left<=right){
            if(s.charAt(left)!=s.charAt(right)){
                return isPalindrome(left+1,right,s) || isPalindrome(left,right-1,s);
            }
            left++;
            right--;   //remember example of 'abc' is false
        }
        return true;
    }

    public boolean isPalindrome(int left,int right,String s){

        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}