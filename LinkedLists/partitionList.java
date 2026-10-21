class  partitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        
        // dummy head for elements < x
        ListNode smallerDummy = new ListNode(0);
        ListNode smaller = smallerDummy;
        
        // dummy head for elements >= x
        ListNode greaterDummy = new ListNode(0);
        ListNode greater = greaterDummy;
        
        ListNode curr = head;

        while(curr != null){
            if(curr.val < x){
                smaller.next = curr;
                smaller = smaller.next;
            } else {
                greater.next = curr;
                greater = greater.next;
            }
            curr = curr.next;
        }
        
        greater.next = null;
        
        // Connect the end of the smaller list to the start of the greater list
        smaller.next = greaterDummy.next;
        
        //Return the head of the smaller list
        return smallerDummy.next;
    }
}