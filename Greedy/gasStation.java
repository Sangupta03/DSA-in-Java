package Greedy;

class gasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank=0;
        int currTank=0;

        int start=0;

        for(int i=0;i<gas.length;i++){
            int gain=(gas[i]-cost[i]);
            
            totalTank+=gain;
            currTank+=gain;

            if(currTank<0){
                currTank=0; //start again
                start=i+1;
            } 
        }
        // If total gas is less than total cost,
        // completing the circle is impossible.
        if(totalTank<0) return -1;
        return start;
    }
}