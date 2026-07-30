package Greedy;
import java.util.*;
class candyDistribution {
    public int candy(int[] ratings) {
        int n=ratings.length;
        int[] candies=new int[n];
        Arrays.fill(candies,1);

        for(int i=1;i<n;i++){
            if(ratings[i-1]<ratings[i]){
                candies[i]=candies[i-1]+1;
            }
        }

        for(int i=n-2;i>=0;i--){
            if(ratings[i+1]<ratings[i]){
                candies[i]=Math.max(candies[i],candies[i+1]+1);
            }
        }
        int total=0;
        for(int x:candies){
            total+=x;
        }
        return total;
    }
}