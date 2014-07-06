package algolythmer;

public class Shakutori {

    int N = 10;
    int S = 17;
    int[] a = {5,12,3,5,10,7,4,9,2,8};
    
    long O = 0;

    long H = 0;

    public static void main(String[] args) {
        
        new Shakutori().solve();

        new Shakutori().newSolve();

    }

    void newSolve() {

        int res = N + 1;
        int s = 0, t = 0, sum = 0;
        
        while(true) {
            while (t < N && sum < S) {
                sum += a[t++];
                H++;
            }
            
            if (sum < S) break;
            res = Math.min(res, t - s);
            sum -= a[s++];
        }
        
        if (res > N) {
            res = 0;
        }
        
        System.out.println(res);
        
        System.out.println(H);
    }

    
    void solve() {
        
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + a[i];
        }
        
        int res = N;
        for (int i = 1; sum[i] + S <= sum[N]; i++) {
            int t = lowerBound(i, N, S + sum[i - 1], sum );
            
            res = Math.min(res, t - i + 1);
        }
        
        System.out.println(res);
        
        System.out.println(O);
    }
    
    int lowerBound(int sPos, int ePos, int target, int[] elements) {
        
        int max = ePos;
        int min = sPos;
        int pos = sPos;
        while(true) {
            O++;
            pos = (min + max) / 2;
            int now = elements[pos];
            
            if (now >= target) {
                if (max == pos) break;
                max = pos;
            } else {
                if (min == pos) break;
                min = pos;
            }
        }
        return max;
    }
    
    
}
