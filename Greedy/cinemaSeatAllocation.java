package Greedy;
import java.util.*;

class cinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer,HashSet<Integer>> hp=new HashMap<>(); //row to col

        //store reserved seats
        for(int[] seats:reservedSeats){
            if(!hp.containsKey(seats[0])){
                hp.put(seats[0],new HashSet<>());
            }
            hp.get(seats[0]).add(seats[1]);
            //row to col
        }

        int ans=2*n;

        for(HashSet<Integer> seat: hp.values()){
            boolean left=true;
            boolean right=true;
            boolean middle=true;

            for(int i=2;i<=5;i++){
                if(seat.contains(i)){
                    left=false;
                    break;
                }

            }

            for(int i=4;i<=7;i++){
                if(seat.contains(i)){
                    middle=false;
                    break;
                }
            }
            for(int i=6;i<=9;i++){
                if(seat.contains(i)){
                    right=false;
                }
            }
            ans-=2; //initially remove 2 groups for each reserved row

            if(left && right){
                ans+=2;//2 groups
            }else if(left || right || middle){
                ans+=1; //only 1 group as reserved seats
            }

        }
        return ans;
    }
}

//start with 2*n because every completely empty row accommodates two groups //2345 6789
//store only rows having reservations, then for each such row check the three possible 4-seat blocks and replace its default contribution with the actual contribution
