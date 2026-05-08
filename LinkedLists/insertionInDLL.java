package LinkedLists;

class Solution {
    Node insertAtPos(Node head, int p, int x) {
        // code here
        Node temp=head;
        
        for(int i=0;i<p;i++){
            if(temp==null) return head;
            temp=temp.next;
        }
        Node newNode=new Node(x);
        newNode.next=temp.next;
        newNode.prev=temp;
        if(temp.next!=null){
            temp.next.prev=newNode;
        }
        temp.next=newNode;
        return head;
    }
}
