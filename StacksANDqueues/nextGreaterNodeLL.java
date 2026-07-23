package StacksANDqueues;
import java.util.*;

class nextGreaterNodeLL {
    public int[] nextLargerNodes(ListNode head) {
        
        ArrayList<Integer> arr=new ArrayList<>();
        ListNode curr=head;
        while(curr!=null){
            arr.add(curr.val);
            curr=curr.next;
        }
        int n=arr.size();
        int[] ans=new int[n];

        Stack<Integer> stk=new Stack<>();
        for(int i=0;i<arr.size();i++){
            while(!stk.isEmpty() && arr.get(stk.peek())<arr.get(i)){
                ans[stk.pop()]=arr.get(i);
            }
            stk.push(i);
        }
        return ans;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}