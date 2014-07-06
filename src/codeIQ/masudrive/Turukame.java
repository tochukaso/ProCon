package codeIQ.masudrive;

public class Turukame {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int ans = 0;
        for (int a = 0; a < 10; a++) {
            int set = 0;
            set = set | (1 << a);
            for (int d = 0; d < 10;d++) {
                if ((set & (1 << d)) > 0) continue;
                set = set | (1 << d);
                for (int e = 0; e < 10;e++) {
                    if ((set & (1 << e)) > 0) continue;
                    set = set | (1 << e);
                    for (int i = 0; i < 10;i++) {
                        if ((set & (1 << i)) > 0) continue;
                        set = set | (1 << i);
                        for (int k = 0; k < 10;k++) {
                            if ((set & (1 << k)) > 0) continue;
                            set = set | (1 << k);
                            for (int l = 0; l < 10;l++) {
                                if ((set & (1 << l)) > 0) continue;
                                set = set | (1 << l);
                                for (int r = 1; r < 10;r++) {
                                    if ((set & (1 << r)) > 0) continue;
                                    set = set | (1 << r);
                                    for (int s = 1; s < 10;s++) {
                                        if ((set & (1 << s)) > 0) continue;
                                        set = set | (1 << s);
                                        for (int t = 1; t < 10;t++) {
                                            if ((set & (1 << t)) > 0) continue;
                                            set = set | (1 << t);
                                            for (int w = 1; w < 10;w++) {
                                                if ((set & (1 << w)) > 0) continue;
                                                set = set | (1 << w);
                                                // READ + WRITE + TALK = SKILL;
                                                int sum = 
                                                r*1000 + e*100+ a*10 + d +
                                                w*10000+ r*1000 + i*100 + t*10 + e +
                                                t*1000 + a*100 + l*10 + k;
                                                
                                                int skill = 
                                                s*10000 + k*1000 + i*100 + l*10 + l;
                                                if (sum == skill) {
//                                                    System.out.println("" +a+d+e+i+k+l+r+s+t+w);
                                                    ans++;
                                                }
                                                
                                                set = set ^ (1 << w);
                                            }
                                            set = set ^ (1 << t);
                                        }
                                        set = set ^ (1 << s);
                                    }
                                    set = set ^ (1 << r);

                                }
                                set = set ^ (1 << l);

                            }
                            set = set ^ (1 << k);

                        }
                        set = set ^ (1 << i);

                    }
                    set = set ^ (1 << e);

                }
                set = set ^ (1 << d);

            }
            set = set ^ (1 << a);

        }
        
        System.out.println(ans);
        
        System.out.println(System.currentTimeMillis()  - start);
    }
    
}
