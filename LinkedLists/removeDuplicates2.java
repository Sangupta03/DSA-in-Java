class removeDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dNode=new ListNode(-1);
        ListNode temp=dNode;

        while(head!=null){
            if(head.next!=null && head.val==head.next.val){
                while(head.next!=null && head.val==head.next.val){
                    head=head.next;
                }
                temp.next=head.next;
            }else{
                temp.next=head; //keep the node
                temp=temp.next;  //move temp forward only after checking and adding head
            }
            head=head.next;
        }
        return dNode.next;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
    }
}