package StacksANDqueues;

class myQueue {
    
    public int size;
    public int rear;
    public int front;
    public int[] arr;
    // Constructor
    public myQueue(int n) {
        // Define Data Structures
        arr=new int[n];
        size=0;
        front=rear=-1;
    
    }

    public boolean isEmpty() {
        // Check if queue is empty
        if(size==0) return true;
        else return false;
    }

    public boolean isFull() {
        // Check if queue is full
        return size==arr.length;
    }

    public void enqueue(int x) {
        // Enqueue
        if(size==arr.length) return;
        if(size==0){
            front=rear=0;
        }else{
            rear=(rear+1)%arr.length;
        }
        arr[rear]=x;
        size++;
    }

    public void dequeue() {
        // Dequeue
        if(isEmpty()) return;
        int ele=arr[front];
        if(size==1){
            rear=front=-1; //reset pointers
        }else{
            front=(front+1)%arr.length;
        }
        size--;
    }

    public int getFront() {
        // Get front element
        if(isEmpty()) return -1;
        return arr[front];
    }

    public int getRear() {
        // Get last element
        if(isEmpty()) return -1;
        return arr[rear];
    }
}