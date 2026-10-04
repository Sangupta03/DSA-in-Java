class validParenthesisString {
    public boolean checkValidString(String s) {
        int cnt1=0;
        int star1=0;

        for(char ch:s.toCharArray()){
            if(ch=='('){
                cnt1++;
            }else if(ch==')'){
                if(cnt1>0) cnt1--;
                else if(star1>0) star1--;
                else return false;
            }else{
                star1++;
            }
        }

        int cnt2=0;
        int star2=0;
        //check from back

        int n=s.length();

        for(int i=n-1;i>=0;i--){
            char ch=s.charAt(i);
            if(ch==')'){
                cnt2++;
            }else if(ch=='('){
                if(cnt2>0) cnt2--;
                else if(star2>0) star2--;
                else return false;
            }else{
                star2++;
            }
        }
        return true;
    }
}

//eg * can be counted as ')' and '(' both
//eg (*) is valid and (()* is valid 