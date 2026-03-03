package LinkedLists;

class removeNthFromEndQ {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || head.next==null) return null;
        ListNode fast=head;
        ListNode slow=head;

        for(int i=0;i<n;i++){
            fast=fast.next;
        }
        if(fast==null){
            ListNode dNode=head.next;
            head.next=null;
            return dNode;
        }
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        //ListNode delNode=slow.next;
        slow.next=slow.next.next;
        return head;
    }
}