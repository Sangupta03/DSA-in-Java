class swapNodesPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dNode=new ListNode(0);
        dNode.next=head;

        ListNode prev=dNode;

        while(prev.next!=null && prev.next.next!=null){
            ListNode first=prev.next;
            ListNode second=prev.next.next;

            //do swap
            first.next=second.next;
            second.next=first;
            prev.next=second;  //case connect prev node to new swapped first node;
            // Move prev forward by 2 nodes to set up the next pair
            prev=first;
        }
        return dNode.next;
    }
}

class ListNode{
  int val;
  ListNode next;
  ListNode(int val){
    this.val=val;
  }
}