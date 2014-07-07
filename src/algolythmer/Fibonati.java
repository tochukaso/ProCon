package algolythmer;
import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
 
public class Fibonati {
//    private static final String INPUT_PATH = "C:\\atcoder\\regular_004_004.txt";
    private static final String INPUT_PATH = null;

    void debug(Object ... o) {
        pw.write(deepToString(o));
    }
    PrintWriter pw = new PrintWriter(System.out);

    static final int N = 1000000;
//    static final int N = 10;
    
    public void solve() { 
        
        double[] dp = new double[N + 1];
        
        int pre = 1;
        int nPre = 1;
        
//        List<Integer> orgList = new ArrayList<Integer>();
        
        for (int i = 2; i <= N; i++) {
            int now = pre + nPre;
            if (now > N) break;
            nPre= pre;
            pre = now;
            //            orgList.add(now);
            
            dp[now]++;
/**
            for (;now <= N; now += pre) {
                dp[now]++;
            }
**/
        }
/**
     
        Integer[] org = (Integer[]) orgList.toArray(new Integer[orgList.size()]);
        int[] orgin = new int[org.length];
        for (int i = 0; i < org.length; i++) {
            orgin[i] = org[i];
        }
**/        
        
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= i / 2; j ++) {
                dp[i] += dp[j];
            }
            
            if (i % 10000 == 0) System.out.println(i);
        }

/**
        for (int i : orgin) {
            for (int j = i + 1; j <= N; j++) {
                if (i + j > N) break;
                dp[i + j] += dp[i] * dp[j];
            }
        }
**/        

        
        /**
        
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j + 1< i; j++) {
                for (int k = j + 1; k < i; k++) {
                        dp[i] += (dp[j] * dp[k]);
                }
                
            }
        }
**/     
        
        System.out.println(dp[N]);
        
    }
 
    BufferedReader br = null;

    private int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private long readLong() throws IOException {
        return Long.parseLong(br.readLine());
    }

    private int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Integer.parseInt(s[i]);
        }
        return out;
    }

    private Integer[] convIntArray(int[] arg) {
        int len = arg.length;
        Integer[] res = new Integer[len];
        for (int i = 0; i < len; i++) {
            res[i] = arg[i];
        }
        return res;
    }
    
    private long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Long.parseLong(s[i]);
        }
        return out;
    }

    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }

    void generate(int[] p, int L, int R) {
        if (L == R) { // all numbers are set
          // do something with permutation in array p[]
            System.out.println(deepToString(convIntArray(p)));
        } else { // numbers at positions [0, L-1] are set, try to set L-th position
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; p[L] = p[i]; p[i] = tmp;
            generate(p, L+1, R);
            tmp = p[L]; p[L] = p[i]; p[i] = tmp;
          }
        }
     }

    /***
     * 最大公約数を求める関数
     * @param n1
     * @param n2
     * @return
     */
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
   public static void main(String[] args) {
        Fibonati app = new Fibonati();
        app.solve();
    }

   class Edge {
       int x, y;
       
       Edge(int x, int y) {
           this.x = x;
           this.y = y;
       }
       
   }
}