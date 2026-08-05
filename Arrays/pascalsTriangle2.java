import java.util.*;
class pascalsTriangle2 {
    public List<Integer> getRow(int rowIdx) {
        List<Integer> ans=new ArrayList<>();

        long res=1;
        ans.add(1);
        for(int i=1;i<=rowIdx;i++){
            res=res*(rowIdx-i+1);
            res=res/i;
            ans.add((int)res);
        }
        return ans;
    }
}