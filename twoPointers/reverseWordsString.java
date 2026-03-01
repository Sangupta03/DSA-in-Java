package twoPointers;

class reverseWordsString {
    public String reverseWords(String s) {
        StringBuilder sb=new StringBuilder();
        int end=s.length()-1;

        while(end>=0){
            while(end>=0 && s.charAt(end)==' ') end--;
            int start=end;
            while(start>=0 && s.charAt(start)!=' ') start--;
            sb.append(s.substring(start+1,end+1)).append(" ");
            end=start-1;
        }
        return sb.toString().trim();
    }
}