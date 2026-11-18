package Heaps;
import java.util.*;

class processTasks {
    public int[] assignTasks(int[] servers, int[] tasks) {
        
        PriorityQueue<Pair> ready=new PriorityQueue<>((a,b)->a.server!=b.server? Integer.compare(a.server,b.server): Integer.compare(a.idx,b.idx));

        //first compare by server wt then by server idx if equal wt

        PriorityQueue<Tasks> busy=new PriorityQueue<>((a,b)->{
            if(a.finishT!=b.finishT) return Long.compare(a.finishT,b.finishT);

            if(a.server!=b.server) return Integer.compare(a.server,b.server);
            return Integer.compare(a.idx,b.idx);
        });
        int[] ans=new int[tasks.length];

        for(int i=0;i<servers.length;i++){
            ready.offer(new Pair(servers[i],i));
        }

        for(int t=0;t<tasks.length;t++){
            while(!busy.isEmpty() && busy.peek().finishT<=t){
                Tasks free=busy.poll();
                ready.offer(new Pair(free.server,free.idx));
            } //added free server back

            if(!ready.isEmpty()){
                Pair a=ready.poll();
                ans[t]=a.idx;
                busy.offer(new Tasks(t+tasks[t],a.server,a.idx));
            }else{
                Tasks earliest=busy.poll();
                ans[t]=earliest.idx; //latest busy server to be freed can be used

                //remember to add earliest finishTime with task t
                busy.offer(new Tasks(earliest.finishT+tasks[t],earliest.server,earliest.idx));
            }
        }
        return ans;
    }
}

class Pair{
    int server;
    int idx;
    Pair(int server,int idx){
        this.server=server;
        this.idx=idx;
    }
}

class Tasks{
    int server;
    long finishT;
    int idx;
    Tasks(long finishT,int server,int idx){
        this.finishT=finishT;
        this.server=server;
        this.idx=idx;
    }
}