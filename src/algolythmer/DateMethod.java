package algolythmer;

public class DateMethod {

public static int[] CUMDOM = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    
    /**
     * calcurate Day
     * @param y
     * @param m
     * @param d
     * @return
     */
    public static int enc(int y, int m, int d)
    {
        int ret = 0;
        ret += (y-1) * 365;
        ret += (y-1) / 4;
        ret -= (y-1) / 100;
        ret += (y-1) / 400;
        ret += CUMDOM[m-1];
        if(m >= 3 && y % 4 == 0 && (y % 400 == 0 || y % 100 != 0)){
            ret++;
        }
        ret += d;
        return ret-1;
    }
}
