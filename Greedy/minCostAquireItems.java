package Greedy;

class minCostsAquireItems {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        
        if(need1==0 && need2==0) return 0;

        long common=Math.min(need1,need2);

        long ans1=Math.min(costBoth,(long)cost1+cost2)*common;

        //now remaining left common-need1, common-need2

        long rem1=need1-common;
        long rem2=need2-common;

        long ans2=Math.min(costBoth,cost2)*rem2+Math.min(costBoth,cost1)*rem1;

        return ans1+ans2;
    }
}
