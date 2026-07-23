class add2NumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode t1=reverse(l1);
        ListNode t2=reverse(l2);

        ListNode dNode=new ListNode(-1);
        ListNode temp=dNode;
        int carry=0;

        while(t1!=null || t2!=null){
            int sum=carry;
            if(t1!=null) sum+=t1.val;
            if(t2!=null) sum+=t2.val;

            ListNode newNode=new ListNode(sum%10);
            carry=sum/10;
            temp.next=newNode;
            temp=temp.next;
            if(t1!=null) t1=t1.next;
            if(t2!=null) t2=t2.next;
        }
        if(carry==1){
            ListNode newNode=new ListNode(1);
            temp.next=newNode;
        }
        ListNode nHead=reverse(dNode.next);
        return nHead;
    }

    public ListNode reverse(ListNode head){
        if(head==null || head.next==null) return head;

        ListNode curr=head;
        ListNode next=null;
        ListNode prev=null;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
}