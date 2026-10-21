class reverseNodesKgrp {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode curr=head;
        ListNode prevNode=null;

        while(curr!=null){
            ListNode kthNode=findK(curr,k);
            if(kthNode==null){
                if(prevNode!=null) prevNode.next=curr;
                //no more nodes to find k left
                break;
            }

            ListNode nextNode=kthNode.next;
            kthNode.next=null; //break the connection
            ListNode newHead=reverse(curr);
            if(curr==head){
                head=newHead;
            }else{
                prevNode.next=newHead;
            }
            prevNode=curr;
            curr=nextNode;
        }
        return head;   
    }
    public ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        ListNode next=null;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public ListNode findK(ListNode head,int k){
        if(head==null || k==1) return head;

        ListNode curr=head;

        while(curr!=null && k>1){
            curr=curr.next;
            k--;
        }
        return curr;
    }
}