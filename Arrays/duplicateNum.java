class duplicateNum {
    public int findDuplicate(int[] nums) {
        
        int slow=nums[0];
        int fast=nums[0];

        while(true){
            //detect cycle
            slow=nums[slow];
            fast=nums[nums[fast]];
            if(slow==fast) break;
        }

        //find start of cycle
        slow=nums[0];
        while(slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }
}