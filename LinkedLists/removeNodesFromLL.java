class removeNodesFromLL {
    public ListNode removeNodes(ListNode head) {
        
        head=reverse(head);
        ListNode curr=head;
        int max=curr.val;

        while(curr!=null && curr.next!=null){
            
            if(curr.next.val<max){
                curr.next=curr.next.next;
            }else{
                max=curr.next.val;
                curr=curr.next;
            }
        }
        ListNode nHead=reverse(head);
        return nHead;
    }

    public ListNode reverse(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        ListNode curr=head;
        ListNode prev=null;
        ListNode next=null;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
}