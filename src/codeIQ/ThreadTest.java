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

    // ��s���N���X
    class OfficeLady extends Thread {
        public int myCounter = 0;                    // ������1�~�ʂ̖���
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                myCounter=myCounter + 1;                        // �������J�E���g�A�b�v
                MainFrame.nyuukin();                // ��V�X�e����������(�J�E���g�A�b�v)
            }
        }
    }
    // ��V�X�e���N���X
    class MainFrame {
        public static Integer counter = 0;            // �������ꂽ1�~�ʂ̖���
        // 1�~�ʓ�������
        public synchronized static void nyuukin() {
            MainFrame.counter++;                // �������J�E���g�A�b�v
        }
    }

