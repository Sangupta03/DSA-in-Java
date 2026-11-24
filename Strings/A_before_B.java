class A_before_B {
    public boolean checkString(String s) {
        
        int lastSeenA=-1;
        int lastSeenB=-1;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);

            if(ch=='a'){
                lastSeenA=i;
                if(lastSeenB<i && lastSeenB!=-1) return false;
            }else if(ch=='b'){
                lastSeenB=i;
                if(lastSeenA<i || lastSeenA==-1) continue;
                else return false;
                
            }
        }
        return true;
    }
}