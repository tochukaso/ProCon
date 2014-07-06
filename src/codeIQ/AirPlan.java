package codeIQ;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AirPlan {
    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\ticketgobble\\tickets.txt";
    int N;
    List<String> qList;
    List<String> answerList = new ArrayList<String>();

    public void solve() { 
        try (PrintWriter pw = new PrintWriter(System.out)){
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));

            List<String> countryList = new ArrayList<String>();
            List<Integer> fromList = new ArrayList<Integer>();
            List<Integer> toList = new ArrayList<Integer>();

            String[] line = null;
            while((line = readStrArray()) != null) {
                countryList.add(line[0]);
                fromList.add(parseDay(line[1].split("-")[0]));
                toList.add(parseDay(line[1].split("-")[1]));
            }
            
            int[] dp = new int[fromList.size()];
            
            List<String> ansCountry = new ArrayList<String>();
            dp[0] = -1;
            int i = 1;
            for (; i < dp.length; i++) {
                int min = 366;
                int from = dp[i - 1] + 1;
                int minIndex = -1;
                for (int j = 0; j < fromList.size(); j++) {
                    int s = fromList.get(j);
                    if (s < from) continue;

                    int to = toList.get(j);
                    if (min > to) {
                        min = to;
                        minIndex = j;
                    }
                }
                if (min == 366) break;
                dp[i] = min;
                ansCountry.add(countryList.get(minIndex));
            }

            Collections.sort(ansCountry);
            pw.print(i - 1);
            for (String s : ansCountry) {
                pw.print(" "+s);
            }
            
            pw.flush();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }

    int parseDay(String day) {
        Calendar cal = Calendar.getInstance();
        
        String[] s = day.split("/");
        cal.set(Calendar.MONTH, Integer.parseInt(s[0]) -1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[1]));
        
        return cal.get(Calendar.DAY_OF_YEAR);
    }
    
    BufferedReader br = null;

    private int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
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

    private String[] readStrArray() throws IOException {
        String line = br.readLine();
        if (line == null) return null;
        String[] s = line.split(" ");
        return s;
    }

    public static void main(String[] args) {
        AirPlan app = new AirPlan();
        app.solve();
    }

}

