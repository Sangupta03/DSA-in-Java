package LinkedLists;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val=val;
        this.next=null;
    }
}

class LinkedList{
    Node head;
    LinkedList(){
        head=null;
    }
    
    void insertAtHead(int val){
        Node newNode=new Node(val);
        if(head==null){
            head=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
    
    void insertAtTail(int val){
        Node newNode=new Node(val);
        if(head==null){
            head=newNode;
            return;
        }
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newNode;
    }
    void deleteByVal(int val){
        if(head==null) return;
        if(head.val==val){
            head=head.next;
            return;
        }
        Node temp=head;
        while(temp.next!=null && temp.next.val!=val){
            temp=temp.next;
        }
        if(temp.next!=null) temp.next=temp.next.next;
    }
    
    boolean findKey(int val){
        if(head.val==val) return true;
        Node temp=head;
        while(temp!=null){
            if(temp.val==val) return true;
            temp=temp.next;
        }
        return false;
    }
}
