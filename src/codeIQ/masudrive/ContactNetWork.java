package codeIQ.masudrive;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ContactNetWork {

    static int N = 14;

    static Deque<Con> que;
    
    void solve() {

        
    }
    
    
    public static void main(String[] args) {
        
        new ContactNetWork().solve();
        
        
        Con baseCon = new Con(0, 0, 0);
        
        for (int i = 0; i < N; i++) {
            que = new ArrayDeque<Con>();
            
//            addCon(baseCon);

            
            int minTime = 100;
            int minTelCnt = 100;

            Con next = null;
            
            while(!que.isEmpty()) {

                Con con = que.poll();
                boolean[] t = new boolean[100];
                
                int time = 0;
                int telCnt = 0;
                
                for (Con c : con.list) {
                    telCnt ++;
                    t[c.sT] = true;
                    int tIndex = c.time;
                    if (c.cnt > 1) {
                        telCnt ++;
                        while(true) {
                            if(!t[tIndex]) {
                                t[tIndex] = true;
                                time = Math.max(time, tIndex);
                                break;
                            }
                            tIndex++;
                        }
                    }
                    time = Math.max(time, tIndex);
                }

                if ((minTime == time && minTelCnt > telCnt) || minTime > time) {
                    minTime = time;
                    minTelCnt = telCnt;
                    next = con;
                }
            }
            
            if (i + 1 == N) {
                System.out.println(minTime + " " + minTelCnt);
            }
        }

    }

    static Con addCon(Con c , int t) {
        if (c.list != null && c.list.size() > 0) {
            for (Con x : c.list) {
                addCon(x, t);
            }
        }
        Con n = new Con(c);
        n.add(t);
        que.add(n);
        return null;
    }
    
    static class Con {
        int cnt;
        int time;
        int sT;
        
        List<Con> list = new ArrayList<Con>();
        
        public Con(int cnt, int time, int sT) {
            this.cnt = cnt;
            this.time = time;
            this.sT = sT;
        }

        public Con(Con con) {
            this.cnt = con.cnt;
            this.time = con.time;
            this.sT = con.sT;
            
            this.list = new ArrayList<Con>(con.list);
        }
        
        public Con add(int time) {
            Con res = new Con(this.cnt + 1, time, this.sT);
            return res;
        }
        
    }
}
