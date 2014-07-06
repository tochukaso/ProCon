package codeIQ;

import static java.util.Arrays.deepToString;

public class MajikAnaglam {

    int N = 0;
    boolean isBreak = true;

    boolean isExistenceCenter = false;
    int center = 0;
    int max = 0;
    public static void main(String[] args) {
        new MajikAnaglam().solve(5);
/**
        for (int i = 3; i <=5; i++) {
            new MajikAnaglam().solve(i);
        }
**/
    }
    
    void solve(int i) {
        N = i;
        isBreak = false;
        int mathCnt = i * i;
        max = mathCnt / 2;
        isExistenceCenter = mathCnt % 2 != 0;
        center = N * N / 2;
        System.out.println(center);
        int[][] math = new int[i][i];
        
        int[] element = new int[mathCnt];
        
        int index = 0;
        int elemIndex = -max;
        while(elemIndex <= max) {
            if (!isExistenceCenter && elemIndex == 0) elemIndex++;
            element[index++] = elemIndex++;
        }
        
        
        generateAll(element);
        
    }

    void generateAll(int[] p) {
        generate(p, 0, p.length - 1);
     }

    long O = 0;
    void generate(int[] p, int L, int R) {
        if (isBreak) return;
        if (L == R) { // all numbers are set
            
          // do something with permutation in array p[]
            O++;
            if (O % 100 == 0) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(p[i * N + j] + ", ");
                    }
                    System.out.println();
                }
                System.out.println();
            }

            // ‰¡
            for (int i = 0; i < N; i++) {
                int num = 0;
                for (int j = 0; j < N; j++) {
                    num += p[i*N + j];
                }
                if (num != 0) return;
            }

            
            // c
            for (int i = 0; i < N; i++) {
                int num = 0;
                for (int j = 0; j < N; j++) {
                    num += p[i + j*N];
                }
                if (num != 0) return;
            }


            // ŽÎ‚ß(‰EãË¶‰º)
            {
                int num = 0;
                for (int j = 0; j < N; j++) {
                    num += p[N*(j) + (N -j) - 1];
//                    System.out.print("[ " + (N*(j) + (N -j) - 1) + "] " + num + " : ");
                }
//                System.out.println(num);
                if (num != 0) return;

            }

            
            // ŽÎ‚ß(¶ãË‰E‰º)
            {
                int num = 0;
                for (int i = 0; i < N; i++) {
                    num += p[i*N + i];
                }
                if (num != 0) return;
            }
            
            System.out.println("clear ");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(p[i * N + j] + ", ");
                }
                System.out.println();
            }

            isBreak = true;
        } else { // numbers at positions [0, L-1] are set, try to set L-th position
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; 
            p[L] = p[i]; 
            p[i] = tmp;

            if (isExistenceCenter) {
                if (L < center) {
                    if (p[L] == 0) continue;
                } else if (L == center && p[L] != 0) {
                    continue;
                }
            }

            int redraw = L % N;
            
            if (redraw >= (N + 1) / 2) {
                int num = 0;
                for (int j = N * (L / N); j < redraw + N * (L / N); j++) {
                    num += p[j];
                }
                if (Math.abs(num) > (N - redraw) * max) continue;            
            }

            
            
            if (L > 0 && redraw == 0) {
                int num = 0;
                for (int j = L - N; j < N + (L - N); j++) {
                    num += p[j];
                }
                if (num != 0) continue;
            }

            if (L >= (N+1) * N / 2) {
                int cnt = L / N + 1;
                int num = 0;
                for (int j = 0 ; j < cnt; j++ ) {
                    num += p[redraw + j * N];
                }
                
                if (Math.abs(num) > (N - cnt) * max) continue;            
                
            }
            
            if (L >= N * (N - 1)) {
                int num = 0;
                for (int j = 0; j < N; j++) {
                    num += p[L%N + j*N];
                }
                if (num != 0) continue;
            }

            if (L == R - N + 1){
                int num = 0;
                for (int j = 0; j < N; j++) {
                    num += p[N*(j) + (N -j) - 1];
//                    System.out.print("[ " + (N*(j) + (N -j) - 1) + "] " + num + " : ");
                }
//                System.out.println(num);
                if (num != 0) continue;

            }
            
            
            generate(p, L+1, R);
            tmp = p[L];
            p[L] = p[i];
            p[i] = tmp;
          }
        }
     }

}
