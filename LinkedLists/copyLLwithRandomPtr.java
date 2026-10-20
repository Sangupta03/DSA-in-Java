
class copyLLwithRandomPtr {
    public Node copyRandomList(Node head) {
        if(head==null) return null;

        createCopy(head);
        copyRandom(head);
        return connectAll(head);
    }

    //create a list of node with its copy side by side
    // eg A-> A'->B -> B' -> C -> C' ->null   , so copies of nodes created
    public void createCopy(Node head){
        Node temp=head;

        while(temp!=null){
            Node newNode=new Node(temp.val);
            newNode.next=temp.next;
            temp.next=newNode;
            temp=temp.next.next;
        }
    }

    //now copy random ptr values into the copyNodes
    public void copyRandom(Node head){
        Node temp=head;

        while(temp!=null){
            //copyNode=temp.next;
            if(temp.random!=null){
                temp.next.random=temp.random.next;
            }else{
                temp.next.random=null;
            }
            temp=temp.next.next;
        }
    }

    //seperate both the copy list and original list using dummy node dNode, res and temp
    public Node connectAll(Node head){
        Node dNode=new Node(-1);
        Node res=dNode;
        Node temp=head;

        while(temp!=null){
            res.next=temp.next;
            res=res.next;

            temp.next=temp.next.next;
            temp=temp.next;
        }
        return dNode.next;
    }
}

class Node{
  int val;
  Node next;
  Node random;
  Node(){}
    Node(int val){
        this.val=val;
    }
  Node(int val,Node next){
    this.val=val;
    this.next=null;
    this.random=null;
  }
}