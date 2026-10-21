class maxTwinSumLL {
    public int pairSum(ListNode head) {

        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode newHead=reverse(slow);

        int maxSum=0;

        ListNode first=head;
        ListNode second=newHead;

        while(second!=null){
            maxSum=Math.max(maxSum,first.val+second.val);
            first=first.next;
            second=second.next;
        }
        return maxSum;
    }
    public ListNode reverse(ListNode head){
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

class ListNode{
  ListNode next;
  int val;
  ListNode(ListNode next,int val){
    this.val=val;
    this.next=next;
  }
}