package StacksANDqueues;

class MyCircularDeque {
    private int front;
    private int rear;
    private int[] arr;
    private int size;
    public MyCircularDeque(int k) {
        size=0;
        arr=new int[k];
        rear=front=-1;
    }
    
    public boolean insertFront(int value) {
        if(isFull()) return false;
        if(size==0){
            front=rear=0;
        }else{
            front=(front-1+arr.length)%arr.length;
            //you need to add arr.length before taking the modulo so that negative numbers wrap around correctly
        }
        arr[front]=value;
        size++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if(isFull()) return false;
        if(size==0){
            rear=front=0;
        }else{
            rear=(rear+1)%arr.length;
        }
        arr[rear]=value;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if(isEmpty()) return false;
        if(size==1){
            rear=front=-1;
        }
        else front=(front+1)%arr.length;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if(isEmpty()) return false;
        if(size==1){
            rear=front=-1;
        }else{
            rear=(rear-1+arr.length)%arr.length; //add so negative no. wrap around
        }
        size--;
        return true;
    }
    
    public int getFront() {
        if(isEmpty()) return -1;
        return arr[front];
    }
    
    public int getRear() {
        if(isEmpty()) return -1;
        return arr[rear];
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    public boolean isFull() {
        return arr.length==size;
    }
}