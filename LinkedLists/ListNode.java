package LinkedLists;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class deleteMiddleQ {
    public ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null){
            return null;
        }
        ListNode fast=head.next.next; //so that slow points to middle-1;
        ListNode slow=head;
        //elegant way is that slow points to middle-1;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //ListNode delNode=slow.next;
        slow.next=slow.next.next;
        return head; 
    }
}