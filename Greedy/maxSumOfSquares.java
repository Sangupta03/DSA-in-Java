package Greedy;

class maxSumOfSquares {
    public String maxSumOfSquaresQ(int num, int sum) {

        if(sum<1 || sum>num*9) return "";
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<num;i++){
            int digit=Math.min(9,sum);
            sb.append(digit);

            sum-=digit;
        }

        if(sum>0) return ""; //impossible case

        return sb.toString();
    }
}