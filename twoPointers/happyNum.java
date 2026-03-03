package twoPointers;
class HappyNum {
    public int next(int n){
        int total=0;
        while(n>0){
            int d=n%10;
            n=n/10;
            total+=d*d;
        }
        return total;
    }
    public boolean isHappy(int n) {
        int slow=n;
        int fast=next(n); //same as do-while logic in fast,slow pointer, not initialised same so that loop runs

        while(fast!=1 && slow!=fast){
            slow=next(slow);
            fast=next(next(fast)); //2 steps ahead of slow;
        }
        return fast==1;
    }
}
