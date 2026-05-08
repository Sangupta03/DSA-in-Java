package LinkedLists;

class Node{
    int data;
    Node prev;
    Node next;
    Node(int data){
        this.data=data;
        this.next=null;
        this.prev=null;
    }
}

class DLL{
    Node head;
    DLL(){
        head=null;
    }
    
    void insertAtHead(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        newNode.next=head;
        head.prev=newNode;
        head=newNode;
    }
    
    void insertAtEnd(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newNode;
        newNode.prev=temp;
    }
    
    void insertAfter(int key,int data){
       
        Node temp=head;
        while(temp!=null && temp.data!=key){
            temp=temp.next;
        }
        if(temp==null) return;
        Node newNode=new Node(data);
        newNode.next=temp.next;
        newNode.prev=temp;
        if(temp.next!=null){
            temp.next.prev=newNode;
        }
        temp.next=newNode;
    }
    
    void deleteNode(int key){
        Node temp=head;
        while(temp!=null && temp.data!=key){
            temp=temp.next;
        }
        if(temp==null) return;
        if(temp.prev!=null){
            temp.prev.next=temp.next;
        }else{
            head=temp.next;
            if(head!=null){
                head.prev=null;
            }
        }
        
        if(temp.next!=null){
            temp.next.prev=temp.prev;
        }
    }
    void traverseForward(){
        
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"<->");
            temp=temp.next;
        }
        System.out.print("Null");
    }
    void traverseBackward(){
        if(head==null) return;
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        while(temp!=null){
            
            System.out.print(temp.data+"<->");
            temp=temp.prev;
        }
        System.out.print("Null");
    }
}