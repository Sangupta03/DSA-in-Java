class sortList { 
  //same way as merge sort
  //divide and merge
    public ListNode sortListQ(ListNode head) {
        return merge(head);
    }
    public ListNode merge(ListNode head){
        if(head==null || head.next==null) return head;

        ListNode midNode=findMiddle(head);
        ListNode leftNode=head;
        
        ListNode rightNode=midNode.next;
        midNode.next=null;
        //disconnect the middle

        leftNode=merge(leftNode);
        rightNode=merge(rightNode); //recusively divide and merge
        return merge2LL(leftNode,rightNode); //like merge sort
    }

    public ListNode findMiddle(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode slow=head;
        ListNode fast=head;
        fast=fast.next; //edge case for even length
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public ListNode merge2LL(ListNode head1,ListNode head2){
        ListNode dNode=new ListNode(-1);
        ListNode temp=dNode;

        ListNode t1=head1;
        ListNode t2=head2;

        while(t1!=null && t2!=null){
            if(t1.val<=t2.val){
                temp.next=t1;
                t1=t1.next;
            }else{
                temp.next=t2;
                t2=t2.next;
            }
            temp=temp.next;
        }

        if(t1!=null){
            temp.next=t1;
        }
        if(t2!=null){
            temp.next=t2;
        }
        return dNode.next;
    }
}