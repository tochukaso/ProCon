package codeIQ;

public class ThreadTest {

public static void main(String[] args) {
    System.out.println(Thread.currentThread() + ": Start. (A)");
    try {
        Thread th = new Thread() {
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + ": Start. (B)");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(this + ": i = " + i);
                        Thread.sleep(1000);
                    }
                    System.out.println(Thread.currentThread() + ": End. (B)");
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        };
        th.start();
        

        Thread.currentThread().sleep(1000);
        Thread.currentThread().sleep(1000);
//        th.join();
    } catch (Exception e) {
        System.out.println(e);
    }
    System.out.println(Thread.currentThread() + ": End. (A)");
}
}

    // 銀行員クラス
    class OfficeLady extends Thread {
        public int myCounter = 0;                    // 数えた1円玉の枚数
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                myCounter=myCounter + 1;                        // 枚数をカウントアップ
                MainFrame.nyuukin();                // 基幹システム入金操作(カウントアップ)
            }
        }
    }
    // 基幹システムクラス
    class MainFrame {
        public static Integer counter = 0;            // 入金された1円玉の枚数
        // 1円玉入金処理
        public synchronized static void nyuukin() {
            MainFrame.counter++;                // 枚数をカウントアップ
        }
    }

