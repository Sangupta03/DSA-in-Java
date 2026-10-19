class timeNeededBuyTickets {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n=tickets.length;

        int ans=0;

        for(int i=0;i<n;i++){
            if(i!=k && i<k){
                // People ahead of k can buy up to tickets[k] times
                ans+=Math.min(tickets[k],tickets[i]);
            }else if(i>k){
                // People behind k miss out on the last round, so they buy up to tickets[k] - 1 times
                ans+=Math.min(tickets[k]-1,tickets[i]);
            }else if(k==i){
                // Person k buys all of their tickets
                ans+=tickets[k];
            }
        }
        return ans;
    }
}