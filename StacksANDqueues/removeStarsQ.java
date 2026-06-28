class removeStarsQ {
    public String removeStars(String s) {
        
        Stack<Character> stk=new Stack<>();

        for(char ch:s.toCharArray()){
            if(ch=='*'){
                stk.pop();
            }else{
                stk.push(ch);
            }
        }

        StringBuilder sb=new StringBuilder();

        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }

        return sb.reverse().toString();
    }
}