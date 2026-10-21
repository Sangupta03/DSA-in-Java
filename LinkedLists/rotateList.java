class rotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || k==0) return head; //no rotations needed
        
        int len=1;
        ListNode tail=head;
        while(tail.next!=null){
            len++;
            tail=tail.next;
        }
        k=k%len;
        ListNode newTail=findTail(head,len-k);

        tail.next=head;
        head=newTail.next;
        newTail.next=null;
        return head;
    }

    public ListNode findTail(ListNode head,int k){
        if(head==null || k==1) return head;

        while(head!=null && k>1){
            k--;
            head=head.next;
        }
        return head;
    }
}