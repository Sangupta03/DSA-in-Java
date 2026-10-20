class MyLinkedList {
    public ListNode head;
    public int size;
    public MyLinkedList() {
        head=new ListNode(0);
        size=0;
    }
    
    public int get(int index) {
        if(index<0 || index>=size) return -1;
        ListNode curr=head;
        for(int i=0;i<=index;i++){
            curr=curr.next;
        }
        return curr.val;
    }
    
    public void addAtHead(int val) {
        ListNode newNode=new ListNode(val);
        newNode.next=head.next;
        head.next=newNode;
        size++;
    }
    
    public void addAtTail(int val) {
        ListNode curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        size++;
        ListNode newTail=new ListNode(val);
        curr.next=newTail;
    }
    
    public void addAtIndex(int index, int val) {
        if(index<0 || index>size) return;
        ListNode curr=head;

        for(int i=0;i<index;i++){
            curr=curr.next;
        }
        ListNode newNode=new ListNode(val);
        newNode.next=curr.next;
        curr.next=newNode;
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if(index<0 || index>=size) return;

        ListNode curr=head;
        
        for(int i=0;i<index;i++){
            curr=curr.next;
        }
        ListNode dNode=curr.next;
        curr.next=curr.next.next;
        dNode.next=null;
        size--;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
    ListNode(){};
    ListNode(int val){
        this.val=val;
    }
}