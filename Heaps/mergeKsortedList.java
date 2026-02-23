package Heaps;
import java.util.*;


class mergeKsortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a,b)->a.val-b.val);

        for(ListNode head:lists){
            if(head!=null) pq.offer(head);
        }

        
        ListNode dNode=new ListNode(-1);
        ListNode temp=dNode;
        
        while(!pq.isEmpty()){
            ListNode nNode=pq.poll();
            temp.next=nNode;
            temp=temp.next;

            if(nNode.next!=null) pq.offer(nNode.next);
        }
        return dNode.next;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}