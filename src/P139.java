import java.util.Arrays;
import java.util.Scanner;

public class P139 {

    static int N = 0;
    //static boolean[] cows = null;
    static int[] cows;
    static int min = Integer.MAX_VALUE;
    static int[] f = null;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt( sc.nextLine());
//        cows = new boolean[N];
        cows = new int[N];
        f = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = sc.nextLine().equals("F") ? 0 : 1;
        }

        int minK = -1;
        
        for (int K = 1; K <= N; K++) {
            {
                int changeCount = calculate(K);
                if (min > changeCount) {
                    min = changeCount;
                    minK = K;
                }
            }
            
        }
        
        System.out.println(minK + " " + min);
        sc.close();
//        System.out.println(min);
    }
    
    static int calculate(int K) {
        
        Arrays.fill(f, 0);
        
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i + K <= N ; i++) {
            if ((cows[i] + sum) % 2 != 0) {
                cnt++;
                f[i] = 1;
            }
            
            sum+=f[i];
            if (i - K + 1 >= 0) {
                sum -= f[i - K + 1];
            }
            System.out.println(sum);
        }
        
        
        for (int i = N - K + 1; i < N; i++) {
            if ((cows[i] + sum) % 2 != 0) {
                return Integer.MAX_VALUE;
            }
            if ( i - K + 1 >= 0) {
                sum -= f[i - K + 1];
            }
        }
        return cnt;
    }
    
    
}
