package codeIQ.masudrive;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Handkerchief {

    private static final int N = 8;

    void solve() {

        // 初期状態を表す
        Integer[] start = new Integer[N];
        for (int i = 1; i <= N; i++) {
            start[i - 1] = i;
        }

        // 最終状態を表す
        // 移動しない人はN番目の人とする。
        // Nが偶数の場合、N / 2の人も移動しない。
        Integer[] end = new Integer[N];
        for (int i = 1; i < N; i++) {
            end[i - 1] = N - i;
        }
        end[N - 1] = N;

        Deque<Type> que = new ArrayDeque<Type>();
        Map<String, Integer> dp = new HashMap<String, Integer>();
        dp.put(getKey(start), 0);
        int min = Integer.MAX_VALUE;

        for (int z = 0; z < 8; z++) {
            // n人目の後ろにハンカチを落とす
            {
                Integer[] gameState = Arrays.copyOf(start, N);
                int oni = gameState[z];
                gameState[z] = 0;
                int beforePosition = z;
                int moveSum = N;
                Type s = new Type(gameState, oni, beforePosition, moveSum);
                s.log = String.valueOf(oni);
                que.push(s);
                dp.put(getKey(gameState), N);
            }
            
            String endKey = getKey(end);
            while(!que.isEmpty()) {
                Type t = que.poll();
                
                Integer[] gameState = t.gameState;
                int oni = t.oni;
                int beforePosition = t.beforePosition;
                int moveSum = t.moveSum;
                String key = getKey(t.gameState);
                
                if (key.equals(endKey)) {
                    System.out.println(moveSum + " " + t.log);
                    min = Math.min(moveSum, min);
                    
                    continue;
                }
    
                if (dp.containsKey(key) && dp.get(key) < moveSum) continue;
    
                // oniが0の場合、前回移動した場所の隣の場所に移動する
                for (int i = 0; i < N; i++) {
                    if (i == beforePosition) continue;
                    int next = i;
                    int nextOni = gameState[next];
                    Integer[] nextGameState = Arrays.copyOf(gameState, N);
                    nextGameState[next] = oni;
                    int nextMoveSum = moveSum + moveScore(beforePosition, next, N);
                    int nextBeforePosition = next;
    
                    String nextKey = getKey(nextGameState);
    
                    if (dp.containsKey(nextKey) && dp.get(nextKey) <= nextMoveSum || nextMoveSum >= 97) {
    //                if (dp.containsKey(nextKey) && dp.get(nextKey) <= nextMoveSum) {
    
                        continue;
                    }
                    
                    Type nextT = new Type(nextGameState, nextOni, nextBeforePosition, nextMoveSum);
                    nextT.log = t   .log + ":" + nextOni;
                    dp.put(nextKey, nextMoveSum);
                    que.push(nextT);
                }
            }
        }
        System.out.println(min);
        
    }

    String getKey(Integer[] arg) {
        StringBuilder sb = new StringBuilder();
        
        for (int i : arg) {
            sb.append(":" + i);
        }
        return sb.toString();
        
    }
    
    static int moveScore(int beforePosition, int toPosition, int sumPosition) {
        if (toPosition > beforePosition) {
            return toPosition - beforePosition + sumPosition;
        } else {
            return sumPosition - beforePosition + toPosition + sumPosition;
        }
    }

    static int searchPos(int beforePosition, int sumPosition) {
        if (true) {
            
            if (beforePosition >= sumPosition - 1) {
                System.out.println("not in");
                return 0;
            } else {
                return beforePosition + 1;
            }
        }
        
        
        
        if (beforePosition > (sumPosition - 1) / 2) {
            return beforePosition - sumPosition / 2;
        } else {
            return beforePosition + sumPosition / 2;
        }
    }

    
    public static void main(String[] args) {
        
        new Handkerchief().solve();
        
    }

    static int pushNum(int target, int num, int changePos) {
        int res = target;
        
        int fromNum = target - (int) (target - Math.pow(10, changePos - 1));
        int toNum = num * (int) Math.pow(10, changePos - 1);
        
        res = res - fromNum + toNum;
        
        return res;
    }

    
    static int swap(int target, int from, int to) {
        int res = target;
        
        int fromNum = target - (int) (target - Math.pow(10, from - 1));
        int toNum = target - (int) (target - Math.pow(10, to - 1));
        
        res -=(fromNum + toNum);
        
        fromNum = target - (int) (target - Math.pow(10, to - 1));
        fromNum = target - (int) (target - Math.pow(10, from - 1));
        
        return res;
    }
    
}

class Type {
    Integer[] gameState = null;
    int beforePosition = 0;
    int oni = 0;

    int moveSum = 0;

    String log = "";
    
    Type(Integer[] gameState, int oni, int num, int score) {
        this.gameState = gameState;
        this.oni = oni;
        this.beforePosition = num;
        this.moveSum = score;
    }
}

class MyQue {
    
    int[][] que;
    int size;
    int index;
    int length;
    
    
    
    int[] score;
    
    MyQue(int size) {
        que = new int[size][];
        this.size = size;
    }
    
    boolean isEmpty() {
        return que[index + 1] != null;
    }
    
    int[] poll() {
        if (++index > size) index = 0;
        return que[index];
    }
    
    int getScore() {
        return score[index];
    }
    
    void put(int[] arg, int score) {
        if (++length > size) length = 0;
        que[length] = arg;
        this.score[length] = score;
    }
    
}