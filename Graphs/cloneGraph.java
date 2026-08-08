package Graphs;

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class cloneGraph {
    public Node cloneGraphQ(Node node) {
        HashMap<Node,Node> hp=new HashMap<>(); //ORIGINAL NODE  →  CLONED NODE
        //to clone graph use hashmap, it helps keep track of the visited and already created nodes
        //apply Bfs
        Deque<Node> q=new ArrayDeque<>();

        if(node==null) return null;
        hp.put(node,new Node(node.val));
        q.offer(node); //Queue contains ORIGINAL nodes

        while(!q.isEmpty()){
            Node curr=q.poll();

            for(Node nbh:curr.neighbors){
                if(!hp.containsKey(nbh)){
                    hp.put(nbh,new Node(nbh.val)); //add beighbor of orignal node to clone
                    q.offer(nbh);
                    //push original node through which we traverse the complete graph
                }
                hp.get(curr).neighbors.add(hp.get(nbh));
                //connect clone ke neighbors
            }
        }
        return hp.get(node); //clone starting node
    }
}