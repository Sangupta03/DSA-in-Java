import java.util.*;

class ProductOfNumbers {
    ArrayList<Integer> list;
    public ProductOfNumbers() {
        list=new ArrayList<>();
    }
    
    public void add(int num) {
        list.add(num);
    }
    
    public int getProduct(int k) {
        
        int prod=1;
        int cnt=0;
        int idx=list.size()-1;
        while(idx>=0 && cnt!=k){
            cnt++;
            int ele=list.get(idx);
            prod*=ele;
            idx--;
        }
        return prod;
    }
}

//Optimal approach

//use prefix product

class ProductOfNumbers2 {
    ArrayList<Integer> prefixProd;
    public ProductOfNumbers2() {
        prefixProd=new ArrayList<>();
        prefixProd.add(1);
    }
    
    public void add(int num) {
        if(num==0){
            prefixProd.clear();
            prefixProd.add(1);
        }else{
            int last=prefixProd.get(prefixProd.size()-1);
            prefixProd.add(last*num);
        }
    }
    
    public int getProduct(int k) {
        int size=prefixProd.size(); //n+1  // n is the total number added, +1 is the extra prefix 1 we added at start

        if(size<=k){
            return 0;
        }else{
            return prefixProd.get(size-1)/prefixProd.get(size-1-k);
        }
    }
}