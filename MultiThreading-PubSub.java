class Q {
    int x;
    boolean set = false;

    public synchronized void get() {
        if(!set) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(x);
        set = false;
        notify();
    }

    public synchronized void set(int i) {
        if(set) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.x=i;
        set = true;
        notify();
    }
}

class Producer extends Thread {
    Q q;
    int i = 0;

    public Producer(Q q) {
        this.q=q;
        Thread t = new Thread(this, "producer");
        t.start();
    }
    public void run() {
        while(true) {
            i++;
            q.set(i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    Q q;

    public Consumer(Q q) {
        this.q=q;
        Thread t = new Thread(this, "consumer");
        t.start();
    }
    public void run() {
        while(true) {
            q.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



public class PubSub {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }

}
