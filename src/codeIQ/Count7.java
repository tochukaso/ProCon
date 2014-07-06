package codeIQ;

public class Count7 {
    
    int[] dp = new int[32];
    
    static final int BASE = 7;
    
    {
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < 32; i++) {
            dp[i] = dp[i - 1] * 10 * 2;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Count7().solve(75));
    }
    
    int solve(int num) {
        
        int length = String.valueOf(num).length();

        int sum = 0;
        for (int i = length ; i > 0; i--) {
            int target = num / (int) Math.pow(10, i - 1);
            sum += dp[i - 1] * (target);
            if (target > BASE) {
                sum += dp[i - 1] * 9;
            }
            if (target >= BASE) {
                sum ++;
            }
            
            num -= target * (int) Math.pow(10, i - 1);
        }
        
        return sum;
    }
    
}
