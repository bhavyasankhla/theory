class Odd extends Thread {
    public void run() {
        for(int i = 1; i<10; i+=2) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Even extends Thread {
    public void run() {
        for(int i = 2; i<11; i+=2) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class MT {

    public static void main(String[] args) {

        Odd odd = new Odd();
        Even even = new Even();

        odd.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        even.start();


    }


}
