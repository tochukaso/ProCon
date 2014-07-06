package codeIQ;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.net.www.http.HttpClient;
import sun.rmi.runtime.Log;

public class Space {

//    static final String URL = "http://spacetalky.textfile.org/api.cgi?input=";

    static final String URL = "http://tochukaso0529.appspot.com/";  

    
    static final String INPUT_FILE_PATH = "C:\\Users\\y-ohmori\\Downloads\\spacetalky\\words.txt" ;

    static final String OUTPUT_FILE_PATH = "C:\\Users\\y-ohmori\\Downloads\\spacetalky\\out.txt" ;

    public static void main(String[] args) throws Exception {
        String s = getEWords();
        System.out.println(s);
    }
    

//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(new File(INPUT_FILE_PATH)));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE_PATH)));
//
//        try {
//            String s = null;
//            while ((s = br.readLine()) != null) {
//                String enc = decode(s);
//                if (enc == null) {
//                    bw.write("X:" + s);
//                    System.out.println(enc);
//                    bw.newLine();
//                    continue;
//                }
//                System.out.println(enc);
//
//                String eWord = getEWords(enc);
//
//                if (equalString(s, eWord)) {
//                    bw.write(enc + ":" + s);
//                } else {
//                    bw.write("X:" + s);
//                    System.err.println(enc + " : " + eWord);
//                }
//                bw.newLine();
//            }
//            bw.flush();
//
//        } finally {
//            br.close();
//            bw.close();
//        }
//    }

    static boolean equalString(String comp, String comp2) {
        if (comp == comp2) {
            return true;
        }
        int n = comp.length();
        char v1[] = comp.toCharArray();
        char v2[] = comp2.toCharArray();
        int i = 0;
        int j = 0;
        while (n-- != 0) {
            if (v1[i++] != v2[j++])
            return false;
        }
        return true;
    }

    static final String encode(String word) {
        StringBuilder out = new StringBuilder();

        char[] cA = word.toCharArray();
        char b = 0;
        int cnt = 0;
        for (char c : cA) {
            if (c != b || cnt > 26) {
                if (b != 0) {
                    out.append(Character.toChars(cnt + 96));
                }
                out.append(c);
                cnt = 1;
                b = c;
            } else {
                cnt++;
            }
        }
        out.append(Character.toChars(cnt + 96));
        return out.toString();
    }

    static final String decode(String word) {
        StringBuilder out = new StringBuilder();
        if (word.length() %2 != 0) {
            return null;
        }
        char[] cA = word.toCharArray();
        for (int i = 0; i < cA.length; i+=2) {
            int cnt = Character.getNumericValue(cA[i + 1]) - 9;
            for (int j = 0; j < cnt; j ++) {
                out.append(cA[i]);
            }
        }
        return out.toString();
    }



    static final String getEWords(String dWord) throws Exception {
        URL url = new URL(URL + dWord);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        con.connect();

        BufferedInputStream bi = new BufferedInputStream(con.getInputStream());

        byte[] b = new byte[1024];
        bi.read(b);
        String s = new String(b);

        bi.close();
        con.disconnect();
        return s;
    }

    static final String getEWords() throws Exception {
        URL url = new URL(URL);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        con.connect();

        BufferedInputStream bi = new BufferedInputStream(con.getInputStream());

        byte[] b = new byte[1024];
        bi.read(b);
        String s = new String(b, "UTF-8");

        bi.close();
        con.disconnect();
        return s;
    }

    
}
