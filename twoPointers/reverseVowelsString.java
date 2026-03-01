package twoPointers;
class reverseVowelsString {
    public String reverseVowels(String s) {
        int n=s.length();
        char[] arr=s.toCharArray();

        int left=0;
        int right=n-1;

        while(left<right){
            while(left<right && !isVowel(arr[left])) left++;
            while(left<right && !isVowel(arr[right])) right--;
            char temp=arr[left];
            arr[left]=arr[right];
            arr[right]=temp;
            left++;
            right--;
        }
        return new String(arr);   
    }
    public boolean isVowel(char c){
        return "AEIOUaeiou".indexOf(c)!=-1;
    }
}