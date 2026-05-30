package StacksANDqueues;

class sumOfSuba {
    public int sumSubarrayMins(int[] arr) {

        long MOD=1000000007;
        int n=arr.length;
        int[] nse=new int[n];
        int[] pse=new int[n];
        long total=0;
        Stack<Integer> stk=new Stack<>();

        for(int i=n-1;i>=0;i--){
            while(!stk.isEmpty() && arr[stk.peek()]>=arr[i]){
                stk.pop();
            }
            nse[i]=stk.isEmpty()?n:stk.peek();
            stk.push(i);
        }

        stk.clear();

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && arr[stk.peek()]>arr[i]){
                stk.pop();
            }
            pse[i]=stk.isEmpty()?-1:stk.peek();
            stk.push(i);
        }

        stk.clear();

        for(int i=0;i<n;i++){
            long left=i-pse[i];
            long right=nse[i]-i;
            total=(total+(left*right*(long)arr[i])%MOD)%MOD;
        }
        return (int) total;
    }
}