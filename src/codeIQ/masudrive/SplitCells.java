package codeIQ.masudrive;

import java.util.Arrays;

public class SplitCells {

    static int m = 4;
    static int n = 3;
    
    static int max = m * n;
    public static void main(String[] args) {
        if (m * n % 2 != 0) return;
        
        int leftCellCnt = m * n / 2;
        
        int[][] map = new int[n + 1][m + 1];
        map[1][1] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = Math.max(1, map[i+1][j] + map[i][j + 1]);
            }
        }
        printMatrix(map);

        int[] elem = new int[max];
        for (int i = 0; i < max; i++) {
            elem[i] = i;
        }
        permutation(elem, 1, leftCellCnt - 1);
    }

    
    static void printMatrix(int[][] p) {
        for (int[] i : p) printArray(i);
    }
    
    static void printArray(int[] p) {
        for (int i : p) System.out.print(i + " ");
        System.out.println();
    }

    
    static void permutation(int[] elements, int nowCnt, int totalCnt) {
        if (nowCnt == totalCnt) { 
            System.out.println(Arrays.toString(elements));
            // TODO insertCode
        } else {
            
          for (int i = nowCnt; i < elements.length; i++) {
            int tmp = elements[nowCnt]; 
            elements[nowCnt] = elements[i]; 
            elements[i] = tmp;
            permutation(elements, nowCnt+1, totalCnt);
            tmp = elements[nowCnt]; 
            elements[nowCnt] = elements[i]; 
            elements[i] = tmp;
          }
        }
     }

}
