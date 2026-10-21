
 
class palindromeLL {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) return true;

        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            
            slow=slow.next;
            fast=fast.next.next;
        }
        //find middle of LL then reverse from there and compare the two LL as palindrome
        ListNode newHead=reverse(slow);

        ListNode first=head;
        ListNode second=newHead;

        while(second!=null){
            if(first.val!=second.val){
                return false;
            }
            first=first.next;
            second=second.next;
        }
        return true;
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
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}