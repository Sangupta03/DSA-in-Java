class Solution {
    public Node reverse(Node head) {

        Node temp=null;
        Node curr=head;
        
        while(curr!=null){
            temp=curr.prev;
            curr.prev=curr.next;
            curr.next=temp;
            curr=curr.prev;
        }
        
        if(temp!=null){
            head=temp.prev;
        }
        return head;
    }
}