
class PrintAll {

    boolean printedOdd = false;

    public synchronized void printOdd() {

        for(int i = 1;i<10;i+=2) {

            if(printedOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
            printedOdd = true;
            notify();
        }

    }

    public synchronized void printEven() {
        for(int i = 2 ;i<11;i+=2) {

            if(!printedOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(i);
            printedOdd = false;
            notify();

        }
    }

}


class OddClass extends Thread {
    PrintAll printAll;

    public OddClass(PrintAll printAll) {
        this.printAll = printAll;
        Thread t = new Thread(this, "odd");
        t.start();
    }

    public void run() {
        printAll.printOdd();
    }
}

class EvenClass extends Thread {
    PrintAll printAll;

    public EvenClass(PrintAll printAll) {
        this.printAll = printAll;
        Thread t1 = new Thread(this, "even");
        t1.start();
    }

    public void run() {
        printAll.printEven();
    }
}


public class EvenOddMT {
    public static void main(String[] args) {
        PrintAll printAll = new PrintAll();
        new OddClass(printAll);
        new EvenClass(printAll);
    }

}
