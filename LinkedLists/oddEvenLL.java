class oddEvenLL {
    public ListNode oddEvenList(ListNode head) {
        ListNode odd=head;
        ListNode evenHead=head.next;
        ListNode even=head.next;

        while(even!=null && even.next!=null){
            odd.next=odd.next.next;
            even.next=even.next.next;

            odd=odd.next;
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }
}