package codeIQ.masudrive;

import java.util.ArrayList;
import java.util.List;

public class Glass {

    public static void main(String[] args) {
        
        new Glass().solve();
        
    }
    
    int maxN = 100;
    int minN = 10;
    void solve() {
        
        int sum = 0;

        // B > C,かつ、 10 <= B+C <= 100 
        for (int i = 2; i < maxN; i++) {
            for (int j = 1; j < i; j++) {
                int glassSum = i + j;
                if (minN <= glassSum && glassSum <= maxN && glassSum % 2 == 0) {
                    if (gcd(i, j) > 1) {
                        continue;
                    }
                    glassies = new int[3];
                    glassies[0] = glassSum;
                    glassies[1] = i;
                    glassies[2] = j;
                    min = 10000;
                    searchCnt = 0;
                    System.out.print(i + " : " + j);

                    dp = new boolean[maxN + 1][maxN + 1][maxN + 1];
                    
                    int[] cA = new int[3];
                    cA[0] = glassSum;
                    cA[1] = 0;
                    cA[2] = 0;
                    search(cA);
                    System.out.println(" " + min);
                    sum++;
                }
            }
        }
        
        System.out.println(sum);
        
    }

    int[] glassies = null;
    boolean[][][] dp = null;
    
    int min = 0;
    int searchCnt = 0;
    void search(int[] cA) {
        if (dp[cA[0]][cA[1]][cA[2]]) {
            return;
        }
        dp[cA[0]][cA[1]][cA[2]] = true;

        if (cA[0] == glassies[0]/2) {
            min = Math.min(searchCnt, min);
            return;
        }

//        print(cA);

        permutationRange(0, 2, cA);
    }

    void print(int[] p) {
        for (int i : p) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    void permutationRange(int from, int to, int[] args) {
        int cnt = to - from + 1;
        int[] elements = new int[cnt];
        for (int i = 0 ; i <  cnt; i++) {
            elements[i] = from++;
        }
        permutation(elements, 0, cnt - 1, args);
    }

    void permutation(int[] p, int L, int R, int[] args) {
        if (L == R) { 
            searchCnt ++;
            int[] next = new int[3];
            int tmp = args[p[0]];
            next[p[0]] = Math.min(glassies[p[0]], args[p[0]] + args[p[1]]);
            next[p[1]] = args[p[1]] - (next[p[0]] - tmp);
            next[p[2]] = args[p[2]];
            
            search(next);
            searchCnt --;
        } else {
            
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; 
            p[L] = p[i]; 
            p[i] = tmp;
            permutation(p, L+1, R, args);
            tmp = p[L]; 
            p[L] = p[i]; 
            p[i] = tmp;
          }
        }
     }

    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }

}
