class LargestNumber {
    public String largestNumber(int[] nums) {
        //write a custom comparator 3,30-> 303 or 330 -> choose 330 then continue;

        String[] arr=new String[nums.length];
        for(int i=0;i<nums.length;i++){
            arr[i]=String.valueOf(nums[i]);
        }

        Arrays.sort(arr,(a,b)->{
            String ab=a+b;
            String ba=b+a;
            return ba.compareTo(ab);
        });

        if(arr[0]=="0"){
            return "";
        }
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<arr.length;i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}