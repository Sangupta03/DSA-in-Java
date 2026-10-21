class reverseLL2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        // Dummy node to handle edge cases where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevLeft = dummy;

        // Move prevLeft to the node right before 'left'
        for (int i = 0; i < left - 1; i++) {
            prevLeft = prevLeft.next;
        }

        // Isolate the sublist
        ListNode subListHead = prevLeft.next;
        ListNode curr = subListHead;
        
        // Move curr to the 'right' node
        for (int i = 0; i < right - left; i++) {
            curr = curr.next;
        }
        ListNode nextRight = curr.next;

        // Cut the sublist off so reverseList knows where to stop
        curr.next = null;

        // Reverse the isolated sublist
        ListNode newSubListHead = reverseList(subListHead);
        
        prevLeft.next = newSubListHead;   // Connect front part to new head
        subListHead.next = nextRight;       // Connect old head (now tail) to the rest of the list

        return dummy.next;
    }
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}