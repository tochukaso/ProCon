import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
 
public class P141 {
    private static final String INPUT_PATH = null;

    final int[] dx = {-1, 0, 0, 0, 1};
    final int[] dy = {0, -1, 0, 1, 0};
    int M;
    int N;
    int[][] cows;
    int[][] answer;
    int[][] work;
    
    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            
            M = readBufInt();
            N = readBufInt();
            cows = new int[M][];
            for (int i = 0; i < M; i++) {
                cows[i] = readIntArray();
            }
            
            answer = new int[M][N];
            work = new int[M][N];
            
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < 1 << N; i++) {
                for (int j = 0; j < M; j++) {
                    Arrays.fill(work[j], 0);
                }
                
                for (int j = 0; j < N; j++) {
                    work[0][N - j - 1] = i >> j & 1;
                    pw.print((i >> j & 1) + " ");
                }
                pw.println();
                pw.println("----------------");
                
                int num = calc();
                
                if (num >= 0 && res > num) {
                    res = num;
                    for (int k = 0; k < M; k++) {
                        answer[k] = Arrays.copyOf(work[k], N);
                    }
                }
            }
            
            if (res == Integer.MAX_VALUE) {
               pw.println("IMPOSSIBLE");
            } else {
                printMatrix(answer);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }

    int get(int x, int y) {
        int c = cows[x][y];
        for (int i = 0; i < 5; i++) {
            int x2 = x + dx[i];
            int y2 = y + dy[i];
            if (0 <= x2 && x2 < M && 0 <= y2 && y2 < N) {
                c += work[x2][y2];
            }
        }
        return c % 2;
    }
    
    int calc() {
        
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (get(i -1, j) != 0) {
                    work[i][j] = 1;
                }
            }
        }

        
        pw.println("----------------");
        
        printMatrix(work);
        for (int m = 0; m < M; m++) {
            for (int i = 0; i < N; i++) {
                pw.print(get(m, i) + " ");
            }
            pw.println();
        }
        
        for (int i = 0; i < N; i++) {
            if (get(M - 1, i) != 0) {
                return -1;
            }
        }
        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                res += work[i][j];
            }
        }
        
        return res;
    }
    

    private int brPos = 0;
    private int[] brBuf = null;
    
    private int readBufInt() throws IOException {
        if (brBuf == null || brBuf.length == brPos) {
            brBuf = readIntArray();
            brPos = 0;
        }
        return brBuf[brPos++];
    }

    private int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Integer.parseInt(s[i]);
        return out;
    }

    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }

    public static void main(String[] args) {
        P141 app = new P141();

        app.solve();
        pw.flush();
    }

    void printMatrix(int[][] p) {
        for (int[] i : p) printArray(i);
    }
    
    void printArray(int[] p) {
        
        for (int i = 0; i < p.length; i++) {
            if (i + 1 == N) {
                pw.println(p[i]);
            } else {
                pw.print(p[i] + " ");
            }
        }
    }

   BufferedReader br = null;
   static PrintWriter pw = new PrintWriter(System.out);

}
