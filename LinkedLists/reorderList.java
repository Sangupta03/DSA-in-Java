class reorderList {
    public void reorderListQ(ListNode head) {
        if(head==null || head.next==null) return;

        //Find the middle of the linked list
        ListNode middle=findMiddle(head);
        ListNode t1=head;

        //Split the list into two halves and reverse the second half
        ListNode t2=middle.next;
        middle.next=null;
        t2=reverse(t2);

        //merge the 2 halves
        while(t2!=null){
           ListNode temp1=t1.next;
           ListNode temp2=t2.next;

           t1.next=t2;
           t2.next=temp1;

           t1=temp1;
           t2=temp2;
        }
    }

    public ListNode findMiddle(ListNode head){
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode prev=null;
        ListNode next=null;
        ListNode curr=head;


        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
}